package allback.school_assignment.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class GaleShapleyAlgorithm {

  public String calculate(List<String> school_ids, List<Integer> student_ids) {
    // 1. 모든 data 긁어옴
    Map<String, Integer> schools = getAllSchool(); // 학교 이름과 정원
    Map<Integer, List<String>> students = getAllStudentList(); // 학생 id와 지망 리스트
    int n_wishes = 2; // 지망 순위 개수, 위 list의 lenth
    Map<Integer, List<String>> forbidden = getAllForbidden(); // 매칭 금지 리스트, 무조건 비어있음

    // 2. 연산 수행
    List<Object> result = allocate(schools, students, n_wishes, forbidden, school_ids, student_ids);

    // 3. 결과 출력
    return printResult(result);
  }

  private List<Object> allocate(Map<String, Integer> schools, Map<Integer, List<String>> students,
      int n_wishes, Map<Integer, List<String>> forbidden, List<String> school_ids,
      List<Integer> student_ids) {




    return null;
  }

  private String printResult(List<Object> result) {
    @SuppressWarnings("unchecked") Map<Integer, String> allocation = (Map<Integer, String>) result.get(0);
    @SuppressWarnings("unchecked") Map<String, Integer> remain_schools = (Map<String, Integer>) result.get(1);
    @SuppressWarnings("unchecked") List<Integer> remain_students = (List<Integer>) result.get(2);

    System.out.println("Policy: GaleShapleyAlgorithm" +
        ", seed: 0 " +
        ", allocation: " + allocation +
        ", remain_schools: " + remain_schools +
        ", remain_students: " + remain_students);

    return String.format("Policy GaleShapleyAlgorithm!");
  }

  private Map<String, Integer> getAllSchool() {

    return new HashMap<String, Integer>() {{ put("a", 1); put("b", 1); put("c", 1); }};
  }

  private Map<Integer, List<String>> getAllStudentList() {

    return new HashMap<Integer, List<String>>() {{
      put(1, new ArrayList<String>() {{ add("a"); add("c"); }});
      put(2, new ArrayList<String>() {{ add("b"); add("c"); }});
      put(3, new ArrayList<String>() {{ add("a"); add("b"); }});
    }};
  }

  private HashMap<Integer, List<String>> getAllForbidden() {

    return new HashMap<Integer, List<String>>();
  }

  @GetMapping("/alloc")
  public String alloc(@RequestParam(value = "policy", defaultValue = "Hybrid") String policy,
      @RequestParam(value = "seed", defaultValue = "0") long seed) {
    /*
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
    */
    return null;
  }
}
