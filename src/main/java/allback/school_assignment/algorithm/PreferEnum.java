package allback.school_assignment.algorithm;

import lombok.Getter;

public enum PreferEnum {
  P1(0, "1지망"),
  P2(1, "2지망"),
  P3(2, "3지망"),
  P4(3, "4지망"),
  P5(4, "5지망"),
  P6(5, "6지망"),
  P7(6, "7지망"),
  RANDOM(7, "임의배정");

  @Getter
  private final int code;
  @Getter
  private final String name;

  PreferEnum(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static PreferEnum valueOf(int code) {
    for (PreferEnum i : values()) {
      if (code == i.code) {
        return i;
      }
    }
    return null;
  }
}
