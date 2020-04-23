package com.akman.springbootdemo.repository;

import com.akman.springbootdemo.model.footballer.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {

    List<Footballer> findByFootballClub_ClubName(String clubName);

    @Query("select f from Footballer f where concat(lower(f.firstName),' ',lower(f.lastName)) like concat('%', lower(:name), '%') order by f.firstName, f.lastName")
    List<Footballer> findByFirstNameAndLastName(String name);

    List<Footballer> findAllByFootballClub_IdInAndContractBeginDateLessThanEqualAndContractEndDateGreaterThanEqual
            (List<Long> footballClubIdList, LocalDate beginYear, LocalDate endYearEndOfYear);

    List<Footballer> removeAllByFootballClub_ClubName(String clubName);

    List<Footballer> removeByFirstNameAndLastName(String firstName, String lastName);
}
