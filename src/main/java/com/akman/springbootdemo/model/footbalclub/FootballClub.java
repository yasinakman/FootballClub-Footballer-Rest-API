package com.akman.springbootdemo.model.footbalclub;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footballer.Footballer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "footballClub", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Footballer> footballers;
}
