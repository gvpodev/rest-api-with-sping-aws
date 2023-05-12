package udemy.apiawsmysql.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.mapper.PersonMapper;
import udemy.apiawsmysql.mocks.MockPerson;
import udemy.apiawsmysql.model.Person;
import udemy.apiawsmysql.repository.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper mapper;

    private PersonService service;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

        service = new PersonService(repository, mapper);
    }

    @Test
    void findAllPerson() {
    }

    @Test
    void findPersonById() {
        var id = 1L;
        Person entity = input.mockEntity();
        entity.setId(id);

        doReturn(Optional.of(entity)).when(repository).findById(id);

        var result = service.findPersonById(id);

        assertNotNull(result);
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
    }

    @Test
    void createPerson() {
        var id = 1L;
        Person entity = input.mockEntity(1);
        entity.setId(id);
        PersonVO vo = input.mockVO(1);
        vo.setKey(id);
        doReturn(entity).when(repository).save(any(Person.class));

        var result = service.createPerson(vo);

        assertNotNull(result);
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
    }

    @Test
    void updatePerson() {
        var id = 1L;
        Person entity = input.mockEntity(1);
        entity.setId(id);
        PersonVO vo = input.mockVO(1);
        vo.setKey(id);
        doReturn(Optional.of(entity)).when(repository).findById(id);
        doReturn(entity).when(repository).save(any(Person.class));

        var result = service.updatePerson(vo);

        assertNotNull(result);
        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
    }

    @Test
    void deletePerson() {
        Person entity = input.mockEntity(1);
        doReturn(Optional.of(entity)).when(repository).findById(1L);

        service.deletePerson(1L);
    }
}