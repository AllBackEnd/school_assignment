package allback.school_assignment.school.service;

import com.opencsv.exceptions.CsvValidationException;

public interface SchoolService {
    public void readCsvAndInsertData(String csvFilePath, String year) throws CsvValidationException;
}
