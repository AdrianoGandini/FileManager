package ie.app.com;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField backupFilePath;
	private JTextField organizeFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\fabbroa\\Documents\\FileOrganizer\\logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 488);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenMenuItem = new JMenuItem("Open");
		mntmOpenMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		    	
		    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	int result = fileChooser.showOpenDialog(null);
		    	
		    	if(result == fileChooser.APPROVE_OPTION) {
		    		String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
		    	}
			}
		});
		mnFileMenu.add(mntmOpenMenuItem);
		
		JMenuItem mntmExitMenuItem = new JMenuItem("Exit");
		mntmExitMenuItem.addActionListener(new ActionListener() {
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
		mnFileMenu.add(mntmExitMenuItem);
		
		JMenu mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Version");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "File Organizer\n Version: 1.0.0\nReleased Date: january 2025\n" +
		    			"© 2025 All rights reserved.");
			}
		});
		mnHelpMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBackupFilePath = new JLabel("File Backup path");
		lblBackupFilePath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBackupFilePath.setBounds(139, 91, 168, 42);
		contentPane.add(lblBackupFilePath);
		
		backupFilePath = new JTextField();
		backupFilePath.setBounds(315, 97, 266, 31);
		contentPane.add(backupFilePath);
		backupFilePath.setColumns(10);
		
		JButton btnBackupFilePath = new JButton("Select File Path");
		btnBackupFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		    	
		    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	int result = fileChooser.showOpenDialog(null);
		    	
		    	if(result == fileChooser.APPROVE_OPTION) {
		    		String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
		    	}
			}
		});
		btnBackupFilePath.setBounds(315, 138, 129, 21);
		contentPane.add(btnBackupFilePath);
		
		JLabel lblFileOrganizePath = new JLabel("File Organize path");
		lblFileOrganizePath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileOrganizePath.setBounds(139, 204, 168, 42);
		contentPane.add(lblFileOrganizePath);
		
		organizeFilePath = new JTextField();
		organizeFilePath.setColumns(10);
		organizeFilePath.setBounds(315, 210, 266, 31);
		contentPane.add(organizeFilePath);
		
		JButton btnOrganizeFilePath = new JButton("Select File Path");
		btnOrganizeFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		    	
		    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	int result = fileChooser.showOpenDialog(null);
		    	
		    	if(result == fileChooser.APPROVE_OPTION) {
		    		String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
		    	}
			}
		});
		btnOrganizeFilePath.setBounds(315, 251, 129, 21);
		contentPane.add(btnOrganizeFilePath);
	}
}
