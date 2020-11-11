package com.mycompany.tennis.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "MATCH_TENNIS")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idMatch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VAINQUEUR")
    private Joueur vainqueur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FINALISTE")
    private Joueur finaliste;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EPREUVE")
    private Epreuve epreuve;
    @OneToOne(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
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
