package com.mycompany.tennis.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "SCORE_VAINQUEUR")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idScore;
    @Column(name = "SET_1")
    private Byte set1;
    @Column(name = "SET_2")
    private Byte set2;
    @Column(name = "SET_3")
    private Byte set3;
    @Column(name = "SET_4")
    private Byte set4;
    @Column(name = "SET_5")
    private Byte set5;
    @Transient
    private Match match;

    public Score() {
    }

    public Score(Long idScore, Byte set1, Byte set2, Byte set3, Byte set4, Byte set5) {
        this.idScore = idScore;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.set5 = set5;
    }

    public Long getIdScore() {
        return idScore;
    }

    public void setIdScore(Long idScore) {
        this.idScore = idScore;
    }

    public Byte getSet1() {
        return set1;
    }

    public void setSet1(Byte set1) {
        this.set1 = set1;
    }

    public Byte getSet2() {
        return set2;
    }

    public void setSet2(Byte set2) {
        this.set2 = set2;
    }

    public Byte getSet3() {
        return set3;
    }

    public void setSet3(Byte set3) {
        this.set3 = set3;
    }

    public Byte getSet4() {
        return set4;
    }

    public void setSet4(Byte set4) {
        this.set4 = set4;
    }

    public Byte getSet5() {
        return set5;
    }

    public void setSet5(Byte set5) {
        this.set5 = set5;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Score{" +
                "idScore=" + idScore +
                ", set1=" + set1 +
                ", set2=" + set2 +
                ", set3=" + set3 +
                ", set4=" + set4 +
                ", set5=" + set5 +
                ", match=" + match +
                '}';
    }
}
