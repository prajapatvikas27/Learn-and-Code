package FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.gson.Gson;

public class FileUtil {

	String fileName;
	Gson gson;
	File file;
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;
	BufferedReader bufferedReader;
	
	public void setFile() {
		this.file = new File(fileName);
	}

	public File getFile() {
		return file;
	}

	
	public String getFileName() {
		return fileName;
	}

	public FileUtil() {
	
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		setFile();
	}


	public void storeObjectInFile(Object object) {
		
		createFile();			
		boolean foundObject = findObjectInFile(object);
		
		if(!foundObject) {
			writeObjectToFile(object);
		}
			
	}
	
	private String getObjectInStringFormat(Object object) {
		
		gson = new Gson();
		String objectInStringFormat = gson.toJson(object);
		return objectInStringFormat;
	}

	private void writeObjectToFile(Object object) {
		
		try {
			fileWriter = new FileWriter(file.getAbsoluteFile(),true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(System.lineSeparator() + getObjectInStringFormat(object));
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

	private void createFile() {
		
		if(!getFile().exists()) {
			try {
				file.createNewFile();								
			} 
			catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

	private boolean findObjectInFile(Object object) {
		
		String regex = new Gson().toJson(object).toString();
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
		file = new File(fileName);
		

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
        
				 String line = scanner.nextLine();
				 Matcher matcher = pattern.matcher(line);
				 while (matcher.find()) {     // find the next match
			         System.out.println("find() found the pattern \"" + matcher.group()
			               + "\" starting at index " + matcher.start()
			               + " and ending at index " + matcher.end());
			         scanner.close();
			         return true;
			      }
			}
			scanner.close();
		}
		catch(FileNotFoundException e) { 

		}
		return false;
		
	}

	public boolean delete(Object object) {
		createFile();
		boolean foundObject = findObjectInFile(object);
		
		if(foundObject) {
			return removeObjectFromFile(object);
		}
		
		return false;
	}

	
	public <UserField,UserFieldValue> boolean delete(
			Class className,
			UserField userField,
			UserFieldValue userFieldValue) {
		createFile();
		boolean foundField = findFieldInFile(className, userField,userFieldValue);
		
		if(foundField) {
			return removeObjectFromFile(className, userField, userFieldValue);
		}
		
		return false;
	}
	
	
	private <UserField, UserFieldValue> boolean removeObjectFromFile(Class className, 
					UserField userField, UserFieldValue userFieldValue) 
			{
		
		
		
		
		System.out.println("removing object");
		String lineToRemove = getObjectToDelete(className,userField,userFieldValue);
		System.out.println("Object To delete : " + lineToRemove);
		List objectList;
		objectList = getObjectsInObjectList();
//		StringBuilder fileData = getDataIntoStringBuffer();
//		Pattern pattern = Pattern.compile(lineToRemove,Pattern.LITERAL);
//		
//		 Matcher matcher = pattern.matcher(fileData);
//		 while (matcher.find()) {     // find the next match
//			 fileData.delete(matcher.start(),matcher.end()-1);
//		 }
//		
//		
//		System.out.println(fileData);
//		writeDataToFile(fileData.toString());
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

	private <UserField,UserFieldValue> String getObjectToDelete(
			Class className, UserField userField, UserFieldValue userFieldValue
			) {
		
		String fieldName = userField.toString();
		String fieldValue = userFieldValue.toString();
	    String regex = "\""+ userField + "\"" + ":" + "\"" + userFieldValue + "\"";
	    System.out.println("Pattern to match : " + regex);
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
		file = new File(fileName);
		

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
        
				 String line = scanner.nextLine();
				 Matcher matcher = pattern.matcher(line);
				 while (matcher.find()) {     // find the next match
			         System.out.println("find() found the pattern \"" + matcher.group()
			               + "\" starting at index " + matcher.start()
			               + " and ending at index " + matcher.end());
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

	private <UserField,UserFieldValue> boolean findFieldInFile(
			Class className,
			UserField userField,
			UserFieldValue userFieldValue)
	{
		
		String fieldName = userField.toString();
		String fieldValue = userFieldValue.toString();
	    String regex = "\""+ userField + "\"" + ":" + "\"" + userFieldValue + "\"";
	    System.out.println("Pattern to match : " + regex);
		Pattern pattern = Pattern.compile(regex,Pattern.LITERAL);
		file = new File(fileName);
		

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
        
				 String line = scanner.nextLine();
				 Matcher matcher = pattern.matcher(line);
				 while (matcher.find()) {     // find the next match
			         System.out.println("find() found the pattern \"" + matcher.group()
			               + "\" starting at index " + matcher.start()
			               + " and ending at index " + matcher.end());
			         scanner.close();
			         return true;
			      }
			}
			scanner.close();
		}
		catch(FileNotFoundException e) { 

		}
		return false;

	}

	private boolean removeObjectFromFile(Object object) {
		System.out.println("removing object");
		String lineToRemove = getObjectInStringFormat(object);
		List objectList;
		objectList = getObjectsInObjectList();
//		StringBuilder fileData = getDataIntoStringBuffer();
//		Pattern pattern = Pattern.compile(lineToRemove,Pattern.LITERAL);
//		
//		 Matcher matcher = pattern.matcher(fileData);
//		 while (matcher.find()) {     // find the next match
//			 fileData.delete(matcher.start(),matcher.end()-1);
//		 }
//		
//		
//		System.out.println(fileData);
//		writeDataToFile(fileData.toString());
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

	private List getObjectsInObjectList() {
		
		List objectList = new ArrayList<String>();
	
		
	    try {
	    	bufferedReader = new BufferedReader(new FileReader(getFile()));
	        String line = bufferedReader.readLine();

	        while (line != null) {
//	            sb.append(line);
//	            sb.append("\n");
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

	private void writeDataToFile(List objectList) {
				
		
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

	private StringBuilder getDataIntoStringBuffer() {
		
		
	    try {
	    	bufferedReader = new BufferedReader(new FileReader(getFile()));
	        StringBuilder sb = new StringBuilder();
	        String line = bufferedReader.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = bufferedReader.readLine();
	        }
	        return sb;
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

	
		

}
