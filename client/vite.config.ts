import path, { resolve } from "path";
import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@api": resolve(__dirname, "./src/api"),
      "@assets": resolve(__dirname, "./src/assets"),
      "@components": resolve(__dirname, "./src/components"),
      "@constants": resolve(__dirname, "./src/constants"),
      "@hooks": resolve(__dirname, "./src/hooks"),
      "@pages": resolve(__dirname, "./src/pages"),
      "@router": resolve(__dirname, "./src/router"),
      "@styles": resolve(__dirname, "./src/styles"),
      "@type": resolve(__dirname, "./src/types"),
      "@utils": resolve(__dirname, "./src/utils"),
      "@": path.resolve(__dirname, "./src"),
    },
  },
});
