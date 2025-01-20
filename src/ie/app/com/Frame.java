package ie.app.com;

import java.awt.Color;

import javax.swing.*;

public class Frame {

    public static void main(String[] args) {
        
       JFrame frame = new JFrame();
       frame.setTitle("File Organizer");
       
       frame.setVisible(true);
       frame.setSize(500, 500);
       
       //Close the application when click on frame x.
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setResizable(false);
       
       ImageIcon image = new ImageIcon("logo.jpg");
       frame.setIconImage(image.getImage());
       
       frame.getContentPane().setBackground(Color.DARK_GRAY);
    }
}
