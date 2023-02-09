package br.com.felix.projeto.controller;

import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.data.v2.PersonVo2;
import br.com.felix.projeto.services.PersonService;
import br.com.felix.projeto.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController  {

    @Autowired
    private PersonService service;

    @RequestMapping(method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonVo> findAll() {
        return service.findAll();
    }

    @GetMapping (value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVo findById(@PathVariable(value = "id") Long id) {

        return service.findById(id);
    }

    @PostMapping (consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
               public PersonVo created(@RequestBody PersonVo person) {
        return service.created(person);
    }
   @PostMapping (value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVo2 createdV2(@RequestBody PersonVo2 person) {
        return service.createdV2(person);

   }

    @PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVo update(@RequestBody PersonVo person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
              service.delete(id);
              return ResponseEntity.noContent().build();
    }
}

