import { useState } from 'react';
import { mocks } from '@/mocks/mocks';
import { MdOutlineExpandMore } from 'react-icons/md';
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@components/common/Tooltip/Tooltip';
import { TableCell, TableRow } from '@components/common/Table/Table';

interface TooltipComponentProps {
  mitigationId: string;
  relatedDefendTechniques: { defendId: string }[];
}

const TooltipComponent = ({
  mitigationId,
  relatedDefendTechniques,
}: TooltipComponentProps) => (
  <Tooltip>
    <TooltipTrigger className="text-gray-500 cursor-default hover:underline hover:text-black">
      {mitigationId}
    </TooltipTrigger>
    <TooltipContent>
      <p className="mb-2 text-base font-semibold text-red-500">
        {mitigationId}
      </p>
      <div className="divide-x">
        {relatedDefendTechniques.map(({ defendId }) => (
          <a key={defendId} className="px-2 hover:underline" href="">
            {defendId}
          </a>
        ))}
      </div>
    </TooltipContent>
  </Tooltip>
);

const TableContent = () => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen((prev) => !prev);

  const controlsToShow = open
    ? mocks.attack.data.relatedControls
    : mocks.attack.data.relatedControls.slice(0, 1);

  return controlsToShow.map((control, index) => (
    <TableRow key={control.controlId}>
      {index === 0 && (
        <TableCell
          rowSpan={open ? controlsToShow.length : 1}
          className="border-r"
        >
          통신 악용; 승인되지 않은 제어권 요청 생성
        </TableCell>
      )}
      {index === 0 && (
        <TableCell rowSpan={open ? controlsToShow.length : 1}>
          {mocks.attack.data.attack.attackId}
        </TableCell>
      )}
      <TableCell className="border-l">{control.controlId}</TableCell>
      <TooltipProvider>
        <TableCell className="space-x-2">
          {mocks.attack.data.relatedMitigations.map(
            ({ mitigationId, relatedDefendTechniques }) => (
              <TooltipComponent
                key={mitigationId}
                mitigationId={mitigationId}
                relatedDefendTechniques={relatedDefendTechniques}
              />
            ),
          )}
        </TableCell>
      </TooltipProvider>
      <TableCell className="text-nowrap">
        {mocks.attack.data.relatedCves
          .map(({ cveId, cvss }) => `${cveId} (${cvss})`)
          .join(', ')}
      </TableCell>
      <TableCell>
        <button onClick={handleOpen}>
          {open === false ? (
            <MdOutlineExpandMore size={24} />
          ) : index === 0 ? (
            <MdOutlineExpandMore size={24} className="rotate-180" />
          ) : (
            '-'
          )}
        </button>
      </TableCell>
    </TableRow>
  ));
};

export default TableContent;
