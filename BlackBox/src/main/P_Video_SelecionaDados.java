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
import javax.swing.ImageIcon;


public class P_Video_SelecionaDados extends JFrame{
	
	JButton btnSelecionarYaw = new JButton("Selecionar Yaw");
	JButton btnSelecionarPitch = new JButton("Selecionar Pitch");
	JButton btnSelecionarRoll = new JButton("Selecionar Roll");
	JButton btnSelecionarVelocidade = new JButton("Selecionar Velocidade");
	JButton btnSelecionarPosioX = new JButton("Selecionar Posi\u00E7\u00E3o X");
	JButton btnSelecionarPosioY = new JButton("Selecionar Posi\u00E7\u00E3o Y");
	JButton btnSelecionarPresso = new JButton("Selecionar Press\u00E3o");
	JButton btnSelecionarAltura = new JButton("Selecionar Altura");
	JButton btnSelecionarRpm = new JButton("Selecionar RPM");
	JButton btnSelecionarDeflexoProfundor = new JButton("Selecionar Deflex\u00E3o Profundor");
	JButton btnSelecionarDeflexoLeme = new JButton("Selecionar Deflex\u00E3o Leme");
	JButton btnSelecionarDeflexoAileron = new JButton("Selecionar Deflex\u00E3o Aileron");
	JButton btnSelecionarWow = new JButton("Selecionar Wow");


	JButton btnSelecionarGrfico = new JButton("Selecionar Gr\u00E1fico 1");
	JButton btnSelecionarGrfico_1 = new JButton("Selecionar Gr\u00E1fico 2");
	JButton btnSelecionarGrfico_2 = new JButton("Selecionar Gr\u00E1fico 3");
	JButton btnSelecionarGrfico_3 = new JButton("Selecionar Gr\u00E1fico 4");
	JButton btnSelecionarGrfico_4 = new JButton("Selecionar Gr\u00E1fico 5");
	JButton btnSelecionarGrfico_5 = new JButton("Selecionar Gr\u00E1fico 6");
	JButton btnCarregar = new JButton("Carregar para tela");
	I_Video i;

	
	
    final static int YAW = 0;
    final static int PITCH = 1;
    final static int ROLL = 2;	
    final static int VELOCIDADE = 3;
    final static int POSICAOX = 4;    
    final static int POSICAOY = 5;    
    final static int PRESSAO = 6;
    final static int ALTURA = 7;
    final static int RPM = 8;
    final static int WOW = 9;
    final static int DEFLEXAOPROFUNDOR = 10;
    final static int DEFLEXAOLEME = 11;
    final static int DEFLEXAOAILERON = 12;
	
    final static int GRAFICO1 = 0;
    final static int GRAFICO2 = 1;
    final static int GRAFICO3 = 2;	
    final static int GRAFICO4 = 3;
    final static int GRAFICO5 = 4;    
    final static int GRAFICO6 = 5; 
	JComboBox cbEscalaTempo = new JComboBox();

	
	
