package com.example.jsf_projet.DAO;

import com.example.jsf_projet.model.User;

import java.util.List;

public interface UserDao {
boolean ajouter(User user);
List<User>lister();
boolean delete( String email);
boolean existEmail(User user);
boolean edit(User user);
}
