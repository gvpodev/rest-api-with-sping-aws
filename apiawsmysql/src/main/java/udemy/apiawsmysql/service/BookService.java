package udemy.apiawsmysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import udemy.apiawsmysql.controller.BookController;
import udemy.apiawsmysql.data.vo.v1.BookVO;
import udemy.apiawsmysql.exception.ResourceNotFoundException;
import udemy.apiawsmysql.mapper.DozerMapper;
import udemy.apiawsmysql.model.Book;
import udemy.apiawsmysql.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookVO> findAllBooks() {
        var books = DozerMapper.parseListObjects(this.repository.findAll(), BookVO.class);
        books.forEach(b -> b.add(linkTo(methodOn(BookController.class).findAllBooks()).withSelfRel()));

        return books;
    }

    public BookVO findBookById(Long id) throws ResourceNotFoundException {
        var book = DozerMapper.parseObject(this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id " + id)), BookVO.class);
        book.add(linkTo(methodOn(BookController.class).findBookById(id)).withSelfRel());

        return book;
    }

    public BookVO createBook(BookVO book) {
        var savedBook = DozerMapper.parseObject(this.repository.save(DozerMapper.parseObject(book, Book.class)), BookVO.class);
        savedBook.add(linkTo(methodOn(BookController.class).findBookById(book.getKey())).withSelfRel());

        return savedBook;
    }

    public BookVO updateBook(BookVO book) throws ResourceNotFoundException {
        var entity = this.repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id " + book.getKey()));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());

        var bookUpdated = DozerMapper.parseObject(this.repository.save(entity), BookVO.class);
        book.add(linkTo(methodOn(BookController.class).findBookById(book.getKey())).withSelfRel());

        return bookUpdated;
    }

    public void deleteBook(Long id) throws ResourceNotFoundException {
        var entity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id " + id));

        this.repository.delete(entity);
    }
}
