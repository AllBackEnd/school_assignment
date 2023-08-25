package allback.school_assignment.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GaleShapleyAlgorithm {

  private SchoolCurCntMap schoolCurCntMap;
  private boolean [] entered;
  @SuppressWarnings("unchecked")
  private Map<Integer, String> allocation;
  @SuppressWarnings("unchecked")
  private Map<String, Integer> remain_schools;
  @SuppressWarnings("unchecked")
  private List<Integer> remain_students;

  public List<Object> allocate(Map<String, Integer> schoolsMaxCntList,
      Map<Integer, List<String>> studentsEngageList, int n_wishes,
      Map<Integer, List<String>> forbidden, List<String> school_ids, List<Integer> student_ids) {

    // 1. 학교 별로 현재 입학 인원과 최대 정원을 관리하는 class의 객체 생성, 기타 초기화 작업
    schoolCurCntMap = new SchoolCurCntMap(schoolsMaxCntList);
    entered = new boolean[student_ids.size()];
    allocation = new HashMap<>();
    remain_schools = new HashMap<>();
    remain_students = new ArrayList<>();

    // 2. 모든 학생이 가장 높은 학교부터 지원
    for (int prefer = 0; prefer < n_wishes; prefer++) {
      log.info("----------- prefer : {} -----------", prefer);

      // 3. 각 학생이 list에 들어간 순서대로 학교를 지원
      for (int stdIdx = 0; stdIdx < studentsEngageList.size(); stdIdx++) {
        // 4. 이미 지원이 완료된 학생이라면 skip
        if (isEntered(stdIdx)) {
          log.info("prefer : {}, stdIdx : {} already entered\n", prefer, stdIdx);
          continue;
        }

        // 5. 특정 학생이 현재 우선 순위에서 원하는 학교 획득
        String preferSchool = getPreferSchool(prefer, stdIdx, student_ids, studentsEngageList);

        // 6. 해당 학교에 지원
        if (schoolCurCntMap.isRemain(preferSchool)) {
          enterSchool(preferSchool, stdIdx, student_ids);
          log.info("prefer : {}, stdIdx : {} entered\n", prefer, stdIdx);
        } else {
          log.info("prefer : {}, stdIdx : {} ----- not entered\n", prefer, stdIdx);
        }
      }
    }

    // 7. 임의 배정
    log.info("----- remain -----");
    for (int remainIdx = 0; remainIdx < student_ids.size(); remainIdx++) {
      // 8. 이미 배정 되었으면 패스
      if (isEntered(remainIdx)) {
        continue;
      }
      
      String remainSchool = schoolCurCntMap.getRemainSchool();
      enterSchool(remainSchool, remainIdx, student_ids);
      log.info("prefer : {}, stdIdx : {} remain - \n", remainSchool, remainIdx);
    }

    // 9. 간단하게 오류 검사
    checkNoRemainStudent();

    // 10. 결과 값 리턴
    List<Object> result = new ArrayList<>();
    result.add(allocation);
    result.add(remain_schools);
    result.add(remain_students);
    return result;
  }

  // 지원 완료 여부 판단
  private boolean isEntered(int stdIdx) {
    return  (entered[stdIdx]);
  }

  // 현재 우선 순위에서 선호 학교명 획득
  private String getPreferSchool(int prefer, int stdIdx, List<Integer>student_ids, Map<Integer, List<String>> studentsEngageList) {
    Integer id = student_ids.get(stdIdx);
    String school = studentsEngageList.get(id).get(prefer);
    log.info("std id : {}, sc name : {}", id, school);
    return school;
  }

  // 학교에 지원 (지원 가능한 상태에서만 호출됨)
  private void enterSchool(String preferSchool, int stdIdx, List<Integer> school_ids) {
    schoolCurCntMap.increaseCurCnt(preferSchool);
    entered[stdIdx] = true;
    allocation.put(school_ids.get(stdIdx), preferSchool);
  }

  private void checkNoRemainStudent() {
    for (int i = 0; i < entered.length; i++) {
      if (!entered[i]) {
        throw new RuntimeException("Remain student");
      }
    }
  }

  public String printResult() {
    System.out.println("Policy: GaleShapleyAlgorithm" +
        ", seed: 0 " +
        ", allocation: " + allocation +
        ", remain_schools: " + remain_schools +
        ", remain_students: " + remain_students + "\n\n\n");

    return String.format("Policy GaleShapleyAlgorithm!");
  }
}
