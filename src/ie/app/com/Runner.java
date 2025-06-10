package ie.app.com;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {
	public static void main(String[] args) {

		
		String UserInputSorce = args.length > 0 ? args[0] : "Test";
		Path source = Paths.get(UserInputSorce);
		
		
		FileBackupManager backup = new FileBackupManager(new FilesUtility());
		//FileOrganizerManager organizer = new FileOrganizerManager(new FilesUtility());

			try {
				//organizer.process(source);
				backup.backup(source.toAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
		
	}
}
