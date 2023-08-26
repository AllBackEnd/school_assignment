package allback.school_assignment.result.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import allback.school_assignment.algorithm.GaleShapleyAlgorithm;
import allback.school_assignment.result.service.AssignmentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/naver")
    public @ResponseBody String saveAsnm(@RequestParam("seed") int seed) {
        Iterator iterator = alloc("Hybrid", seed).entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, String> allocElement = (Entry<Integer, String>) iterator.next();
            assignmentService.saveAssignment(allocElement.getKey(), allocElement.getValue(), seed);
        }
        String message = "배정 완료 되었습니다.";
        return message;
    }

    public Map<Integer, String> alloc(@RequestParam(value = "policy", defaultValue = "Hybrid") String policy, 
						@RequestParam(value = "seed", defaultValue = "0") long seed) {
		Map<String, Integer> schools = new HashMap<String, Integer>() {{ put("a", 1); put("b", 1); put("c", 1); }};
		Map<Integer, List<String>> students = new HashMap<Integer, List<String>>() {{
			put(1, new ArrayList<String>() {{ add("a"); add("c"); }});
			put(2, new ArrayList<String>() {{ add("b"); add("c"); }});
			put(3, new ArrayList<String>() {{ add("a"); add("b"); }});
		}};
		int n_wishes = 2;
		Map<Integer, List<String>> forbidden = new HashMap<Integer, List<String>>();
		List<String> school_ids = new ArrayList<String>() {{ add("a"); add("b"); add("c"); }};
		List<Integer> student_ids = new ArrayList<Integer>() {{ add(1); add(2); add(3); }};
		Random rand = new Random(seed);
		Collections.shuffle(school_ids, rand);
		Collections.shuffle(student_ids, rand);
		List<Object> result = null;
		result = new GaleShapleyAlgorithm().allocate(schools, students, n_wishes, forbidden, school_ids, student_ids);
		save(result,engageList,grade,algorithm);
		//else result = new Allocator().Allocate(PolicyType.Hybrid, schools, students, n_wishes, forbidden, school_ids, student_ids);
		@SuppressWarnings("unchecked") Map<Integer, String> allocation = (Map<Integer, String>) result.get(0);
		@SuppressWarnings("unchecked") Map<Integer, List<String>> remain_schools = (Map<Integer, List<String>>) result.get(1);
		@SuppressWarnings("unchecked") List<Integer> remain_students = (List<Integer>) result.get(2);
		System.out.println("Policy: " + policy +
						", seed: " + seed +
						", allocation: " + allocation +
						", remain_schools: " + remain_schools +
						", remain_students: " + remain_students);
        return allocation;
    }
}

