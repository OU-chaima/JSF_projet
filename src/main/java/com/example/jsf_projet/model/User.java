package com.example.jsf_projet.model;

import java.util.Date;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String ville;
    private Date date_naissance;
    private String eitNom;
    private boolean editing;
    public User(){}
    public User(int id,String nom, String prenom, String email, String ville, Date date_naissance) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.ville = ville;
        this.date_naissance = date_naissance;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }
}
