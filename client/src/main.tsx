import React from 'react';
import ReactDOM from 'react-dom/client';
import App from '@/App.tsx';
import { Toaster } from 'react-hot-toast';
import '@styles/globals.css';
import '@utils/i18n.ts';
import { QueryClientProvider } from '@tanstack/react-query';
import { queryClient } from '@hooks/queryClient';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Toaster position="top-center" reverseOrder={false} />
      <App />
    </QueryClientProvider>
  </React.StrictMode>,
);
