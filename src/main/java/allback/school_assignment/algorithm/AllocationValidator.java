package allback.school_assignment.algorithm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllocationValidator {

  public void validate(Map<Integer, PreferEnum> transRes, Map<Integer, List<String>> engageList) {
    // 1. 바깥쪽 반복문
    Iterator outIter = transRes.entrySet().iterator();
    while (outIter.hasNext()) {
      Entry<Integer, PreferEnum> outEle = (Entry<Integer, PreferEnum>) outIter.next();
      Integer outId = outEle.getKey();
      PreferEnum outPrefer = outEle.getValue();

      // 2. 안쪽 반복문
      Iterator inIter = transRes.entrySet().iterator();
      while (inIter.hasNext()) {
        Entry<Integer, PreferEnum> inEle = (Entry<Integer, PreferEnum>) inIter.next();
        Integer inId = inEle.getKey();
        PreferEnum inPrefer = inEle.getValue();

        // 3. 자기 자신이면 skip
        if (outId == inId) {
          continue;
        }

        // 4. stable한지 검사
        String outSchool = getPreferSchool(outId, outPrefer.getCode(), engageList);
        if (isStable(outSchool, inId, inPrefer.getCode(), engageList)) {
          //log.info("outId : {}, outPrefer : {}, inId : {}, inPrefer : {}, stable\n", outId,
          //    outPrefer.getName(), inId, inPrefer.getName());
        } else {
          log.info("outId : {}, outPrefer : {}, inId : {}, inPrefer : {}, unstable ----- \n", outId,
              outPrefer.getName(), inId, inPrefer.getName());
        }
      }
    }
  }

  private boolean isStable(String outSchool, int inId, int inPrefer,
      Map<Integer, List<String>> engageList) {

    for (int inIndex = 1; inIndex < inPrefer; inIndex++) {
      String inSchool = getPreferSchool(inId, inIndex, engageList);
      if (outSchool.equals(inSchool)) {
        log.info("outSchool : {}, pre prefer : {}, new prefer : {}", outSchool, inPrefer, inIndex);
        return false;
      }
    }

    return true;
  }

  private String getPreferSchool(int stdId, int preIdx, Map<Integer, List<String>> engageList) {
    return engageList.get(stdId).get(preIdx);
  }
}
