package com.example.jsf_projet.DAO;

import com.example.jsf_projet.DB.DaoFactory;
import com.example.jsf_projet.model.User;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class UserDaoImp implements UserDao{
    private DaoFactory daoFactory;
    public UserDaoImp(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserDaoImp() {

    }

    @Override
    public boolean ajouter(User user) {
        int i=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            java.util.Date utilDate = user.getDate_naissance();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement = connection.prepareStatement("insert into user_(nom,prenom,ville,email,date_naissance) values(?,?,?,?,?)");
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getVille());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5, sqlDate);
             i=preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + i);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while inserting user", e);
        }
       if(i==1)return true;
       return false;
    }
    @Override
    public List<User> lister() {
        List<User> users = new ArrayList<>();
        Connection connexion = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            connexion = daoFactory.getConnection();
            st = connexion.createStatement();
            rs = st.executeQuery("SELECT nom, prenom, ville, email, date_naissance FROM user_");

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String ville = rs.getString("ville");
                String email = rs.getString("email");
                Date date = rs.getDate("date_naissance");

                User user = new User();
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setVille(ville);
                user.setEmail(email);
                user.setDate_naissance(date);

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(connexion, st, rs);
        }

        return users;
    }
    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean edit(User user) {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "update user_ set nom = ?, prenom=?, ville=?, email=?, date_naissance=? where id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getVille());
                ps.setString(4, user.getEmail());
                ps.setDate(5, (java.sql.Date) user.getDate_naissance());
                ps.setInt(6, user.getId());

                int result = ps.executeUpdate();
                if (result == 1) {
                    return true;
                } else {
                    // Log a warning if the update didn't affect exactly one row
                    System.out.println("Unexpected result count: " + result);
                    return false;
                }
            }
        } catch (SQLException e) {
            // Log the exception for detailed information
            e.printStackTrace();

            // Display the error message to the user
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating user", "An error occurred while updating user: " + e.getMessage()));
        }

        return false;
    }

    public boolean delete(String email1) {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "delete from user_ WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email1);


            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existEmail(User user) {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT COUNT(*) FROM user_ WHERE email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
