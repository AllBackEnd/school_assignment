package allback.school_assignment;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class GaleShapleyAlgorithmTest {

  @Test
  public void calculateTest() {
    GaleShapleyAlgorithm algorithm = new GaleShapleyAlgorithm();

    algorithm.calculate(GaleShapleyAlgorithm.SCHOOL_IDS, GaleShapleyAlgorithm.STUDENT_IDS);
  }
}
