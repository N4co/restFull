package br.com.felix.projeto.converter;

import br.com.felix.projeto.data.v1.BookVo;
import br.com.felix.projeto.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVo mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVo> mockVOList() {
        List<BookVo> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    private Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(book.getId());
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date());
        book.setTitle("Some Title" + number);
        book.setPrice(25D);

        return book;
    }

    private BookVo mockVO(Integer number) {
        BookVo book = new BookVo();
        book.setKey(book.getKey());
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date());
        book.setTitle("Some Title" + number);
        book.setPrice(25D);
        return book;
    }


    }
