package com.hashedin.carresearch;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class CarResearch {

	public static void main(String args[]) throws IOException {

		InputStream rawXMlStream = getRawInputStream();
		ModelParser parser = new DomParser();
		List<Model> models = parser.parse(rawXMlStream);
		
		CarResearch researcher = new CarResearch();
		Model mostExpensiveCar = researcher.getMostExpensiveCar(models);
		System.out.println(mostExpensiveCar);
		
		Model cheapestCar = researcher.getCheapestCar(models);
		System.out.println(cheapestCar);
		
	}
	

	private static InputStream getRawInputStream() throws IOException {
		URL url = new URL("http://services.forddirect.fordvehicles.com/products/ModelSlices?make=Ford");
		return url.openStream();
	}

	public Model getMostExpensiveCar(List<Model> models) {
		return Collections.max(models, new ModelComparator());
	}
	
	public Model getCheapestCar(List<Model> models) {
		return Collections.min(models, new ModelComparator());
	}
		
}
