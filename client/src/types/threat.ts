interface AttackType {
  attackId: string;
  attackName: string;
  attackType: 'technique' | 'tactic' | 'procedure';
  attackUrl: string;
  mappingDescription: string;
}

interface ControlType {
  controlId: string;
  controlName: string;
}

interface MitigationType {
  mitigationId: string;
  mitigationName: string;
  mitigationUrl: string;
  relatedDefendTechniques: DefendTechniqueType[];
}

export interface DefendTechniqueType {
  defendId: string;
  defendName: string;
  defendUrl: string;
}

interface CVEType {
  cveId: string;
  cvss: number | null;
  phase: string;
  description: string;
}

export interface ThreatModelType {
  attack: AttackType;
  relatedControls: ControlType[];
  relatedMitigations: MitigationType[];
  relatedCves: CVEType[];
}
