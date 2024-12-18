package com.Happy.runner;

import org.springframework.boot.CommandLineRunner;

import com.Happy.entity.EventsEntity;

public class EventsDataRunner implements CommandLineRunner {
	
	@Override
	public void run(String... Shakthi) throws Exception {
		// TODO Auto-generated method stub
		EventsEntity e = new EventsEntity();
		e.setEventAmount(10000);
		e.setEventDetails("The Details is about the Events......");
		
	}

}
