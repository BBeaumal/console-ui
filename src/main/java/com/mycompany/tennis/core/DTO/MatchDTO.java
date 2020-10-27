package com.mycompany.tennis.core.DTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MATCH_TENNIS")
public class MatchDTO {


    private Long idMatch;
    private JoueurDTO vainqueur;
    private JoueurDTO finaliste;
    private EpreuveFullDTO epreuve;

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public JoueurDTO getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDTO vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDTO getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDTO finaliste) {
        this.finaliste = finaliste;
    }

    public EpreuveFullDTO getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveFullDTO epreuve) {
        this.epreuve = epreuve;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", vainqueur=" + vainqueur +
                ", finaliste=" + finaliste +
                '}';
    }
}
