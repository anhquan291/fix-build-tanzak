import messaging from "@react-native-firebase/messaging";
import NavigationService from "../services/NavigationService.js";
import PushNotification, { Importance } from "react-native-push-notification";
import PushNotificationIOS from "@react-native-community/push-notification-ios";

const CHANNEL_ID = "Tanzak";
const queue = {};
class NotificationService {
  static async clickNotification(remoteMessage) {
    if (!!remoteMessage?.data?.book) {
      NavigationService.navigate("BookDetail", {
        id: remoteMessage?.data?.book,
      });
      delete queue[remoteMessage.messageId];
    }
  }

  static async initChannel() {
    const existed = await PushNotification.channelExists(CHANNEL_ID);
    if (!existed) {
      await PushNotification.createChannel({
        channelId: CHANNEL_ID,
        channelName: CHANNEL_ID,
        channelDescription: "",
        playSound: false,
        soundName: "default",
        importance: Importance.HIGH,
      });
    }
  }
  static async init() {
    await messaging().registerDeviceForRemoteMessages();

    messaging().subscribeToTopic("all");

    // notify foreground
    messaging().onMessage(async (remoteMessage ={}) => {
      console.log("onMessage notify foreground", remoteMessage);

      if (!remoteMessage?.data || !!queue[remoteMessage.messageId]) return;
      queue[remoteMessage.messageId] = remoteMessage.messageId;

      //create local notify in case foreground
      await PushNotification.localNotification({
        channelId: CHANNEL_ID,
        smallIcon: "ic_launcher",
        title: remoteMessage?.notification?.title || "",
        message: remoteMessage?.notification?.body || "",
        userInfo: remoteMessage?.data,
        messageId: remoteMessage?.messageId,
        playSound: true,
        vibrate: true,
        vibration: 300,
        priority: "high",
      });
    });

    // open notification after quit
    messaging()
      .getInitialNotification()
      .then((remoteMessage) => {
        console.log("onMessage getInitialNotification", remoteMessage);
        NotificationService.clickNotification(remoteMessage);
      });

    PushNotification.configure({
      onNotification: function (notification) {
        console.log("open notification:", notification);

        NotificationService.clickNotification(notification);
        if(Platform.OS === "ios"){
          notification.finish(PushNotificationIOS.FetchResult.NoData);
        }
      },
      permissions: {
        alert: true,
        badge: true,
        sound: true,
      },
      popInitialNotification: true,
      requestPermissions: true,
    });
    await NotificationService.initChannel();
  }

  //check permission notification
  static async checkNotificationPermission() {
    const authStatus = await messaging().requestPermission();
    const enabled =
      authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
      authStatus === messaging.AuthorizationStatus.PROVISIONAL;
    return enabled;
  }

  static async getNotificationFcmToken() {
    return messaging().getToken() || "";
  }
}

export default NotificationService;
