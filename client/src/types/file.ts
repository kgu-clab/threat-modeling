type AttackTypeType =
  | 'bundle'
  | 'extension-definition'
  | 'identity'
  | 'attack-flow'
  | 'threat-actor'
  | 'attack-action'
  | 'attack-condition'
  | 'attack-operator'
  | 'attack-asset';

interface AttackBaseType {
  type: AttackTypeType;
  id: string;
  spec_version: string;
  created: string;
  modified: string;
}

interface AttackActionType extends AttackBaseType {
  name: string;
  tactic_id: string;
  technique_id: string;
  description: string;
  effect_refs: string[];
  extensions: {
    [key: string]: {
      [key: string]: string;
    };
  };
}

export interface AttackFlowJsonType extends AttackBaseType {
  objects: Array<AttackActionType>;
}
