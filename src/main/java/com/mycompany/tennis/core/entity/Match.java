package com.mycompany.tennis.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MATCH_TENNIS")
public class Match {

    private Long idMatch;
    private Joueur vainqueur;
    private Joueur finaliste;
    private Epreuve epreuve;
    private Score score;

    public Match() {
    }

    public Match(Long idMatch, Joueur vainqueur, Joueur finaliste, Epreuve epreuve) {
        this.idMatch = idMatch;
        this.vainqueur = vainqueur;
        this.finaliste = finaliste;
        this.epreuve = epreuve;
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", vainqueur=" + vainqueur +
                ", finaliste=" + finaliste +
                ", epreuve=" + epreuve +
                ", score=" + score +
                '}';
    }
}
