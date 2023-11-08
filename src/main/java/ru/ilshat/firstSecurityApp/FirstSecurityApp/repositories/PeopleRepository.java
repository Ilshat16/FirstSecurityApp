package ru.ilshat.firstSecurityApp.FirstSecurityApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ilshat.firstSecurityApp.FirstSecurityApp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{
	Optional<Person> findByUsername(String username);
}
