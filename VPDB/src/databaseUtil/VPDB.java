package databaseUtil;
import java.lang.reflect.Field;

import FileUtil.FileUtil;

/*
 * This class is used as an ORM layer for storing, retrieving, updating and
 * deleting your data into file system
 * @author Vikas Prajapat*/
public class VPDB{
	
	FileUtil fileUtil = new FileUtil();
	
	
	/*
	 * This method is used for saving object as data into the database
	 * @param object Object instance that need to be saved inside the database*/
	public void save(Object object) {
		
	fileUtil.setFileName(object.getClass().toString());	
	System.out.println(object.getClass().toString());
	fileUtil.storeObjectInFile(object);
	}
	
	
	/*
	 * This method is used for deleting an existing object as data in the 
	 * database.
	 * @param object Object that needs to be deleted from the database.
	 * */
	public boolean delete(Object object) {
		fileUtil.setFileName(object.getClass().toString());	
		return fileUtil.delete(object);
	}
	
	public <UserField, UserFieldValue> boolean delete(Class className,
													  UserField userField,
													  UserFieldValue userFieldValue) 
	{
		
		
		fileUtil.setFileName("class "+className.getName());
	
		return fileUtil.delete(className,userField,userFieldValue);
	}
	
}
