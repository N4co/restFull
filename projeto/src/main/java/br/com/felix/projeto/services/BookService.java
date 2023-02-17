package br.com.felix.projeto.services;

import br.com.felix.projeto.Exception.RequiredObjectIsNullException;
import br.com.felix.projeto.Exception.ResourceNotFounExceptionHandler;
import br.com.felix.projeto.controller.BookController;
import br.com.felix.projeto.data.v1.BookVo;
import br.com.felix.projeto.mapper.DozerConverter;
import br.com.felix.projeto.mapper.custom.BookMapper;
import br.com.felix.projeto.model.Book;
import br.com.felix.projeto.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    private final Logger log = Logger.getLogger(BookService.class.getName());
    @Autowired
    private BookRepositories repositories;
    @Autowired
    private BookMapper BookMapper;

    public List<BookVo> findAll() {

        log.info("search or people");

        var Books = DozerConverter.parseListObject(repositories.findAll(), BookVo.class);
        Books.stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
       return Books;
    }
    public BookVo findById(Long id) {

        log.info("search or Book ");

       var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));
        var vo = DozerConverter.parseObject(entity, BookVo.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;

    }

    public BookVo created(BookVo Book) {
        if (Book == null ) throw new RequiredObjectIsNullException();
        log.info("created or Book ");
        var entity = DozerConverter.parseObject(Book, Book.class);
        var vo = DozerConverter.parseObject(repositories.save(entity ), BookVo.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public BookVo update(BookVo Book) {
        log.info("updating or Book ");
        var entity = repositories.findById(Book.getKey())
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        Book.setAuthor(Book.getAuthor());
        Book.setLaunchDate(Book.getLaunchDate());
        Book.setTitle(Book.getTitle());
        Book.setPrice(Book.getPrice());

        var vo = DozerConverter.parseObject(repositories.save(entity), BookVo.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void  delete (Long id ) {

        log.info("delete or Book ");

        var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        repositories.delete(entity);

    }
}

