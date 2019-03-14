package test;

import java.io.IOException;

import FileUtil.UserField;
import databaseUtil.ITTDB;

public class DeleteTest {

	public static void main(String[] args) {
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		ITTDB ittdb = new ITTDB();
		
		UserField userField = new UserField("name",dummyEmployee.name);
		try {
			ittdb.delete(dummyEmployee.getClass(),userField);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//vpdb.delete(dummyEmployee);	
		
	}

}
