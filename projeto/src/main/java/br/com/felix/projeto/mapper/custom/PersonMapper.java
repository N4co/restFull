package br.com.felix.projeto.mapper.custom;

import br.com.felix.projeto.data.v2.PersonVo2;
import br.com.felix.projeto.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVo2 convertEntityToVo(Person person) {
        PersonVo2 vo = new PersonVo2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress((person.getAddress()));
        vo.setGender(person.getGender());
        vo.setHappyBirthay(new Date());
        return vo;
    }
    public Person convertVoToEntity(PersonVo2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress((person.getAddress()));
        entity.setGender(person.getGender());
        return entity;
    }
}

