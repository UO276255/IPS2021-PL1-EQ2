package com.uniovi.muebleria.maven.util;

public class test {

	public static void main(String[] args) {
		Database db = new Database();
		db.createDatabase(true);
		System.out.println("BD creada");
		db.loadDatabase();
		System.out.println("Datos iniciales cargados en la BD");
		
		//asasasasa
	}

}
