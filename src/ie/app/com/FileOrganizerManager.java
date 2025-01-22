package ie.app.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.LinkOption;
import java.nio.file.FileVisitOption;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The FileOrganizer class organizes files by their extensions.
 * It scans files in a directory and sorts them into separate folders.
 */
public class FileOrganizerManager {

    private FilesUtility utility;
    private Map<Path, String> fileExtensionMap = new HashMap<>();

    /**
     * Constructor to initialize the FileOrganizer with a utility instance.
     * @param utility an instance of FilesUtility
     */
    public FileOrganizerManager(FilesUtility utility) {
        this.utility = utility;
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
                fileExtensionMap.put(path, fileExtension);
            });
        } catch (IOException io) {
            System.err.println("Error processing directory: " + inputDirectory + " - " + io.getMessage());
        }
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
            String extension = entry.getValue();
            
            Path relativePath = inputDirectory.relativize(filePath);
            Path extensionDirectory = outputDirectory.resolve(extension);
            Path resolvedOutputFile = extensionDirectory.resolve(relativePath);
            
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
    public void process(Path inputDirectory) throws IOException {
    	
    	String userDirectory = System.getProperty("user.home");//Retrieve a string with user home directory
    	Path backupDirectory = Paths.get(userDirectory).resolve("Backup");
    	Path outputDirectory = backupDirectory.resolve("Organized");
   
        mapFileExtension(inputDirectory);
        sortFilesByType(inputDirectory, outputDirectory);
    }

}
