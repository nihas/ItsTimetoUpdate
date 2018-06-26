package app.map.updatelib;

import android.content.Context;
import app.map.updatelib.interfaces.ITrackerPresenter;
import app.map.updatelib.presenter.UpdatePresenter;

/**
 * Created by Nihas Nizar on 6/26/2018.
 */

public class ItsTimetoUpdate {

    static ITrackerPresenter presenter;
    static Context context;

    public static void checkForUpdate(Context contexts, String packageName, int ic_launcher, String v_code, boolean forceupdate) {
        context = contexts;
        presenter = new UpdatePresenter();
        presenter.initialize(contexts);
        presenter.checkForUpdate(packageName,ic_launcher,v_code,forceupdate);
        context = contexts;
    }
}
