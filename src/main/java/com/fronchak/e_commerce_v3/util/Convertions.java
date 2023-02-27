package com.fronchak.e_commerce_v3.util;

import com.fronchak.e_commerce_v3.entities.enums.Grade;

public class Convertions {

	public static Grade getGrade(Double average) {
		return Grade.toEnum((int) Math.round(average));
	}
}
