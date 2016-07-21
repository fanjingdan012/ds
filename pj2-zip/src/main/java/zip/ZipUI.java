package zip;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ZipUI extends JFrame {
    public static Zip zip = new Zip();
	public ZipUI() {
		
		//add panel
		
		super("Zip");
		this.setLayout(new BorderLayout());
		Container container = this.getContentPane();		
		JPanel puzzlePanel = new JPanel();
		puzzlePanel.setLayout(new GridLayout(2, 1));		
		container.add(puzzlePanel, BorderLayout.CENTER);	
		
		//set frame
		
		this.setSize(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

        //button Zip		
		JButton loadButton = new JButton("Zip");
		puzzlePanel.add(loadButton);
		loadButton.addActionListener(new ActionListener() {			

			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser openChooser = new JFileChooser(new File("files"));
				openChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = openChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File openFile = openChooser.getSelectedFile();
					zip.zip(openFile,openFile.getParent()+"/");
					JOptionPane.showMessageDialog(null, "Finished!");
				}
				
				
			}
			
			
		});
		
		//button unzip
		
		JButton unZip = new JButton("unZip");
		puzzlePanel.add(unZip);
		unZip.addActionListener(new ActionListener() {			

			public void actionPerformed(ActionEvent arg0) {			
				JFileChooser openChooser = new JFileChooser(new File("files"));
				openChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = openChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File openFile = openChooser.getSelectedFile();
					if(!openFile.getName().substring(openFile.getName().length()-7).equals("zipfile")){
						JOptionPane.showMessageDialog(null, "not a zipfile");
					}		
					zip.unZip(openFile,openFile.getParent()+"/");					
					JOptionPane.showMessageDialog(null, "Finished!");	
				}
				
				
			}
		});
	}
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		JFrame ZipFrame = new ZipUI();
		ZipFrame.setVisible(true);
	}

}
