package br.com.felix.projeto.controller;

import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.services.PersonService;
import br.com.felix.projeto.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpoints Manager of people")
public class PersonController  {

    @Autowired
    private PersonService service;


    @RequestMapping(method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find All People", description = "Find All People",
    tags={"people"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonVo.class))
                            )
                            } ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    }
            )

    public List<PersonVo> findAll() {
        return service.findAll();
    }


    @GetMapping (value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find By Id Person", description = "Find By ID Person",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",

                            content = @Content(schema = @Schema(implementation = PersonVo.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonVo findById(@PathVariable(value = "id") Long id) {

        return service.findById(id);
    }


    @PostMapping (consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Add Person",
            description = "Create and Add Person, received in format Json , xml and yaml",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",

                            content = @Content(schema = @Schema(implementation = PersonVo.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
               public PersonVo created(@RequestBody PersonVo person) {
        return service.created(person);
    }



    @PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Updates data Person", description = "Updates data Person",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Update  and Add Person, received in format Json , xml and yaml",
                            responseCode = "200",

                            content = @Content(schema = @Schema(implementation = PersonVo.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonVo update(@RequestBody PersonVo person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete Person", description = "Delete By ID Person",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
              service.delete(id);
              return ResponseEntity.noContent().build();
    }
}

