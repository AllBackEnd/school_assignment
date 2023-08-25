package allback.school_assignment.school.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import lombok.RequiredArgsConstructor;
import allback.school_assignment.school.domain.School;
import allback.school_assignment.school.repository.SchoolRepository;
import allback.school_assignment.valuetype.Gender;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
   
    private final SchoolRepository schoolRepository;

    @Override
    @Transactional
    public void readCsvAndInsertData(String csvFilePath, String year) throws CsvValidationException {
        System.out.println("파일을 읽고 있습니다.");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8)).withSkipLines(1).build()) {
            String[] nextLine;
            long i = 1L;
            while ((nextLine = reader.readNext()) != null) {      
                schoolRepository.save(School.createSchool(i,nextLine[0],Integer.valueOf(nextLine[1]),Gender.valueOf(nextLine[2]),year));
                i = i + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
