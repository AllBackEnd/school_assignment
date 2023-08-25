package allback.school_assignment;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import allback.school_assignment.algorithm.SchoolMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GaleShapleyAlgorithmTest {

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

  @Test
  public void calculateTest() {
    GaleShapleyAlgorithm algorithm = new GaleShapleyAlgorithm();
    List<Object> result = algorithm.allocate(SCHOOL_DATA, STUDENT_DATA, 3, new HashMap<>(), SCHOOL_IDS, STUDENT_IDS);
    algorithm.printResult();

    SchoolMapper schoolMapper = new SchoolMapper();
    schoolMapper.map((Map<Integer, String>) result.get(0), STUDENT_DATA);
  }
}
