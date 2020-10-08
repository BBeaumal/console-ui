package com.mycompany.tennis.core.entity;

public class Tournoi {

    private Long idTournoi;
    private String nomTournoi;
    private String codeTournoi;

    public Tournoi() {
    }

    public Tournoi(Long idTournoi, String nomTournoi, String codeTournoi) {
        this.idTournoi = idTournoi;
        this.nomTournoi = nomTournoi;
        this.codeTournoi = codeTournoi;
    }

    public Long getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(Long idTournoi) {
        this.idTournoi = idTournoi;
    }

    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public String getCodeTournoi() {
        return codeTournoi;
    }

    public void setCodeTournoi(String codeTournoi) {
        this.codeTournoi = codeTournoi;
    }

    @Override
    public String toString() {
        return "Tournoi{" +
                "idTournoi=" + idTournoi +
                ", nomTournoi='" + nomTournoi + '\'' +
                ", codeTournoi='" + codeTournoi + '\'' +
                '}';
    }
}
