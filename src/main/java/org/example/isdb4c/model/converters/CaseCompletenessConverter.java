package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.CaseCompleteness;
import org.example.isdb4c.model.types.EmployeeStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = false)
public class CaseCompletenessConverter implements AttributeConverter<CaseCompleteness, String> {

    @Override
    public String convertToDatabaseColumn(CaseCompleteness caseCompleteness) {
        if (caseCompleteness == null) {
            return null;
        }
        return caseCompleteness.getDescription();
    }

    @Override
    public CaseCompleteness convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(CaseCompleteness.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}