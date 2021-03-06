package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.service.BookService;
import com.mycompany.myapp.service.AuthorService;
// import com.mycompany.myapp.service.

import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Book.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    @Inject
    private BookService bookService;
    @Inject
    private AuthorService authorService;

    /**
     * POST  /books : Create a new book.
     *
     * @param book the book to create
     * @return the ResponseEntity with status 201 (Created) and with body the new book, or with status 400 (Bad Request) if the book has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/books")
    @Timed
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to save Book : {}", book);

        if (book.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("book", "idexists", "A new book cannot already have an ID")).body(null);
        }
        System.out.println(book.getTacGia());
        // System.out.println(authorService.findOne(book.getTacGia()));
        // System.out.println(authorService.findOne(book.getTacGia()).getId());
        if(authorService.findOne(book.getTacGia()).getId()==""){
          return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("book", "idexists", "Author not exist")).body(null);
        }
        book.setTenTacgia(authorService.findOne(book.getTacGia()).getTenTacgia());
        Book result = bookService.save(book);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("book", result.getId().toString()))
            .body(result);
    }


    @GetMapping(value="/books/addtoCart/{id}")
    @Timed
    public ResponseEntity<Book> addBookToCart (@PathVariable String id) throws URISyntaxException{
      //,@RequestBody String userid
      Book book=bookService.findOne(id);
      int result = bookService.addBookToCart(book);
      // if(result==0)
      // {
        return ResponseEntity.created(new URI("/api/books/" + book.getId()))
          .headers(HeaderUtil.createEntityCreationAlert("book", book.getId().toString()))
          .body(book);
      // }
      // else return ResponseEntity.created(new URI("/api/books/" + book.getId()))
      //   .headers(HeaderUtil.createEntityCreationAlert("book", book.getId().toString()))
      //   .body(result);
    }
    @GetMapping("/books/subfromCart/{id}")
    @Timed
    public ResponseEntity<Book> subBookFromCart (@PathVariable String id) throws URISyntaxException{
      //,@RequestBody String userid
      Book book=bookService.findOne(id);
      int result = bookService.subBookFromCart(book);
      if(result==0){
        System.out.println("thanh cong");
      }
      if(result==1){
        System.out.println("het hang");
      }
      if(result==2){
        System.out.println("chua dat hang");
      }
      if(result==3){
        System.out.println("chua tao gio hang");
      }
      // {
        return ResponseEntity.created(new URI("/api/books/" + book.getId()))
          .headers(HeaderUtil.createEntityCreationAlert("book", book.getId().toString()))
          .body(book);
      // }
      // else return ResponseEntity.created(new URI("/api/books/" + book.getId()))
      //   .headers(HeaderUtil.createEntityCreationAlert("book", book.getId().toString()))
      //   .body(result);
    }

    @GetMapping("/books/tacgia/{id}")
    @Timed
    public ResponseEntity<List<Book>> findAllByTacGia(@PathVariable String id) throws URISyntaxException{
      List<Book> page = bookService.findAllByTacGia(id);
      HttpHeaders headers = new HttpHeaders();

  		URI location=new URI("books/tacgia/"+id);

  		headers.setLocation(location);

      // HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/books");
      return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/books/tag/{tag}")
    @Timed
    public ResponseEntity<List<Book>> findAllByTag(@PathVariable String tag) throws URISyntaxException{
      List<Book> page = bookService.findAllByTag(tag);
      System.out.println(page);
      HttpHeaders headers = new HttpHeaders();

  		URI location=new URI("books/tag/"+tag);

  		headers.setLocation(location);

      // HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/books");
      return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    /**
     * PUT  /books : Updates an existing book.
     *
     * @param book the book to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated book,
     * or with status 400 (Bad Request) if the book is not valid,
     * or with status 500 (Internal Server Error) if the book couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/books")
    @Timed
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to update Book : {}", book);
        if (book.getId() == null) {
            return createBook(book);
        }
        Book result = bookService.save(book);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("book", book.getId().toString()))
            .body(result);
    }

    /**
     * GET  /books : get all the books.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of books in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/books")
    @Timed
    public ResponseEntity<List<Book>> getAllBooks(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Books");
        Page<Book> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/books");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /books/:id : get the "id" book.
     *
     * @param id the id of the book to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the book, or with status 404 (Not Found)
     */
    @GetMapping("/books/{id}")
    @Timed
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        log.debug("REST request to get Book : {}", id);
        Book book = bookService.findOne(id);
        return Optional.ofNullable(book)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /books/:id : delete the "id" book.
     *
     * @param id the id of the book to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/books/{id}")
    @Timed
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("book", id.toString())).build();
    }

}
