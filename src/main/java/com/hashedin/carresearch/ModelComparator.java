package com.hashedin.carresearch;

import java.util.Comparator;

public class ModelComparator implements Comparator<Model> {

	public int compare(Model m1, Model m2) {
		return (int)(m1.getMinPrice() - m2.getMinPrice());
	}

}
