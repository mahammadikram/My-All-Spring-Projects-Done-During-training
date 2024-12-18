package com.Happy.serviceInterface;



import java.util.List;

import com.Happy.entity.EventsEntity;
import com.Happy.entity.Form;

public interface EventServiceInterface {

   // public String loadEvent(String eventCategory);
   public EventsEntity loadEventData(String eventCategory);

public List<Form> getUserEvents(String email);

public void deleteUserBooking(Integer id);

}
