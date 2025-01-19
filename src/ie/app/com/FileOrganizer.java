package ie.app.com;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

//The idea of this class is basically create a logic to identify a file extension and mode to the related folder 
public class FileOrganizer {

	private Path sorceDir;
	//I have to create a Data Structure to hold the file name/path and the file extension
	private Map<Path, String> fileExtensionMap = new HashMap<>();
	
	//Constructor
	public FileOrganizer(Path sorceDir) {
		this.sorceDir = sorceDir;
	}

	//First I need a method to return the file extension. I will return a String, check for the index of the last "." and return the substring after.
	public String getFileExtension(String path) {
		int last = path.lastIndexOf(".");
		return path.substring(last + 1).toLowerCase(); 
	}
	
	//A method to go through all the files in the folder and add the filename and extension to the map.
	public void mapFileExtension(Path path) {
		if (!Files.exists(path)) {
			System.out.println("The provided path is not valid: " + path);
			return;
		}
		
		//TODO
	}
	
	
	/*
	 *debug
	public static void main(String[] args) {
		FileOrganizerUtility utility = new  FileOrganizerUtility();
		
		List<String> testFiles = new ArrayList<>();
		testFiles.add("Document.pdf");
        testFiles.add("Image.jpeg");
        testFiles.add("Archive.zip");
        testFiles.add("Presentation.pptx");
        testFiles.add("Spreadsheet.xlsx");
        testFiles.add("Script.js");
        testFiles.add("Stylesheet.css");
        testFiles.add("Executable.exe");
        testFiles.add("Audio.mp3");
        testFiles.add("Video.mp4");
		
        for (String names : testFiles) {
        	System.out.println("The file " + names+ " extension is: " + utility.fileExtension(names));
        }
		
	}
	
	*/
}
