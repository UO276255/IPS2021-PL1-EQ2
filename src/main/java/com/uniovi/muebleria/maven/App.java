package com.uniovi.muebleria.maven;

import java.awt.EventQueue;

import com.uniovi.muebleria.maven.vista.VistaLogin;
public class App 
{
    public static void main( String[] args )
    {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
    }
}