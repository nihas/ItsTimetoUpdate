package app.map.updatelib.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.map.updatelib.R;
import app.map.updatelib.interfaces.OnConfirmAlertDialogClickListner;


public class AlertConfirmDialog extends DialogFragment implements View.OnClickListener{

    private static final String BUTTON_NO = "button_no";
    private static final String BUTTON_YES = "button_yes";
    private static final String MESSAGE = "message";
    private static final String MESSAGE_STRING = "message_string";
    private static final String TITLE = "title";
    private static final String ICON = "icon";

    TextView tvMessage;
    private String buttonNo, buttonYes,title,messageString;
    int icon,message;
    private static OnConfirmAlertDialogClickListner alertClickListener;

    public static AlertConfirmDialog newInstance(String title, int message, int drawableIcon, String buttonYes, String buttonNo, OnConfirmAlertDialogClickListner onConfirmAlertDialogClickListner) {
        AlertConfirmDialog frag = new AlertConfirmDialog();
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(MESSAGE, message);
        args.putInt(ICON, drawableIcon);
        args.putString(BUTTON_YES, buttonYes);
        args.putString(BUTTON_NO, buttonNo);
        frag.setArguments(args);
        alertClickListener=onConfirmAlertDialogClickListner;
        return frag;
    }

    public static AlertConfirmDialog newInstance(String title, String message, int drawableIcon, String buttonYes, String buttonNo, OnConfirmAlertDialogClickListner onConfirmAlertDialogClickListner) {
        AlertConfirmDialog frag = new AlertConfirmDialog();
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE_STRING, message);
        args.putInt(ICON, drawableIcon);
        args.putString(BUTTON_YES, buttonYes);
        args.putString(BUTTON_NO, buttonNo);
        frag.setArguments(args);
        alertClickListener=onConfirmAlertDialogClickListner;
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            alertClickListener = (OnConfirmAlertDialogClickListner) getTargetFragment();
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
//        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        title = getArguments().getString(TITLE);
        icon = getArguments().getInt(ICON);
        if(getArguments().containsKey(MESSAGE)) {
            message = getArguments().getInt(MESSAGE);
        }else{
            messageString=getArguments().getString(MESSAGE_STRING);
        }
        buttonNo = getArguments().getString(BUTTON_NO);
        buttonYes = getArguments().getString(BUTTON_YES);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_confirm_alert, null);
        AppCompatImageView tvIcon = (AppCompatImageView) view.findViewById(R.id.tvIcon);
        if(icon!=0) {
            tvIcon.setImageResource(icon);
        }else{
            tvIcon.setVisibility(View.GONE);
        }
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        if(getArguments().containsKey(MESSAGE)) {
            tvMessage.setText(message);
        }else{
            tvMessage.setText(messageString);
        }
        TextView btYes = (TextView) view.findViewById(R.id.btYes);
        btYes.setText(buttonYes);
        TextView btNo = (TextView) view.findViewById(R.id.btNo);
        btNo.setText(buttonNo);
        btNo.setOnClickListener(this);
        btYes.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btNo) {
            alertClickListener.onNegativeButtonClick();
            dismiss();
        }

        if (v.getId() == R.id.btYes) {
            alertClickListener.onPositiveButtonClick();
            dismiss();
        }
    }
}
