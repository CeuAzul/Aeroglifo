package main;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JTable;

import java.awt.FlowLayout;

import javax.swing.ListSelectionModel;

import au.com.bytecode.opencsv.CSVReader;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.plaf.basic.BasicStatusBarUI;
import org.jfree.chart.ChartPanel;
import org.pushingpixels.substance.api.SubstanceColorSchemeBundle;
import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.ui.SubstanceCheckBoxUI;
import org.pushingpixels.substance.internal.ui.SubstanceColorChooserUI;
import org.pushingpixels.substance.internal.utils.SubstanceColorResource;
import org.pushingpixels.substance.internal.utils.SubstanceColorUtilities;
import org.pushingpixels.substance.internal.utils.border.SubstancePaneBorder;
import org.pushingpixels.substance.swingx.SubstanceStatusBarUI;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SplashScreen;

import javax.swing.JToggleButton;
import javax.swing.JWindow;

import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;


public class P_Control extends JFrame{

	DefaultTableModel dtm = new DefaultTableModel(0, 0);
	JTable table = new JTable(dtm);
	JPanel panelNomeDadosBotoes = new JPanel();
	JScrollPane scrollTabela= new JScrollPane(table);
	JScrollPane scrollNomeDados= new JScrollPane(panelNomeDadosBotoes);
	JPanel panelTabela = new JPanel();
	JPanel panelGrafico = new JPanel();
	JPanel panelCard = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel painel_infodados = new JPanel();
	final int TAMANHO_MIN_COLUNA = 100;
	CardLayout cl;
	JPanel painel_plota_grafico = new JPanel();
	JPanel painelUtilidades = new JPanel();
	JPanel painelCorteAtual = new JPanel();
	JLabel lblStatistics = new JLabel("");

    G_Padrao graficoPadrao;
	G_Utilidades_Corte graficoCorteAtual;
	private JTextField textField;
	private JTextField tfPagina;
	final JButton btnExibirSelecionados = new JButton("Esconder selecionados");
	JLabel lblPagina = new JLabel("P\u00E1gina:");
	JButton btnCimaTabela = new JButton("");
	JButton btnBaixoTabela = new JButton("");
	ControlBox c;
	private JTextField tfNovoInicio;
	private JTextField tfNovoFinal;
	private JTextField tfTempoAntes;
	private JTextField tfDepoisDoPouso;
	
