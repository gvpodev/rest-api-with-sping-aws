package udemy.apiawsmysql.service;

import org.springframework.stereotype.Service;
import udemy.apiawsmysql.exception.ResourceNotFoundException;
import udemy.apiawsmysql.model.Person;
import udemy.apiawsmysql.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person createPerson(Person person) {
        return this.repository.save(person);
    }

    public List<Person> findAllPerson() {
        return this.repository.findAll();
    }

    public Person findPersonById(Long id) throws ResourceNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find person with id " + id));
    }

    public Person updatePerson(Person person) throws ResourceNotFoundException {
        var entity =  this.repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + person.getId()));

        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return this.repository.save(entity);
    }

    public void deletePerson(Long id) throws ResourceNotFoundException {
        var entity =  this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + id));

        this.repository.delete(entity);
    }

    public List<Person> findAllPersonByAddressOrderedByGender(String address) {
        return this.repository.findAllByAddressOrderByGender(address);
    }
}
