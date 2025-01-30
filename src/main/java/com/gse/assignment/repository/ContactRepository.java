package com.gse.assignment.repository;

import com.gse.assignment.domain.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
//    find by email if return in asc order of id
    @Query("SELECT c FROM Contact c WHERE c.email = ?1 ORDER BY c.id ASC")
    List<Contact> findByEmail(String email);

    @Query("SELECT c FROM Contact c WHERE c.phone = ?1 ORDER BY c.id ASC")
    List<Contact> findByPhone(Long phone);

    List<Contact> findByPrimaryId(Long primaryContact);
}
