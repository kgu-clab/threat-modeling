import { useCallback, useEffect, useState } from 'react';
import { WiDaySunny } from 'react-icons/wi';
import { WiNightClear } from 'react-icons/wi';

const ThemeSwitch = () => {
  const [darkMode, setDarkMode] = useState(
    () =>
      localStorage.theme === 'dark' ||
      (!('theme' in localStorage) &&
        window.matchMedia('(prefers-color-scheme: dark)').matches),
  );

  useEffect(() => {
    // 다크 모드 상태에 따라 클래스를 추가하거나 제거하고, 로컬 스토리지에 상태를 저장합니다.
    const className = darkMode ? 'dark' : 'light';
    document.documentElement.className = className;
    localStorage.setItem('theme', className);
  }, [darkMode]);

  const toggleDarkMode = useCallback(() => {
    setDarkMode(!darkMode);
  }, [darkMode]);

  return (
    <button
      onClick={toggleDarkMode}
      className="rounded-full hover:bg-gray-100 dark:hover:bg-white/10"
    >
      {darkMode ? <WiDaySunny size={28} /> : <WiNightClear size={28} />}
    </button>
  );
};

export default ThemeSwitch;
