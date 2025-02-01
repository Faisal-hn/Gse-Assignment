package com.gse.assignment.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gse.assignment.entry.ContactEntry;
import com.gse.assignment.exception.InvalidContactFormatException;

import java.io.IOException;

public class ContactEntryDeserializer extends JsonDeserializer<ContactEntry> {

    @Override
    public ContactEntry deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        ContactEntry contactEntry = new ContactEntry();
        contactEntry.setEmail(node.get("email").asText());

        JsonNode phoneNode = node.get("phone");
//        phone number must be numeric
        if (phoneNode.isNumber()) {
            contactEntry.setPhone(phoneNode.asLong());
        } else {
            throw new InvalidContactFormatException("Phone number must be numeric.");
        }

        return contactEntry;
    }
}