	public P_Video_SelecionaDados(final I_Video i_Video) {
		super("CaSoft");
		i = i_Video;
		setTitle("Seleção de dados");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSeleoDeDados = new JLabel("Sele\u00E7\u00E3o de dados");
		lblSeleoDeDados.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSeleoDeDados.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSeleoDeDados, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Medidores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.add(panel_2, "cell 0 0,width 100%,height 50%");
		panel_2.setLayout(new MigLayout("", "[][grow][][][][]", "[][][][][][][][][][][][]"));
		
		JLabel lblTempoescala = new JLabel("Tempo (Escala):");
		lblTempoescala.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Tempo.png")));
		panel_2.add(lblTempoescala, "cell 0 0,alignx left");
		
		cbEscalaTempo.setModel(new DefaultComboBoxModel(new String[] {"Segundos", "Milissegundos"}));
		panel_2.add(cbEscalaTempo, "cell 1 0,growx");
		
		JLabel lblSelecioneAEscala = new JLabel("(Escala utilizada nos dados)");
		lblSelecioneAEscala.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel_2.add(lblSelecioneAEscala, "cell 2 0 2 1");
		
		JLabel lblYaw = new JLabel("Yaw (B\u00FAssola):");
		lblYaw.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Yaw.png")));
		panel_2.add(lblYaw, "cell 0 1");
		btnSelecionarYaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				i.selecionaSensorParaDado(YAW);
			}
		});
		
		panel_2.add(btnSelecionarYaw, "cell 1 1");
		
		JLabel lblPitch = new JLabel("Pitch (Horizonte):");
		lblPitch.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Pitch.png")));
		panel_2.add(lblPitch, "cell 0 2");
		btnSelecionarPitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(PITCH);				
			}
		});
		
		panel_2.add(btnSelecionarPitch, "cell 1 2");
		
		JLabel lblRoll = new JLabel("Roll (Horizonte):");
		lblRoll.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Roll.png")));
		panel_2.add(lblRoll, "cell 2 2");
		btnSelecionarRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(ROLL);				
			}
		});
		
		panel_2.add(btnSelecionarRoll, "cell 3 2");
		
		JLabel lblVelocidadevelocmetro = new JLabel("Medidor 1 (Veloc\u00EDmetro):");
		lblVelocidadevelocmetro.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Velocidade.png")));
		panel_2.add(lblVelocidadevelocmetro, "cell 0 3");
		btnSelecionarVelocidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(VELOCIDADE);
				
			}
		});
		
		panel_2.add(btnSelecionarVelocidade, "cell 1 3");
		
		JLabel lblPosioX = new JLabel("Posi\u00E7\u00E3o X (GPS):");
		lblPosioX.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Gps.png")));
		panel_2.add(lblPosioX, "cell 0 4");
		btnSelecionarPosioX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(POSICAOX);		
				
			}
		});
		
		panel_2.add(btnSelecionarPosioX, "cell 1 4");
		
		JLabel lblPosioY = new JLabel("Posi\u00E7\u00E3o Y (GPS):");
		lblPosioY.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Gps.png")));
		panel_2.add(lblPosioY, "cell 2 4");
		btnSelecionarPosioY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(POSICAOY);
				
			}
		});
		
		panel_2.add(btnSelecionarPosioY, "cell 3 4");
		
		JLabel lblPressobarmetro = new JLabel("Medidor 2 (Bar\u00F4metro)");
		lblPressobarmetro.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Barometro.png")));
		panel_2.add(lblPressobarmetro, "cell 0 5");
		btnSelecionarPresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(PRESSAO);
				
			}
		});
		
		panel_2.add(btnSelecionarPresso, "cell 1 5");
		
		JLabel lblAlturaaltmetro = new JLabel("Medidor 3 (Alt\u00EDmetro)");
		lblAlturaaltmetro.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Altimetro.png")));
		panel_2.add(lblAlturaaltmetro, "cell 0 6");
		btnSelecionarAltura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(ALTURA);	
				
			}
		});
		
		panel_2.add(btnSelecionarAltura, "cell 1 6");
		
		JLabel lblRpm = new JLabel("Medidor 3 (RPM)");
		lblRpm.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/RPM.png")));
		panel_2.add(lblRpm, "cell 0 7");
		btnSelecionarRpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(RPM);
				
			}
		});
		
		panel_2.add(btnSelecionarRpm, "cell 1 7");
		
		JLabel lblDeflexoProfundor = new JLabel("Medidor 4 (Fator de carga X)");
		lblDeflexoProfundor.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Deflexao.png")));
		panel_2.add(lblDeflexoProfundor, "cell 0 8");
		btnSelecionarDeflexoProfundor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(DEFLEXAOPROFUNDOR);
				
			}
		});
		
		panel_2.add(btnSelecionarDeflexoProfundor, "cell 1 8");
		
		JLabel lblDeflexoLeme = new JLabel("Medidor 5 (Fator de carga Y)");
		lblDeflexoLeme.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Deflexao.png")));
		panel_2.add(lblDeflexoLeme, "cell 2 8");
		btnSelecionarDeflexoLeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(DEFLEXAOLEME);
			}
		});
		
		panel_2.add(btnSelecionarDeflexoLeme, "cell 3 8");
		
		JLabel lblDeflexoAileron = new JLabel("Medidor 5 (Fator de carga Z)");
		lblDeflexoAileron.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Deflexao.png")));
		panel_2.add(lblDeflexoAileron, "cell 0 9");
		btnSelecionarDeflexoAileron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(DEFLEXAOAILERON);
				
			}
		});
		
		panel_2.add(btnSelecionarDeflexoAileron, "cell 1 9");
		
		JLabel lblWow = new JLabel("Wow");
		lblWow.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Wow.png")));
		panel_2.add(lblWow, "cell 0 10");
		btnSelecionarWow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaSensorParaDado(WOW);
				
			}
		});
		
		panel_2.add(btnSelecionarWow, "cell 1 10");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Gr\u00E1ficos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.add(panel_3, "cell 0 1,width 100%,height 50%");
		panel_3.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][]"));
		
		JLabel lblGrfico = new JLabel("Gr\u00E1fico 1:");
		lblGrfico.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblGrfico, "cell 0 0");
		btnSelecionarGrfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(0);
			}
		});
		
		panel_3.add(btnSelecionarGrfico, "cell 1 0");
		
		JLabel lblGrfico_1 = new JLabel("Gr\u00E1fico 2:");
		lblGrfico_1.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblGrfico_1, "cell 2 0");
		btnSelecionarGrfico_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(1);
			}
		});
		
		panel_3.add(btnSelecionarGrfico_1, "cell 3 0");
		
		JLabel lblGrfico_2 = new JLabel("Gr\u00E1fico 3:");
		lblGrfico_2.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblGrfico_2, "cell 4 0");
		btnSelecionarGrfico_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(2);
				
			}
		});
		
		panel_3.add(btnSelecionarGrfico_2, "cell 5 0");
		
		JLabel lblGrfico_3 = new JLabel("Gr\u00E1fico 4:");
		lblGrfico_3.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblGrfico_3, "cell 0 1");
		btnSelecionarGrfico_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(3);
				
			}
		});
		
		panel_3.add(btnSelecionarGrfico_3, "cell 1 1");
		
		JLabel lblSelecionarGrfico = new JLabel("Gr\u00E1fico 5:");
		lblSelecionarGrfico.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblSelecionarGrfico, "cell 2 1");
		btnSelecionarGrfico_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(4);
				
			}
		});
		
		panel_3.add(btnSelecionarGrfico_4, "cell 3 1");
		
		JLabel lblGrfico_4 = new JLabel("Gr\u00E1fico 6:");
		lblGrfico_4.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/Graficos.png")));
		panel_3.add(lblGrfico_4, "cell 4 1");
		btnSelecionarGrfico_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i.selecionaDadoParaGrafico(5);
				
			}
		});
		
		panel_3.add(btnSelecionarGrfico_5, "cell 5 1");
		btnCarregar.setIcon(new ImageIcon(P_Video_SelecionaDados.class.getResource("/icons/SelecaoDados/CarregarTela.png")));
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i_Video.carregaParaVideo();
				dispose();
				
			}
		});
		
		panel.add(btnCarregar, BorderLayout.SOUTH);

		cbEscalaTempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(cbEscalaTempo.getSelectedIndex());
				i.salvaEscalaTempo(cbEscalaTempo.getSelectedIndex());
				
			}
		});
	}
	
	


	public void mudaBotao(boolean botaoSensor, int botao, String texto, boolean escritaPreta) {
		if(botaoSensor) {
	    switch (botao) {
	        case YAW:
	            mudaBotao(btnSelecionarYaw, texto, escritaPreta);
	            break;
	        case PITCH:
	            mudaBotao(btnSelecionarPitch, texto, escritaPreta);
	            break;
	        case ROLL:
	            mudaBotao(btnSelecionarRoll, texto, escritaPreta);
	            break;
	        case VELOCIDADE:
	            mudaBotao(btnSelecionarVelocidade, texto, escritaPreta);
	            break;
	        case POSICAOX:
	            mudaBotao(btnSelecionarPosioX, texto, escritaPreta);
	            break;
	        case POSICAOY:
	            mudaBotao(btnSelecionarPosioY, texto, escritaPreta);
	            break;
	         case PRESSAO:
		        mudaBotao(btnSelecionarPresso, texto, escritaPreta);
	            break;
	        case ALTURA:
	            mudaBotao(btnSelecionarAltura, texto, escritaPreta);
	            break;
	        case RPM:
	            mudaBotao(btnSelecionarRpm, texto, escritaPreta);
	            break;
	        case WOW:
	            mudaBotao(btnSelecionarWow, texto, escritaPreta);
	            break;
	        case DEFLEXAOPROFUNDOR:
	            mudaBotao(btnSelecionarDeflexoProfundor, texto, escritaPreta);
	            break;
	        case DEFLEXAOLEME:
	            mudaBotao(btnSelecionarDeflexoLeme, texto, escritaPreta);
	            break;
	        case DEFLEXAOAILERON:
	            mudaBotao(btnSelecionarDeflexoAileron, texto, escritaPreta);
	            break;
	        default:
	             System.out.println("wtf?");
	     }
		}else {
			switch (botao) {
		        case GRAFICO1:
		            mudaBotao(btnSelecionarGrfico, texto, escritaPreta);
		            break;
		        case GRAFICO2:
		            mudaBotao(btnSelecionarGrfico_1, texto, escritaPreta);
		            break;
		        case GRAFICO3:
		            mudaBotao(btnSelecionarGrfico_2, texto, escritaPreta);
		            break;
		        case GRAFICO4:
		            mudaBotao(btnSelecionarGrfico_3, texto, escritaPreta);
		            break;
		        case GRAFICO5:
		            mudaBotao(btnSelecionarGrfico_4, texto, escritaPreta);
		            break;
		        case GRAFICO6:
		            mudaBotao(btnSelecionarGrfico_5, texto, escritaPreta);
		            break;
			}

		}
	}


	private void mudaBotao(JButton btn, String texto, boolean escritaPreta) {
		btn.setText(texto);
		if(!escritaPreta) {
			btn.setForeground(Color.GRAY);
		}else {
			btn.setForeground(Color.BLACK);
		}
		
	}




	public void selecionaEscalaDeTempo(int escalaTempo) {
		System.out.println("selecionando "+ escalaTempo);
		if(escalaTempo == -1) {
			escalaTempo = 0;
		}
		cbEscalaTempo.setSelectedIndex(escalaTempo);
		btnCarregar.requestFocus();
	}


}