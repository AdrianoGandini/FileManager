package ie.app.com;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


public class FileBackupManager {
	
	
	private void createDirectory(Path targetDir) {
		try {
			Files.createDirectories(targetDir);
			System.out.println("Directory " + targetDir + " created successffuly!");
		} catch (FileAlreadyExistsException e) {
			System.out.println("Directory " + targetDir + " already exists!");
		} catch (IOException io) {
			System.out.println("An I/O error occurred while creating directory " + targetDir + ": " + io.getMessage());
		}
	}
	
	//Method will return the resolved Path. I am taking is consideration that the targetDir is in the same root directory. 
	private Path resolveDirectory(Path sourcePath,Path sourceDir, Path targetDir) {
		Path relativePath = sourceDir.relativize(sourcePath);
		return targetDir.resolve(relativePath);
	}

	private void filesBeckup(Path sourceDir, Path targetDir){
		try(Stream<Path> files = Files.walk(sourceDir)){
			files.forEach(sourcePath -> {
				Path targetPath = resolveDirectory(sourcePath, sourceDir, targetDir); 
				try {
					Files.copy(sourcePath, targetPath);
					System.out.println("File " + targetPath + " has been backup!");
				}catch(FileAlreadyExistsException e) {
					System.out.println("File " + targetPath + " already exist");
				}catch(IOException io) {
					System.out.println("Error an IO excepition");
					io.printStackTrace();
				}
			});	
		}catch (IOException io) {
			System.out.println("outer!");
		}
		
	}
	
	public void backup(Path sourceDir, Path targetDir) throws IOException {
		createDirectory(targetDir);
		filesBeckup(sourceDir, targetDir);
	}
}
