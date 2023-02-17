package br.com.felix.projeto.controller;

import br.com.felix.projeto.data.v1.BookVo;
import br.com.felix.projeto.services.BookService;
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
@RequestMapping("/api/book/v1")
@Tag(name="Book", description = "Endpoints Manager of Book")
public class BookController {

    @Autowired
    private BookService service;
    @RequestMapping(method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find All Books", description = "Find All Books",
    tags={"people"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookVo.class))
                            )
                            } ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    }
            )

    public List<BookVo> findAll() {
        return service.findAll();
    }


    @GetMapping (value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find By Id Book", description = "Find By ID Book",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",

                            content = @Content(schema = @Schema(implementation = BookVo.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BookVo findById(@PathVariable(value = "id") Long id) {

        return service.findById(id);
    }

    @PostMapping (consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Add Book",
            description = "Create name Book, suport and format Json , xml and yaml",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",

                            content = @Content(schema = @Schema(implementation = BookVo.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
               public BookVo created(@RequestBody BookVo Book
) {
        return service.created(Book);
    }
   @PostMapping (value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BookVo createdV2(@RequestBody BookVo book) {
        return service.created(book);

   }

    @PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Updates data Book", description = "Updates data Book",
            tags={"people"},
            responses = {
                    @ApiResponse(description = "Update Entitie Book",
                            responseCode = "200",

                            content = @Content(schema = @Schema(implementation = BookVo.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized Request", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public BookVo update(@RequestBody BookVo Book
) {
        return service.update(Book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary="Delete Book", description = "Delete By ID Book",
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

