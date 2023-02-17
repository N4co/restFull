package br.com.felix.projeto.mapper.custom;

import br.com.felix.projeto.data.v1.BookVo;
import br.com.felix.projeto.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookVo convertEntityToVo(Book book) {
        BookVo vo = new BookVo();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice((book.getPrice()));
        vo.setTitle((book.getTitle()));
        return vo;
    }
    public Book convertVoToEntity(BookVo book) {
        Book entity = new Book();
        entity.setId(book.getKey());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice((book.getPrice()));
        entity.setTitle(book.getTitle());
        return entity;
    }
}

