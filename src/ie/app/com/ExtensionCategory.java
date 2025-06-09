package ie.app.com;

import java.util.HashMap;
import java.util.Map;

public class ExtensionCategory {

	private Map<String, String> extensionToCategory;
	
	public ExtensionCategory() {
	initializeExtensionMap();
	}
	
	private void initializeExtensionMap() {
	extensionToCategory = new HashMap<>();
	
	// Text files
	extensionToCategory.put("txt", "Text");
	extensionToCategory.put("md", "Text");
	
	// Document files
	extensionToCategory.put("pdf", "Document");
	extensionToCategory.put("doc", "Document");
	extensionToCategory.put("docx", "Document");
	extensionToCategory.put("odt", "Document");
	
	// Spreadsheet files
	extensionToCategory.put("xls", "Spreadsheet");
	extensionToCategory.put("xlsx", "Spreadsheet");
	extensionToCategory.put("ods", "Spreadsheet");
	
	// Presentation files
	extensionToCategory.put("ppt", "Presentation");
	extensionToCategory.put("pptx", "Presentation");
	extensionToCategory.put("odp", "Presentation");
	
	// Image files
	extensionToCategory.put("jpg", "Image");
	extensionToCategory.put("jpeg", "Image");
	extensionToCategory.put("png", "Image");
	extensionToCategory.put("gif", "Image");
	extensionToCategory.put("bmp", "Image");
	extensionToCategory.put("svg", "Image");
	
	// Audio files
	extensionToCategory.put("mp3", "Audio");
	extensionToCategory.put("wav", "Audio");
	extensionToCategory.put("flac", "Audio");
	
	// Video files
	extensionToCategory.put("mp4", "Video");
	extensionToCategory.put("mkv", "Video");
	extensionToCategory.put("avi", "Video");
	extensionToCategory.put("mov", "Video");
	
	// Archive files
	extensionToCategory.put("zip", "Archive");
	extensionToCategory.put("rar", "Archive");
	extensionToCategory.put("7z", "Archive");
	extensionToCategory.put("tar", "Archive");
	extensionToCategory.put("gz", "Archive");
	
	// Executable files
	extensionToCategory.put("exe", "Executable");
	extensionToCategory.put("msi", "Executable");
	extensionToCategory.put("apk", "Executable");
	extensionToCategory.put("jar", "Executable");
	
	// Data files
	extensionToCategory.put("csv", "Data");
	extensionToCategory.put("json", "Data");
	extensionToCategory.put("xml", "Data");
	
	// Source code files
	extensionToCategory.put("html", "Code");
	extensionToCategory.put("css", "Code");
	extensionToCategory.put("js", "Code");
	extensionToCategory.put("java", "Code");
	extensionToCategory.put("py", "Code");
	extensionToCategory.put("c", "Code");
	extensionToCategory.put("cpp", "Code");
	}
	
	public Map<String, String> getExtensionMap() {
	return extensionToCategory;
	}
}
