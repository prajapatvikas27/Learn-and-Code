package test;

import java.io.IOException;

import databaseUtil.VPDB;

public class UpdateTest {

	public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		VPDB vpdb = new VPDB();
		
		try {
			vpdb.save(dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 dummyEmployee = new DummyEmployee("dummy2","dummyF2","dummyM2");
		try {
			vpdb.modify(dummyEmployee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
