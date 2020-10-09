package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DAO.TournoiDAOImpl;
import com.mycompany.tennis.core.entity.Tournoi;

public class TournoiService {
    private TournoiDAOImpl tournoiDAOImpl;

    public TournoiService() {
        this.tournoiDAOImpl = new TournoiDAOImpl();
    }

    public void createTournoi(Tournoi tournoi) {
        tournoiDAOImpl.create(tournoi);
    }

    public Tournoi getTournoi(Long idT) {
        Tournoi tournoi = tournoiDAOImpl.getById(idT);
        return tournoi;
    }
}
