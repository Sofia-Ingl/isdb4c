package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.types.PersonStatus;
import org.example.isdb4c.model.types.Sex;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class PersonNetTransfer {

    private Integer id;
    private String status;
    private String name;
    private String alias;
    private String sex;
    private String citizenship;
    private String passport;
    private String address;
    private LocalDate birthDate;
    private String location;
    private Integer accessLvl;

    public PersonNetTransfer() {}

    public PersonNetTransfer(ObservedPerson person) {
        id = person.getId();
        status = person.getStatus().getDescription();
        name = person.getName();
        alias = person.getAlias();
        sex = person.getSex().getDescription();
        citizenship = person.getCitizenship();
        passport = person.getPassport();
        address = person.getAddress();
        birthDate = person.getBirthDate();
        location = person.getLocation();
        accessLvl = person.getAccessLvl();
    }
}
