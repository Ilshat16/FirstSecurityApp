package ru.ilshat.firstSecurityApp.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.ilshat.firstSecurityApp.FirstSecurityApp.models.Person;
import ru.ilshat.firstSecurityApp.FirstSecurityApp.repositories.PeopleRepository;

@Service
public class RegistrationService {
	
	private final PeopleRepository peopleRepository;

	@Autowired
	public RegistrationService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	@Transactional
	public void register(Person person) {
		peopleRepository.save(person);
	}
}