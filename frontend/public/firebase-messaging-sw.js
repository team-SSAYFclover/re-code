importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/9.2.0/firebase-messaging.js');

const firebaseConfig = {
  apiKey: 'AIzaSyCD-dm8gYbrvTkpqzitKKYbjpyV5EuPno4',
  projectId: 'recode-43ead',
  messagingSenderId: '19941674840',
  appId: '1:19941674840:web:43538b7853c17f12aa9f2d',
};

const firebaseApp = firebase.initializeApp(firebaseConfig);

// Initialize Firebase Cloud Messaging and get a reference to the service
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
  // TODO : payload를 이용한 notification 생성
  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
    icon: '/firebase-logo.png',
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});

// 웹 푸시 알림 수신
self.addEventListener('push', function (event) {
  console.log('push: ', event.data.json());
  if (!event.data.json()) return;

  const resultData = event.data.json().notification;
  const notificationTitle = resultData.title;
  const notificationOptions = {
    body: resultData.body,
    icon: resultData.image,
    ...resultData,
  };
  console.log('push: ', { resultData, notificationTitle, notificationOptions });

  self.registration.showNotification(notificationTitle, notificationOptions);
});

self.addEventListener('notificationclick', function (event) {
  console.log('notification click');
  const url = '/';
  event.notification.close();
  event.waitUntil(clients.openWindow(url));
});
