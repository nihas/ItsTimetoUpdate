package app.map.updatelib.interfaces;

import android.content.Context;

/**
 * Created by Nihas Nizar on 6/26/2018.
 */
public interface ITrackerPresenter {
    void initialize(Context context);


    void checkForUpdate(String packageName, int ic_launcher, String v_code, boolean forceupdate);
}
