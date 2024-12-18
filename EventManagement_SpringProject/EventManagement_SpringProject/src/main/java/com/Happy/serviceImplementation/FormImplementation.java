package com.Happy.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Happy.entity.Form;
import com.Happy.repository.FormRepo;
import com.Happy.serviceInterface.FormService;

@Service
public class FormImplementation implements FormService {
    @Autowired
    FormRepo repo;

    @Override
    public int save(Form form) {
        return repo.save(form).getId();
    }

   

}
