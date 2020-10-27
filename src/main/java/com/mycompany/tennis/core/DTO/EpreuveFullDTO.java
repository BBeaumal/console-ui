package com.mycompany.tennis.core.DTO;

public class EpreuveFullDTO {
    private Long idEpreuve;
    private Short annee;
    private TournoiDTO tournoi;
    private Character typeEpreuve;


    public Long getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(Long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public TournoiDTO getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDTO tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
