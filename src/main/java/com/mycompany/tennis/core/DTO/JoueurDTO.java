package com.mycompany.tennis.core.DTO;

import javax.persistence.*;

@Entity
public class JoueurDTO {

    private Long idJ;
    private String nom;
    private String prenom;
    private Character sexe;


    //Constructeurs
    public JoueurDTO() {
    }

    public JoueurDTO(Long idJ, String nom, String prenom, Character sexe) {
        this.idJ = idJ;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    //Getters & Setters
    public Long getIdJ() {
        return idJ;
    }

    public void setIdJ(Long idJ) {
        this.idJ = idJ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "idJ=" + idJ +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe=" + sexe +
                '}';
    }
}
