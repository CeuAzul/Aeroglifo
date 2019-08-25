package main;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import org.apache.commons.lang.ArrayUtils;
import javax.swing.ImageIcon;


public class P_Dado_AdicionaDado extends JFrame{
	private JTextField tfNomeDado;
	private JTextField tfSensor;
	private JTextField tfUnidadeDeMedida;
	JComboBox cbTipodeVariavel;
	JCheckBox chckbxExibeNaTabela;
	JCheckBox chckbxExibeNosBotes;
	JButton btnOk;
	private JScrollPane scrollPane;
	public JList list;
	private JButton btnBaixo;
	private JButton btnCima;
	private JButton btnDeletar;
	private ListSelectionModel listSelectionModel;
	int ordemSelecionada = 0;
	I_Dado idado;
	
	public P_Dado_AdicionaDado() {
		super("CAsoft");
		setTitle("Dados");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel(new MigLayout("", "[80px:200px:400px,grow][][grow]20[grow]", "[]8[]8[]8[]8[]5[][][grow]"));
		panel.setBorder(new TitledBorder(null, "Detalhes do dado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 0 1 7,grow");
		
		list = new JList();
		list.setModel(new DefaultListModel());
        listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler());
		scrollPane.setViewportView(list);
		
		btnCima = new JButton("");
		btnCima.setIcon(new ImageIcon(P_Dado_AdicionaDado.class.getResource("/icons/Tabela/Cima.png")));
		panel.add(btnCima, "cell 1 0");
		
		btnCima.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	idado.salvaCampos(ordemSelecionada, false);
	        	idado.elevaOrdem(list.getSelectedIndex());
	        }});
		
		
		JLabel lblNomeDoDado = new JLabel("Nome do dado: ");
		
		panel.add(lblNomeDoDado, "cell 2 0,alignx trailing");
		
		tfNomeDado = new JTextField();
		panel.add(tfNomeDado, "cell 3 0,growx");
		tfNomeDado.setColumns(10);
		
		btnBaixo = new JButton("");
		btnBaixo.setIcon(new ImageIcon(P_Dado_AdicionaDado.class.getResource("/icons/Tabela/Baixo.png")));
		panel.add(btnBaixo, "cell 1 1");
		
		btnBaixo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	idado.salvaCampos(ordemSelecionada, false);
	        	idado.abaixaOrdem(list.getSelectedIndex());
	        }});
		
		JLabel lblSensorRelacionado = new JLabel("Sensor relacionado: ");
		panel.add(lblSensorRelacionado, "cell 2 1,alignx trailing");
		
		tfSensor = new JTextField();
		panel.add(tfSensor, "cell 3 1,growx");
		tfSensor.setColumns(10);
		
		JLabel lblUnidadeDeMedida = new JLabel("Unidade de medida:");
		panel.add(lblUnidadeDeMedida, "cell 2 2,alignx trailing");
		
		tfUnidadeDeMedida = new JTextField();
		panel.add(tfUnidadeDeMedida, "cell 3 2,growx");
		tfUnidadeDeMedida.setColumns(10);
		
		JLabel lblTipoDeVariavel = new JLabel("Tipo de variavel:");
		panel.add(lblTipoDeVariavel, "cell 2 3,alignx trailing");
		
		cbTipodeVariavel = new JComboBox();
		cbTipodeVariavel.setModel(new DefaultComboBoxModel(idado.TIPO_DE_DADOS));
		panel.add(cbTipodeVariavel, "cell 3 3,growx");
		
		chckbxExibeNaTabela = new JCheckBox("Exibe na tabela");
		panel.add(chckbxExibeNaTabela, "cell 2 4,alignx center");
		
		chckbxExibeNosBotes = new JCheckBox("Exibe nos bot\u00F5es");
		panel.add(chckbxExibeNosBotes, "cell 3 4,alignx left");
		
		btnOk = new JButton("Adicionar");
		btnOk.setIcon(new ImageIcon(P_Dado_AdicionaDado.class.getResource("/icons/Graficos/Adicionar.png")));
		panel.add(btnOk, "cell 2 5 2 1,growx");
		
		btnDeletar = new JButton("");
		btnDeletar.setIcon(new ImageIcon(P_Dado_AdicionaDado.class.getResource("/icons/Editardados/Lixeira.png")));
		panel.add(btnDeletar, "cell 1 6,growx");
		
		btnDeletar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	idado.deletaDado(ordemSelecionada);
	        }});
		
		btnOk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	if (ordemSelecionada == list.getModel().getSize()-1) {
		        	idado.adicionaDado(tfNomeDado.getText(), tfSensor.getText(), tfUnidadeDeMedida.getText(), cbTipodeVariavel.getSelectedItem().toString(), chckbxExibeNaTabela.isSelected(), chckbxExibeNosBotes.isSelected());
				}else {
					idado.salvaCampos(ordemSelecionada, true);
					P_Dado_AdicionaDado.this.dispose();
				}
	        }});

		
	}
	
	public void setIdado(I_Dado iDado) {		// Interliga interface com painel
		idado = iDado;
	}
	
	
