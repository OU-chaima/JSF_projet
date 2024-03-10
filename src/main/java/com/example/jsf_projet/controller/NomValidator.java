package com.example.jsf_projet.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("nomValidator")
public class NomValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = (String) value;

        if (name.matches(".*\\d.*")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Name", "Name should not contain digits");
            throw new ValidatorException(message);
        }
    }
}

