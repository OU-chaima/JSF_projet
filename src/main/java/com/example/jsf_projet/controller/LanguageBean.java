package com.example.jsf_projet.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;


public class LanguageBean {
    private String selectedLanguage;
    private final List<Locale> supportedLanguages;

    public LanguageBean() {
        // Initialisation des langues supportées
        supportedLanguages = new ArrayList<>();
        supportedLanguages.add(new Locale("fr"));
        supportedLanguages.add(new Locale("en"));
        // Langue par défaut
        selectedLanguage = "fr"; // Langue par défaut
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public List<Locale> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void changeLanguage() {
        // Mettre à jour la locale de la vue avec la langue sélectionnée
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(selectedLanguage));
    }
}