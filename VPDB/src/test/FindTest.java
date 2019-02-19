package test;

import java.io.IOException;

import FileUtilExceptions.ObjectNotFoundInFileException;
import databaseUtil.VPDB;

public class FindTest {

public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		VPDB vpdb = new VPDB();
		
		try {
		vpdb.find(dummyEmployee);
		}
		catch (ObjectNotFoundInFileException e) {
			// TODO: handle exception
			System.out.println("No Object Found in the database");
		}
	}

}
