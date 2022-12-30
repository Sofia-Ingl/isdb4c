package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.Sex;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SexConverter implements AttributeConverter<Sex, String> {

    @Override
    public String convertToDatabaseColumn(Sex sex) {
        if (sex == null) {
            return null;
        }
        return sex.getDescription();
    }

    @Override
    public Sex convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(Sex.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
