package test;

import java.io.IOException;

import FileUtil.UserField;
import databaseUtil.VPDB;

public class DeleteTest {

	public static void main(String[] args) {
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		VPDB vpdb = new VPDB();
		
		UserField userField = new UserField("name",dummyEmployee.name);
		try {
			vpdb.delete(dummyEmployee.getClass(),userField);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//vpdb.delete(dummyEmployee);	
		
	}

}
