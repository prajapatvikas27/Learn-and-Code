package FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import FileUtilExceptions.FieldNotFoundInFileException;
import FileUtilExceptions.ObjectNotFoundInFileException;
import FileUtilExceptions.PatternNotFoundInFileException;

public class FileUtil {

	String fileName;
	Gson gson;
	File file;
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;
	BufferedReader bufferedReader;
	
	public FileUtil() {
		
	}
	
	public void setFile() {
		this.file = new File(fileName);
	}

	public File getFile() {
		return file;
	}

	
	public String getFileName() {
		return fileName;
	}

	

	public void setFileName(String fileName) {
		this.fileName = fileName;
		setFile();
	}

	
	public void save(Object object) throws IOException {
		
		createFileIfNotPresent();		
		
		try {
		
			findObjectInFile(object);
			
		}
		catch(ObjectNotFoundInFileException e) {
		
			writeObjectToFile(object);
		
		}
			
	}
	
	public boolean delete(Object object) throws IOException {
		createFileIfNotPresent();
		
		try {
			findObjectInFile(object);
			return removeObjectFromFile(object);
		}
		catch(ObjectNotFoundInFileException e) {
			
		}
				
		return false;
	}
	
	
	public boolean delete(UserField userField) throws IOException {
		
		createFileIfNotPresent();	
		
			
			findFieldInFile(userField);
			return removeObjectFromFile(userField);

	}
	
	
	private synchronized void writeObjectToFile(Object object) throws IOException {
		
		try {

			fileWriter = new FileWriter(file.getAbsoluteFile(),true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(System.lineSeparator() + getObjectJsonInStringFormat(object));
		
		} catch (IOException e) {
			
			throw e;
		}finally {
		
			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}



	private void findObjectInFile(Object object) throws
	 ObjectNotFoundInFileException{
		
		String regex = getObjectJsonInStringFormat(object);
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
	
		try {
			
			findPatternInFile(pattern);
			
	
		}
		catch(PatternNotFoundInFileException e) {
			throw new ObjectNotFoundInFileException();
		}
		
			
	}
	
	
	private synchronized void findPatternInFile(Pattern pattern) 
			throws PatternNotFoundInFileException{
			
		try {
			Scanner scanner = new Scanner(getFile());

			while (scanner.hasNextLine()) {
        
				 String line = scanner.nextLine();
				 Matcher matcher = pattern.matcher(line);
				 while (matcher.find()) {     // find the next match			        
			         scanner.close();
			    
			         return;
			      }
			}
			scanner.close();
		}
		catch(FileNotFoundException e) { 
		
		}

		
			throw new PatternNotFoundInFileException();
		
	}

	private  boolean removeObjectFromFile(UserField userField) 
			{
		
		String lineToRemove = getObjectToDelete(userField);
		List objectList = getObjectsInObjectList();
		Iterator objectListItems = objectList.iterator();
		
		while(objectListItems.hasNext()) {
			String item = (String) objectListItems.next();
			if(item.trim().equals(lineToRemove)) {
				objectListItems.remove();
			}
		}
		
		writeDataToFile(objectList);
		return true;	
		
	}

	private  String getObjectToDelete(UserField userField)
	{
		
	    String regex = generateRegexFromField(userField);
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
        
				 String line = scanner.nextLine();
				 Matcher matcher = pattern.matcher(line);
				 while (matcher.find()) {     // find the next match
			       
			         scanner.close();
			         return line;
			      }
			}
			scanner.close();
		}
		catch(FileNotFoundException e) { 

		}
		
		return null;
	}

	private void findFieldInFile(UserField userField)
	throws FieldNotFoundInFileException
	{
		
	
	    String regex = 	generateRegexFromField(userField);
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
		
		try {

			findPatternInFile(pattern);
		}
		catch (PatternNotFoundInFileException e) {

			throw new FieldNotFoundInFileException();
		}
		

	}

	

	private boolean removeObjectFromFile(Object object) {
		String lineToRemove = getObjectJsonInStringFormat(object);
		List objectList;
		objectList = getObjectsInObjectList();

		Iterator objectListItems = objectList.iterator();
		while(objectListItems.hasNext()) {
			String item = (String) objectListItems.next();
			if(item.trim().equals(lineToRemove)) {
				objectListItems.remove();
			}
		}
		writeDataToFile(objectList);
		return true;
		
	}

	private synchronized List getObjectsInObjectList() {
		
		List objectList = new ArrayList<String>();
	
		
	    try {
	    	bufferedReader = new BufferedReader(new FileReader(getFile()));
	        String line = bufferedReader.readLine();

	        while (line != null) {

	        	objectList.add(line);
	        	
	            line = bufferedReader.readLine();
	        }
	        return objectList;
	    } catch (IOException e) {
			e.printStackTrace();
		}finally {
	    	
			try {

				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ex) {

				ex.printStackTrace();

			}
	    	
	    }
		
		return null;
		
	}

	private synchronized void writeDataToFile(List objectList) {
				
		
		try {
			fileWriter = new FileWriter(getFile().getAbsoluteFile());
			bufferedWriter = new BufferedWriter(fileWriter);
			Iterator objectListIterator = objectList.iterator();
			while(objectListIterator.hasNext()) {
				String objectItem = (String) objectListIterator.next();
				bufferedWriter.write(objectItem);
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
		
			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	private synchronized void createFileIfNotPresent() throws IOException {
		
		if(!getFile().exists()) {
			
				file.createNewFile();								
		}
		
	}
	
	private String generateRegexFromField(UserField userField) {
		
		String fieldName = userField.getUserFieldName();
		String fieldValue = userField.getUserFieldValue();
	    String regex = "\""+ fieldName + "\"" + ":" + "\"" + fieldValue + "\"";
		return regex;
	}

	private String getObjectJsonInStringFormat(Object object) {
		
		gson = new Gson();
		String objectInStringFormat = gson.toJson(object);
		return objectInStringFormat;
	}

	public void modify(Object object) throws IOException, ObjectNotFoundInFileException {
	
	
		createFileIfNotPresent();		
		findObjectInFile(object);
		updateObjectInFile(object);
		
		
	}

	private void updateObjectInFile(Object object) throws IOException{
		
		
	}

	public Object find(Object object) {
		
		return object;		
	}
	
}