/*		Classe chamada para lidar com os cliques das listas	 
 * */
	 class SharedListSelectionHandler implements ListSelectionListener {	
	        public void valueChanged(ListSelectionEvent e) { 
	            int selected = list.getSelectedIndex();
	            boolean isAdjusting = e.getValueIsAdjusting(); 
	            if(isAdjusting) {
	            	System.out.println("selecionado: "+selected);
			    	idado.salvaCampos(ordemSelecionada, false);
	            	ordemSelecionada = selected;
	            	if(ordemSelecionada == list.getModel().getSize()-1) {		//	 Se o cara clicou em "Novo dado", entra no modo de adição de dado
	            		modoAdicionar();
	            		System.out.println("ultimo item selecionado");
	            	}else {
	            		modoEditar();											// Se não, fica na edição de dados
		            	idado.atualizaCampos(ordemSelecionada);	            		
	            	}

	            }
	        }

	    }
	 

	 
	 
/*	Função que altera os campos no modo para adicionar e editar dados*/	 
	 public void modoAdicionar() {
		btnOk.setText("Adicionar");;
		btnDeletar.setEnabled(false);
		btnBaixo.setEnabled(false);
		btnCima.setEnabled(false);
		tfNomeDado.setText("");
		tfSensor.setText("");
		tfUnidadeDeMedida.setText("");
		cbTipodeVariavel.setSelectedItem(0);
		chckbxExibeNaTabela.setSelected(false);
		chckbxExibeNosBotes.setSelected(false);
	}
	 
	 public void modoEditar() {
		btnOk.setText("Ok");
		btnDeletar.setEnabled(true);
		if(ordemSelecionada == list.getModel().getSize()-2) {			// Se for o ante-penultimo, não pode ir pra baixo
			btnBaixo.setEnabled(false);			
		}else {
			btnBaixo.setEnabled(true);
		}

		if(ordemSelecionada == 0) {										// Se for o primeiro não pode ir pra cima
			btnCima.setEnabled(false);
		}else {
			btnCima.setEnabled(true);			
		}
	}
	 
	 
	 
/*	Funções que setam os valores dos campos na tela
 * */	 
	 public void setNomeCampo(String nome){
		 tfNomeDado.setText(nome);
	 }
	 
	public void setSensorCampo(String nomeSensor){
		 tfSensor.setText(nomeSensor);
	 }
	 
	 public void setUnidadeMedida(String unidadeMedida){
		 tfUnidadeDeMedida.setText(unidadeMedida);
	 }
	 
	 public void setTipoDeDadoCampo(int indice){
		 cbTipodeVariavel.setSelectedIndex(indice);
	 }
	 
	 public void setExibeTabelaCampo(boolean exibe) {
		 chckbxExibeNaTabela.setSelected(exibe);
	 }
	 
	 public void setExibeBotaoCampo(boolean exibe) {
		 chckbxExibeNosBotes.setSelected(exibe);
	 }
	 


	 
	 
	 
	 public String getNomeCampo(){
		 return tfNomeDado.getText();
	 }
	 
	 public String getSensorCampo(){
		 return tfSensor.getText();
	 }
	 
	 public String getUnidadeMedida(){
		 return tfUnidadeDeMedida.getText();
	 }
	 
	 public int getTipoDeDadoCampo(){
		 return cbTipodeVariavel.getSelectedIndex();
	 }
	 
	 public boolean getExibeTabelaCampo() {
		 return chckbxExibeNaTabela.isSelected();
	 }
	 
	 public boolean getExibeBotaoCampo() {
		 return chckbxExibeNosBotes.isSelected();
	 }
	 
/*	Popula a lista do painel com os dados fornecidos pela lista de nome, na ordem fornecida pela lista "ordem"
 * */	 
	 public void setListaDados(List<String> listaNomes, List<Integer> ordem) {
		 DefaultListModel model = (DefaultListModel) list.getModel();
		 model.clear();
		 for (int i = 0; i < listaNomes.size(); i++) {
			 int id = ArrayUtils.indexOf(ordem.toArray(), i);						// Pega o index o qual a ordem é i
			model.add(i, listaNomes.get(id));										// Pega o nome do dado que a ordem é i
		 }
		 model.addElement("[Novo...]");
		 list.setModel(model);
	 }

	 
	 
/*	Funções que selecionam dinâmicamente a lista nos valores especificados (ultimo, primeiro e genérico)
 * */
	public void setOrdemAdicionarDado() {
		ordemSelecionada = list.getModel().getSize()-1;		// Seleciona o ultimo componente
		list.setSelectedIndex(ordemSelecionada);
	}

	public void setOrdemEditarDado() {
		ordemSelecionada = 0;								// Seleciona o primeiro componente
		list.setSelectedIndex(0);
	}
	
	public void setOrdem(int ordem) {
		ordemSelecionada = ordem;
		list.setSelectedIndex(ordem);
	}

	public void limpaCampos() {
		setNomeCampo("");
		setSensorCampo("");
		setUnidadeMedida("");
		setTipoDeDadoCampo(0);
		setExibeTabelaCampo(false);
		setExibeBotaoCampo(false);
		
	}

}