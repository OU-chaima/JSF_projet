package com.example.jsf_projet.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("prenomValidator")
public class PrenomValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String prenom = (String) value;

        // Add your validation logic here
        // For example, check if the prenom is valid
        if (prenom.length() < 2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Prenom", "Prenom should have at least 2 characters");
            throw new ValidatorException(message);
        }
    }
}

