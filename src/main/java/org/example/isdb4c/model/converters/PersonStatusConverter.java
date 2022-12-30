package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.Legality;
import org.example.isdb4c.model.types.PersonStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PersonStatusConverter implements AttributeConverter<PersonStatus, String> {

    @Override
    public String convertToDatabaseColumn(PersonStatus personStatus) {
        if (personStatus == null) {
            return null;
        }
        return personStatus.getDescription();
    }

    @Override
    public PersonStatus convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(PersonStatus.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}