package ie.app.com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * File Organizer application main frame.
 * 
 * This application provides a GUI for backing up and organizing files
 * from an input directory to an output directory.
 * 
 * @author Adriano Gandini
 * @version 1.0.0
 * @since 2025-01-01
 */

public class FileOrganizerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField backupPathField;
	private JTextField organizePathField;

	
	/**
     * Application entry point.
     * 
     * @param args command line arguments (not used)
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FileOrganizerFrame frame = new FileOrganizerFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private FileBackupManager backupManager;
	private FileOrganizerManager organizerManager;

	/**
	 * Constructor for the main application frame.
	 * Initializes manager classes and sets up all GUI components.
	 */
	public FileOrganizerFrame() {
		backupManager = new FileBackupManager(new FilesUtility());
		organizerManager = new FileOrganizerManager(new FilesUtility(), new ExtensionCategory());
		
		setupFrame();
		setupMenuBar();
		setupContentPane();
		setupInputFields();
	}

	/**
	 * Configures basic window properties such as title, icon, size, and close behavior.
	 */
	private void setupFrame() {
		setTitle("File Organizer");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\fabbroa\\Documents\\FileOrganizer\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 488);
	}

	/**
	 * Creates and configures the menu bar with File and Help menus.
	 * File menu contains Open and Exit options.
	 * Help menu contains Version information.
	 */
	private void setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem openDirectoryMenuItem = new JMenuItem("Open");
		openDirectoryMenuItem.addActionListener(this::handleOpenDirectory);
		fileMenu.add(openDirectoryMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(this::handleExit);
		fileMenu.add(exitMenuItem);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem versionMenuItem = new JMenuItem("Version");
		versionMenuItem.addActionListener(this::showVersionDialog);
		helpMenu.add(versionMenuItem);
	}

	/**
	 * Sets up the main content panel with border and layout.
	 */
	private void setupContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}

	/**
	 * Creates and positions all input fields, labels, and buttons for the main interface.
	 * This includes:
	 * - Source directory selection (backup path)
	 * - Destination directory selection (organize path)  
	 * - Action buttons for directory selection and running backup
	 */
	private void setupInputFields() {
		
		// Select the folder to back up
		JLabel backupPathLabel = new JLabel("File Backup Path");
		backupPathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backupPathLabel.setBounds(139, 91, 168, 42);
		contentPane.add(backupPathLabel);

		// Select the folder to back up text field
		backupPathField = new JTextField();
		backupPathField.setBounds(315, 97, 266, 31);
		contentPane.add(backupPathField);
		backupPathField.setColumns(10);

		// Select the folder to back up button
		JButton selectBackupPathButton = new JButton("Select File Path");
		selectBackupPathButton.setBounds(315, 138, 129, 21);
		selectBackupPathButton.addActionListener(e -> chooseDirectory(backupPathField));
		contentPane.add(selectBackupPathButton);

		// Backup Output Directory path
		JLabel organizePathLabel = new JLabel("Backup Output Path");
		organizePathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		organizePathLabel.setBounds(139, 204, 168, 42);
		contentPane.add(organizePathLabel);

		// Backup Output Directory path text field
		organizePathField = new JTextField();
		organizePathField.setBounds(315, 210, 266, 31);
		contentPane.add(organizePathField);
		organizePathField.setColumns(10);

		// Backup Output Directory button
		JButton selectOrganizePathButton = new JButton("Select File Path");
		selectOrganizePathButton.setBounds(315, 251, 129, 21);
		selectOrganizePathButton.addActionListener(e -> chooseDirectory(organizePathField));
		contentPane.add(selectOrganizePathButton);
		
		//Run application button
		JButton runBackupButton = new JButton("Run");
		runBackupButton.setBounds(315, 290, 168, 21);
		runBackupButton.addActionListener(e -> runBackup());
		contentPane.add(runBackupButton);
	}

	/**
	 * Utility method to open a directory chooser dialog and populate a text field.
	 * This method is reused for both source and destination directory selection.
	 * 
	 * @param targetField The text field to populate with the selected directory path
	 */
	private void chooseDirectory(JTextField targetField) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
			targetField.setText(selectedDirectory);
		}
	}

	// Menu item: "Open"
	private void handleOpenDirectory(ActionEvent e) {
		chooseDirectory(new JTextField());
	}

	// Menu item: "Exit"
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

	/**
	 * Event handler for the "Version" menu item.
	 * Displays application version and copyright information.
	 * 
	 * @param e The action event (not used)
	 */
	private void showVersionDialog(ActionEvent e) {
		JOptionPane.showMessageDialog(
				this,
				"File Organizer\nVersion: 1.0.0\nReleased Date: January 2025\n© 2025 All rights reserved."
		);
	}
	
	/**
	 * Executes the backup and organization process.
	 * This method:
	 * 1. Validates that both paths are selected
	 * 2. Converts string paths to Path objects
	 * 3. Calls backup manager to backup files
	 * 4. Calls organizer manager to organize files
	 * 5. Shows success or error messages
	 */
	private void runBackup() {
		
		// Get the source directory path from the text field
		String inputDirectory = backupPathField.getText();
		Path inputDirectoryPath = Paths.get(inputDirectory);

		
		// Get the destination directory path from the text field 
		String outputDirectory = organizePathField.getText();
		Path outputDirectoryPath = Paths.get(outputDirectory); //Still have to create the logic to accept the output directory. The actual one sent directly to user directory
		
		// Validate that both paths have been selected
		if (inputDirectory.isEmpty() || outputDirectory.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select both paths first.");
	        return;
		}
		
		try {
			// This processes files from input directory and organizes them in output directory
			organizerManager.process(inputDirectoryPath, outputDirectoryPath);
			JOptionPane.showMessageDialog(this, "Backup completed successfully.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error during backup: " + ex.getMessage());
		}
	}
}
