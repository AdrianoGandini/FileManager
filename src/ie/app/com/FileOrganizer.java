package ie.app.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileOrganizer {

    private FilesUtility utility;
    private Map<Path, String> fileExtensionMap = new HashMap<>();

    public FileOrganizer(FilesUtility utility) {
        this.utility = utility;
    }

    private String getFileExtension(Path path) {
        String fileName = path.getFileName().toString();
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex + 1).toLowerCase();
    }

    private void mapFileExtension(Path inputDirectory) throws IOException {
        if (!Files.exists(inputDirectory) || !Files.isDirectory(inputDirectory)) {
            System.out.println("The provided path is not valid: " + inputDirectory);
            return;
        }
        
        try (Stream<Path> files = Files.walk(inputDirectory)) {
            files.filter(Files::isRegularFile).forEach(path -> {
                String fileExtension = getFileExtension(path);
                fileExtensionMap.put(path, fileExtension);
            });
        } catch (IOException io) {
            System.err.println("Error processing directory: " + inputDirectory + " - " + io.getMessage());
        }
    }

    private void sortFilesByType(Path inputDirectory, Path outputDirectory) throws IOException {
        for (Map.Entry<Path, String> entry : fileExtensionMap.entrySet()) {
            Path filePath = entry.getKey();
            String extension = entry.getValue();
            
            
            Path relativePath = inputDirectory.relativize(filePath);
            Path extensionDirectory = outputDirectory.resolve(extension);
            Path resolvedOutputFile = extensionDirectory.resolve(relativePath);
            
            
            utility.createDirectoryIfNeeded(resolvedOutputFile.getParent());
            
            try {
                Files.copy(filePath, resolvedOutputFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("Error copying file: " + filePath + " to " + resolvedOutputFile + " - " + e.getMessage());
            }
        }
    }

    public void process(Path inputDirectory, Path outputDirectory) throws IOException {
        mapFileExtension(inputDirectory);
        sortFilesByType(inputDirectory, outputDirectory);
    }

    public static void main(String[] args) {
        Path test = Paths.get("Test");
        Path outputTest = Paths.get("OutputTest");
        
        FileOrganizer or = new FileOrganizer(new FilesUtility());
        
        try {
            or.process(test, outputTest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
