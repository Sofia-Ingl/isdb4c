package org.example.isdb4c.repository;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.types.PersonStatus;
import org.example.isdb4c.model.types.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<ObservedPerson, Integer> {

    public List<ObservedPerson> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    public List<ObservedPerson> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
    public List<ObservedPerson> findAllByWitnessCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);

    public List<ObservedPerson> findAllByMemberships_OrganizationIdAndAccessLvlLessThanEqual(Integer organizationId, Integer accessLvl);

    @Modifying
    @Transactional
    @Query(value = "update observed_person set " +
            "person_name = :personName, " +
            "cathegory = :#{#personStatus.toString()}, " +
            "person_alias = :personAlias, " +
            "person_sex = :#{#personSex.toString()}, " +
            "citizenship = :citizenship, " +
            "passport = :pass, " +
            "address = :addr, " +
            "birth_date = :birthDate, " +
            "person_location = :location " +
            "where id = :personId", nativeQuery = true)
    void updatePersonById(@Param("personName") String personName,
                          @Param("personStatus") PersonStatus personStatus,
                          @Param("personAlias") String personAlias,
                          @Param("personSex") Sex personSex,
                        @Param("citizenship") String citizenship,
                          @Param("pass") String pass,
                          @Param("addr") String addr,
                          @Param("birthDate") LocalDate birthDate,
                          @Param("location") String location,
                        @Param("personId") int personId);



//    @Modifying
//    @Transactional
//    @Query(value = "insert into observed_person(cathegory, person_name, " +
//            "person_alias, person_sex, citizenship, " +
//            "passport, address, birth_date," +
//            "person_location, access_lvl) values " +
//            "(:#{#personStatus.toString()},:personName, :personAlias, " +
//            ":#{#personSex.toString()}," +
//            ":citizenship, :pass, :addr, :birthDate, :location, :accessLvl)",
//            nativeQuery = true)
//    void addPerson(@Param("personName") String personName,
//                   @Param("personStatus") PersonStatus personStatus,
//                   @Param("personAlias") String personAlias,
//                   @Param("personSex") Sex personSex,
//                   @Param("citizenship") String citizenship,
//                   @Param("pass") String pass,
//                   @Param("addr") String addr,
//                   @Param("birthDate") LocalDate birthDate,
//                   @Param("location") String location,
//                   @Param("accessLvl") Integer accessLvl);



}
