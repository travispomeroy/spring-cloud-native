package com.polarbookshop.catalogservice.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polarbookshop.catalogservice.domain.BookService;
import com.polarbookshop.catalogservice.domain.BookService.BookNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Test
	@DisplayName("when get book not existing then should return 404")
	void whenGetBookNotExistingThenShouldReturn404() throws Exception {
		var isbn = "73737313940";
		given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
		mockMvc.perform(get("/books/{isbn}", isbn)).andExpect(status().isNotFound());
	}

}