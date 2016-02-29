package main;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;


public class P_Sobre extends JFrame{
	I_Video i;
	private JPanel panel;
	private JLabel lblSincronizaoDeDados;
	private JPanel panel_1;
	private JLabel lblAerglifoPrograma;
	private JLabel lblVersobeta;
	private JLabel lblAtenoProgramaEm;
	private JLabel lblCoisasEstranhasPodem;
	private JLabel lblFeitoPorCu;
	private JLabel label;
	private JLabel lblProgramaoLeonardoMariga;
	private JLabel lblContribuies;
	private JLabel lblNewLabel;
	private JLabel lblCarolineMariga;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnLicenacones;
	private JPanel panel_4;

	
	
	public P_Sobre() {
		super("Aeróglifo");
		setPreferredSize(new Dimension(600, 675));
		setTitle("Aeróglifo - Sobre o programa");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblSincronizaoDeDados = new JLabel("Sobre o programa");
		lblSincronizaoDeDados.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSincronizaoDeDados.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSincronizaoDeDados, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow][][][][][][][][][][][][][][]"));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblAtenoProgramaEm = new JLabel("ATEN\u00C7\u00C3O: Programa em constru\u00E7\u00E3o");
		panel_2.add(lblAtenoProgramaEm, BorderLayout.NORTH);
		lblAtenoProgramaEm.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAtenoProgramaEm.setIcon(new ImageIcon(P_Sobre.class.getResource("/icons/Sobre/Disco.png")));
		lblAtenoProgramaEm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtenoProgramaEm.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAtenoProgramaEm.setForeground(Color.RED);
		lblAtenoProgramaEm.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblCoisasEstranhasPodem = new JLabel("Coisas estranhas podem acontecer aqui...");
		lblCoisasEstranhasPodem.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblCoisasEstranhasPodem, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_3, "cell 0 1,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		lblAerglifoPrograma = new JLabel("  Aer\u00F3glifo  - Programa de visualiza\u00E7\u00E3o, edi\u00E7\u00E3o e an\u00E1lise de dados de telemetria");
		panel_3.add(lblAerglifoPrograma, "cell 0 0");
		lblAerglifoPrograma.setIcon(new ImageIcon(P_Sobre.class.getResource("/icons/Main/Icone.png")));
		
		lblVersobeta = new JLabel("Vers\u00E3o 1.0.0 (Beta)");
		lblVersobeta.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblVersobeta, "flowy,cell 0 1,growx");
		
		lblFeitoPorCu = new JLabel("Feito por:   C\u00E9u Azul Aeronaves");
		lblFeitoPorCu.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblFeitoPorCu, "cell 0 1,growx");
		
		label = new JLabel("");
		panel_3.add(label, "cell 0 1,growx");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(P_Sobre.class.getResource("/icons/Sobre/CeuAzul.png")));
		
		lblContribuies = new JLabel("Contribui\u00E7\u00F5es:");
		lblContribuies.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblContribuies, "cell 0 1,growx");
		
		lblProgramaoLeonardoMariga = new JLabel("Leonardo Mariga (leomariga@gmail.com)");
		lblProgramaoLeonardoMariga.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblProgramaoLeonardoMariga, "cell 0 1,growx");
		
		lblNewLabel = new JLabel("Rafael Ara\u00FAjo Lehmkuhl (rafael.lehmkuhl93@gmail.com)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel, "cell 0 1,growx");
		
		lblCarolineMariga = new JLabel("Caroline Mariga (carolmariga@gmail.com)");
		lblCarolineMariga.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblCarolineMariga, "cell 0 1,growx");
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, "cell 0 1,growx");
		
		btnLicenacones = new JButton("Licen\u00E7a \u00EDcones");
		btnLicenacones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Icon Made by Freepik from www.flaticon.com \n"
						+ "Icon Made by Daniel Bruce from www.flaticon.com \n"
						+ "Icon Made by SimpleIcon from www.flaticon.com \n"
						+ "Icon Made by Google from www.flaticon.com \n"
						+ "Icon Made by Pixelvisualization from www.flaticon.com \n"
						+ "Icon Made by Egor Rumyantsev from www.flaticon.com \n"
						+ "Icon Made by Webalys from www.flaticon.com \n");
			}
		});
		panel_4.add(btnLicenacones);
		btnLicenacones.setHorizontalAlignment(SwingConstants.RIGHT);

	}

}