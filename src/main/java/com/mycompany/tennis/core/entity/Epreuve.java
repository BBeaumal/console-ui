package com.mycompany.tennis.core.entity;

public class Epreuve {

    private Long idEpreuve;
    private Short annee;
    private Tournoi tournoi;
    private Character typeEpreuve;

    public Epreuve() {
    }

    public Epreuve(Long idEpreuve, Short annee, Tournoi tournoi, Character typeEpreuve) {
        this.idEpreuve = idEpreuve;
        this.annee = annee;
        this.tournoi = tournoi;
        this.typeEpreuve = typeEpreuve;
    }

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

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "idEpreuve=" + idEpreuve +
                ", annee=" + annee +
                ", tournoi=" + tournoi +
                ", typeEpreuve=" + typeEpreuve +
                '}';
    }
}
