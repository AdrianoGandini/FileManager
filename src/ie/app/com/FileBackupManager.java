package ie.app.com;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


public class FileBackupManager {
	
	
	private void createDirectoryIfNeeded(Path targetDir) throws IOException {
		if (!Files.exists(targetDir)) {
			Files.createDirectories(targetDir);
			System.out.println("Directory " + targetDir + " created successffuly!");
		}
	}
	
	//Method will return the resolved Path. I am taking is consideration that the targetDir is in the same root directory. 
	private Path resolveDirectory(Path sourcePath,Path sourceDir, Path targetDir) {
		Path relativePath = sourceDir.relativize(sourcePath);
		return targetDir.resolve(relativePath);
	}
	
	/*
	 * private void processPath(Path sourcePath, Path sourceDir, Path targetDir) {
	 * Path targetPath = resolveDirectory(sourcePath, sourceDir, targetDir);
	 * 
	 * try { if(Files.isDirectory(sourcePath)) { createDirectoryIfNeeded(targetDir);
	 * }else if (Files.isRegularFile(sourcePath)) { Files.copy(sourceDir,
	 * targetPath, null) } } }
	 */

	private void filesBackup(Path sourceDir, Path targetDir){
		try(Stream<Path> files = Files.walk(sourceDir)){
			files.forEach(sourcePath -> {
				Path targetPath = resolveDirectory(sourcePath, sourceDir, targetDir); 
				try {
					//debug
					System.out.println();
					System.out.println("sorce path: " + sourcePath + " / target path is: " + targetPath);
					System.out.println(Files.exists(targetPath));
					System.out.println(Files.exists(sourcePath));
					System.out.println();
					//debug
					Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("File " + targetPath + " has been backup!");
				}catch(IOException io) {
					System.out.println("An error occurred while copying " + sourcePath + " to " + targetPath + ": " + io.getMessage());
				}
			});	
		}catch (IOException io) {
			System.out.println("An I/O error occurred while accessing source directory " + sourceDir + ": " + io.getMessage());
		}		
	}
	
	public void backup(Path sourceDir, Path targetDir) throws IOException {
		if(!Files.exists(targetDir)) {
			createDirectoryIfNeeded(targetDir);
		}
		filesBackup(sourceDir, targetDir);
	}
}
