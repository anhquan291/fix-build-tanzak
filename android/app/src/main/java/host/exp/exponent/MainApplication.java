package host.exp.exponent;

import android.app.Application;
import com.facebook.react.BuildConfig;
import com.appsflyer.reactnative.RNAppsFlyerPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.facebook.react.ReactPackage;
import com.idehub.Billing.InAppBillingBridgePackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import org.devio.rn.splashscreen.SplashScreenReactPackage;

import java.util.Arrays;
import java.util.List;

import org.unimodules.core.interfaces.Package;
import host.exp.exponent.generated.BasePackageList;

import host.exp.exponent.ExpoApplication;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingPackage;
import okhttp3.OkHttpClient;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactApplication;
import com.dieam.reactnativepushnotification.ReactNativePushNotificationPackage; 
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;

public class MainApplication extends ExpoApplication implements ReactApplication {

  @Override
  public boolean isDebug() {
    return BuildConfig.DEBUG;
  }
   @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  // Needed for `react-native link`
  public List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        // Add your own packages here!
        // TODO: add native modules!

        // Needed for `react-native link`
            new AsyncStoragePackage(),
            new RNAppsFlyerPackage(),
            new SplashScreenReactPackage(),
            new InAppBillingBridgePackage(),
            new AppLovin(),
            new RNDeviceInfo(),
            new ReactNativeFirebaseAppPackage(),
            new ReactNativeFirebaseMessagingPackage(),
            new FastImageViewPackage(),
            new ReactNativePushNotificationPackage()
    );
  }

  public List<Package> getExpoPackages() {
    return new BasePackageList().getPackageList();
  }
  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
              new ReactNativeFirebaseAppPackage(),
              new ReactNativeFirebaseMessagingPackage()
      );
    }
  };
  @Override
  public String gcmSenderId() {
    return null;
  }

  public static OkHttpClient.Builder okHttpClientBuilder(OkHttpClient.Builder builder) {
    // Customize/override OkHttp client here
    return builder;
  }
}