	public P_Control(final ControlBox c) {
		super("Aeróglifo");
		this.c = c;

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		//table.setCellSelectionEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(1024, 768));
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		getContentPane().add(panel_1);
		panel_1.setLayout(new MigLayout("insets 0, gapy 0", "[grow,fill]", "[50px]0[grow,fill][][]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 50, 87));

		panel.putClientProperty(SubstanceLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panel_1.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("insets 0", "[]1[]1[]5[grow]1[]1[]", "[grow]"));
		
		JButton btnTabela = new JButton("Tabela");
		btnTabela.putClientProperty(SubstanceLookAndFeel.COLORIZATION_FACTOR, 0);
		btnTabela.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		btnTabela.setToolTipText("Tabela");
		btnTabela.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/Tabela.png")));
		btnTabela.setBackground(SystemColor.menu);
		btnTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCard, "tabela");
			}
		});

		panel.add(btnTabela, "flowx,cell 0 0,alignx left,height 50px");
		
		JButton btnGraficos = new JButton("Gr\u00E1ficos");
		btnGraficos.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		btnGraficos.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/Graficos.png")));
		btnGraficos.setToolTipText("Gr\u00E1ficos");
		btnGraficos.setBackground(SystemColor.menu);
		btnGraficos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("apertou gráficos");
				panelNomeDadosBotoes.removeAll();
			//	painel_plota_grafico.removeAll();
			//	painel_infodados.removeAll();
				cl.show(panelCard, "grafico");

				carregaBotoesDados();

			}
		});
		panel.add(btnGraficos, "cell 1 0,growy");
		
		JButton btnUtilidades = new JButton("");
		btnUtilidades.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		btnUtilidades.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/Outros.png")));
		btnUtilidades.setToolTipText("Utilidades");
		btnUtilidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCard, "utilidades");
			}
		});
		btnUtilidades.setBackground(SystemColor.menu);
		panel.add(btnUtilidades, "cell 2 0,growy");
		
		JButton btnAnaliseVideo = new JButton("An\u00E1lise interativa");
		btnAnaliseVideo.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		btnAnaliseVideo.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/AnaliseVideo.png")));
		btnAnaliseVideo.setToolTipText("An\u00E1lise de v\u00EDdeo");
		btnAnaliseVideo.setBackground(SystemColor.menu);
		btnAnaliseVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ivideo.exibeTelaVideo(true);
			}
		});
		panel.add(btnAnaliseVideo, "cell 4 0,height 45px,aligny top");
		panelCard.setBorder(null);
		

		panel_1.add(panelCard, "cell 0 1,grow");
		panelCard.setLayout(new CardLayout(0, 0));
		
		
		panelCard.add(panelTabela, "tabela");
		panelCard.add(panelGrafico, "grafico");
		panelCard.add(painelUtilidades, "utilidades");

		
		
		panelTabela.setLayout(new MigLayout("", "[][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][grow][][][][][][][][][][][][][][][][][][][][]"));
		
		panelGrafico.setLayout(new MigLayout("", "[shrink 0][grow,fill][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][grow,fill][grow][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("- Dados - ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		panelGrafico.add(lblNewLabel, "cell 0 0,growx");
		scrollNomeDados.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollNomeDados.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollNomeDados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		scrollNomeDados.setBorder(null);
		panelGrafico.add(scrollNomeDados, "cell 0 1 1 28,growx");
		panelNomeDadosBotoes.setLayout(new MigLayout("", "[grow,fill]", "[][]"));
		
		JButton btnAqui = new JButton("aqui");
		btnAqui.setVerticalAlignment(SwingConstants.TOP);
		
				btnAqui.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						G_Padrao graficoPadrao = new G_Padrao();
						List<Float> valx = new ArrayList<Float>();
						List<Float> valy = new ArrayList<Float>();
		
						graficoPadrao.setEixoX("testex");
						graficoPadrao.setEixoY("testey");
						graficoPadrao.setNomeGrafico("nome");
						graficoPadrao.setValores(valx, valy);
						setGraficoPainel(graficoPadrao.getPainelPadrao());
						
					}
				});
				panelNomeDadosBotoes.add(btnAqui, "cell 0 0,growx,aligny top");
		
		
		
		painel_infodados.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGrafico.add(painel_infodados, "cell 1 0 38 29,grow");
		painel_infodados.setLayout(new MigLayout("", "[grow,fill]", "[][grow]"));
		

		painel_plota_grafico.setBorder(new LineBorder(new Color(0, 0, 0)));
		painel_infodados.add(painel_plota_grafico, "cell 0 0,height 80%,grow");
		painel_plota_grafico.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		painel_infodados.add(panel_2, "cell 0 1,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel painel_guia_grafico = new JPanel();
		painel_guia_grafico.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.add(painel_guia_grafico, BorderLayout.NORTH);
		painel_guia_grafico.setLayout(new MigLayout("", "[left][][][][grow][][right][right][right]", "[grow]"));
		
		
		JButton btnAdicionarDado = new JButton("");
		btnAdicionarDado.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Graficos/Adicionar.png")));
		btnAdicionarDado.setToolTipText("Adicionar gr\u00E1fico de dado");
		btnAdicionarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = c.abreSelecaoIdDado("Qual dado deseja adicionar ao gráfico");
				graficoPadrao.adicionaDadoGrafico(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor(), GerenteDeDados.dado.get(id).getValor(), GerenteDeDados.dado.get(id).getNomeDado());
			}
		});
		btnAdicionarDado.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btnAdicionarDado, "cell 0 0");
		
		
		JButton btn_Wow_SegundoItem = new JButton("Mostrar decolagem e pouso");
		btn_Wow_SegundoItem.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Graficos/Decolagem.png")));
		btn_Wow_SegundoItem.setToolTipText("Exibir decolagem e pouso");
		
		//Create the popup menu.
        final JPopupMenu popGrafico = new JPopupMenu();
        popGrafico.add(new JMenuItem(new AbstractAction("Detecção automática") {
            public void actionPerformed(ActionEvent e) {
				c.selecionaComoWow(true);
				exibeRegiaoNoGraficoPadrao();
            }
        }));
        popGrafico.add(new JMenuItem(new AbstractAction("Selecionar dado para Wow") {
            public void actionPerformed(ActionEvent e) {
				c.selecionaComoWow(false);
				exibeRegiaoNoGraficoPadrao();
            }
        }));
        popGrafico.add(new JMenuItem(new AbstractAction("Manual") {
            public void actionPerformed(ActionEvent e) {
				if(c.pedeWowManual()) {
					exibeRegiaoNoGraficoPadrao();
				}
            }
        }));
		
		btn_Wow_SegundoItem.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	popGrafico.show(e.getComponent(), e.getX(), e.getY());
            }
        });
		btn_Wow_SegundoItem.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btn_Wow_SegundoItem, "cell 8 0,alignx right");
		
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		lblStatistics.setVerticalAlignment(SwingConstants.TOP);
		
		
		lblStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		panel_10.add(lblStatistics);
		

		
		cl = (CardLayout)(panelCard.getLayout());
		cl.show(panelCard, "tabela");
		
		JPanel panelTabOpt = new JPanel();
		panelTabOpt.setBorder(null);
		panelTabela.add(panelTabOpt, "flowx,cell 0 0 4 22,grow");
		panelTabOpt.setLayout(new MigLayout("", "[52px,grow]", "[26px][][][][][][grow]"));
		
		final JButton btnExibirTodos = new JButton("Exibir todos");
		panelTabOpt.add(btnExibirTodos, "cell 0 0,growx");
		
		btnExibirTodos.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	c.exibeTodosDadosIndicadosTabela();
	        	btnExibirSelecionados.setEnabled(true);
	        	btnExibirTodos.setEnabled(false);
	        	
	    }});
		
		

		
		
		panelTabela.add(scrollTabela, "cell 4 1 29 21,grow");
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuIniciar = new JMenu("Iniciar");
		menuIniciar.setDisabledIcon(null);
		menuBar.add(menuIniciar);
		
		JMenu menuDados = new JMenu("Dados");
		menuBar.add(menuDados);
		
		
		JMenuItem abrir = new JMenuItem(new AbstractAction("Abrir arquivo de telemetria") {
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		    	int result = fileChooser.showOpenDialog(P_Control.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    File selectedFile = fileChooser.getSelectedFile();
		    	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    	    try {
						leArquivo(selectedFile.getAbsolutePath(), c);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	//    c.itxt.setJanelaAberta(true);
		    	}
		    }
		});
		abrir.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/Abrir arquivo de telemetria.png")));
		menuIniciar.add(abrir);
		
		JMenuItem mntmSobre = new JMenuItem(new AbstractAction("Sobre") {
		    public void actionPerformed(ActionEvent e) {
		    	c.exibeSobre();
		    	
		    	
		    }
		});
		mntmSobre.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/three-men.png")));
		menuIniciar.add(mntmSobre);
		
		JMenuItem adicionarDado = new JMenuItem(new AbstractAction("Novo dado") {
		    public void actionPerformed(ActionEvent e) {
		    	c.idado.exibeTelaAdicionaDado(true);
		    }
		});
		adicionarDado.setText("Editar dados");
		adicionarDado.setActionCommand("Editar dados");
		adicionarDado.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/bird157.png")));
		menuDados.add(adicionarDado);		
		
		
		
				panelTabOpt.add(btnExibirSelecionados, "cell 0 1");
				
				
				btnExibirSelecionados.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	c.escondeDadosIndicadosTabela();
	        	btnExibirTodos.setEnabled(true);
	        	btnExibirSelecionados.setEnabled(false);
	        	
	    }});
				btnExibirSelecionados.setEnabled(false);
				
				JLabel lblViajarParaO = new JLabel("Viajar para o tempo:");
				panelTabOpt.add(lblViajarParaO, "cell 0 2");
				
				textField = new JTextField();
				panelTabOpt.add(textField, "cell 0 3,growx");
				textField.setColumns(10);
				
				JButton btnIr = new JButton("Buscar dado");
				btnIr.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Tabela/Busca.png")));
				btnIr.setToolTipText("Ir");
				btnIr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						c.vaiProTempo(Float.parseFloat(textField.getText()));
					}
				});
				panelTabOpt.add(btnIr, "cell 0 4,growx");
				
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tabela:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
				panelTabOpt.add(panel_3, "cell 0 6");
				panel_3.setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][]"));
				btnCimaTabela.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Tabela/Cima.png")));
				

				btnCimaTabela.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						c.moveTabela(false);
					}
				});
				panel_3.add(btnCimaTabela, "cell 1 0 3 1,growx");
				
				
				lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
				panel_3.add(lblPagina, "cell 1 1 3 1,growx");
				
				tfPagina = new JTextField();
				panel_3.add(tfPagina, "cell 1 2,growx");
				tfPagina.setColumns(10);
				
				JButton btnIrPagina = new JButton("Ir");
				btnIrPagina.setIcon(null);
				btnIrPagina.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						c.vaiPraPagina(Integer.parseInt(tfPagina.getText()));
					}
				});
				panel_3.add(btnIrPagina, "cell 3 2");
				btnBaixoTabela.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Tabela/Baixo.png")));
				
				
				btnBaixoTabela.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.moveTabela(true);
					}
				});
				panel_3.add(btnBaixoTabela, "cell 1 3 3 1,growx");
				
				painelUtilidades.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_5 = new JPanel();
				panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
				painelUtilidades.add(panel_5, BorderLayout.WEST);
				panel_5.setLayout(new MigLayout("", "[]", "[][][][]"));
				
				JButton btnCorteInteligente = new JButton("Corte inteligente");
				panel_5.add(btnCorteInteligente, "cell 0 0,growx");
				
				JButton btnJuntarCsv = new JButton("Juntar CSV");
				btnJuntarCsv.setEnabled(false);
				panel_5.add(btnJuntarCsv, "cell 0 1,growx");
				
				JButton btnFiltrarDado = new JButton("Filtrar dado");
				btnFiltrarDado.setEnabled(false);
				panel_5.add(btnFiltrarDado, "cell 0 2,growx");
				
				JButton btnSalvarEmCsv = new JButton("Salvar em CSV");
				btnSalvarEmCsv.setEnabled(false);
				panel_5.add(btnSalvarEmCsv, "cell 0 3,growx");
				
				JPanel cardUtilidades = new JPanel();
				painelUtilidades.add(cardUtilidades, BorderLayout.CENTER);
				cardUtilidades.setLayout(new CardLayout(0, 0));
				
				JPanel painelCorteInteligente = new JPanel();
				cardUtilidades.add(painelCorteInteligente, "name_119190115204598");
				painelCorteInteligente.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_6 = new JPanel();
				painelCorteInteligente.add(panel_6, BorderLayout.NORTH);
				panel_6.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_7 = new JPanel();
				panel_6.add(panel_7, BorderLayout.NORTH);
				panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
				
				JButton btnSelecionarDadoVisualizacao = new JButton("Selecionar dado para visuliza\u00E7\u00E3o");
				btnSelecionarDadoVisualizacao.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int id = c.abreSelecaoIdDado("Qual dado quer visualizar?");
						graficoCorteAtual = new G_Utilidades_Corte();
						graficoCorteAtual.setEixoX(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getUnidadeDeMedida());
						graficoCorteAtual.setEixoY(GerenteDeDados.dado.get(id).getUnidadeDeMedida());
						graficoCorteAtual.setNomeGrafico(GerenteDeDados.dado.get(id).getNomeDado());
						graficoCorteAtual.setValores(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor(), GerenteDeDados.dado.get(id).getValor());
						graficoCorteAtual.setId(id);
						painelCorteAtual.removeAll();
						painelCorteAtual.add(graficoCorteAtual.getPainelPadrao());
						painelCorteAtual.revalidate();
					}
				});
				btnSelecionarDadoVisualizacao.setHorizontalTextPosition(SwingConstants.CENTER);
				btnSelecionarDadoVisualizacao.setHorizontalAlignment(SwingConstants.LEFT);
				panel_7.add(btnSelecionarDadoVisualizacao);
				
				//Create the popup menu.
		        final JPopupMenu popup = new JPopupMenu();
		        popup.add(new JMenuItem(new AbstractAction("Selecionar Wow automático") {
		            public void actionPerformed(ActionEvent e) {
		            	c.selecionaComoWow(true);
		            	exibeRegiaoNoGraficoCorte();
		            }
		        }));
		        popup.add(new JMenuItem(new AbstractAction("Selecionar dado para Wow") {
		            public void actionPerformed(ActionEvent e) {
		            	c.selecionaComoWow(false);
		            	exibeRegiaoNoGraficoCorte();
		            }
		        }));
				
				JButton btnExibirWow = new JButton("Exibir Wow");
				btnExibirWow.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent e) {
		                popup.show(e.getComponent(), e.getX(), e.getY());
		            }
		        });
				btnExibirWow.setHorizontalAlignment(SwingConstants.LEFT);
				panel_7.add(btnExibirWow);
				
				JPanel panel_4 = new JPanel();
				panel_6.add(panel_4, BorderLayout.CENTER);
				panel_4.setLayout(new MigLayout("", "[][][][]20[grow]", "[grow]20[][][]"));
				
				JPanel panel_9 = new JPanel();
				panel_9.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Corte r\u00E1pido", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
				panel_4.add(panel_9, "cell 4 0 1 4,growy");
				panel_9.setLayout(new MigLayout("", "[grow][grow]", "[][][]"));
				
				JLabel lblTempoAntesDa = new JLabel("Tempo antes da decolagem:");
				panel_9.add(lblTempoAntesDa, "cell 0 0");
				
				JLabel lblTempoDepoisDo = new JLabel("Tempo depois do pouso");
				panel_9.add(lblTempoDepoisDo, "cell 1 0");
				
				tfTempoAntes = new JTextField();
				panel_9.add(tfTempoAntes, "cell 0 1,growx,aligny top");
				tfTempoAntes.setColumns(10);
				
				tfDepoisDoPouso = new JTextField();
				panel_9.add(tfDepoisDoPouso, "cell 1 1,growx");
				tfDepoisDoPouso.setColumns(10);
				
				JButton btnConfirmarTempo = new JButton("Ir");
				btnConfirmarTempo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						  if(NumberUtils.isNumber(tfTempoAntes.getText())) {
						  	 float tempoInicioCorte = c.tempoDecolagem - Float.parseFloat(tfTempoAntes.getText());
					    	 graficoCorteAtual.setInicio(tempoInicioCorte, "Inicio");
					    	 tfNovoInicio.setText(tempoInicioCorte+"");
						  }
						  if(NumberUtils.isNumber(tfDepoisDoPouso.getText())) {
						  	 float tempoFinalCorte = c.tempoPouso + Float.parseFloat(tfDepoisDoPouso.getText());
					    	 graficoCorteAtual.setFim(tempoFinalCorte, "Final");
					    	 tfNovoFinal.setText(tempoFinalCorte+"");
						}
					}
				});
				panel_9.add(btnConfirmarTempo, "cell 0 2 2 1,growx");
				
				JLabel lblNovoInicio = new JLabel("Novo inicio");
				panel_4.add(lblNovoInicio, "cell 0 1,alignx trailing");
				
				tfNovoInicio = new JTextField();
				panel_4.add(tfNovoInicio, "cell 1 1");
				tfNovoInicio.setColumns(10);
				tfNovoInicio.getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void removeUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void insertUpdate(DocumentEvent e) {
					    warn();
					  }

					  public void warn() {
						  if(NumberUtils.isNumber(tfNovoInicio.getText())) {
					    	 graficoCorteAtual.setInicio(Float.parseFloat(tfNovoInicio.getText()), "Inicio");
						  }
					  }
					});
				JLabel lblNovoFinal = new JLabel("Novo final");
				panel_4.add(lblNovoFinal, "cell 2 1,alignx trailing");
				
				tfNovoFinal = new JTextField();
				panel_4.add(tfNovoFinal, "cell 3 1,growx");
				tfNovoFinal.setColumns(10);
				tfNovoFinal.getDocument().addDocumentListener(new DocumentListener() {
					  public void changedUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void removeUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void insertUpdate(DocumentEvent e) {
					    warn();
					  }

					  public void warn() {
						  if(NumberUtils.isNumber(tfNovoFinal.getText())) {
					    	 graficoCorteAtual.setFim(Float.parseFloat(tfNovoFinal.getText()), "Final");
						  }
					  }
					});

				
				JButton btnSelecionarInicioNo = new JButton("Utilizar selecionado como \"inicio\"");
				btnSelecionarInicioNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tfNovoInicio.setText("");
						graficoCorteAtual.setInicio((float) graficoCorteAtual.getUltimoXSelecionado(), "Inicio");
						tfNovoInicio.setText(graficoCorteAtual.getUltimoXSelecionado()+"");
						
					}
				});
				panel_4.add(btnSelecionarInicioNo, "cell 0 2 2 1,growx");
				
				JButton btnSelecionarFinalNo = new JButton("Utilizar selecionado como \"fim\"");
				btnSelecionarFinalNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tfNovoFinal.setText("");
						graficoCorteAtual.setFim((float) graficoCorteAtual.getUltimoXSelecionado(), "Final");
						tfNovoFinal.setText(graficoCorteAtual.getUltimoXSelecionado()+"");
					}
				});
				panel_4.add(btnSelecionarFinalNo, "cell 2 2 2 1,growx");
				
				JPanel panel_8 = new JPanel();
				painelCorteInteligente.add(panel_8, BorderLayout.CENTER);
				panel_8.setLayout(new MigLayout("", "[grow]", "[][grow][][grow][30]"));
				
				JLabel lblAtual = new JLabel("Atual:");
				lblAtual.setFont(new Font("Dialog", Font.BOLD, 15));
				panel_8.add(lblAtual, "cell 0 0");
				

				painelCorteAtual.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_8.add(painelCorteAtual, "cell 0 1,width 100%,height 45%");
				painelCorteAtual.setLayout(new BorderLayout(0, 0));
				
				JButton btnCarregarPreviso = new JButton("Carregar previs\u00E3o");
				btnCarregarPreviso.setEnabled(false);
				btnCarregarPreviso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				panel_8.add(btnCarregarPreviso, "cell 0 2");
				
				JPanel painelCortePrevisao = new JPanel();
				painelCortePrevisao.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_8.add(painelCortePrevisao, "cell 0 3,width 100%,height 45%");
				painelCortePrevisao.setLayout(new BorderLayout(0, 0));
				
				JLabel lblEmConstruo = new JLabel("Em constru\u00E7\u00E3o");
				lblEmConstruo.setHorizontalAlignment(SwingConstants.CENTER);
				lblEmConstruo.setIcon(new ImageIcon(P_Control.class.getResource("/icons/Main/demolition.png")));
				lblEmConstruo.setFont(new Font("Dialog", Font.ITALIC, 20));
				painelCortePrevisao.add(lblEmConstruo, BorderLayout.CENTER);
				
				JButton btnSalvarComoNovo = new JButton("Confirmar corte");
				btnSalvarComoNovo.setEnabled(false);
				panel_8.add(btnSalvarComoNovo, "flowx,cell 0 4,grow");
				
			      JXStatusBar bar = new JXStatusBar();
		//	      bar.setUI(new SubstanceStatusBarUI());
			      JLabel statusLabel = new JLabel("Céu Azul - UFSC");
			      statusLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			      statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			      JXStatusBar.Constraint c1 = new JXStatusBar.Constraint() ;
			    //  JXStatusBar.Constraint c2 = new JXStatusBar.Constraint() ;
			      c1.setFixedWidth(0);

			      bar.add(statusLabel, c1);     // Fixed width of 100 with no inserts
