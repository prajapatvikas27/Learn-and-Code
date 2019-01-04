package FileUtilExceptions;

import java.io.IOException;

public class FileOperationException extends IOException {


	public FileOperationException(String message) throws IOException {
		throw new IOException(message);
	}
	
	
}
