import { initializeApp } from 'firebase/app';
import { getMessaging, getToken } from 'firebase/messaging';
import { axiosCommonInstance } from '@/apis/axiosInstance';

const firebaseConfig = {
  apiKey: import.meta.env.VITE_FIREBASE_API_KEY,
  authDomain: import.meta.env.VITE_FIREBASE_AUTH_DOMAIN,
  projectId: import.meta.env.VITE_FIREBASE_PROJECT_ID,
  storageBucket: import.meta.env.VITE_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: import.meta.env.VITE_FIREBASE_MESSAGING_SENDER_ID,
  appId: import.meta.env.VITE_FIREBASE_APP_ID,
  measurementId: import.meta.env.VITE_FIREBASE_MEASUREMENT_ID,
};

export const app = initializeApp(firebaseConfig);
export const messaging = getMessaging(app);

export const requestPermission = async () => {
  const storedToken = localStorage.getItem('RECODE_FCM_TOKEN');
  let token;

  const permission = await Notification.requestPermission();

  if (permission != 'granted') return;

  try {
    token = await getToken(messaging, { vapidKey: import.meta.env.VITE_FIREBASE_VAPID_KEY });
  } catch (err) {
    token = await getToken(messaging, { vapidKey: import.meta.env.VITE_FIREBASE_VAPID_KEY });
  }

  if (token == storedToken) return;

  if (token) {
    const res = await axiosCommonInstance.post('/users/fcm', {
      token,
    });

    if (res.status == 200) {
      localStorage.setItem('RECODE_FCM_TOKEN', token);
    }
  }
};
