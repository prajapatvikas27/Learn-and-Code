package test;

import java.io.IOException;

import databaseUtil.ITTDB;

public class SaveTest {

	public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		ITTDB ittdb = new ITTDB();
		
		try {
			ittdb.save(dummyEmployee);
			System.out.println("data Saved"+dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DummyEmployee dummyEmployee2 = new DummyEmployee("dummy2","dummyF2","dummyM2");
		try {
			ittdb.save(dummyEmployee2);
			System.out.println("data Saved"+dummyEmployee2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
