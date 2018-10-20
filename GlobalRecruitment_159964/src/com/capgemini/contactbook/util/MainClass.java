package com.capgemini.contactbook.util;

public class MainClass {
	public static void main(String[] args) {
		if(ConnectionProvider.getDBConnection()!=null) System.out.println("Connection Open!");
		else System.out.println("Not connected, kindly check for problems.");
	}
}
