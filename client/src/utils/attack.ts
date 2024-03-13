import type { AttackFlowJsonType } from '@type/file';

export interface ParsedResult {
  [techniqueId: string]: string[];
}

/**
 * 주어진 JSON 데이터에서 공격 흐름(Attack Flow)을 파싱하여 결과를 반환합니다.
 * 각 `attack-action` 항목은 하나 이상의 `effect_refs`를 포함할 수 있으며, 이는 `attack-condition` 항목의 ID와 일치합니다.
 * 이 함수는 `attack-action`의 `technique_id`를 키로 하고, 해당 `technique_id`에 연결된 모든 `attack-condition`의 `description`을 배열로 가지는 객체를 반환합니다.
 *
 * @param {AttackFlowJsonType} json - 공격 흐름 데이터를 포함하는 JSON 객체.
 * @returns {ParsedResult} 결과 객체, 여기서 `technique_id`별로 여러 `description`을 배열로 포함합니다.
 */

export const parserAttackFlow = (json: AttackFlowJsonType): ParsedResult => {
  const result: ParsedResult = {};
  const attackConditionMap: { [key: string]: string } = {};

  json.objects
    .filter((object) => object.type === 'attack-condition')
    .forEach((condition) => {
      attackConditionMap[condition.id] = condition.description;
    });

  json.objects
    .filter((object) => object.type === 'attack-action')
    .forEach((action) => {
      action.effect_refs.forEach((effectRef) => {
        const conditionDescription = attackConditionMap[effectRef];
        if (conditionDescription) {
          if (result[action.technique_id]) {
            result[action.technique_id].push(conditionDescription);
          } else {
            result[action.technique_id] = [conditionDescription];
          }
        }
      });
    });

  return result;
};
