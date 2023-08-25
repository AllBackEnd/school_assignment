package allback.school_assignment.valuetype;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("남"),
    FEMALE("여");

    String code;

    Gender(String code) {
        this.code = code;
    }

    public static Gender ofCode(String code) {
        return Arrays
                .stream(Gender.values())
                .filter(c -> c.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("DB에 잘못된 값이 입력 되었습니다."));
    }
}
