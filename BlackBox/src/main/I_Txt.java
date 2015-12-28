package main;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class I_Txt {
	P_Txt ptxt;
	ControlBox c;
	String stringDados;
	boolean temNome = false;
	boolean temUnidadeDeMedida = false;
	
	public I_Txt(ControlBox control){	
		c = control;
		ptxt = new P_Txt(this);
		ptxt.pack();
		ptxt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ptxt.setVisible(false);
		
	}

	
	public void setDadosTelemetriaTxt(String dados, boolean adiciona){
		//ptxt.setTxtTelemetria(dados, adiciona);
		stringDados = stringDados + dados;
	}
	
	public String getDadosTelemetriaTxt(){
		return ptxt.getTxtTelemetria();
	
	}
	


	public void setJanelaAberta(boolean aberto){
		ptxt.setVisible(aberto);
		
	}
	

	public void associaDados(String[] valores, int nLinha) {
		System.out.println("Entrou no associaDados "+ nLinha);
		if(nLinha == 0) {
			if (JOptionPane.showConfirmDialog(null, "A primeira linha possui o nome dos dados?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				temNome = true;
				if (JOptionPane.showConfirmDialog(null, "A segunda linha tem as unidades de medida??", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				   temUnidadeDeMedida = true;
				} else {
				   temUnidadeDeMedida = false;
				}
			} else {
			   temNome = false;
			   temUnidadeDeMedida = false;
			}
		//	int ndadosCadastrados = GerenteDeDados.config.getMaxOrdem() +1;

		}
		
		if(nLinha == 0) {
			if(temNome) {
				for (int i = 0; i < valores.length; i++) {
					if(valores[i].equals("")) {
						break;
					}
					GerenteDeDados.adicionaDado(valores[i], false, c.idado.TIPO_DE_DADOS[0], "", true, true, "");
				}
			}
		}
		
		if(nLinha == 1) {
			if(temUnidadeDeMedida) {
				for (int i = 0; i < valores.length; i++) {
					if(valores[i].equals("")) {
						break;
					}
					GerenteDeDados.dado.get(i).setUnidadeDeMedida(valores[i]);
				}
			}
		}
		
		
    	if(nLinha >= 2) {
			System.out.println("linha: " +nLinha);
    		processaAssociacao(valores);
    	}
	}


	private void processaAssociacao(String[] valores) {
		for (int i = 0; i < GerenteDeDados.dado.size(); i++) {
			System.out.println("olá"+i);
			if(valores[i].equals("")) {
				break;
			}
			GerenteDeDados.dado.get(i).associaValorAoPrograma(valores[i]);				
		}
		
	}



	
	
}
