package host.exp.exponent;
import org.devio.rn.splashscreen.SplashScreen;
import android.content.res.Configuration;
import android.content.Intent;
import host.exp.exponent.BuildConfig;
import android.os.Bundle;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.google.android.gms.ads.MobileAds;


import java.util.List;

import org.unimodules.core.interfaces.Package;
import host.exp.exponent.experience.DetachActivity;
import host.exp.exponent.generated.DetachBuildConstants;

// FIREBASE LOG CRASH CONFIG
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends DetachActivity {

     private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SplashScreen.show(this);  // here

        MobileAds.initialize(this, "ca-app-pub-9366264571562182~7999107197");

        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );
       FirebaseApp.initializeApp(this);
       mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


      Crashlytics crashlyticsKit = new Crashlytics.Builder()
          .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
          .build();
    }

  @Override
  public String publishedUrl() {
    return "https://exp.host/@robin_nguyen/proj-tanzaku-app";
  }

  @Override
  public String developmentUrl() {
    return DetachBuildConstants.DEVELOPMENT_URL;
  }

  @Override
  public List<ReactPackage> reactPackages() {
    return ((MainApplication) getApplication()).getPackages();
  }

  @Override
  public List<Package> expoPackages() {
    return ((MainApplication) getApplication()).getExpoPackages();
  }

  @Override
  public boolean isDebug() {
    return BuildConfig.DEBUG;
  }

  @Override
  public Bundle initialProps(Bundle expBundle) {
    // Add extra initialProps here
    return expBundle;
  }

}
