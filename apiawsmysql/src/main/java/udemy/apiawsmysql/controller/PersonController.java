package udemy.apiawsmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.data.vo.v2.PersonVOV2;
import udemy.apiawsmysql.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO createPerson(@RequestBody PersonVO person) {
        return this.service.createPerson(person);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVOV2 createPersonV2(@RequestBody PersonVOV2 person) {
        return this.service.createPersonV2(person);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> findAllPerson() {
        return this.service.findAllPerson();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO findPersonById(@PathVariable Long id) {
        return this.service.findPersonById(id);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO updatePerson(@RequestBody PersonVO person) {
        return this.service.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        this.service.deletePerson(id);

        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/order-by-gender/{address}")
//    public List<Person> findAllPersonOrderedByGender(@PathVariable String address) {
//        return this.service.findAllPersonByAddressOrderedByGender(address);
//    }

}
