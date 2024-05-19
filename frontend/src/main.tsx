import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';

async function enableMocking() {
  if (import.meta.env.VITE_NODE_ENV !== 'development') {
    console.log('msw off');
    return;
  }

  const { worker } = await import('./mocks/browser.ts');
  return worker.start({ onUnhandledRequest: 'bypass' });
}

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
});

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <QueryClientProvider client={queryClient}>
      <React.StrictMode>
        <App />
        <ReactQueryDevtools initialIsOpen={false} />
      </React.StrictMode>
    </QueryClientProvider>
  );
});
