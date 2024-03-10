package com.example.jsf_projet.service;
import com.example.jsf_projet.DAO.UserDaoImp;
import com.example.jsf_projet.DAO.UserDao;
import com.example.jsf_projet.DB.DaoFactory;
import com.example.jsf_projet.model.User;
import java.io.*;
import java.util.List;

public class UserService {
    private  UserDao userDao;
    public UserService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.getUserDao();
    }

    public String addUser(User user) {
        try {
            boolean emailExist = userDao.existEmail(user);
            if (!emailExist) {
                boolean added = userDao.ajouter(user);
                if (added) {
                    return user.getNom() + " is added";
                } else {
                    return user.getNom() + " is not added";
                }
            } else {
                return user.getEmail() + " is already exist";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error adding user";
        }
    }

    public List<User> selectUser(){
        return userDao.lister();
    }


    public boolean editUser(User user) {
        try {
            // Code de mise à jour dans la base de données
            return userDao.edit(user);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public String deleteUser(String email) {
        boolean isDeleted = userDao.delete(email);

        return isDeleted ? email + " is deleted" : email + " is not deleted";
    }

}
