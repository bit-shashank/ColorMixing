package GuiScript;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;

public class ImageColorChooser extends JDialog {
	JLabel lblImage ;
	BufferedImage workImg;
	static Color chosen;
	ImageColorChooser dialog;
	private final JPanel contentPanel = new JPanel();
	private JLabel colorPreview;
	private JLabel lblChosen;
	
	/**
	 * Create the dialog.
	 */
	public ImageColorChooser() {
		setBounds(100, 100, 665, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
		   lblImage = new JLabel("");
		   lblImage.addMouseListener(new MouseAdapter() {
		   	@Override
		   	public void mousePressed(MouseEvent e) {
		   		if(workImg!=null){
		   			int x=e.getX();
		   			int y=e.getY();
		   			Color c=new Color(workImg.getRGB(x, y));
		   			colorPreview.setBackground(c);
		   			chosen=c;
		   			lblChosen.setBackground(c);
		   		}
		   	}
		   });
		   lblImage.addMouseMotionListener(new MouseMotionAdapter() {
		   	@Override
		   	public void mouseMoved(MouseEvent e) {
		   		if(workImg!=null){
		   			int x=e.getX();
		   			int y=e.getY();
		   			Color c=new Color(workImg.getRGB(x, y));
		   			colorPreview.setBackground(c);
		   		}
		   	}
		   });
			lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
			lblImage.setBounds(10, 10, 629, 339);
			contentPanel.add(lblImage);
		}
		
		/*
		 * Creating the Browse Image button
		 * When clicked opens a file chooser dialog to select a file
		 * and stores the selected image file in a buffered image
		 */
		JButton btnBrowseImage = new JButton("Browse Image");
		btnBrowseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          //filter the files,to show only image files
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          int result = file.showSaveDialog(null);
		           //if the user click on save in Jfilechooser
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
		              String path = selectedFile.getAbsolutePath();
		              ImageIcon icon=ResizeImage(path);
		              lblImage.setIcon(icon);         
		              workImg = new BufferedImage(
		            		  icon.getIconWidth(),
		            		  icon.getIconHeight(),
		            		  BufferedImage.TYPE_INT_RGB);
		            		Graphics g = workImg.createGraphics();
		            		// paint the Icon to the BufferedImage.
		            		icon.paintIcon(null, g, 0,0);
		            		g.dispose();
		          	}
				}
		});
		btnBrowseImage.setBounds(10, 365, 137, 34);
		contentPanel.add(btnBrowseImage);
		
		/*
		 * JLabel to show the preview of selected color.
		 */
		colorPreview = new JLabel("");
		colorPreview.setBorder(new TitledBorder(null, "Color Preview", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
		colorPreview.setBounds(157, 365, 211, 34);
		contentPanel.add(colorPreview);
		colorPreview.setOpaque(true);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chosen==null)
					JOptionPane.showMessageDialog(null, "Please Choose A Color");
					dialog.dispose();
			}
		});
		btnOk.setBounds(481, 365, 61, 34);
		contentPanel.add(btnOk);
		
		/*
		 * Adding the cancel button
		 * When pressed it closes the choose from image dialog
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		btnCancel.setBounds(552, 365, 87, 34);
		contentPanel.add(btnCancel);
		
		lblChosen = new JLabel("");
		lblChosen.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblChosen.setBounds(382, 365, 73, 34);
		contentPanel.add(lblChosen);
		lblChosen.setOpaque(true);
	
		dialog=this;
	}
	
	/*
	 * Reads the image from @imagePath into and ImageIcon object
	 * and resizes the image to fit the container label
	 */
	public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg =img;
        newImg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}

