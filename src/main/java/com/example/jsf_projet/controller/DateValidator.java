package com.example.jsf_projet.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesValidator("dateValidator")
public class DateValidator implements Validator {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String dateStr = (String) value;

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(dateStr);
        } catch (ParseException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Format de date incorrect. Utilisez le format dd/MM/yyyy.", null);
            throw new ValidatorException(message);
        }
    }
}
