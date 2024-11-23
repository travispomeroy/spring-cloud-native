package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> viewBookList() {
		return bookRepository.findAll();
	}

	public Book viewBookDetails(String isbn) {
		return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
	}

	public Book addBookToCatalog(Book book) {
		if (bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}

		return bookRepository.save(book);
	}

	public Book editBookDetails(String isbn, Book book) {
		return bookRepository.findByIsbn(isbn).map(x -> {
			var bookToUpdate = new Book(x.isbn(), book.title(), book.author(), book.price());
			return bookRepository.save(bookToUpdate);
		}).orElseGet(() -> addBookToCatalog(book));
	}

	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}

	public static final class BookNotFoundException extends RuntimeException {

		public BookNotFoundException(String isbn) {
			super("The book with ISBN " + isbn + " was not found");
		}

	}

	public static final class BookAlreadyExistsException extends RuntimeException {

		public BookAlreadyExistsException(String isbn) {
			super("A book with ISBN " + isbn + " already exists");
		}

	}

}
