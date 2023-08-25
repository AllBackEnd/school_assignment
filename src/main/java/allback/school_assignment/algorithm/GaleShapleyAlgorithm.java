package allback.school_assignment.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GaleShapleyAlgorithm {

  private static String S_ONE = "a에이고";
  private static String S_TWO = "b비이고";
  private static String S_THR = "c씨이고";
  private static String S_FOU = "d디이고";

  public static Map<String, Integer> SCHOOL_DATA = new HashMap<String, Integer>() {{
    put(S_ONE, 2);
    put(S_TWO, 3);
    put(S_THR, 4);
    put(S_FOU, 3);
  }};
  public static Map<Integer, List<String>> STUDENT_DATA = new HashMap<Integer, List<String>>() {{
    put(3001, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_THR);
    }});
    put(3002, new ArrayList<String>() {{
      add(S_TWO);
      add(S_THR);
      add(S_FOU);
    }});
    put(3003, new ArrayList<String>() {{
      add(S_ONE);
      add(S_THR);
      add(S_FOU);
    }});
    put(3004, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_FOU);
    }});
    put(2001, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_THR);
    }});
    put(2002, new ArrayList<String>() {{
      add(S_TWO);
      add(S_THR);
      add(S_FOU);
    }});
    put(2003, new ArrayList<String>() {{
      add(S_ONE);
      add(S_THR);
      add(S_FOU);
    }});
    put(2004, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_FOU);
    }});
    put(1001, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_THR);
    }});
    put(1002, new ArrayList<String>() {{
      add(S_TWO);
      add(S_THR);
      add(S_FOU);
    }});
    put(1003, new ArrayList<String>() {{
      add(S_ONE);
      add(S_THR);
      add(S_FOU);
    }});
    put(1004, new ArrayList<String>() {{
      add(S_ONE);
      add(S_TWO);
      add(S_FOU);
    }});
  }};
  public static ArrayList<String> SCHOOL_IDS = new ArrayList<String>() {{
    add(S_ONE);
    add(S_TWO);
    add(S_THR);
    add(S_FOU);
  }};
  public static ArrayList<Integer> STUDENT_IDS = new ArrayList<Integer>() {{
    add(3001);
    add(3002);
    add(3003);
    add(3004);
    add(2001);
    add(2002);
    add(2003);
    add(2004);
    add(1001);
    add(1002);
    add(1003);
    add(1004);
  }};

  private SchoolCurCntMap schoolCurCntMap;
  private boolean [] entered;
  @SuppressWarnings("unchecked")
  private Map<Integer, String> allocation;
  @SuppressWarnings("unchecked")
  private Map<String, Integer> remain_schools;
  @SuppressWarnings("unchecked")
  private List<Integer> remain_students;

  public String calculate(List<String> school_ids, List<Integer> student_ids) {
    // 1. data 확보
    Map<String, Integer> schoolsMaxCntList = getAllSchool(); // 학교 이름과 정원
    Map<Integer, List<String>> studentsEngageList = getAllStudentList(); // 학생 id와 지망 리스트
    int n_wishes = 3; // 지망 순위 개수, 위 list의 lenth
    Map<Integer, List<String>> forbidden = getAllForbidden(); // 매칭 금지 리스트, 무조건 비어있음

    // 2. 연산 수행
    allocate(schoolsMaxCntList, studentsEngageList, n_wishes, forbidden, school_ids, student_ids);

    // 3. 결과 출력
    return printResult();
  }

  private void allocate(Map<String, Integer> schoolsMaxCntList,
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

    checkNoRemainStudent();
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

  private String printResult() {
    System.out.println("Policy: GaleShapleyAlgorithm" +
        ", seed: 0 " +
        ", allocation: " + allocation +
        ", remain_schools: " + remain_schools +
        ", remain_students: " + remain_students);

    return String.format("Policy GaleShapleyAlgorithm!");
  }

  private Map<String, Integer> getAllSchool() {

    return SCHOOL_DATA;
  }

  private Map<Integer, List<String>> getAllStudentList() {

    return STUDENT_DATA;
  }

  private HashMap<Integer, List<String>> getAllForbidden() {

    return new HashMap<Integer, List<String>>();
  }

  /*
  @GetMapping("/alloc")
  public String alloc(@RequestParam(value = "policy", defaultValue = "Hybrid") String policy,
      @RequestParam(value = "seed", defaultValue = "0") long seed) {

    Map<String, Integer> schools = new HashMap<String, Integer>() {{ put("a", 1); put("b", 1); put("c", 1); }};
    Map<Integer, List<String>> students = new HashMap<Integer, List<String>>() {{
      put(1, new ArrayList<String>() {{ add("a"); add("c"); }});
      put(2, new ArrayList<String>() {{ add("b"); add("c"); }});
      put(3, new ArrayList<String>() {{ add("a"); add("b"); }});
    }};
    int n_wishes = 2;
    Map<Integer, List<String>> forbidden = new HashMap<Integer, List<String>>();
    List<String> school_ids = new ArrayList<String>() {{ add("a"); add("b"); add("c"); }};
    List<Integer> student_ids = new ArrayList<Integer>() {{ add(1); add(2); add(3); }};
    Random rand = new Random(seed);
    Collections.shuffle(school_ids, rand);
    Collections.shuffle(student_ids, rand);
    List<Object> result;
    if (policy.equals("Single")) result = new Allocator().Allocate(PolicyType.Single, schools, students, n_wishes, forbidden, school_ids, student_ids);
    else result = new Allocator().Allocate(PolicyType.Hybrid, schools, students, n_wishes, forbidden, school_ids, student_ids);
    @SuppressWarnings("unchecked") Map<Integer, String> allocation = (Map<Integer, String>) result.get(0);
    @SuppressWarnings("unchecked") Map<Integer, List<String>> remain_schools = (Map<Integer, List<String>>) result.get(1);
    @SuppressWarnings("unchecked") List<Integer> remain_students = (List<Integer>) result.get(2);
    System.out.println("Policy: " + policy +
        ", seed: " + seed +
        ", allocation: " + allocation +
        ", remain_schools: " + remain_schools +
        ", remain_students: " + remain_students);
    return String.format("Policy %s!", policy);

    return null;
  }
  */
}