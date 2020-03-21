package GuiScript;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import LogicScript.ColorMixture;

public class ColorMixtureGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorMixtureGui window = new ColorMixtureGui();
					ColorMixture.setQty1(50);
					ColorMixture.setQty2(50);
					window.frame.setVisible(true);
					try {
						for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								UIManager.setLookAndFeel(info.getClassName());
								break;
							}
						}
					} catch (Exception e) {
						// If Nimbus is not available, you can set the GUI to another look and feel.
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ColorMixtureGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel panelColorChoose = new JPanel();
		panelColorChoose.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelColorChoose.setBounds(10, 11, 357, 329);
		frame.getContentPane().add(panelColorChoose);
		panelColorChoose.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 166, 337, 2);
		panelColorChoose.add(separator);

		JLabel lblAmount2 = new JLabel("Amount :50");
		lblAmount2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAmount2.setBounds(10, 234, 71, 26);
		panelColorChoose.add(lblAmount2);

		JLabel c1Preview = new JLabel("");
		c1Preview.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Color 1 Preview",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
		c1Preview.setBounds(10, 11, 321, 44);
		c1Preview.setOpaque(true);
		panelColorChoose.add(c1Preview);

		JLabel c2Preview = new JLabel("");
		c2Preview.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Color 2 Preview",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
		c2Preview.setBounds(10, 179, 321, 44);
		c2Preview.setOpaque(true);
		panelColorChoose.add(c2Preview);

		JLabel lblAmount1 = new JLabel("Amount :50");
		lblAmount1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAmount1.setBounds(10, 76, 71, 26);
		panelColorChoose.add(lblAmount1);

		JSlider sliderC1 = new JSlider();
		sliderC1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblAmount1.setText("Amount:" + sliderC1.getValue());
				ColorMixture.setQty1(sliderC1.getValue());
			}
		});
		sliderC1.setPaintLabels(true);
		sliderC1.setBounds(85, 76, 262, 36);
		sliderC1.setMajorTickSpacing(10);
		panelColorChoose.add(sliderC1);

		JSlider sliderC2 = new JSlider();
		sliderC2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblAmount2.setText("Amount:" + sliderC2.getValue());

				ColorMixture.setQty2(sliderC2.getValue());
			}
		});
		sliderC2.setPaintLabels(true);
		sliderC2.setBounds(85, 234, 262, 43);
		sliderC2.setMajorTickSpacing(10);
		panelColorChoose.add(sliderC2);

		JButton btnChooseC1 = new JButton("Choose Color");
		btnChooseC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c1 = JColorChooser.showDialog(null, "Choose Color 1", Color.RED);
				ColorMixture.setC1(c1);
				c1Preview.setBackground(c1);

			}
		});
		btnChooseC1.setBounds(10, 123, 141, 32);
		panelColorChoose.add(btnChooseC1);

		JButton btnImagePick1 = new JButton("Pick From Image");
		btnImagePick1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageColorChooser icc = new ImageColorChooser();
				icc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				icc.setModal(true);
				icc.setVisible(true);
				if (ImageColorChooser.chosen != null) {
					ColorMixture.setC1(ImageColorChooser.chosen);
					c1Preview.setBackground(ImageColorChooser.chosen);
				}
				icc.setVisible(false);
			}
		});
		btnImagePick1.setToolTipText("");
		btnImagePick1.setBounds(175, 123, 156, 32);
		panelColorChoose.add(btnImagePick1);

		JButton btnChooseC2 = new JButton(" Choose Color");
		btnChooseC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c2 = JColorChooser.showDialog(null, "Choose Color 2", Color.BLACK);
				c2Preview.setBackground(c2);
				ColorMixture.setC2(c2);
			}
		});
		btnChooseC2.setBounds(10, 288, 141, 32);
		panelColorChoose.add(btnChooseC2);

		JButton btnImagePick2 = new JButton("Pick From Image");
		btnImagePick2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageColorChooser icc = new ImageColorChooser();
				icc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				icc.setModal(true);
				icc.setVisible(true);
				if (ImageColorChooser.chosen != null) {
					ColorMixture.setC2(ImageColorChooser.chosen);
					c2Preview.setBackground(ImageColorChooser.chosen);
				}
				icc.setVisible(false);
			}
		});
		btnImagePick2.setToolTipText("");
		btnImagePick2.setBounds(175, 288, 156, 32);
		panelColorChoose.add(btnImagePick2);

		JLabel mixOutput = new JLabel("");
		mixOutput.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Mix Color Output",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.DARK_GRAY));
		mixOutput.setBounds(480, 11, 167, 329);
		mixOutput.setOpaque(true);
		frame.getContentPane().add(mixOutput);

		JButton btnMix = new JButton("Mix");
		btnMix.setFont(new Font("Sylfaen", Font.PLAIN, 25));
		btnMix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ColorMixture.validateInput()) {
					Color result = ColorMixture.mix();
					mixOutput.setText("");
					mixOutput.setBackground(result);
				} else
					mixOutput.setText("Invalid Input");
			}
		});
		btnMix.setBounds(377, 133, 98, 73);
		frame.getContentPane().add(btnMix);
	}
}
