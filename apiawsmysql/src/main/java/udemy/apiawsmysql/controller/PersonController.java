package udemy.apiawsmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.apiawsmysql.model.Person;
import udemy.apiawsmysql.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("v1/person")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return this.service.createPerson(person);
    }

    @GetMapping
    public List<Person> findAllPerson() {
        return this.service.findAllPerson();
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable Long id) {
        return this.service.findPersonById(id);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        return this.service.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        this.service.deletePerson(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order-by-gender/{address}")
    public List<Person> findAllPersonOrderedByGender(@PathVariable String address) {
        return this.service.findAllPersonByAddressOrderedByGender(address);
    }

}
