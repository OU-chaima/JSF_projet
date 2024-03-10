package com.example.jsf_projet.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("villeValidator")
public class VilleValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String ville = (String) value;

        // Add your validation logic here
        // For example, check if the ville is valid
        if (ville.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Ville", "Ville cannot be empty");
            throw new ValidatorException(message);
        }
    }
}

