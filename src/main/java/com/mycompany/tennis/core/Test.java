package com.mycompany.tennis.core;

import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.services.JoueurService;
import com.mycompany.tennis.core.services.MatchService;
import com.mycompany.tennis.core.services.TournoiService;

public class Test {
    public static void main(String[] args) {
        JoueurService joueurService = new JoueurService();
        TournoiRepositoryImpl tournoiRepositoryImpl = new TournoiRepositoryImpl();
        TournoiService tournoiService = new TournoiService();
        MatchService matchService = new MatchService();
        //Recherche d'un joueur
//         Joueur bartoli = joueurDAOImpl.getById(21L);
//        System.out.println(bartoli.getPrenom() + " " + bartoli.getNom() + " " + bartoli.getSexe());
//        Joueur joueur = joueurService.getJoueur(55L);
//        System.out.println("Le joueur numéro " + joueur.getIdJ() + " est " + joueur.getPrenom() + " " + joueur.getNom() + " " + joueur.getSexe());
//
//        Tournoi tournoi = tournoiService.getTournoi(1L);
//        System.out.println("Le tournoi numéro " + tournoi.getIdTournoi() + " est " + tournoi.getNomTournoi());
        //Création d'un joueur
//        Joueur noah = new Joueur();
//        noah.setPrenom("Yannick");
//        noah.setNom("Noah");
//        noah.setSexe('H');
//        joueurService.createJoueur(noah);
//
//        System.out.println("L'identifiant du joueur créé est " + noah.getIdJ());

        //Création d'un tournoi
//        Tournoi openFFL = new Tournoi();
//        openFFL.setNomTournoi("Open FFL");
//        openFFL.setCodeTournoi("OF");
//        tournoiService.createTournoi(openFFL);
//
//        System.out.println("Le tournoi créé est " + openFFL.getNomTournoi());

        //Création d'un match
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);
        match.setScore(score);
        score.setMatch(match);
        Joueur federer = new Joueur();
        federer.setIdJ(32L);
        Joueur murray = new Joueur();
        murray.setIdJ(34L);
        match.setVainqueur(federer);
        match.setFinaliste(murray);
        Epreuve epreuve = new Epreuve();
        epreuve.setIdEpreuve(10L);
        match.setEpreuve(epreuve);
        matchService.enregistrerNouveauMatch(match);

        System.out.println("L'identifiant du match créé est " + match.getIdMatch());
        //Modif d'un joueur
//        Joueur noah = joueurDAOImpl.getById(53L);
//        noah.setPrenom("Yannick");
//        joueurDAOImpl.update(noah);

        //Suppression d'un joueur
//        joueurDAOImpl.delete(53L);

        //Affichage de la liste des joueurs
//        List<Tournoi> tournoiList = tournoiDAOImpl.getAll();
//        for (Tournoi tournoi : tournoiList) {
//            System.out.println(tournoi.getNomTournoi() + " " + tournoi.getCodeTournoi());
//        }
    }

}
