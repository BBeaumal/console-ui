package com.mycompany.tennis.core;

import com.mycompany.tennis.core.DAO.JoueurDAOImpl;
import com.mycompany.tennis.core.entity.Joueur;

public class Test {
    public static void main(String[] args) {
        JoueurDAOImpl joueurDAOImpl = new JoueurDAOImpl();
        //Recherche d'un joueur
        // Joueur bartoli = joueurDAOImpl.getById(21L);
        //System.out.println(bartoli.getPrenom() + " " + bartoli.getNom() + " " + bartoli.getSexe());

        //Création d'un joueur
        Joueur noah = new Joueur();
        noah.setPrenom("Yannick");
        noah.setNom("Noah");
        noah.setSexe('H');
        joueurDAOImpl.create(noah);

        System.out.println("L'identifiant du joueur créé est " + noah.getIdJ());

        //Modif d'un joueur
//        Joueur noah = joueurDAOImpl.getById(53L);
//        noah.setPrenom("Yannick");
//        joueurDAOImpl.update(noah);

        //Suppression d'un joueur
//        joueurDAOImpl.delete(53L);

        //Affichage de la liste des joueurs
//        List<Joueur> joueurList = joueurDAOImpl.getAll();
//        for (Joueur joueur : joueurList) {
//            System.out.println(joueur.getPrenom() + " " + joueur.getNom() + " " + joueur.getSexe());
//        }
    }

}
