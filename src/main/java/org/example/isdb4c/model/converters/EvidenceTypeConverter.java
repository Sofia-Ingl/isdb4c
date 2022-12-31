package org.example.isdb4c.model.converters;

import org.example.isdb4c.model.types.EvidenceType;
import org.example.isdb4c.model.types.Legality;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = false)
public class EvidenceTypeConverter implements AttributeConverter<EvidenceType, String> {

    @Override
    public String convertToDatabaseColumn(EvidenceType evidenceType) {
        if (evidenceType == null) {
            return null;
        }
        return evidenceType.getDescription();
    }

    @Override
    public EvidenceType convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(EvidenceType.values())
                .filter(c -> c.getDescription().equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}