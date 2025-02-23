package com.gse.assignment.service;

import com.gse.assignment.entry.ContactEntry;
import com.gse.assignment.domain.Contact;
import com.gse.assignment.repository.ContactRepository;
import com.gse.assignment.validator.ContactValidator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final ContactValidator contactValidator;

    public ContactService(ContactRepository contactRepository, ContactValidator contactValidator) {
        this.contactRepository = contactRepository;
        this.contactValidator = contactValidator;
    }

    public Map<String, Object> resolveContact(ContactEntry contactEntry) {
        contactValidator.validateInput(contactEntry);

        Contact emailMatch = getByEmail(contactEntry.getEmail());
        Contact phoneMatch = getByPhone(contactEntry.getPhone());
        Contact primaryContact;
        if (emailMatch != null && phoneMatch != null) {
            // Link contacts if different primaries
            primaryContact = consolidateContacts(emailMatch, phoneMatch);
        } else if (emailMatch != null || phoneMatch != null) {
            // Add secondary record
            Contact existingContact = emailMatch != null ? emailMatch : phoneMatch;
            primaryContact = addSecondaryRecord(existingContact, contactEntry);
        } else {
            // Create new primary record
            primaryContact = createNewPrimaryContact(contactEntry);
        }

        return buildResponse(primaryContact);
    }

    private Contact getByEmail(String email) {
        List<Contact> contacts = contactRepository.findByEmail(email);
        return contacts.isEmpty() ? null : contacts.get(0);
    }

    private Contact getByPhone(Long phone) {
        List<Contact> contacts = contactRepository.findByPhone(phone);
        return contacts.isEmpty() ? null : contacts.get(0);
    }

    private Contact consolidateContacts(Contact contact1, Contact contact2) {
        Contact primaryContact1 = getPrimaryContact(contact1);
        Contact primaryContact2 = getPrimaryContact(contact2);
        if (primaryContact1.getId() < primaryContact2.getId()) {
            primaryContact2.setPrimaryId(primaryContact1.getId());
            contactRepository.save(primaryContact2);
            return primaryContact1;
        } else {
            primaryContact1.setPrimaryId(primaryContact2.getId());
            contactRepository.save(primaryContact1);
            return primaryContact2;
        }
    }

    private Contact getPrimaryContact(Contact contact) {
        if (contact.getPrimaryId() == null) {
            return contact;
        }
        Long primaryId = contact.getPrimaryId();
        Optional<Contact> parentContact = contactRepository.findById(primaryId);
        if (parentContact.isPresent()) {
            return getPrimaryContact(parentContact.get());
        }
        return contact;
    }

    private Contact addSecondaryRecord(Contact existingContact, ContactEntry contactEntry) {
        Contact primary = getPrimaryContact(existingContact);
        Contact secondary = new Contact();
        secondary.setEmail(contactEntry.getEmail());
        secondary.setPhone(contactEntry.getPhone());
        secondary.setPrimaryId(primary.getId());
        contactRepository.save(secondary);
        return primary;
    }

    private Contact createNewPrimaryContact(ContactEntry contactEntry) {
        Contact contact = new Contact();
        contact.setEmail(contactEntry.getEmail());
        contact.setPhone(contactEntry.getPhone());
        contact.setPrimaryId(null);
        return contactRepository.save(contact);
    }

    private Map<String, Object> buildResponse(Contact primaryContact) {
        List<Contact> allRelatedContacts = getContactListByBfs(primaryContact);
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        Set<Long> contactIds = new LinkedHashSet<>();
        Set<String> emails = new LinkedHashSet<>();
        Set<Long> phones = new LinkedHashSet<>();
        for (Contact contact : allRelatedContacts) {
            contactIds.add(contact.getId());
            emails.add(contact.getEmail());
            phones.add(contact.getPhone());
        }
        response.put("contactIds", contactIds);
        response.put("emails", emails);
        response.put("phones", phones);
        return response;
    }

    private List<Contact> getContactListByBfs(Contact primaryContact) {
        Queue<Long> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        queue.add(primaryContact.getId());
        visited.add(primaryContact.getId());
        List<Contact> result = new ArrayList<>();
        result.add(primaryContact);
        while (!queue.isEmpty()) {
            Long currentParentId = queue.poll();
            List<Contact> relatedContacts = contactRepository.findByPrimaryId(currentParentId);
            for (Contact contact : relatedContacts) {
                if (!visited.contains(contact.getId())) {
                    queue.add(contact.getId());
                    visited.add(contact.getId());
                    result.add(contact);
                }
            }
        }
        return new ArrayList<>(result);
    }

}

