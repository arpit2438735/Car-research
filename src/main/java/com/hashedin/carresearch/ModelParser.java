package com.hashedin.carresearch;

import java.io.InputStream;
import java.util.List;

public interface ModelParser {

	public List<Model> parse(InputStream xmlFile);
	
}
