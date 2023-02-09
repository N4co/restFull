package br.com.felix.projeto.services;

import br.com.felix.projeto.Exception.RequiredObjectIsNullException;
import br.com.felix.projeto.Exception.ResourceNotFounExceptionHandler;
import br.com.felix.projeto.controller.PersonController;
import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.data.v2.PersonVo2;
import br.com.felix.projeto.mapper.DozerConverter;
import br.com.felix.projeto.mapper.custom.PersonMapper;
import br.com.felix.projeto.model.Person;
import br.com.felix.projeto.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final Logger log = Logger.getLogger(PersonService.class.getName());
    @Autowired
    private PersonRepositories repositories;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonVo> findAll() {

        log.info("search or people");

        var persons = DozerConverter.parseListObject(repositories.findAll(), PersonVo.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
       return persons;
    }
    public PersonVo findById(Long id) {

        log.info("search or person");

       var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));
        var vo = DozerConverter.parseObject(entity, PersonVo.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;

    }

    public PersonVo created(PersonVo person) {

        if (person == null ) throw new RequiredObjectIsNullException();
        log.info("created or person");
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repositories.save(entity ), PersonVo.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
   public PersonVo2 createdV2(PersonVo2 person) {

        log.info("created or personV2");
        var entity = personMapper.convertVoToEntity(person);
        var vo = personMapper.convertEntityToVo(repositories.save(entity));
  return vo;
    }

    public PersonVo update(PersonVo person) {

        log.info("updating or person");
        var entity = repositories.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repositories.save(entity), PersonVo.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void  delete (Long id ) {

        log.info("delete or person");

        var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        repositories.delete(entity);

    }
}

