package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.EmployeeStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = false)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if (employeeStatus == null) {
            return null;
        }
        return employeeStatus.getDescription();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
