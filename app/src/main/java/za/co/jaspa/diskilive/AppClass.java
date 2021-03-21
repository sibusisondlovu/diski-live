package za.co.jaspa.diskilive;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppClass  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                              .setDefaultFontPath("fonts/PoppinsRegular.ttf")
                                              .setFontAttrId(R.attr.fontPath)
                                              .build()
        );
        //....
    }
}
