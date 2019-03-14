package test;

import java.io.IOException;

import databaseUtil.ITTDB;

public class UpdateTest {

	public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		ITTDB ittdb = new ITTDB();
		
		try {
			ittdb.save(dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 dummyEmployee = new DummyEmployee("dummy2","dummyF2","dummyM2");
		try {
			ittdb.modify(dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
