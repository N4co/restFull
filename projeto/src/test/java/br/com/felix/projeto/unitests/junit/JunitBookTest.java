package br.com.felix.projeto.unitests.junit;

import br.com.felix.projeto.Exception.RequiredObjectIsNullException;
import br.com.felix.projeto.data.v1.BookVo;
import br.com.felix.projeto.model.Book;
import br.com.felix.projeto.repositories.BookRepositories;
import br.com.felix.projeto.services.BookService;
import br.com.felix.projeto.unitests.mock.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class JunitBookTest {

    @Autowired
    MockBook input;


    @InjectMocks
    private BookService BookService;

    @Mock
    private BookRepositories repositories;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testFindById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repositories.findById(1L)).thenReturn(Optional.of(entity));

        var result = BookService.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Some Author Test1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());



    }


    @Test
    void testCreate() {
        Book entity = input.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1l);

        BookVo vo = input.mockVO(1);
        vo.setKey(1l);


        when(repositories.save(entity)).thenReturn(persisted);
        var result = BookService.created(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author Test1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());

        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());


    }
    @Test
    void testCreateWithNullBook() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            BookService.created(null);
        });
        String expectedMessage ="Não é permitido persistir um objeto null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

        @Test
    void testUpdate() {

        Book entity = input.mockEntity(1);
        Book persisted = entity;
        persisted.setId(1l);

        BookVo vo = input.mockVO(1);
        vo.setKey(1l);


        when(repositories.findById(1L)).thenReturn(Optional.of(entity));
        when(repositories.save(entity)).thenReturn(persisted);
        var result = BookService.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
            assertEquals("Some Author Test1", result.getAuthor());
            assertEquals("Some Title1", result.getTitle());
            assertEquals(25D, result.getPrice());
            assertNotNull(result.getLaunchDate());
    }



    @Test
    void testDelete() {
        Book entity = input.mockEntity();
        entity.setId(1l);

        when(repositories.findById(1l)).thenReturn(Optional.of(entity));

        BookService.delete(1L);
    }


}
