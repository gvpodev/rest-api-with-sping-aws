package udemy.apiawsmysql.service;

import org.springframework.stereotype.Service;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.exception.ResourceNotFoundException;
import udemy.apiawsmysql.mapper.DozerMapper;
import udemy.apiawsmysql.model.Person;
import udemy.apiawsmysql.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonVO createPerson(PersonVO person) {

        return DozerMapper.parseObject(this.repository.save(DozerMapper.parseObject(person, Person.class)), PersonVO.class);
    }

    public List<PersonVO> findAllPerson() {
        return DozerMapper.parseListObjects(this.repository.findAll(), PersonVO.class);
    }

    public PersonVO findPersonById(Long id) throws ResourceNotFoundException {
        var entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find person with id " + id));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO updatePerson(PersonVO person) throws ResourceNotFoundException {
        var entity =  this.repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + person.getId()));

        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        return DozerMapper.parseObject(this.repository.save(DozerMapper.parseObject(entity, Person.class)), PersonVO.class);
    }

    public void deletePerson(Long id) throws ResourceNotFoundException {
        var entity =  this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + id));

        this.repository.delete(entity);
    }
}
