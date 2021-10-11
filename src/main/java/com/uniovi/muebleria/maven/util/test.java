package com.uniovi.muebleria.maven.util;

import java.awt.EventQueue;

import com.uniovi.muebleria.maven.vista.VistaMuebleria;

public class test {

	public static void main(String[] args) {	
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMuebleria frame = new VistaMuebleria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

}
