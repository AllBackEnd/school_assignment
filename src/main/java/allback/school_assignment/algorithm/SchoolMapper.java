package allback.school_assignment.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolMapper {

  @Getter
  private Map<Integer, PreferEnum> transResult;
  @Getter
  private Map<String, Integer> lastPrefer;

  // 배정 학교 이름을 지망 순위로 변환
  public Map<Integer, PreferEnum> map(Map<Integer, String> allocation, Map<Integer, List<String>> engageList) {
    transResult = new HashMap<>();
    lastPrefer = new HashMap<>();

    Iterator iterator = allocation.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Integer, String> allocElement = (Entry<Integer, String>) iterator.next();
      Integer stdId = allocElement.getKey();
      String allocSchool = allocElement.getValue();

      PreferEnum prefer = findPrefer(allocSchool, engageList.get(stdId));
      transResult.put(stdId, prefer);
      updateIfBigger(allocSchool, prefer.getCode());
      log.info("std id : {}, prefer : {}, school name : {}", stdId, prefer.getName(), allocSchool);
    }

    return transResult;
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

  public void updateIfBigger(String key, Integer prefer) {
    // 비어있는 상태였다면 put하고 끝
    if (!lastPrefer.containsKey(key)) {
      lastPrefer.put(key, prefer);
      return;
    }

    // 작다면 update
    if (lastPrefer.get(key) < prefer) {
      lastPrefer.replace(key, prefer);
    }
  }

  public Integer getLastPreferForSchool(String key) {
    return lastPrefer.get(key);
  }

  public void printLastPrefer() {
    log.info("-----\n\n");
    Iterator iter = lastPrefer.entrySet().iterator();
    while (iter.hasNext()) {
      Entry<String, Integer> element = (Entry<String, Integer>) iter.next();
      log.info("key : {}, last prefer : {}", element.getKey(), element.getValue());
    }
    log.info("-----\n\n");
  }
}
