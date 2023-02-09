package br.com.felix.projeto.junit;

import br.com.felix.projeto.Exception.RequiredObjectIsNullException;
import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.model.Person;
import br.com.felix.projeto.repositories.PersonRepositories;
import br.com.felix.projeto.services.PersonService;
import br.com.felix.projeto.unitests.mock.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class JunitTests {


    MockPerson input;

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepositories repositories;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testFindById() {
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repositories.findById(1L)).thenReturn(Optional.of(entity));
        var result = personService.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Male", result.getGender());



    }

    @Test
    void test() {
        fail ("not implemented");
    }
    @Test
    void testCreate() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1l);

        PersonVo vo = input.mockVO(1);
        vo.setKey(1l);


        when(repositories.save(entity)).thenReturn(persisted);
        var result = personService.created(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());


    }
    @Test
    void testCreateWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            personService.created(null);
        });
        String expectedMessage ="Não é permitido persistir um objeto null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

        @Test
    void testUpdate() {

        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1l);

        PersonVo vo = input.mockVO(1);
        vo.setKey(1l);


        when(repositories.findById(1L)).thenReturn(Optional.of(entity));
        when(repositories.save(entity)).thenReturn(persisted);
        var result = personService.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }



    @Test
    void testDelete() {
        Person entity = input.mockEntity();
        entity.setId(1l);

        when(repositories.findById(1l)).thenReturn(Optional.of(entity));

        personService.delete(1L);
    }


}
