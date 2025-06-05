package ie.app.com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\fabbroa\\Documents\\FileOrganizer\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 488);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem openDirectoryMenuItem = new JMenuItem("Open");
		openDirectoryMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
					// You might assign this to a field or log it
				}
			}
		});
		fileMenu.add(openDirectoryMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to exit?",
					"Exit Confirmation",
					JOptionPane.YES_NO_OPTION
				);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		fileMenu.add(exitMenuItem);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem versionMenuItem = new JMenuItem("Version");
		versionMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					null,
					"File Organizer\nVersion: 1.0.0\nReleased Date: January 2025\n© 2025 All rights reserved."
				);
			}
		});
		helpMenu.add(versionMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel backupPathLabel = new JLabel("File Backup Path");
		backupPathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backupPathLabel.setBounds(139, 91, 168, 42);
		contentPane.add(backupPathLabel);

		backupPathField = new JTextField();
		backupPathField.setBounds(315, 97, 266, 31);
		contentPane.add(backupPathField);
		backupPathField.setColumns(10);

		JButton selectBackupPathButton = new JButton("Select File Path");
		selectBackupPathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
					backupPathField.setText(selectedDirectory);
				}
			}
		});
		selectBackupPathButton.setBounds(315, 138, 129, 21);
		contentPane.add(selectBackupPathButton);

		JLabel organizePathLabel = new JLabel("File Organize Path");
		organizePathLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		organizePathLabel.setBounds(139, 204, 168, 42);
		contentPane.add(organizePathLabel);

		organizePathField = new JTextField();
		organizePathField.setColumns(10);
		organizePathField.setBounds(315, 210, 266, 31);
		contentPane.add(organizePathField);

		JButton selectOrganizePathButton = new JButton("Select File Path");
		selectOrganizePathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
					organizePathField.setText(selectedDirectory);
				}
			}
		});
		selectOrganizePathButton.setBounds(315, 251, 129, 21);
		contentPane.add(selectOrganizePathButton);
	}
}
