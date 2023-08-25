package allback.school_assignment.valuetype;

import jakarta.persistence.AttributeConverter;

public class GradeConverter implements AttributeConverter<Grade, String> {

    @Override
    public String convertToDatabaseColumn(Grade attribute) {
        return attribute.getKoreanName();
    }

    @Override
    public Grade convertToEntityAttribute(String dbData) {
        return Grade.OfCode(dbData);
    }
}
