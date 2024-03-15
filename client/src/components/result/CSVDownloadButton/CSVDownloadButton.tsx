import { Button } from '@components/common/Button/Button';
import { CSV_HEADER } from '@constants/header';
import type { ReportModelType, ThreatModelType } from '@type/model';
import { type AttackFlowResultType } from '@utils/attack';
import { escapeCSV, formatCves, formatMitigations } from '@utils/string';
import { useTranslation } from 'react-i18next';
import { PiFileCsv } from 'react-icons/pi';
import { useLocation } from 'react-router-dom';

interface CSVDownloadButtonProps extends React.ComponentProps<typeof Button> {
  data: ThreatModelType[];
}

const CSVDownloadButton = ({ data, ...rest }: CSVDownloadButtonProps) => {
  const { t } = useTranslation();
  const location = useLocation();
  const { data: level6 } = location.state as { data: AttackFlowResultType };

  const report: ReportModelType[] = [];
  // CSV로 추출할 수 있는 형태로 변환
  data?.forEach((attack) => {
    report.push({
      level6: level6[attack.attack.attackId]?.join(' / ') || '-',
      techniqueId: attack.attack.attackId,
      controlId: attack.relatedControls,
      mitigationId: attack.relatedMitigations,
      cve: attack.relatedCves,
    });
  });

  const onclickDownload = () => {
    const csvData =
      CSV_HEADER +
      report
        .flatMap((attack) => {
          const { level6, controlId, mitigationId, cve, techniqueId } = attack;
          const mitigationsStr = formatMitigations(mitigationId);
          const cveIdsStr = formatCves(cve);
          return controlId.map(
            (control) =>
              [
                escapeCSV(level6),
                escapeCSV(techniqueId),
                escapeCSV(control.controlId),
                escapeCSV(control.controlName),
                escapeCSV(mitigationsStr),
                escapeCSV(cveIdsStr),
              ].join(',') + '\n',
          );
        })
        .join('');
    // 파일 다운로드 처리
    const blob = new Blob(['\uFEFF' + csvData], {
      type: 'text/csv; charset=utf-8',
    });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', 'threat-modeling-report.csv');
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  return (
    <Button size="sm" onClick={onclickDownload} {...rest}>
      <PiFileCsv size={20} className="mr-1" /> {t('download')}
    </Button>
  );
};

export default CSVDownloadButton;
