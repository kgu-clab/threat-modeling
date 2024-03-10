import { Button } from '@components/common/Button/Button';
import {
  Table,
  TableBody,
  TableHead,
  TableHeader,
  TableRow,
} from '@components/common/Table/Table';
import TableContent from '@components/result/TableContent/TableContent';
import { useTranslation } from 'react-i18next';
import { PiFileCsv } from 'react-icons/pi';

const ResultPage = () => {
  const { t } = useTranslation();

  return (
    <div className="py-6">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-3xl font-bold text-center">Report</h1>
        <Button size="sm">
          <PiFileCsv size={20} className="mr-1" /> {t('download')}
        </Button>
      </div>
      <div className="overflow-auto">
        <Table className="border">
          <TableHeader className="hover:bg-muted/50 text-nowrap">
            <TableRow>
              <TableHead rowSpan={2}>설명</TableHead>
              <TableHead colSpan={2}>평가지표</TableHead>
              <TableHead rowSpan={3}>Mitigation ID (Defend ID)</TableHead>
              <TableHead rowSpan={3}>CVE (CVSS)</TableHead>
              <TableHead rowSpan={3}>Function</TableHead>
            </TableRow>
            <TableRow>
              <TableHead colSpan={2} className="border-l">
                SP 800 - 53 / MITER ATT&CK 기준 매핑
              </TableHead>
            </TableRow>
            <TableRow className="border-l">
              <TableHead>Level.6</TableHead>
              <TableHead>Technique ID</TableHead>
              <TableHead>Control ID</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody className="break-keep">
            <TableContent />
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default ResultPage;
