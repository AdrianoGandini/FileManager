package ie.app.com;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The FileOrganizer class organizes files by their extensions.
 * It scans files in a directory and sorts them into separate folders.
 */
public class FileOrganizerManager {

    private FilesUtility utility;
    private ExtensionCategory category;
    private Map<Path, String> fileExtensionMap = new HashMap<>();
    

    /**
     * Constructor to initialize the FileOrganizer with a utility instance.
     * @param utility an instance of FilesUtility
     */
    public FileOrganizerManager(FilesUtility utility, ExtensionCategory category) {
        this.utility = utility;
        this.category = category;

    }

    
    /**
     * Gets the file extension from a given file path.
     * @param path the file path
     * @return the file extension or an empty string if no extension is found
     */
    private String getFileExtension(Path path) {
        String fileName = path.getFileName().toString();
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1).toLowerCase();
    }

    /**
     * Maps files in the directory to their extensions.
     * @param inputDirectory the directory to scan
     * @throws IOException if an error occurs while accessing the directory
     */
    private void mapFileExtension(Path inputDirectory) throws IOException {
        if (!Files.exists(inputDirectory) || !Files.isDirectory(inputDirectory, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("The provided path is not valid: " + inputDirectory);
            return;
        }
        
        try (Stream<Path> files = Files.walk(inputDirectory, FileVisitOption.FOLLOW_LINKS)) {
            files.filter(Files::isRegularFile).forEach(path -> {
                String fileExtension = getFileExtension(path);
                String extensionCategory = getExtensionCategory(fileExtension); //Method that will compare with the ExtensionCatgory
                fileExtensionMap.put(path, extensionCategory); //If it works I have to change the comments to match with the new logic 
            });
        } catch (IOException io) {
            System.err.println("Error processing directory: " + inputDirectory + " - " + io.getMessage());
        }
    }
    
    /**
     * Gets the category for a given file extension
     * @param extension the file extension to be categorized
     * @return the category name associated with the extension, or "Miscellaneous" if not found
     */
    private String getExtensionCategory(String extension) {
        Map<String, String> categories = category.getExtensionMap();
        
        //Logic to compare the extension with the categories on the categories Map
        for(Map.Entry<String, String> entry : categories.entrySet()) {
            if (extension.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Miscellaneous";
    }

    /**
     * Sorts files into folders based on their file extensions.
     * @param inputDirectory the source directory
     * @param outputDirectory the target directory
     * @throws IOException if an error occurs during file operations
     */
    private void sortFilesByType(Path inputDirectory, Path outputDirectory) throws IOException {
        for (Map.Entry<Path, String> entry : fileExtensionMap.entrySet()) {
            Path filePath = entry.getKey();
            String category = entry.getValue(); 
            
            Path relativePath = inputDirectory.relativize(filePath); 
            Path extensionCategory = outputDirectory.resolve(category);
            Path resolvedOutputFile = extensionCategory.resolve(relativePath);
            
            utility.createDirectoryIfNeeded(resolvedOutputFile.getParent());
            
            Files.copy(filePath, resolvedOutputFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    

    /**
     * Processes files by mapping their extensions and sorting them into folders.
     * @param inputDirectory the source directory
     * @param outputDirectory the target directory
     * @throws IOException if an error occurs during processing
     */
    public void process(Path inputDirectory, Path outputDirectory) throws IOException {
    	
    	LocalDate today = LocalDate.now();
    	
    	//This line was created before the user be able to select the output file directory. Kept as per learning proposes.
    	//String userDirectory = System.getProperty("user.home");//Retrieve a string with user home directory
    	
    	Path backupDirectory = outputDirectory.resolve("Backup - " + today);
    	Path finalOutputDirectory = backupDirectory.resolve("Organized");
   
        mapFileExtension(inputDirectory);
        sortFilesByType(inputDirectory, finalOutputDirectory);
    }

}
