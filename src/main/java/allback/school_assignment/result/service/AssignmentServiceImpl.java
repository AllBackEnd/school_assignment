package allback.school_assignment.result.service;

import allback.school_assignment.algorithm.PreferEnum;
import allback.school_assignment.algorithm.SchoolMapper;
import allback.school_assignment.result.domain.AssignmentResult;
import allback.school_assignment.result.repository.AssignmentResultRepository;
import allback.school_assignment.student.domain.Student;
import allback.school_assignment.student.repository.StudentRepository;
import allback.school_assignment.valuetype.Gender;
import allback.school_assignment.valuetype.Grade;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final StudentRepository studentRepository;

    private final AssignmentResultRepository assignmentResultRepository;

    private final AssignmentResult assignmentResult;

    private final SchoolMapper schoolMapper;

    @Transactional
    @Override
    public void saveAssignment(Integer id, String assignedSchool, int seed) {

        Optional<Student> foundStudent = studentRepository.findById(id.longValue());

        if (foundStudent.isPresent()) {

            final Student student = foundStudent.get();
            //todo 랜덤번호 받은 것, 어떤 알고리즘 사용했나 정보 추가로 넣기
            SchoolMapper schoolMapper = new SchoolMapper();
            schoolMapper.map(Map<Integer, String>, Map<Integer, List<String>>)
            
            final AssignmentResult assignmentResult = AssignmentResult.createAssignmentResult(student.getId(), assignedSchool, ,student.getGender(), seed, student.getYear());

            assignmentResultRepository.save(assignmentResult);
        }   Long id, String schName, String assignedReason, Gender gender, Grade grade, String algorithm, Integer randomNumber, String year
    }

    @Transactional
    @Override
    public void save(List<Object> result, Map<Integer, List<String>> engageList){
        
        schoolMapper.map(result.get(0), engageList);

        
        Iterator iterator = result.get(0).entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, String> allocElement = (Entry<Integer, String>) iterator.next();
            assignmentResult.createAssignmentResult(allocElement.getKey(), allocElement.getValue());
        }
    }

    public Map<Integer, PreferEnum> save2(Map<Integer, String> allocation, Map<Integer, List<String>> engageList) {
        transResult = new HashMap<>();
        lastPrefer = new HashMap<>();

        Iterator iterator = allocation.entrySet().iterator();
        while (iterator.hasNext()) {
        Entry<Integer, String> allocElement = (Entry<Integer, String>) iterator.next();
        Integer stdId = allocElement.getKey();
        String allocSchool = allocElement.getValue();

        PreferEnum prefer = findPrefer(allocSchool, engageList.get(stdId));
        transResult.put(stdId, prefer);
        updateIfBigger(allocSchool, prefer.getCode());
        studentRepository.findById(stdId).get();
        log.info("std id : {}, prefer : {}, school name : {}", stdId, prefer.getName(), allocSchool);
    }

    return transResult;
    }

    // 지망 순위 계산
    private PreferEnum findPrefer(String allocSchool, List<String> engageList) {
        for (int i = 0; i < engageList.size(); i++) {
        if (allocSchool.equals(engageList.get(i))) {
            return PreferEnum.valueOf(i);
        }
        }

        return PreferEnum.RANDOM;
    }

    public void updateIfBigger(String key, Integer prefer) {
        // 비어있는 상태였다면 put하고 끝
        if (!lastPrefer.containsKey(key)) {
          lastPrefer.put(key, prefer);
          return;
        }
    
        // 작다면 update
        if (lastPrefer.get(key) < prefer) {
          lastPrefer.replace(key, prefer);
        }
      }

      public Integer getLastPreferForSchool(String key) {
        return lastPrefer.get(key);
      }
    
      public void printLastPrefer() {
        log.info("-----\n\n");
        Iterator iter = lastPrefer.entrySet().iterator();
        while (iter.hasNext()) {
          Entry<String, Integer> element = (Entry<String, Integer>) iter.next();
          log.info("key : {}, last prefer : {}", element.getKey(), element.getValue());
        }
        log.info("-----\n\n");
      }
}
