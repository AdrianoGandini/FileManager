package ie.app.com;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * FileBackupManager is responsible for backing up files and directories 
 * from a source directory to a target directory, preserving the directory structure.
 */
public class FileBackupManager {

    /**
     * Creates a directory if it does not already exist.
     *
     * @param targetDir the directory path to be created
     * @throws IOException if an I/O error occurs
     */
    private void createDirectoryIfNeeded(Path targetDir) throws IOException {
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir); // Create the directory, including parent directories if necessary
            System.out.println("Directory " + targetDir + " created successfully!");
        }
    }

    /**
     * Resolves the corresponding path in the target directory for a given source path.
     *
     * @param sourcePath the current path being processed in the source directory
     * @param sourceDir the root of the source directory
     * @param targetDir the root of the target directory
     * @return the resolved target path
     */
    private Path resolveDirectory(Path sourcePath, Path sourceDir, Path targetDir) {
        Path relativePath = sourceDir.relativize(sourcePath); // Get the relative path of the source
        return targetDir.resolve(relativePath); // Resolve it against the target directory
    }

    /**
     * Processes a single path, creating directories in the target if needed or copying files.
     *
     * @param sourcePath the current path being processed
     * @param sourceDir the root of the source directory
     * @param targetDir the root of the target directory
     */
    private void processPath(Path sourcePath, Path sourceDir, Path targetDir) {
        Path targetPath = resolveDirectory(sourcePath, sourceDir, targetDir); // Resolve the corresponding path in the target directory

        try {
            if (Files.isDirectory(sourcePath)) {
                // If it's a directory, ensure the corresponding directory exists in the target
                createDirectoryIfNeeded(targetPath);
            } else if (Files.isRegularFile(sourcePath)) {
                // If it's a file, copy it to the target directory
                Files.copy(sourcePath, targetPath);
                System.out.println("File " + targetPath + " has been backed up!");
            }
        }catch (FileAlreadyExistsException file) {
        	//Handles file already exist exception.
        	System.out.println("The file " + targetPath +  " already exist");
        }catch (IOException io) {
            // Handle any I/O exceptions and provide feedback.
            System.out.println("An error occurred while copying " + sourcePath + " to " + targetPath + ": " + io.getMessage());
    }}

    /**
     * Walks through the source directory tree and processes each path.
     *
     * @param sourceDir the root of the source directory
     * @param targetDir the root of the target directory
     */
    private void filesBackup(Path sourceDir, Path targetDir) {
        try (Stream<Path> files = Files.walk(sourceDir)) { // Walk the file tree starting at sourceDir
            files.forEach(sourcePath -> processPath(sourcePath, sourceDir, targetDir)); // Process each path
        } catch (IOException io) {
            // Handle any I/O errors during traversal
            System.out.println(
                "An I/O error occurred while accessing source directory " + sourceDir + ": " + io.getMessage());
        }
    }
    
    //Create the target directory in the user folder. As the hole logic is creating in providing the target directory I have to modify the backup
    //Method. Remove the targerDir and create a variable that will hold a path to user folder creating a backup directory there.
    

    /**
     * Performs the backup operation, ensuring the target directory exists before starting.
     *
     * @param sourceDir the root of the source directory
     * @param targetDir the root of the target directory
     * @throws IOException if an I/O error occurs
     */
    public void backup(Path sourceDir) throws IOException {
    	
    	String userDir = System.getProperty("user.home");//Retrieve a string with user home directory
    	Path backupDir = Paths.get(userDir).resolve("Backup");
    	
        if (!Files.exists(backupDir)) {
            createDirectoryIfNeeded(backupDir); // Ensure the target directory exists
        }
        filesBackup(sourceDir, backupDir); // Start the backup process
    }
}
