package allback.school_assignment.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolMapper {

  // 배정 학교 이름을 지망 순위로 변환
  public Map<Integer, PreferEnum> map(Map<Integer, String> allocation, Map<Integer, List<String>> engageList) {
    Map<Integer, PreferEnum> result = new HashMap<>();

    Iterator iterator = allocation.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Integer, String> allocElement = (Entry<Integer, String>) iterator.next();
      Integer stdId = allocElement.getKey();
      String allocSchool = allocElement.getValue();

      PreferEnum prefer = findPrefer(allocSchool, engageList.get(stdId));
      result.put(stdId, prefer);
      log.info("std id : {}, prefer : {}", stdId, prefer.getName());
    }

    return result;
  }

  // 지망 순위 계산
  private PreferEnum findPrefer(String allocSchool, List<String> engageList) {
    for (int i = 0; i < engageList.size(); i++) {
      if (allocSchool.equals(engageList.get(i))) {
        return PreferEnum.valueOf(i);
      }
    }

    return PreferEnum.RANDOM;
  }
}
