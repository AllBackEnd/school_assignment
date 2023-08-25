package allback.school_assignment;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class GaleShapleyAlgorithmTest {

  @Test
  public void calculateTest() {
    GaleShapleyAlgorithm algorithm = new GaleShapleyAlgorithm();

    algorithm.calculate(new ArrayList<String>() {{
      add("1청주고");
      add("2가나고");
      add("3하마고");
    }}, new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
    }});
  }
}
