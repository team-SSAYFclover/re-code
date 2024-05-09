importScripts('https://www.gstatic.com/firebasejs/10.1.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/10.1.0/firebase-messaging-compat.js');

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
  const notificationTitle = payload.data.title;
  const notificationOptions = {
    body: payload.data.body,
    icon: '/logo.png',
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});

self.addEventListener('notificationclick', function (event) {
  const url = '/';
  event.notification.close();
  event.waitUntil(clients.openWindow(url));
});
