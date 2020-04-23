package com.akman.springbootdemo.repository;

import com.akman.springbootdemo.model.footbalclub.FootballClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface FootballClubRepository extends JpaRepository<FootballClub, Long> {
    @Query("select fc from FootballClub fc where lower(fc.clubName) like concat('%', lower(:clubName), '%')")
    List<FootballClub> findAllByClubName(String clubName);

    Set<FootballClub> findAllByFootballers_IdIn(List<Long> footballerListIds);

    List<FootballClub> removeByClubName(String clubName);

    FootballClub findByClubName(String clubName);
}
