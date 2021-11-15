package com.uniovi.muebleria.maven;

import java.awt.EventQueue;

import com.uniovi.muebleria.maven.vista.VistaMuebleria;
public class App 
{
    public static void main( String[] args )
    {
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
		
//		String texto="Saludos desde Apuntesdejava.com";
//        String encriptMD5=DigestUtils.md5Hex(texto);
//        System.out.println("md5:"+encriptMD5);
		
    }
}
