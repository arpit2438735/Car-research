package com.hashedin.carresearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CarResearchTest {

	private Model cheapestCar;
	private Model mostExpensiveCar;
	private List<Model> allModels; 
	
	@Before
	public void setUp() {
		cheapestCar = new Model();
		cheapestCar.setMake("Ford");
		cheapestCar.setModelName("Fiesta");
		cheapestCar.setYear("2013");
		cheapestCar.setMinPrice(30000);
		
		mostExpensiveCar = new Model();
		mostExpensiveCar.setMake("Ford");
		mostExpensiveCar.setModelName("Mustang");
		mostExpensiveCar.setYear("2013");
		mostExpensiveCar.setMinPrice(50000);
		
		allModels = new ArrayList<Model>();
		allModels.add(cheapestCar);
		allModels.add(mostExpensiveCar);
	}
	
	@Test
	public void testGetMostExpensiveCar() {
		CarResearch research = new CarResearch();
		Model expensiveModel = research.getMostExpensiveCar(allModels);
		
		assertEquals(mostExpensiveCar, expensiveModel);
	}

	@Test
	public void testGetCheapestCar() {
		CarResearch research = new CarResearch();
		Model car = research.getCheapestCar(allModels);
		
		assertEquals(cheapestCar, car);
	}

}
