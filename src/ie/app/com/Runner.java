package ie.app.com;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {
	public static void main(String[] args) {
		Path source = Paths.get("Test");
        Path target = Paths.get("Backup");
		
		FileBackupManager backup = new FileBackupManager();
		
		try {
			backup.backup(source, target);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
