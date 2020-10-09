package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.entity.Tournoi;

public class TournoiService {
    private final TournoiRepositoryImpl tournoiRepositoryImpl;

    public TournoiService() {
        this.tournoiRepositoryImpl = new TournoiRepositoryImpl();
    }

    public void createTournoi(Tournoi tournoi) {
        tournoiRepositoryImpl.create(tournoi);
    }

    public Tournoi getTournoi(Long idT) {
        return tournoiRepositoryImpl.getById(idT);
    }
}