//			      JXStatusBar.Constraint c2 = new JXStatusBarConstraint(JXStatusBar.Constraint.ResizeBehavior.FILL); // Fill with no inserts
			  //    JProgressBar pbar = new JProgressBar();
			  //    bar.add(pbar, c2);            // Fill with no inserts - will use remaining space
				panel_1.add(bar, "cell 0 2,alignx center");
		        toFront();
		        repaint();
	}
	




	private void exibeRegiaoNoGraficoPadrao() {
		graficoPadrao.adicionaMarcacaoIntervalo(c.tempoDecolagem, c.tempoPouso, "Decolagem", "Pouso");
		
	}

	private void exibeRegiaoNoGraficoCorte() {
		graficoCorteAtual.adicionaMarcacaoIntervalo(c.tempoDecolagem, c.tempoPouso, "Decolagem", "Pouso");
		
	}





	protected void carregaBotoesDados() {
		
		int ndadosCadastrados = GerenteDeDados.config.getMaxOrdem() +1;
		for (int i = 0; i < ndadosCadastrados; i++) {
			JButton btnDado = new JButton(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(i)).getNomeDado());
			btnDado.setName(GerenteDeDados.getIndiceTendoOrdem(i)+"");
			panelNomeDadosBotoes.add(btnDado, "cell 0 "+(i)+",growx");
			btnDado.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e){
		        	JButton button = (JButton)e.getSource();
		        	int id = Integer.parseInt(button.getName());
					graficoPadrao = new G_Padrao();
					graficoPadrao.setEixoX(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getUnidadeDeMedida());
					graficoPadrao.setEixoY(GerenteDeDados.dado.get(id).getUnidadeDeMedida());
					graficoPadrao.setNomeGrafico(e.getActionCommand());
					graficoPadrao.setValores(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor(), GerenteDeDados.dado.get(id).getValor());
					graficoPadrao.setId(id);
					atualizaEstatisticas(id);
					setGraficoPainel(graficoPadrao.getPainelPadrao());
		        	
		    }});
			
		}

		
	}

	protected void atualizaEstatisticas(int id) {
		List<Float> valoresDados = GerenteDeDados.dado.get(id).getValor();
		// Get a DescriptiveStatistics instance
		DescriptiveStatistics stats = new DescriptiveStatistics();

		// Add the data from the array
		for( int i = 0; i < valoresDados.size(); i++) {
		        stats.addValue(valoresDados.get(i));
		}
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		double median = stats.getPercentile(50);
		double max = stats.getMax();
		double min = stats.getMin();
		double variancia= stats.getVariance();
		double mediaQuadratica = stats.getQuadraticMean();
		lblStatistics.setText("<html>Estatísticas: <br/> Média: "+mean+" <br/>  Desvio padrão: "+std+" <br/> Mediana: "+median
				+ " <br/> Máximo: "+max+" <br/> Mínimo: "+min+" <br/> Variância: "+variancia+""
						+ " <br/> Média Quadrática: "+mediaQuadratica+"</html>");
		
		
	}





	public void setGraficoPainel(ChartPanel chartPanel) {
		System.out.println("foi?");
		painel_plota_grafico.removeAll();
		painel_plota_grafico.add(chartPanel);
		painel_plota_grafico.revalidate();
	}
	
	
	public void setHeaderTabela(String[] coluna) {
		dtm.setColumnIdentifiers(coluna);
	}
	
	public void addLinhaTabela(String dado[]) {
	//	System.out.println("MERDA");
		dtm.addRow(dado);
	}
	
	public void addColunaTabela(String nomeDado, String dado[]) {
	//	System.out.println("MERDA");
		dtm.addColumn(nomeDado, dado);
	}

	protected void leArquivo(String a, final ControlBox c) throws IOException {
		c.itxt.setDadosTelemetriaTxt("", false);
		 // The name of the file to open.
       String fileName = a;
		int ndadosCadastrados = GerenteDeDados.config.getMaxOrdem() +1;
		if(ndadosCadastrados != 0) {
			if (JOptionPane.showConfirmDialog(null, "Você já possui dados cadastrados.\nDeletar dados antigos?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				   GerenteDeDados.deletaTodosDados();
				} else {
				   return;
				}
		}
       CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
       String [] nextLine;
       int nLinha = 0;
       while ((nextLine = reader.readNext()) != null) {
          c.itxt.associaDados(nextLine, nLinha);
          nLinha++;
       }
       GerenteDeDados.associaValoresDoProgramaAosValoresDosArquivosDeConfiguracao();
       c.atualizaTabela();
       reader.close();
   }
	

	
	
	public int getColunaPeloNome(String columnTitle) {
	    int columnCount = table.getColumnCount();

	    for (int column = 0; column < columnCount; column++) {
	        if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
	            return column;
	        }
	    }

	    return -1;
	}

	public void escondeColuna(int coluna) {
		table.getColumnModel().getColumn(coluna).setMinWidth(0);
		table.getColumnModel().getColumn(coluna).setMaxWidth(0);
	}
	
	public void exibeColuna(int coluna) {
        int width = TAMANHO_MIN_COLUNA; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, coluna);
            Component comp = table.prepareRenderer(renderer, row, coluna);
            width = Math.max(comp.getPreferredSize().width, width);
       //     System.out.println("ESTAPORRA: "+width);
        }

        table.getColumnModel().getColumn(coluna).setMinWidth(100);        
        table.getColumnModel().getColumn(coluna).setMaxWidth(1000); 
        table.getColumnModel().getColumn(coluna).setPreferredWidth(width+50);

    

		
	}
	
	public void arrumaTamanhoColunas() {
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = TAMANHO_MIN_COLUNA; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width, width);
	        }
	        table.getColumnModel().getColumn(column).setPreferredWidth(width+100);
	    }
	}


	public void limpaLista() {
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
	}
	
	
   
    


	public void setaPaginaAtual(int paginaAtual, int totalPaginas) {
		lblPagina.setText("Página: ("+paginaAtual+" de "+totalPaginas+")");
		tfPagina.setText(paginaAtual+"");
		if (paginaAtual == 1) {
			btnCimaTabela.setEnabled(false);
			
		}else {
			btnCimaTabela.setEnabled(true);
		}
		if(paginaAtual == totalPaginas) {
			btnBaixoTabela.setEnabled(false);
		}else {
			btnBaixoTabela.setEnabled(true);
		}
		
	}


	public void selecionaLinha(int registroNaPagina) {
		System.out.println(registroNaPagina);
		table.getSelectionModel().setSelectionInterval(registroNaPagina, registroNaPagina);
		
	}
	   

}

