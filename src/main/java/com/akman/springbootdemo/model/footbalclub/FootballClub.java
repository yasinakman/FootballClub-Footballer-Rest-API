package com.akman.springbootdemo.model.footbalclub;

import com.akman.springbootdemo.model.footballer.Footballer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "FOOTBALL_CLUB")
public class FootballClub {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "club_name")
    private String clubName;

    @Column(name = "club_manager")
    private String clubManager;

    @Column(name = "Currency")
    private String currency;

    @OneToMany(mappedBy = "footballClub", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Footballer> footballers;
}
