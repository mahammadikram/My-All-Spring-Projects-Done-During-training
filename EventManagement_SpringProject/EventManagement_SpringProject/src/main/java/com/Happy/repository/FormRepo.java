package com.Happy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Happy.entity.Form;

import jakarta.transaction.Transactional;

@Transactional
public interface FormRepo extends JpaRepository<Form, Integer> {
    @Modifying
    @Query("SELECT f FROM Form f WHERE f.email = :uemail")
    List<Form> findEventByEmail(String uemail);

    @Modifying
    @Query("DELETE FROM Form WHERE id =:id")
    void cancelEventById(Integer id);
}
