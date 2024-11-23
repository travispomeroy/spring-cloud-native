package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookValidationTest {

	private static Validator validator;

	@BeforeAll
	static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@DisplayName("whenAllFieldsCorrectThenValidationSucceeds")
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = new Book("1234567890", "Title", "Author", 9.90);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}

	@Test
	@DisplayName("when isbn defined but incorrect then validation fails")
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book = new Book("a1234567890", "Title", "Author", 9.90);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
	}

}