package ie.app.com;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FileOrganizerFrame extends JFrame {

    public FileOrganizerFrame() {
        // Frame properties
        setTitle("File Organizer");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Initialize components
        initializeMenuBar();
    }

    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        openItem.addActionListener(this::handleOpen);
        exitItem.addActionListener(this::handleExit);

        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem versionItem = new JMenuItem("Version");
        
        versionItem.addActionListener(this::handleVersion);

        helpMenu.add(versionItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }
    
    private void handleOpen(ActionEvent e) {
    	JFileChooser fileChooser = new JFileChooser();
    	
    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	
    	// Show the dialog and check user response
        int result = fileChooser.showOpenDialog(this);
        
        //Finish the logic based on the result int
    }

    private void handleExit(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void handleVersion(ActionEvent e) {
    	JOptionPane.showMessageDialog(
    			this, 
    			"File Organizer\n Version: 1.0.0\nReleased Date: january 2025\n" +
    			"© 2025 All rights reserved.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileOrganizerFrame frame = new FileOrganizerFrame();
            frame.setVisible(true);
        });
    }
}
