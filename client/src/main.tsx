import React from 'react';
import ReactDOM from 'react-dom/client';
import App from '@/App.tsx';
import { Toaster } from 'react-hot-toast';
import { QueryClientProvider } from '@tanstack/react-query';
import { queryClient } from '@hooks/queryClient';
import '@utils/i18n.ts';
import '@styles/globals.css';
import 'react-notion/src/styles.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Toaster position="top-center" reverseOrder={false} />
      <App />
    </QueryClientProvider>
  </React.StrictMode>,
);
