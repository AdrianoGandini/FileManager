package ie.app.com;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


public class FileBackupManager {
	
	

	private void createDirectory(Path directoryPath) {
		try {
			Files.createDirectories(directoryPath);
			System.out.println("Directory " + directoryPath + " created successffuly!");
		} catch (FileAlreadyExistsException e) {
			System.out.println("File aready exist!");
			return;
		} catch (IOException io) {
			System.out.println("An I/O error occured!" + io.getMessage());
		}
	}
	
	private Path resolveDirectory(Path sourceDir, Path sourcetPath, Path targetDir) {
		Path relativePath = sourceDir.relativize(sourcetPath);
		return targetDir.resolve(relativePath);
	}

	private void filesBeckup(Path sourceDir, Path sourcetPath, Path targetDir){
		try(Stream<Path> files = Files.walk(sourceDir)){
			files.forEach(sourcePath -> {
				Path targetPath = resolveDirectory(sourceDir, sourceDir, targetDir);
				try {
					Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
				}catch(IOException io) {
					
				}
			});	
		}catch (IOException io) {
			
		}
		
	}
	
	public void backup(Path sourceDir, Path targetDir) throws IOException {
		createDirectory(targetDir);
		filesBeckup(sourceDir, targetDir);
	}
}
