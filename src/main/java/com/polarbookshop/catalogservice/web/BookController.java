package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public Iterable<Book> get() {
		return bookService.viewBookList();
	}

	@GetMapping("{isbn}")
	public Book get(@PathVariable String isbn) {
		return bookService.viewBookDetails(isbn);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@Valid @RequestBody Book book) {
		return bookService.addBookToCatalog(book);
	}

	@DeleteMapping("{isbn}")
	public void delete(@PathVariable String isbn) {
		bookService.removeBookFromCatalog(isbn);
	}

	@PutMapping("{isbn}")
	public Book update(@PathVariable String isbn, @Valid @RequestBody Book book) {
		return bookService.editBookDetails(isbn, book);
	}

}
