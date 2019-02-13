package databaseUtil;
import java.io.IOException;

import FileUtil.FileUtil;
import FileUtil.UserField;
import FileUtilExceptions.ObjectNotFoundInFileException;

/*
 * This class is used as an ORM layer for storing, retrieving, updating and
 * deleting your data into file system
 * @author Vikas Prajapat*/
public class VPDB{
	
	FileUtil fileUtil = new FileUtil();
	
	
	/*
	 * This method is used for saving object as data into the database
	 * @param object Object instance that need to be saved inside the database*/
	public void save(Object object) throws IOException {
		
	fileUtil.setFileName(object.getClass().toString());	
	fileUtil.save(object);
	
	}
	
	
	/*
	 * This method is used for deleting an existing object as data in the 
	 * database.
	 * @param object Object that needs to be deleted from the database.
	 * */
	public boolean delete(Object object) throws IOException {
		fileUtil.setFileName(object.getClass().toString());	
		return fileUtil.delete(object);
	}
	


	/*
	 * This method is used for deleting an existing object as data in the 
	 * database.
	 * @param className the database file from which the data needs to be 
	 * deleted
	 * @param userField UserField object based on which the data needs to be 
	 * deleted
	 * */
	public boolean delete( Class className, UserField userField) throws IOException {
	
		fileUtil.setFileName("class "+className.getName());
		
		return fileUtil.delete(userField);
	}
	

	/*
	 * This method is used for modifying an existing object as data in the 
	 * database.
	 * @param object Object that needs to be updated from the database.
	 * */
	public void modify(Object object) throws ObjectNotFoundInFileException, IOException {
		fileUtil.setFileName(object.getClass().toString());	
		fileUtil.modify(object);
	}
	

	/*
	 * This method is used for finding an existing object  in the 
	 * database.
	 * @param object Object that needs to be found in the database.
	 * */
	public void find(Object object) throws ObjectNotFoundInFileException {
		fileUtil.setFileName(object.getClass().toString());	
		fileUtil.find(object);
	}
	
}
