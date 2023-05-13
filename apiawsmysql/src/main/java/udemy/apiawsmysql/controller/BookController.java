package udemy.apiawsmysql.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.apiawsmysql.data.vo.v1.BookVO;
import udemy.apiawsmysql.data.vo.v1.PersonVO;
import udemy.apiawsmysql.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoints to manage Books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping()
    @Operation(summary = "Find all books", description = "Find all books", tags = {"Books"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public List<BookVO> findAllBooks() {
        return this.service.findAllBooks();
    }

    @Operation(summary = "Find a Book", description = "Find a Book by its identification", tags = {"Book"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @GetMapping(value = "/{id}")
    public BookVO findBookById(@PathVariable Long id) {
        return this.service.findBookById(id);
    }

    @Operation(summary = "Create a Book", description = "Create a Book", tags = {"Book"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @PostMapping
    public BookVO createBook(@RequestBody BookVO book) {
        return this.service.createBook(book);
    }

    @Operation(summary = "Update a Book", description = "Update a Book", tags = {"Book"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @PutMapping
    public BookVO updateBook(@RequestBody BookVO book) {
        return this.service.updateBook(book);
    }

    @Operation(summary = "Delete a Book", description = "Delete a Book by its identification", tags = {"Book"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        this.service.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
