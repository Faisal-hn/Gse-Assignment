package com.gse.assignment.validator;

import com.gse.assignment.Entry.ContactEntry;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    private static final String ERROR_MESSAGE = "Either Email or Phone number is undefined, please check.";
    private static final String EMPTY_EMAIL_MESSAGE = "Email is empty, please check.";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


    public void validateInput(ContactEntry contactEntry) {
        validateEmail(contactEntry.getEmail());
        validatePhone(contactEntry.getPhone());
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_EMAIL_MESSAGE);
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private void validatePhone(Long phone) {
        if (phone == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}
