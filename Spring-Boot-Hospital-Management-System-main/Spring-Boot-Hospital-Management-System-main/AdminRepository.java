package com.HospitalManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagement.Entity.Contact;

public interface AdminRepository extends JpaRepository<Contact, Integer> {

}
