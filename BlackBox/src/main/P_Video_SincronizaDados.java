package main;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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


public class P_Video_SincronizaDados extends JFrame{
	I_Video i;
	private JPanel panel;
	private JLabel lblSincronizaoDeDados;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnSincronizar;
	private JLabel lblMomentoDeDecolagem;
	private JLabel lblMomentoDeDecolagem_1;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblADecolagemAcontece;
	private JTextField tfSegundosVideo;
	private JLabel lblSegundos;
	private JLabel lblADecolagemAcontece_1;
	private JTextField tfDecolagemDados;
	private JComboBox cbEscalaTempo;
	private JButton btnDetectarDecolagemVia;
	private JButton btnDetectarDecolagemVia_1;
	private JLabel lblMinutos;
	private JTextField tfMinutosVideo;
	private JLabel lblE;

	
	
	public P_Video_SincronizaDados(final I_Video i_Video) {
		super("CaSoft");
		i = i_Video;
		setPreferredSize(new Dimension(614, 330));
		setTitle("Sincronização de dados");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblSincronizaoDeDados = new JLabel("Sincroniza\u00E7\u00E3o de dados");
		lblSincronizaoDeDados.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSincronizaoDeDados.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSincronizaoDeDados, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "20[grow]20[grow]20", "[grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(panel_2, "cell 0 0,width 50%,growy");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblMomentoDeDecolagem = new JLabel("Momento de decolagem no v\u00EDdeo");
		lblMomentoDeDecolagem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMomentoDeDecolagem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMomentoDeDecolagem.setIcon(new ImageIcon(P_Video_SincronizaDados.class.getResource("/icons/Main/TelaPc.png")));
		lblMomentoDeDecolagem.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblMomentoDeDecolagem, BorderLayout.NORTH);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new MigLayout("", "[grow][]", "[]15[][]5[][]"));
		
		lblADecolagemAcontece = new JLabel("A decolagem acontece em:");
		panel_4.add(lblADecolagemAcontece, "cell 0 0,growx");
		
		tfMinutosVideo = new JTextField();
		panel_4.add(tfMinutosVideo, "flowx,cell 0 1,growx");
		tfMinutosVideo.setColumns(10);
		
		lblMinutos = new JLabel("Minuto(s)");
		panel_4.add(lblMinutos, "cell 1 1");
		
		lblE = new JLabel("e");
		panel_4.add(lblE, "cell 0 2");
		
		tfSegundosVideo = new JTextField();
		panel_4.add(tfSegundosVideo, "cell 0 3,growx");
		tfSegundosVideo.setColumns(10);
		
		lblSegundos = new JLabel("Segundo(s)");
		panel_4.add(lblSegundos, "cell 1 3");
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(panel_3, "cell 1 0,width 50%,growy");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblMomentoDeDecolagem_1 = new JLabel("Momento de decolagem nos dados");
		lblMomentoDeDecolagem_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblMomentoDeDecolagem_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMomentoDeDecolagem_1.setIcon(new ImageIcon(P_Video_SincronizaDados.class.getResource("/icons/Main/Abrir arquivo de telemetria.png")));
		lblMomentoDeDecolagem_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblMomentoDeDecolagem_1, BorderLayout.NORTH);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new MigLayout("", "[grow]", "[]5[]5[]5[]15[]5[]"));
		
		lblADecolagemAcontece_1 = new JLabel("A decolagem acontece nos:");
		panel_5.add(lblADecolagemAcontece_1, "cell 0 0,growx");
		
		tfDecolagemDados = new JTextField();
		panel_5.add(tfDecolagemDados, "cell 0 1,growx");
		tfDecolagemDados.setColumns(10);
		
		cbEscalaTempo = new JComboBox();
		cbEscalaTempo.setModel(new DefaultComboBoxModel(new String[] {"Segundos", "Milissegundos"}));
		panel_5.add(cbEscalaTempo, "cell 0 2,growx");
		
		cbEscalaTempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(cbEscalaTempo.getSelectedIndex());
				i.salvaEscalaTempo(cbEscalaTempo.getSelectedIndex());
				
			}
		});
		
		btnDetectarDecolagemVia = new JButton("Detectar decolagem via Wow (autom\u00E1tico)");
		btnDetectarDecolagemVia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				i.c.selecionaComoWow(true);
				tfDecolagemDados.setText(i.c.tempoDecolagem+"");
			}
		});
		panel_5.add(btnDetectarDecolagemVia, "cell 0 4,growx");
		
		btnDetectarDecolagemVia_1 = new JButton("Detectar decolagem via Wow (selecionar dado)");
		btnDetectarDecolagemVia_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				i.c.selecionaComoWow(false);
				tfDecolagemDados.setText(i.c.tempoDecolagem+"");
			}
		});
		panel_5.add(btnDetectarDecolagemVia_1, "cell 0 5,growx");
		
		btnSincronizar = new JButton("Sincronizar");
		btnSincronizar.setIcon(new ImageIcon(P_Video_SincronizaDados.class.getResource("/icons/Video/Sincronizar.png")));
		btnSincronizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfMinutosVideo.getText().equals("")) {
					tfMinutosVideo.setText(0+"");
				}
				if(tfSegundosVideo.getText().equals("")) {
					tfSegundosVideo.setText("0");
				}
				if(tfDecolagemDados.getText().equals("")) {
					tfDecolagemDados.setText("0");
				}
				float millisVideo = Float.parseFloat(tfMinutosVideo.getText())*60*1000 + Integer.parseInt(tfSegundosVideo.getText())*1000;
				System.out.println("tempo no vídeo: "+ millisVideo);
				float millisDados = Float.parseFloat(tfDecolagemDados.getText());
				if(cbEscalaTempo.getSelectedIndex()==0) {
					millisDados = millisDados*1000;
				}
				System.out.println("Tempo no dados: "+millisDados);
				System.out.println("O tempo "+millisVideo+" do vídeo é equivalente ao tempo "+millisDados+"dos dados");
				System.out.println("O tempo "+(millisVideo-millisVideo)+" do vídeo é equivalente ao tempo "+(millisDados-millisVideo)+"dos dados");
				
				i.setSincronismo(millisVideo, millisDados);
				dispose();
				
			}
		});
		panel.add(btnSincronizar, BorderLayout.SOUTH);

	}
	
	public void selecionaEscalaDeTempo(int escalaTempo) {
		System.out.println("selecionando "+ escalaTempo);
		if(escalaTempo == -1) {
			escalaTempo = 0;
		}
		cbEscalaTempo.setSelectedIndex(escalaTempo);
		btnSincronizar.requestFocus();
	}





}