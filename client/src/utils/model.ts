import type { keyValueType } from '@type/common';
import type { AttackActionType, AttackFlowJsonType } from '@type/file';
import type { AttackFlowResultType } from '@type/model';

/**
 * 주어진 effectRef가 'attack-condition'을 만족할 때까지 attackActions을 재귀적으로 탐색합니다.
 *
 * @param {string} effectRef - 검사할 effect reference
 * @param {AttackActionType[]} attackActions - attack-actions 및 attack-operators의 배열
 * @returns {string} - 찾은 'attack-condition'의 ID, 없으면 빈 문자열 반환
 */
const findAttackCondition = (
  effectRef: string,
  attackActions: AttackActionType[],
): string => {
  // effectRef가 'attack-condition'을 만족하면 반환
  if (effectRef.startsWith('attack-condition')) return effectRef;
  // effectRef가 'attack-condition'을 만족하지 않으면 재귀적으로 탐색
  const currentAction = attackActions.find((action) => action.id === effectRef);
  if (currentAction?.effect_refs) {
    for (const ref of currentAction.effect_refs) {
      if (ref.startsWith('attack-condition')) {
        // 찾은 'attack-condition'을 반환
        return ref;
      } else {
        // 'attack-condition'을 만날 때까지 재귀적으로 탐색
        const result = findAttackCondition(ref, attackActions);
        if (result) return result;
      }
    }
  }
  return '';
};

/**
 * Attack Flow (JSON)를 파싱하여 기술 ID별로 공격 조건의 설명을 배열로 매핑합니다.
 *
 * @param {AttackFlowJsonType} attackFlowJson - Attack Flow Json
 * @returns {AttackFlowResultType} - 기술 ID를 키로 하고, 각 키에 대한 공격 조건 설명의 배열을 값으로 하는 객체
 */
export const parserAttackFlow = (
  attackFlowJson: AttackFlowJsonType,
): AttackFlowResultType => {
  const result: AttackFlowResultType = {};
  const attackConditionMap: keyValueType = {};
  // Attack Flow에서 attack-condition 객체로 attackConditionMap를 생성
  attackFlowJson.objects?.forEach((object) => {
    if (object.type === 'attack-condition') {
      attackConditionMap[object.id] = object.description;
    }
  });
  // description을 가진 attack-action과 attack-operator를 필터링
  const actionsWithDescription = attackFlowJson.objects?.filter(
    (object) =>
      object.type === 'attack-action' || object.type === 'attack-operator',
  );
  // attack-action의 effect_refs를 통해 적절한 attack-condition을 찾아 매핑
  actionsWithDescription?.forEach((action) => {
    action.effect_refs?.forEach((effectRef) => {
      // effect_refs가 attack-condition을 만족할 때까지 재귀적으로 탐색
      const findEffect = findAttackCondition(effectRef, actionsWithDescription);
      // 찾은 attack-condition을 기술 ID를 키로 하는 객체에 매핑
      const conditionDescription = attackConditionMap[findEffect];
      if (action.technique_id && conditionDescription) {
        // 기술 ID가 있고, 조건 설명이 있으면 매핑
        if (!result[action.technique_id]) {
          // 기술 ID가 없으면 배열을 생성
          result[action.technique_id] = [];
        }
        // 기술 ID가 있으면 배열에 조건 설명을 추가
        result[action.technique_id].push(conditionDescription);
      }
    });
  });
  return result;
};
