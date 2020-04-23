package com.akman.springbootdemo.model.footballer;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClub;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString(exclude = {"footballClub"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "FOOTBALLER")
public class Footballer {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Transfer_fee")
    private Double transferFee;

    @Column(name = "Contract_fee")
    private Double contractFee;

    @Column(name = "Currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "Career_start_date")
    private LocalDate careerStartDate;

    @Column(name = "contract_begin_year")
    private LocalDate contractBeginDate;

    @Column(name = "contract_end_year")
    private LocalDate contractEndDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="football_club_id")
    private FootballClub footballClub;
}
