package com.mycompany.tennis.core.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idEpreuve;
    @Type(type = "short")
    private Short annee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOURNOI")
    private Tournoi tournoi;
    @Column(name = "TYPE_EPREUVE", nullable = true, length = 200)
    private Character typeEpreuve;
    @ManyToMany
    @JoinTable(name = "PARTICIPANTS",
            //Relation avec la classe d'origine
            joinColumns = {@JoinColumn(name = "ID_EPREUVE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_JOUEUR")})
    private Set<Joueur> participants;

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

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
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
