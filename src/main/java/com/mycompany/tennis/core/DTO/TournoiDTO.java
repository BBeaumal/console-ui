package com.mycompany.tennis.core.DTO;

public class TournoiDTO {

    private Long idTournoi;
    private String nomTournoi;
    private String codeTournoi;


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
}
