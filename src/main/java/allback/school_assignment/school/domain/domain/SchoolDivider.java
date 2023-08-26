package allback.school_assignment.school.domain.domain;

import allback.school_assignment.ArrayIndexComparator;
import allback.school_assignment.school.domain.School;
import allback.school_assignment.valuetype.Gender;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SchoolDivider {

    public static List<School> divideByGender(List<School> studentList, Gender gender) {

        return studentList.stream().filter(s -> s.getAcceptableGender().equals(gender)).toList();

    }

    public static List<Map<String, Integer>> createPartition(List<School> schoolList, List<Double> ratios, Gender gender) {

        List<Map<String, Integer>> result = new ArrayList<>();

        List<School> filteredSchool = divideByGender(schoolList, gender);
        int total = 0;

        List<Integer> assigned = new ArrayList<>();
        List<Double> fragment = new ArrayList<>();

        for (School school : filteredSchool) {
            total += school.getCapacity();
            assigned.add(0);
            fragment.add(0.0);
        }

        for (Double ratio : ratios) {
            int count = (int) (total * ratio);
            for (int i = 0; i < filteredSchool.size(); i++) {
                double realValue = filteredSchool.get(i).getCapacity() * ratio;
                int r = (int) realValue;
                double s = realValue - r;
                assigned.set(i, r);
                fragment.set(i, fragment.get(i) + s);
                count -= r;

            }

            ArrayIndexComparator arrayIndexComparator = new ArrayIndexComparator(fragment.toArray(new Double[0]));

            Integer[] indexArray = arrayIndexComparator.createIndexArray();

            Arrays.sort(indexArray, arrayIndexComparator);
            Integer integer = 0;
            while (count > 0) {
                integer++;
                count -= 1;
                assigned.set(indexArray[integer], assigned.get(indexArray[integer]) + 1);
                fragment.set(indexArray[integer], fragment.get(indexArray[integer]) - 1);
            }

            Map<String, Integer> partition = new HashMap<>();

            for (int i = 0; i < filteredSchool.size(); i++) {
                partition.put(filteredSchool.get(i).getName(), assigned.get(i));
            }
            log.info("들어감");
            result.add(partition);
        }
        return result;
    }
}
