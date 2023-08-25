package allback.school_assignment.valuetype;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Grade {
    FIRST_GROUP("1군", 10),
    SECOND_GROUP("2군", 40),
    THIRD_GROUP("3군", 40),
    FOURTH_GROUP("4군", 10);

    String koreanName;

    int percentage;

    Grade(String koreanName, int percentage) {
        this.koreanName = koreanName;
        this.percentage = percentage;
    }

    public static Grade OfCode(String koreanName) {
        return Arrays
                .stream(Grade.values())
                .filter(c -> c.getKoreanName().equals(koreanName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("DB에 잘못된 값이 입력 되었습니다."));
    }
}
