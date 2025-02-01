package com.gse.assignment.validator;

import com.gse.assignment.Entry.ContactEntry;
import com.gse.assignment.exception.InvalidContactFormatException;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    private static final String ERROR_EMAIL_REQUIRED = "Email cannot be empty or null.";
    private static final String ERROR_INVALID_EMAIL = "Invalid email format.";
    private static final String ERROR_MESSAGE = "Phone cannot be null.";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


    public void validateInput(ContactEntry contactEntry) {
        validateEmail(contactEntry.getEmail());
        validatePhone(contactEntry.getPhone());
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new InvalidContactFormatException(ERROR_EMAIL_REQUIRED);
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidContactFormatException(ERROR_INVALID_EMAIL);
        }
    }

    private void validatePhone(Long phone) {
        if (phone == null) {
            throw new InvalidContactFormatException(ERROR_MESSAGE);
        }
    }
}
