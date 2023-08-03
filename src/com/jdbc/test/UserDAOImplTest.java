package com.jdbc.test;

import config.ServerInfo;

public class UserDAOImplTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("성공");
	}
	static { 
		try {  
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading Success...");
			
		} catch(ClassNotFoundException e) {
			System.out.println("Driver Loading Fail...");
		} 
	}

}
