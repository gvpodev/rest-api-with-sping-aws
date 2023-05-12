package udemy.apiawsmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import udemy.apiawsmysql.controller.PersonController;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.data.vo.v2.PersonVOV2;
import udemy.apiawsmysql.exception.ResourceNotFoundException;
import udemy.apiawsmysql.mapper.DozerMapper;
import udemy.apiawsmysql.mapper.PersonMapper;
import udemy.apiawsmysql.model.Person;
import udemy.apiawsmysql.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Autowired
    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PersonVO createPerson(PersonVO person) {
        var vo = DozerMapper.parseObject(this.repository.save(DozerMapper.parseObject(person, Person.class)), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        var entity = this.repository.save(mapper.convertVoToEntity(person));

        return mapper.convertEntityToVo(entity);
    }

    public List<PersonVO> findAllPerson() {
        var persons = DozerMapper.parseListObjects(this.repository.findAll(), PersonVO.class);
        persons
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findPersonById(p.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO findPersonById(Long id) throws ResourceNotFoundException {
        var entity = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find person with id " + id));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(id)).withSelfRel());

        return vo;
    }

    public PersonVO updatePerson(PersonVO person) throws ResourceNotFoundException {
        var entity =  this.repository.findById(person.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + person.getKey()));

        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        var vo = DozerMapper.parseObject(this.repository.save(DozerMapper.parseObject(entity, Person.class)), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findPersonById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void deletePerson(Long id) throws ResourceNotFoundException {
        var entity =  this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Unable to find person with id " + id));

        this.repository.delete(entity);
    }
}
