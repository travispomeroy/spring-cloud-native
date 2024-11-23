package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	@DisplayName("when post request then book created")
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1231231231", "Title", "Author", 9.90);

		webClient.post()
			.uri("/books")
			.bodyValue(expectedBook)
			.exchange()
			.expectStatus()
			.isCreated()
			.expectBody(Book.class)
			.value(actual -> {
				assertThat(actual).isNotNull();
				assertThat(actual.isbn()).isEqualTo("1231231231");
			});
	}

}
