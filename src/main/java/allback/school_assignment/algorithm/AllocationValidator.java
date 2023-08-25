package allback.school_assignment.algorithm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllocationValidator {

  public void validate(Map<String, Integer> lastPrefer, Map<Integer, PreferEnum> transRes, Map<Integer, List<String>> engageList) {
    // 1. 각 학생에 대해 순회
    Iterator transIter = transRes.entrySet().iterator();
    while (transIter.hasNext()) {
      Entry<Integer, PreferEnum> entry = (Entry<Integer, PreferEnum>) transIter.next();

      // 2. 현재 몇 지망인지 변수에 저장
      int curPrefer = entry.getValue().getCode();

      // 3. 현재 지망보다 높은 학교에 대해서 순회
      for (int preferIdx = 0; preferIdx < curPrefer; preferIdx++) {
        
        // 4. 현재 지망보다 높은 학교 이름 획득
        String targetSchool = getPreferSchool(entry.getKey(), preferIdx, engageList);

        // 5. 현재 지망보다 높은 학교의 마지막 배정 순위 획득
        int last = lastPrefer.get(targetSchool);

        // 7. stable 판단
        if (last > curPrefer) {
          log.info("unstable -----");
        } else {
          log.info("stable");
        }
      }
    }
  }

  private String getPreferSchool(int stdId, int prefer, Map<Integer, List<String>> engageList) {
    return engageList.get(stdId).get(prefer);
  }
}
