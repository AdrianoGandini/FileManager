package ie.app.com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class FileOrganizerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField backupPathField;
	private JTextField organizePathField;

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

	public FileOrganizerFrame() {
		setupFrame();
		setupMenuBar();
		setupContentPane();
		setupInputFields();
	}

	// Set up basic window
	private void setupFrame() {
		setTitle("File Organizer");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\fabbroa\\Documents\\FileOrganizer\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 488);
	}

	// Set up top menu bar
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

	// Set up main content pane
	private void setupContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}

	// Add path fields and buttons
	private void setupInputFields() {
		JLabel backupPathLabel = new JLabel("File Backup Path");
		backupPathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backupPathLabel.setBounds(139, 91, 168, 42);
		contentPane.add(backupPathLabel);

		backupPathField = new JTextField();
		backupPathField.setBounds(315, 97, 266, 31);
		contentPane.add(backupPathField);
		backupPathField.setColumns(10);

		JButton selectBackupPathButton = new JButton("Select File Path");
		selectBackupPathButton.setBounds(315, 138, 129, 21);
		selectBackupPathButton.addActionListener(e -> chooseDirectory(backupPathField));
		contentPane.add(selectBackupPathButton);

		JLabel organizePathLabel = new JLabel("File Organize Path");
		organizePathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		organizePathLabel.setBounds(139, 204, 168, 42);
		contentPane.add(organizePathLabel);

		organizePathField = new JTextField();
		organizePathField.setBounds(315, 210, 266, 31);
		contentPane.add(organizePathField);
		organizePathField.setColumns(10);

		JButton selectOrganizePathButton = new JButton("Select File Path");
		selectOrganizePathButton.setBounds(315, 251, 129, 21);
		selectOrganizePathButton.addActionListener(e -> chooseDirectory(organizePathField));
		contentPane.add(selectOrganizePathButton);
	}

	// Common logic for choosing a directory
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
		chooseDirectory(new JTextField()); // You can adapt this logic as needed
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

	// Menu item: "Version"
	private void showVersionDialog(ActionEvent e) {
		JOptionPane.showMessageDialog(
				this,
				"File Organizer\nVersion: 1.0.0\nReleased Date: January 2025\n© 2025 All rights reserved."
		);
	}
}
