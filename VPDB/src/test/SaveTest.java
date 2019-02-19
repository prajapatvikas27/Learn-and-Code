package test;

import java.io.IOException;

import databaseUtil.VPDB;

public class SaveTest {

	public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		VPDB vpdb = new VPDB();
		
		try {
			vpdb.save(dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DummyEmployee dummyEmployee2 = new DummyEmployee("dummy2","dummyF2","dummyM2");
		try {
			vpdb.save(dummyEmployee2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
