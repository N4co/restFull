package br.com.felix.projeto.services;

import br.com.felix.projeto.Exception.ResourceNotFounExceptionHandler;
import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.data.v2.PersonVo2;
import br.com.felix.projeto.mapper.DozzerMapper;
import br.com.felix.projeto.mapper.custom.PersonMapper;
import br.com.felix.projeto.model.Person;
import br.com.felix.projeto.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger log = Logger.getLogger(PersonService.class.getName());
    @Autowired
    private PersonRepositories repositories;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonVo> findAll() {

        log.info("search or people");

        return DozzerMapper.parseListObject(repositories.findAll(), PersonVo.class);
    }
    public PersonVo findById(Long id) {

        log.info("search or person");

       var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));
        return DozzerMapper.parseObject(entity, PersonVo.class);
    }

    public PersonVo created(PersonVo person) {

        log.info("created or person");
        var entity = DozzerMapper.parseObject(person, Person.class);
        var vo = DozzerMapper.parseObject(repositories.save(entity ), PersonVo.class);
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
        var entity = repositories.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());

        var vo = DozzerMapper.parseObject(repositories.save(entity), PersonVo.class);
        return vo;
    }

    public void  delete (Long id ) {

        log.info("delete or person");

        var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        repositories.delete(entity);

    }
}

