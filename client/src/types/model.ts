export interface AttackType {
  attackId: string;
  attackName: string;
  attackType: 'technique' | 'tactic' | 'procedure';
  attackUrl: string;
  mappingDescription: string;
}

export interface ControlType {
  controlId: string;
  controlName: string;
}

export interface MitigationType {
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

export interface CVEType {
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

export interface ReportModelType {
  level6: string;
  techniqueId: string;
  controlId: ControlType[];
  mitigationId: MitigationType[];
  cve: CVEType[];
}
