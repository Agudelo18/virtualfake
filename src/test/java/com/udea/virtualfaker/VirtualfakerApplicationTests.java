package com.udea.virtualfaker;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class VirtualfakerApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	void health(){
		assertEquals("HEALTH CHECK OK", dataController.healthCheck());
	}

	@Test
	void version(){
		assertEquals("Version is 1.0.0", dataController.version());
	}

	@Test
	void nationLength(){
		Integer nationLength = dataController.getRamdonNations().size();
		assertEquals(10, nationLength);
	}
	@Test
	void currenciesLength(){
        Integer currenciesLength = dataController.getRamdonCurrencies().size();
		assertEquals(20, currenciesLength);
	}
	@Test
	void aviationLength(){
		Integer aviationLength = dataController.getRamdonAviation().size();
		assertEquals(20, aviationLength);
	}

	@Test
	void testRandomCurrenciesCodeFormat(){
		DataController dataController = new DataController();
		JsonNode response = dataController.getRamdonCurrencies();

		for( int i=0; i<response.size(); i++){
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}"));

		}

	}

	@Test
	void testRandomNationsPerformance(){
		DataController controller = new DataController();
		long startTime = System.currentTimeMillis();
		controller.getRamdonNations();
		long ednTime = System.currentTimeMillis();
		long executionTime = ednTime - startTime;
		System.out.println(executionTime);
		assertTrue(executionTime < 2000);
	}


}
