package com.Happy.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Happy.entity.EventsEntity;
import com.Happy.entity.Form;
import com.Happy.repository.EventsRepo;
import com.Happy.repository.FormRepo;
import com.Happy.serviceInterface.EventServiceInterface;

@Service
public class EventServiceImplementation implements EventServiceInterface {

    @Autowired
    private EventsRepo repo;  

    @Override
    public EventsEntity loadEventData(String eventCategory) {
        Optional<EventsEntity> data = repo.findByEventCategory(eventCategory);
        if (data.isPresent()) {
            return data.get();
        } else {
            return null;
        }
    }
@Autowired
private FormRepo fRepo;
    @Override
    public List<Form> getUserEvents(String email) {
       List <Form> list =fRepo.findEventByEmail(email);
        
        return list;
    }
    @Override
    public void deleteUserBooking(Integer id) {
        
        fRepo.cancelEventById(id);
    }

   
    
}
