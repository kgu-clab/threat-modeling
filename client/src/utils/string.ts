import CryptoJS from 'crypto-js';

const SECURITY_KEY = import.meta.env.VITE_SECURITY_KEY;

/**
 * 주어진 문자열 데이터를 암호화하고, URL에 안전한 문자열로 변환합니다.
 *
 * @param {string} data - 암호화할 원본 문자열 데이터.
 * @returns {string} 암호화하고 URL에 안전하게 인코딩된 문자열.
 */
export const obfuscate = (data: string): string => {
  // CryptoJS의 AES 암호화 메서드를 사용하여 데이터를 암호화합니다.
  const encrypted = CryptoJS.AES.encrypt(data, SECURITY_KEY).toString();
  // 암호화된 문자열에서 URL에 포함될 수 없는 문자들을 안전한 문자로 교체합니다.
  return encrypted.replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
};

/**
 * 암호화되고 URL에 안전하게 인코딩된 문자열을 복호화하여 원래의 문자열 데이터를 복구합니다.
 *
 * @param {string} data - 복호화할 암호화된 문자열 데이터.
 * @returns {string} 복호화된 원본 문자열 데이터.
 */
export const deobfuscate = (data: string): string => {
  // URL에 안전한 인코딩을 원래의 base64 형태로 되돌립니다.
  const base64 =
    data.replace(/-/g, '+').replace(/_/g, '/') +
    '=='.substring(0, (3 * data.length) % 4);
  // CryptoJS의 AES 복호화 메서드를 사용하여 데이터를 복호화합니다.
  const decrypted = CryptoJS.AES.decrypt(base64, SECURITY_KEY);
  // 복호화된 데이터를 문자열로 변환합니다.
  return decrypted.toString(CryptoJS.enc.Utf8);
};
