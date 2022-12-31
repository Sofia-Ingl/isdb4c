package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.Legality;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = false)
public class LegalityConverter implements AttributeConverter<Legality, String> {

    @Override
    public String convertToDatabaseColumn(Legality legality) {
        if (legality == null) {
            return null;
        }
        return legality.getDescription();
    }

    @Override
    public Legality convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(Legality.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}