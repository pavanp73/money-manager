package com.pavan.app;

import com.pavan.app.resources.api.AccountResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoneyManagerApplicationTests {

	@Autowired
	private AccountResource accountResource;

	@Test
	void contextLoads() {
		assertThat(accountResource).isNotNull();
	}

}
