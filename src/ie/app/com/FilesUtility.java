package ie.app.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesUtility {

	
	/**
     * Creates a directory if it does not already exist.
     *
     * @param targetDir the directory path to be created
     * @throws IOException if an I/O error occurs
     */
	  public void createDirectoryIfNeeded(Path targetDir) throws IOException {
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
	  public Path resolveDirectory(Path sourcePath, Path sourceDir, Path targetDir) {
	        Path relativePath = sourceDir.relativize(sourcePath); // Get the relative path of the source
	        return targetDir.resolve(relativePath); // Resolve it against the target directory
	    }
	  
	  
}
