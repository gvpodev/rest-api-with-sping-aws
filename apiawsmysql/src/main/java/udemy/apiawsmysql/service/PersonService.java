package udemy.apiawsmysql.service;

import org.springframework.stereotype.Service;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.exception.ResourceNotFoundException;
import udemy.apiawsmysql.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonVO createPerson(PersonVO person) {
        return new PersonVO();

//        return this.repository.save(person);
    }

    public List<PersonVO> findAllPerson() {
        return new ArrayList<>();

//    return this.repository.findAll();
    }

    public PersonVO findPersonById(Long id) throws ResourceNotFoundException {
        return new PersonVO();
//        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find person with id " + id));
    }

    public PersonVO updatePerson(PersonVO person) throws ResourceNotFoundException {
//        var entity =  this.repository.findById(person.getId()).orElseThrow(
//                () -> new ResourceNotFoundException("Unable to find person with id " + person.getId()));
//
//        entity.setAddress(person.getAddress());
//        entity.setGender(person.getGender());
//        entity.setFirstName(person.getFirstName());
//        entity.setLastName(person.getLastName());
//
//        return this.repository.save(entity);

        return new PersonVO();
    }

    public void deletePerson(Long id) throws ResourceNotFoundException {
        var entity =  this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + id));

        this.repository.delete(entity);
    }

//    public List<PersonVO> findAllPersonByAddressOrderedByGender(String address) {
//        return this.repository.findAllByAddressOrderByGender(address);
//    }
}
