package app.map.updatelib.presenter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import app.map.updatelib.ui.dialogs.AlertConfirmDialog;
import app.map.updatelib.interfaces.ITrackerPresenter;
import app.map.updatelib.interfaces.OnConfirmAlertDialogClickListner;

/**
 * Created by Nihas Nizar on 6/26/2018.
 */
public class UpdatePresenter implements ITrackerPresenter {

    static Context context;
    static final String TAG = "TrackerLib";
    static String APP_PACKAGE_NAME = "";

    public UpdatePresenter() {

    }


    @Override
    public void initialize(Context contex) {
        context = contex;
    }

    @Override
    public void checkForUpdate(String packageName, int ic_launcher, String v_code, boolean forceupdate) {
        APP_PACKAGE_NAME = packageName;
        checkforupdateReal(ic_launcher,forceupdate,v_code);
    }


    public void checkforupdateReal(final int ic_launcher, boolean forceupdate, String v_code) {


        new CheckNewAppVersion(context,APP_PACKAGE_NAME,v_code).setOnTaskCompleteListener(new CheckNewAppVersion.ITaskComplete() {
            @Override
            public void onTaskComplete(CheckNewAppVersion.Result result) {

                //Checks if there is a new version available on Google Play Store.

                if(result.hasNewVersion()){
                    final String appPackageName = APP_PACKAGE_NAME;
                    showConfirmAlertActivityDialogString((AppCompatActivity) context, "Update", "New version of App found. Update Now.", "UPDATE", "CANCEL", ic_launcher, new OnConfirmAlertDialogClickListner() {
                        @Override
                        public void onPositiveButtonClick() {
                            try {
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (ActivityNotFoundException anfe) {
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        }

                        @Override
                        public void onNegativeButtonClick() {

                        }
                    });


                }

//                //Get the new published version code of the app.
                Log.e("getNewVerCode",result.getNewVersionCode()+"");
//                //Get the app current version code.
                Log.e("HASNEWVER",result.getOldVersionCode()+"");
            }
        }).execute();
    }


    public static void showConfirmAlertActivityDialogString(AppCompatActivity activity, String title, String message, String buttonYes, String buttonNo, int drawableIcon, OnConfirmAlertDialogClickListner onConfirmAlertDialogClickListner) {

        AlertConfirmDialog newFragment = AlertConfirmDialog.newInstance(title ,message, drawableIcon, buttonYes, buttonNo, onConfirmAlertDialogClickListner);
        if(activity!=null)
            newFragment.show(activity.getSupportFragmentManager(), "edit_confirm_alert_dialog");
    }



}
