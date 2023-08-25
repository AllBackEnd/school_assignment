package allback.school_assignment;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GaleShapleyAlgorithmTest {

  @Test
  public void calculateTest() {
    GaleShapleyAlgorithm algorithm = new GaleShapleyAlgorithm();

    algorithm.calculate(GaleShapleyAlgorithm.SCHOOL_IDS, GaleShapleyAlgorithm.STUDENT_IDS);
  }
}
