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
import { SiRelay } from 'react-icons/si';
import { useTranslation } from 'react-i18next';
import { Link, useParams } from 'react-router-dom';
import { Button } from '@components/common/Button/Button';

const ResultPage = () => {
  const { t } = useTranslation();
  const { id } = useParams<{ id: string }>();

  if (!id) throw new Error('Invalid ID');

  const { data } = useAttacks(deobfuscate(id));

  return (
    <div className="py-6">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-3xl font-bold">{t('report')}</h1>
        <div className="flex items-center gap-4">
          <Button size="sm">
            <Link
              to="https://center-for-threat-informed-defense.github.io/attack-flow/visualization/"
              target="_blank"
              className="flex items-center gap-1.5"
            >
              <SiRelay size={20} />
              ATT&CK Navigator
            </Link>
          </Button>
          <CSVDownloadButton data={data} />
        </div>
      </div>
      <div className="overflow-auto">
        <Table className="border">
          <TableHeader>
            <TableRow>
              <TableHead rowSpan={2}>Category</TableHead>
              <TableHead colSpan={3}>Evaluation Metrics</TableHead>
              <TableHead rowSpan={3} className="text-nowrap">
                Mitigation ID (Defend ID)
              </TableHead>
              <TableHead rowSpan={3}>CVE (CVSS)</TableHead>
              <TableHead rowSpan={3}>Action</TableHead>
            </TableRow>
            <TableRow>
              <TableHead colSpan={3} className="border-l text-nowrap">
                MITER ATT&CK / NIST SP800-53 Rev.5
              </TableHead>
            </TableRow>
            <TableRow className="border-l">
              <TableHead>Level.6</TableHead>
              <TableHead>Technique ID</TableHead>
              <TableHead>Control ID</TableHead>
              <TableHead>Control Name</TableHead>
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
