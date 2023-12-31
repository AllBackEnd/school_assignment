package allback.school_assignment.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SchoolCurCntMap {

  private Map<String, List<Integer>> map = new HashMap<>();
  private static Integer CUR_IDX = 0;
  private static Integer MAX_IDX = 1;

  public SchoolCurCntMap(Map<String, Integer> schools) {
    Iterator iterator = schools.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, Integer> element = (Entry<String, Integer>) iterator.next();
      map.put(element.getKey(), new ArrayList<>() {{ add(0); add(element.getValue());}});
    }
  }

  private int getCurCnt(String key) {
    return map.get(key).get(CUR_IDX);
  }

  private int getMaxCnt(String key) {
    return map.get(key).get(MAX_IDX);
  }

  public void increaseCurCnt(String key) {
    map.get(key).set(CUR_IDX, getCurCnt(key) + 1);
  }

  public boolean isRemain(String key) {
    if (getCurCnt(key) < getMaxCnt(key)) {
      return true;
    } else {
      return false;
    }
  }

  public String getRemainSchool() {
    Iterator iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, List<Integer>> element = (Entry<String, List<Integer>>) iterator.next();
      if (isRemain(element.getKey())) {
        return element.getKey();
      }
    }

    throw new RuntimeException("No remain school");
  }
}
