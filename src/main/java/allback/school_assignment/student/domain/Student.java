package allback.school_assignment.student.domain;

import allback.school_assignment.valuetype.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Student {

    @Id
    @Field("STD_ID")
    @Setter
    private Long id;

    @Field("STD_NAME")
    private String name;

    @Field("GND")
    @Setter
    private Gender gender;

    @Field("SCR")
    private Long score;

    @Field("PRE1")
    private String firstPreferSchool;

    @Field("PRE2")
    private String secondPreferSchool;

    @Field("PRE3")
    private String thirdPreferSchool;

    @Field("PRE4")
    private String fourthPreferSchool;

    @Field("PRE5")
    private String fifthPreferSchool;

    @Field("PRE6")
    private String sixthPreferSchool;

    @Field("PRE7")
    private String seventhPreferSchool;

    @Field("YEAR_INFO")
    private String year;

    private Student(Long id, Gender gender, String year, Random random) {
            this.id = Integer.valueOf(year)*10000 + id;
            this.name = randomHangulName(random);
            this.gender = gender;
            this.score = createRandomScore(random);
            final List<String> randomPreferSchool = createRandomPreferSchool(id, gender, random);
            this.firstPreferSchool = randomPreferSchool.get(0);
            this.secondPreferSchool = randomPreferSchool.get(1);
            this.thirdPreferSchool = randomPreferSchool.get(2);
            this.fourthPreferSchool = randomPreferSchool.get(3);
            this.fifthPreferSchool = randomPreferSchool.get(4);
            this.sixthPreferSchool = randomPreferSchool.get(5);
            this.seventhPreferSchool = randomPreferSchool.get(6);
            this.year = year;
    }
    public static Student createRandomStudent(Long id, Gender gender, String year, Random random) {
            return new Student(id, gender, year, random);
    }

    private Long createRandomScore(Random random) {
        long min = 0;
        long max = 100000000;
        return random.nextLong(max - min +1) + min;
    }

    private List<String> createRandomPreferSchool(Long id, Gender gender, Random random) {

        List<String> canSchoolList = new ArrayList<>();

                canSchoolList.add("흥덕고");
                canSchoolList.add("봉명고");
                canSchoolList.add("사대부고");
                canSchoolList.add("대성고");
                canSchoolList.add("주성고");
                canSchoolList.add("금천고");

        if(gender == Gender.남) {
            canSchoolList.addAll(Arrays.asList(
                      "충북고"
                    , "청주고"
                    , "청석고"
                    , "운호고"
                    , "세광고"
                    , "신흥고"
                    ));
        } else if (gender == Gender.여) {
            canSchoolList.addAll(Arrays.asList(
                      "청주여고"
                    , "일신여고"
                    , "충북여고"
                    , "산남고"
                    , "청주중앙여고"));
        }
        Collections.shuffle(canSchoolList, random);
        return canSchoolList;
    }

    public static String randomHangulName(Random random) {
        List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
            "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
            "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
            "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
            "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> firstName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
            "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
            "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
            "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
            "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
            "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
            "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
            "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
            "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
            "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(lastName, random);
        Collections.shuffle(firstName, random);
        return lastName.get(0) + firstName.get(0) + firstName.get(1);
      }
}
