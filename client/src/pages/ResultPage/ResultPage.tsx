import {
  Table,
  TableBody,
  TableHead,
  TableHeader,
  TableRow,
} from '@components/common/Table/Table';
import CSVDownloadButton from '@components/result/CSVDownloadButton/CSVDownloadButton';
import TableContent from '@components/result/TableContent/TableContent';
import { useAttacks } from '@hooks/useAttacks';

import { deobfuscate } from '@utils/string';

import { useTranslation } from 'react-i18next';
import { useParams } from 'react-router-dom';

const ResultPage = () => {
  const { t } = useTranslation();
  const { id } = useParams<{ id: string }>();

  if (!id) throw new Error('Invalid ID');

  const { data } = useAttacks(deobfuscate(id));

  return (
    <div className="py-6">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-3xl font-bold">{t('report')}</h1>
        <CSVDownloadButton data={data} />
      </div>
      <div className="overflow-auto">
        <Table className="border">
          <TableHeader>
            <TableRow>
              <TableHead rowSpan={2}>설명</TableHead>
              <TableHead colSpan={2}>평가지표</TableHead>
              <TableHead rowSpan={3} className="text-nowrap">
                Mitigation ID (Defend ID)
              </TableHead>
              <TableHead rowSpan={3}>CVE (CVSS)</TableHead>
              <TableHead rowSpan={3}>Function</TableHead>
            </TableRow>
            <TableRow>
              <TableHead colSpan={2} className="border-l text-nowrap">
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
            {data?.map((attack, index) => (
              <TableContent key={index} data={attack} />
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default ResultPage;
