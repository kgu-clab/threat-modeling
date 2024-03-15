import type { CVEType, MitigationType } from '@type/model';
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

/**
 * 주어진 문자열을 CSV 셀 값으로 안전하게 이스케이프 처리합니다.
 * @param {string} string 이스케이프 처리할 문자열
 * @returns {string} 이스케이프 처리된 문자열
 */
export const escapeCSV = (string: string): string => `"${string}"`;

/**
 * Mitigation 목록을 포맷팅합니다. 각 Mitigation은 Defend ID들과 함께 표시됩니다.
 * @param {MitigationType[]} mitigations Mitigation 객체 배열
 * @returns {string} 포맷팅된 Mitigation 문자열
 */
export const formatMitigations = (mitigations: MitigationType[]): string =>
  mitigations
    .map((mitigation) => {
      const defendsStr = mitigation.relatedDefendTechniques
        .map((defend) => defend.defendId)
        .join(', ');
      return `${mitigation.mitigationId}${defendsStr ? ` (${defendsStr})` : ''}`;
    })
    .join(' / ') || '-';

/**
 * CVE 항목 목록을 포맷팅합니다. 각 항목은 CVE ID와 해당 CVSS 점수(또는 'N/A')로 구성됩니다.
 * @param {CVEType[]} cveItems CVE 항목 객체 배열
 * @returns {string} 포맷팅된 CVE 항목 문자열
 */
export const formatCves = (cveItems: CVEType[]): string =>
  cveItems
    .map((cveItem) => `${cveItem.cveId} (${cveItem?.cvss || 'N/A'})`)
    .join(' / ') || '-';
