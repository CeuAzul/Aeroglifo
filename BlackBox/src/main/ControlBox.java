package main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.jfree.data.time.Second;
import org.jfree.ui.RefineryUtilities;

// http://stackoverflow.com/questions/2671496/java-when-to-use-static-methods
public class ControlBox {

P_Control painel = new P_Control(this);
I_Txt itxt = new I_Txt(this);
I_Dado idado = new I_Dado(this);

	public ControlBox(){ 
		painel.pack();
		painel.setVisible(true);
		painel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("abriu a box");
		GerenteDeDados.inicializa();

				atualizaTabela();

		
	}

	
	public void atualizaTabela() {
		if(GerenteDeDados.config.getMaxOrdem() != -1) {				// Se tem dados cadastrados
			System.out.println("MAX ORDEM DO GERENTE DE DADOS: "+GerenteDeDados.config.getMaxOrdem());
			String[] coluna = new String[GerenteDeDados.config.getDados().size()];
			int[] idEmOrdem = new int[coluna.length];
			
			for (int i = 0; i < coluna.length; i++) {
				
				System.out.println("id da parada: "+GerenteDeDados.getIndiceTendoOrdem(i)+ " de ordem: "+i);
				coluna[i] = GerenteDeDados.config.nomeDados.get(GerenteDeDados.getIndiceTendoOrdem(i));
				idEmOrdem[i] = GerenteDeDados.getIndiceTendoOrdem(i);
			}
			painel.setHeaderTabela(coluna);
			if(!GerenteDeDados.dado.get(0).valor.isEmpty()) {	// E se esses dados tem valores
				System.out.println("TEM DADO GUARDADO");

				
				
				int numLinhas = GerenteDeDados.dado.get(0).getValor().size();
				
				for (int i = 0; i < numLinhas; i++) {
					String[] linhaDados = new String[idEmOrdem.length];
					for (int j = 0; j < coluna.length; j++) {
						if(!GerenteDeDados.dado.get(idEmOrdem[j]).getValor().isEmpty()) {
							System.out.println(GerenteDeDados.config.getDados().get(idEmOrdem[j]));
							linhaDados[j] = GerenteDeDados.dado.get(idEmOrdem[j]).getValor().get(i);	// Pega o valor do dado de ordem j e linha i
						}else {
							linhaDados[j] = "";
						}
					}
					painel.addLinhaTabela(linhaDados);
				}
				painel.arrumaTamanhoColunas();
				escondeDadosIndicadosTabela();
			}
		}else {
			painel.limpaLista();
		}
	}
	
	public void escondeDadosIndicadosTabela() {
		if(GerenteDeDados.config.getMaxOrdem() != -1) {				// Se tem dados cadastrados
			if(!GerenteDeDados.dado.get(0).getValor().isEmpty()) {	// E se esses dados tem valores
				String[] coluna = new String[GerenteDeDados.config.getDados().size()];
				
				for (int i = 0; i < coluna.length; i++) {
					if(!GerenteDeDados.dado.get(i).isExibeTabela()) {
						System.out.println("teste");
						painel.escondeColuna(painel.getColunaPeloNome(GerenteDeDados.config.getDados().get(i)));
					}
				}
			}
		}
	}
		
	public void exibeTodosDadosIndicadosTabela() {
		if(GerenteDeDados.config.getMaxOrdem() != -1) {				// Se tem dados cadastrados
			if(!GerenteDeDados.dado.get(0).getValor().isEmpty()) {	// E se esses dados tem valores		
			
				String[] coluna = new String[GerenteDeDados.config.getDados().size()];
				
				for (int i = 0; i < coluna.length; i++) {
					if(!GerenteDeDados.dado.get(i).isExibeTabela()) {
						
						System.out.println("CAIU AQUI, PORRA");
						painel.exibeColuna(painel.getColunaPeloNome(GerenteDeDados.config.getDados().get(i)));
					}
				}
			}
		
		}
	}
		

		
		
		





}
