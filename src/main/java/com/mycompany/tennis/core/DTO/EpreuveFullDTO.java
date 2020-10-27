package com.mycompany.tennis.core.DTO;

import java.util.Set;

public class EpreuveFullDTO {
    private Long idEpreuve;
    private Short annee;
    private TournoiDTO tournoi;
    private Character typeEpreuve;
    private Set<JoueurDTO> participants;

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

    public Set<JoueurDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<JoueurDTO> participants) {
        this.participants = participants;
    }
}
