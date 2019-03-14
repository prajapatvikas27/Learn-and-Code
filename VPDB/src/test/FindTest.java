package test;

import java.io.IOException;

import FileUtilExceptions.ObjectNotFoundInFileException;
import databaseUtil.ITTDB;

public class FindTest {

public static void main(String[] args) {
		
		
		DummyEmployee dummyEmployee = new DummyEmployee("dummy","dummyF","dummyM");
		ITTDB vpdb = new ITTDB();
		
		try {
		vpdb.find(dummyEmployee);
		}
		catch (ObjectNotFoundInFileException e) {
			// TODO: handle exception
			System.out.println("No Object Found in the database");
		}
	}

}
