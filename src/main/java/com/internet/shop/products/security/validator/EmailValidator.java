package com.internet.shop.products.security.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.internet.shop.products.security.AppConstants.EMAIL_PATTERN;
import static java.lang.String.valueOf;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object email) throws ValidatorException {

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(valueOf(email));

        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Email validation failed.", "Invalid Email format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}