package com.example.jsf_projet.DB;

import com.example.jsf_projet.DAO.UserDao;
import com.example.jsf_projet.DAO.UserDaoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    // Chargement du pilote JDBC dans un bloc statique
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement du pilote JDBC", e);
        }
    }

    public DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/devoir_db", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Assurez-vous de fermer la connexion après utilisation
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer l'exception ou la journaliser
            }
        }
    }

    public UserDao getUserDao() {
        return new UserDaoImp(this);
    }
}
