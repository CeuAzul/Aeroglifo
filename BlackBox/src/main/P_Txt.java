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


public class P_Txt extends JFrame{
	JTextArea ta_raw;
	public P_Txt(final I_Txt itxt) {
		super("CaSoft");
		setPreferredSize(new Dimension(880, 670));
		setTitle("Arquivo de telemetria em txt");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 839, 555);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		ta_raw = new JTextArea();
		scrollPane.setViewportView(ta_raw);
		
		JLabel lblDados = new JLabel("Dados:");
		scrollPane.setColumnHeaderView(lblDados);
		
		JButton btnAssocia = new JButton("Associar dados");
		getContentPane().add(btnAssocia, BorderLayout.SOUTH);
		btnAssocia.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	       // 	itxt.associaDados();
	        	itxt.setJanelaAberta(false);
	    }});
		
		
		
	}

	protected void setTxtTelemetria(String a, boolean adiciona) {
		if(adiciona){
			ta_raw.setText(ta_raw.getText() + a);
		}else{
			ta_raw.setText(a);
		}
	}

	public String getTxtTelemetria() {
		return ta_raw.getText();
	}

}