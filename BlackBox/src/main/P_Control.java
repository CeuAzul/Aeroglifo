package main;
import java.awt.Color;

import javax.swing.AbstractAction;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
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
import org.jfree.chart.ChartPanel;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JToggleButton;

import java.awt.Font;


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
	float tempoDecolagem, tempoPouso;
	G_Padrao graficoPadrao;
	
	public P_Control(final ControlBox c) {
		super("CAsoft");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(1024, 768));
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		getContentPane().add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow,fill]", "[][grow,fill]"));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, "cell 0 0,growx,aligny top");
		panel.setBorder(new TitledBorder(null, "Visualiza\u00E7\u00E3o", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[26px]"));
		
		JButton btnTabela = new JButton("Tabela");
		btnTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCard, "tabela");
			}
		});
		panel.add(btnTabela, "flowx,cell 1 0,alignx left,growy");
		
		JButton btnGraficos = new JButton("Gr\u00E1ficos");
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
		panel.add(btnGraficos, "cell 2 0");
		
		JButton btnDadosDispostos = new JButton("Dados dispostos");
		panel.add(btnDadosDispostos, "cell 3 0");
		
		JButton btnSensores = new JButton("Sensores");
		panel.add(btnSensores, "cell 4 0");
		
		JButton btnCriarSistema = new JButton("Criar sistema");
		panel.add(btnCriarSistema, "cell 5 0");
		

		panel_1.add(panelCard, "cell 0 1,grow");
		panelCard.setLayout(new CardLayout(0, 0));
		
		
		panelCard.add(panelTabela, "tabela");
		panelCard.add(panelGrafico, "grafico");
		
		panelTabela.setLayout(new MigLayout("", "[][][][][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[][grow][][][][][][][][][][][][][][][][][][][][]"));
		
		panelGrafico.setLayout(new MigLayout("", "[shrink 0][grow,fill][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[grow,fill][grow][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		scrollNomeDados.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollNomeDados.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollNomeDados.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollNomeDados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		scrollNomeDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelNomeDadosBotoes.setBackground(SystemColor.control);
		panelGrafico.add(scrollNomeDados, "cell 0 0 1 28, growx");
		panelNomeDadosBotoes.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		JButton btnAqui = new JButton("aqui");
		btnAqui.setVerticalAlignment(SwingConstants.TOP);

		btnAqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				G_Padrao graficoPadrao = new G_Padrao();
				List<String> valx = new ArrayList<String>();
				List<String> valy = new ArrayList<String>();
				valx.add("1");
				valx.add("2");
				valx.add("3");
				valy.add("1");
				valy.add("2");
				valy.add("3");
				graficoPadrao.setEixoX("testex");
				graficoPadrao.setEixoY("testey");
				graficoPadrao.setNomeGrafico("nome");
				graficoPadrao.setValores(valx, valy);
				setGraficoPainel(graficoPadrao.getPainelPadrao());
				
			}
		});
		panelNomeDadosBotoes.add(btnAqui, "growx,aligny top");
		
		
		
		painel_infodados.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGrafico.add(painel_infodados, "cell 1 0 38 28,grow");
		painel_infodados.setLayout(new MigLayout("", "[grow,fill]", "[][grow]"));
		

		painel_plota_grafico.setBorder(new LineBorder(new Color(0, 0, 0)));
		painel_infodados.add(painel_plota_grafico, "cell 0 0,height 70%,grow");
		painel_plota_grafico.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		painel_infodados.add(panel_2, "cell 0 1,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel painel_guia_grafico = new JPanel();
		painel_guia_grafico.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(painel_guia_grafico, BorderLayout.NORTH);
		painel_guia_grafico.setLayout(new MigLayout("", "[left][][][][grow][][right][right][right]", "[grow]"));
		
		JButton btn_Wow_selec = new JButton("Mostrar voo (selecionar dado para wow)");
		btn_Wow_selec.setFont(new Font("Dialog", Font.BOLD, 9));
		btn_Wow_selec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaComoWow(false);
			}
		});
		
		JButton btnAdicionarDado = new JButton("Adicionar dado");
		btnAdicionarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdicionarDado.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btnAdicionarDado, "cell 0 0");
		
		JButton btn_Wow_Manual = new JButton("Mostrar voo (setar Wow manual)");
		btn_Wow_Manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pedeWowManual()) {
					exibeRegiaoNoGrafico();
				}
			}
		});
		
		JButton btnExibiresconderIntervaloDe = new JButton("Exibir/esconder intervalo de voo");
		btnExibiresconderIntervaloDe.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btnExibiresconderIntervaloDe, "cell 1 0");
		btn_Wow_Manual.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btn_Wow_Manual, "cell 6 0,alignx right");
		painel_guia_grafico.add(btn_Wow_selec, "cell 7 0,alignx right");
		
		JButton btn_Wow_SegundoItem = new JButton("Mostrar voo (autom\u00E1tico)");
		btn_Wow_SegundoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaComoWow(true);
			}
		});
		btn_Wow_SegundoItem.setFont(new Font("Dialog", Font.BOLD, 9));
		painel_guia_grafico.add(btn_Wow_SegundoItem, "cell 8 0,alignx right");
		

		
		cl = (CardLayout)(panelCard.getLayout());
		cl.show(panelCard, "tabela");
		
		JPanel panelTabOpt = new JPanel();
		panelTabOpt.setBorder(new TitledBorder(null, "Acesso", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelTabOpt.setBackground(SystemColor.control);
		panelTabela.add(panelTabOpt, "flowx,cell 0 0 4 22,grow");
		panelTabOpt.setLayout(new MigLayout("", "[52px]", "[26px][][]"));
		
		JButton btnTxt = new JButton("Txt");
		panelTabOpt.add(btnTxt, "cell 0 0,growx,aligny top");
		
		final JButton btnExibirTodos = new JButton("Exibir todos");
		panelTabOpt.add(btnExibirTodos, "cell 0 1,growx");
		
		final JButton btnExibirSelecionados = new JButton("Esconder selecionados");

		panelTabOpt.add(btnExibirSelecionados, "cell 0 2");
		
		btnExibirTodos.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	c.exibeTodosDadosIndicadosTabela();
	        	btnExibirSelecionados.setEnabled(true);
	        	btnExibirTodos.setEnabled(false);
	        	
	    }});
		
		
		btnExibirSelecionados.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	c.escondeDadosIndicadosTabela();
	        	btnExibirTodos.setEnabled(true);
	        	btnExibirSelecionados.setEnabled(false);
	        	
	    }});
		
		

		
		
		panelTabela.add(scrollTabela, "cell 4 1 29 21,grow");
		btnTxt.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	c.itxt.setJanelaAberta(true);
	        	
	        	
	        	
	    }});
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuIniciar = new JMenu("Iniciar");
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
		menuIniciar.add(abrir);
		
		JMenuItem adicionarDado = new JMenuItem(new AbstractAction("Novo dado") {
		    public void actionPerformed(ActionEvent e) {
		    	c.idado.exibeTelaAdicionaDado(true);
		    }
		});
		menuDados.add(adicionarDado);		
		btnExibirSelecionados.setEnabled(false);

	}
	
	protected void selecionaComoWow(boolean utilizarOSegundoComoWOW) {
		int id = 1;
		if(!utilizarOSegundoComoWOW) {
			id = abreSelecaoIdDado();
		}
		System.out.println(id);
		procuraRegiaoDeVoo(id);
		exibeRegiaoNoGrafico();
	}

	private void exibeRegiaoNoGrafico() {
		graficoPadrao.adicionaMarcacaoIntervalo(tempoDecolagem, tempoPouso, "Decolagem", "Pouso");
		
	}

	private void procuraRegiaoDeVoo(int id) {
		int tamanho = GerenteDeDados.dado.get(id).getValor().size();
		boolean[] wow = new boolean[tamanho];
		boolean antes = true;
		List<Float> possiveisTemposDeDecolagem = new ArrayList<Float>();
		List<Float> possiveisTemposDePouso = new ArrayList<Float>();
		for (int i = 0; i < wow.length; i++) {
			float numid = Float.parseFloat(GerenteDeDados.dado.get(id).getValor().get(i));
			if(!((numid == 1) || (numid == 0))) {
				JOptionPane.showMessageDialog(null, "Isso não é um dado de Wow (peso nas rodas)\nO dado deve conter apenas 1 e 0");
				return;
			}
			wow[i] = (1 == Integer.parseInt(GerenteDeDados.dado.get(id).getValor().get(i)));
			System.out.println(wow[i]);
			if((antes == true)&&(wow[i]==false)) {																			//Se antes tiver peso nas rodas...
				possiveisTemposDeDecolagem.add(Float.parseFloat(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor().get(i)));
			}
			if((antes == false)&&(wow[i]==true)) {
				possiveisTemposDePouso.add(Float.parseFloat(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor().get(i)));
			}
			antes = wow[i];
		}
		if(possiveisTemposDeDecolagem.size() > possiveisTemposDePouso.size()){	// Se tiver mais decolagens do que pousos, o ultimo instante vira o pouso
			possiveisTemposDePouso.add(Float.parseFloat(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor().get(tamanho-1)));
		}
		List<Float> tempoVoo = new ArrayList<Float>();
		for (int i = 0; i < possiveisTemposDeDecolagem.size(); i++) {
			tempoVoo.add(possiveisTemposDePouso.get(i) - possiveisTemposDeDecolagem.get(i));
		}
		
		System.out.println("tempo de voo: "+Collections.max(tempoVoo));
		int indexMaxTempo = tempoVoo.indexOf(Collections.max(tempoVoo));
		tempoDecolagem = possiveisTemposDeDecolagem.get(indexMaxTempo);
		tempoPouso = possiveisTemposDePouso.get(indexMaxTempo);

	//	System.out.println("número de decolagens: "+possiveisTemposDeDecolagem.get(1));
	//	System.out.println("número de pousos: "+possiveisTemposDePouso.get(1));


		}


	private int abreSelecaoIdDado() {
		int toSize = GerenteDeDados.dado.size();
        String[] stockArr = new String[toSize];

		for (int i = 0; i < toSize; i++) {
		    stockArr[i] = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(i)).getNomeDado();
		}
		
        int ordem =  ask("Selecione o dado", "Qual dado deve ser usado como Wow?", stockArr);
		return  GerenteDeDados.getIndiceTendoOrdem(ordem);
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
					graficoPadrao = new G_Padrao();;
					graficoPadrao.setEixoX(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getUnidadeDeMedida());
					graficoPadrao.setEixoY(GerenteDeDados.dado.get(id).getUnidadeDeMedida());
					graficoPadrao.setNomeGrafico(e.getActionCommand());
					graficoPadrao.setValores(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor(), GerenteDeDados.dado.get(id).getValor());
					graficoPadrao.setId(id);
					setGraficoPainel(graficoPadrao.getPainelPadrao());
		        	
		    }});
			
		}

		
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
		System.out.println("MERDA");
		dtm.addRow(dado);
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
            System.out.println("ESTAPORRA: "+width);
        }

        table.getColumnModel().getColumn(coluna).setMinWidth(100);        
        table.getColumnModel().getColumn(coluna).setMaxWidth(500000); 
        table.getColumnModel().getColumn(coluna).setPreferredWidth(width+20);

    

		
	}
	
	public void arrumaTamanhoColunas() {
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = TAMANHO_MIN_COLUNA; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width, width);
	        }
	        table.getColumnModel().getColumn(column).setPreferredWidth(width+20);
	    }
	}


	public void limpaLista() {
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
	}
	
	
    public static int ask(String titulo, String frase, final String... values) {

        int result = 0;

        if (EventQueue.isDispatchThread()) {

            JPanel panel = new JPanel();
            panel.add(new JLabel(titulo));
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (String value : values) {
                model.addElement(value);
            }
            JComboBox comboBox = new JComboBox(model);
            panel.add(comboBox);

            int iResult = JOptionPane.showConfirmDialog(null, panel, frase, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (iResult) {
                case JOptionPane.OK_OPTION:
                    result = comboBox.getSelectedIndex();
                    break;
            }

        } else {

            Response response = new Response(titulo, frase, values);
                try {
					SwingUtilities.invokeAndWait(response);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                result = response.getResponse();
            

        }

        return result;

    }

    public static class Response implements Runnable {

        private String[] values;
        private int response;
        private String titulo;
        private String frase;

        public Response(String titulo, String frase, String... values) {
            this.values = values;
            this.titulo = titulo;
            this.frase = frase;
        }

        @Override
        public void run() {
            response = ask(titulo, frase, values);
        }

        public int getResponse() {
            return response;
        }
    }
    
    private boolean pedeWowManual() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Instante de decolagem"));
        panel.add(field1);
        panel.add(new JLabel("Instante de pouso"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Selecionar região de voo",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	tempoDecolagem = Float.parseFloat(field1.getText());
        	tempoPouso = Float.parseFloat(field2.getText());
        	return true;																				//Se deu certo retorna como true
        } else {
            return false;
        }
    }
	
	
}

