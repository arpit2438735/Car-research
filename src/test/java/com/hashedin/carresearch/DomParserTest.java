package com.hashedin.carresearch;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class DomParserTest {

	@Test
	public void testParse() {
		DomParser parser = new DomParser();
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("ModelSlices.xml");
		assertNotNull(in);
		
		List<Model> models = parser.parse(in);
		
		assertEquals("Incorrect number of models", 3, models.size());
		
		Model model = models.get(0);
		assertEquals("Ford", model.getMake());
		assertEquals("2011", model.getYear());
		assertEquals("StrippedChassis", model.getModelName());
		assertEquals(23070d, model.getMinPrice(), 0.1d);
		assertEquals(32610d, model.getMaxPrice(), 0.1d);
	}

}
