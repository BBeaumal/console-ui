package com.mycompany.tennis.core.DAO;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDAO {
    public void createMatchScore(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            // Modification d'une donnée dans le tableau
            String sql = "INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR, ID_FINALISTE) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, match.getEpreuve().getIdEpreuve());
            preparedStatement.setLong(2, match.getVainqueur().getIdJ());
            preparedStatement.setLong(3, match.getFinaliste().getIdJ());

            preparedStatement.executeUpdate();

            //Récupère les infos autogénérées
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                match.setIdMatch(rs.getLong(1));
            }

            System.out.println("Match créé avec succès");

// Modification d'une donnée dans le tableau
            String sql2 = "INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setLong(1, match.getScore().getMatch().getIdMatch());
            preparedStatement2.setByte(2, match.getScore().getSet1());
            preparedStatement2.setByte(3, match.getScore().getSet2());
            preparedStatement2.setByte(4, match.getScore().getSet3());
            if (match.getScore().getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, match.getScore().getSet4());
            }
            if (match.getScore().getSet5() == null) {
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, match.getScore().getSet5());
            }

            preparedStatement.executeUpdate();

            //Récupère les infos autogénérées
            ResultSet rs2 = preparedStatement.getGeneratedKeys();
            if (rs2.next()) {
                match.getScore().setIdScore(rs2.getLong(1));
            }

            System.out.println("Score créé avec succès");

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
