package main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eu.hansolo.steelseries.extras.Altimeter;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class I_Video {
	P_Video pVideo;
	P_Video_SelecionaDados pVideoSelecionaDados;
	P_Video_SincronizaDados pVideoSincronizaDados;
	ControlBox c;
	String stringDados;
	boolean temNome = false;
	boolean temUnidadeDeMedida = false;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    final String[] SENSORES = {"Yaw", "Pitch", "Roll", "Velocidade", "Posição X", "Posição Y", "Pressão", "Altura", "RPM", "Wow", "Deflexao Profundor", "Deflexao leme", "Deflexao aileron"};
    
    int numValores;
    int[] idSensores = new int[SENSORES.length];
    int[] idGraficos = {-1, -1, -1, -1, -1, -1};
    
    float[] yaw;
    float[] pitch;
    float[] roll;
    float[] velocidade;
    float[] posicaox;
    float[] posicaoy;
    float[] pressao;
    float[] altura;
    float[] rpm;
    float[] wow;
    float[] defprof;
    float[] defleme;
    float[] defaileron;
    float[] tempo;
    
	int escalaTempo = -1;
	float ajusteSincrono=0;
    
    
	public I_Video(ControlBox control){	
		c = control;
		try {
			pVideo = new P_Video(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pVideo.pack();
		pVideo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pVideo.setVisible(false);
        pVideo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              //  mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        pVideo.setLocationRelativeTo(null);
        pVideo.setIconImage(new ImageIcon(P_Control.class.getResource("/icons/Main/Icone2.png")).getImage());
        
        pVideoSelecionaDados = new P_Video_SelecionaDados(this);
        pVideoSelecionaDados.pack();
        pVideoSelecionaDados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pVideoSelecionaDados.setVisible(false);
        pVideoSelecionaDados.setLocationRelativeTo(null);
        pVideoSelecionaDados.setIconImage(new ImageIcon(P_Control.class.getResource("/icons/Main/Icone2.png")).getImage());
        
        pVideoSincronizaDados = new P_Video_SincronizaDados(this);
        pVideoSincronizaDados.pack();
        pVideoSincronizaDados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pVideoSincronizaDados.setVisible(false);
        pVideoSincronizaDados.setLocationRelativeTo(null);
        pVideoSincronizaDados.setIconImage(new ImageIcon(P_Control.class.getResource("/icons/Main/Icone2.png")).getImage());

 //       atualizaBotoesSensores();

	}



	private void atualizaBotoesSensores() {
		List<String> sensoresLista = new ArrayList<String>();
		for (int i = 0; i < GerenteDeDados.dado.size(); i++) {
			sensoresLista.add(GerenteDeDados.dado.get(i).getSensor());
		}
		for (int i = 0; i < SENSORES.length; i++) {
			idSensores[i] = sensoresLista.indexOf(SENSORES[i]);
		}
		for (int i = 0; i < idSensores.length; i++) {
			if(idSensores[i] != -1) {
				pVideoSelecionaDados.mudaBotao(true, i, GerenteDeDados.dado.get(idSensores[i]).getNomeDado(), false);
			}else {
				pVideoSelecionaDados.mudaBotao(true, i, "Selecionar "+SENSORES[i], true);

			}
		}
		
		
		idGraficos = Configuracoes.getGraficosId();
		System.out.println("grafico 1 "+idGraficos[0]);
		
		for (int i = 0; i < idGraficos.length; i++) {
			if (idGraficos[i] != -1) {
				pVideoSelecionaDados.mudaBotao(false, i, GerenteDeDados.dado.get(idGraficos[i]).getNomeDado(), false);

			}else {
				pVideoSelecionaDados.mudaBotao(false, i, "Selecionar Gráfico "+(i+1), true);

			}
		}
		
		escalaTempo = GerenteDeDados.getEscalaTempo();
		pVideoSelecionaDados.selecionaEscalaDeTempo(escalaTempo);
		pVideo.setTituloMedidores();
		
	}



	public void exibeTelaVideo(boolean exibe) {
		pVideo.setVisible(exibe);
		if(exibe) exibeTelaSelecionaDadosVideo(exibe);
		
	}
	
	public void exibeTelaSelecionaDadosVideo(boolean exibe) {
		pVideoSelecionaDados.setVisible(exibe);
		if(exibe) atualizaBotoesSensores();
	}
	
	public void exibeTelaSincronizaDados(boolean exibe) {
		pVideoSincronizaDados.setVisible(exibe);
		if(exibe) atualizaTelaSincronizaDados();
	}



	private void atualizaTelaSincronizaDados() {
		pVideoSincronizaDados.selecionaEscalaDeTempo(escalaTempo);		
	}



	public void carregaVideo(String caminho) {
        pVideo.iniciaVideo(caminho);
		
	}



	public void selecionaSensorParaDado(int sensor) {
		int idDado = c.abreSelecaoIdDado("Qual dado deve ser visto como "+SENSORES[sensor]+"?");
		
		System.out.println(idDado);
		if(idDado != -1) {
			if (idSensores[sensor] != -1) {
				GerenteDeDados.dado.get(idSensores[sensor]).setSensor("");			// Apaga o antigo idSensor
			}
			GerenteDeDados.dado.get(idDado).setSensor(SENSORES[sensor]);
		}else {
			if (idSensores[sensor] != -1) {
				GerenteDeDados.dado.get(idSensores[sensor]).setSensor("");			// Apaga o antigo idSensor
			}
		}
		atualizaBotoesSensores();
	}



	public void selecionaDadoParaGrafico(int i) {
		int idDado = c.abreSelecaoIdDado("Qual dado deve estar no gráfico "+(i+1)+"?");
		Configuracoes.setGraficosId(idDado, i);
		idGraficos[i] = idDado;
		
		atualizaBotoesSensores();
		
	}



	public void carregaParaVideo() {

		
		for (int i = 0; i < idGraficos.length; i++) {
			if (idGraficos[i] != -1) {
				pVideo.carregaGrafico(i, 
						GerenteDeDados.dado.get(0).getUnidadeDeMedida(),
						GerenteDeDados.dado.get(idGraficos[i]).getUnidadeDeMedida(),
						GerenteDeDados.dado.get(idGraficos[i]).getNomeDado(),
						GerenteDeDados.dado.get(0).getValor(),
						GerenteDeDados.dado.get(idGraficos[i]).getValor(),
						idGraficos[i]);
			}
		}
			List<Float> listaTemp = new ArrayList<Float>();
			
			listaTemp = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor();
			tempo = new float[listaTemp.size()];
			for(int i = 0; i < listaTemp.size(); i++) tempo[i] = listaTemp.get(i);
			listaTemp.clear();
			
			if(idSensores[pVideoSelecionaDados.YAW] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.YAW]).getValor();
				yaw = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) yaw[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.PITCH] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.PITCH]).getValor();
				pitch = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) pitch[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.ROLL] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.ROLL]).getValor();
				roll = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) roll[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.VELOCIDADE] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.VELOCIDADE]).getValor();
				velocidade = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) velocidade[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.POSICAOX] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.POSICAOX]).getValor();
				posicaox = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) posicaox[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.POSICAOY] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.POSICAOY]).getValor();
				posicaoy = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) posicaoy[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.PRESSAO] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.PRESSAO]).getValor();
				pressao = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) pressao[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.ALTURA] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.ALTURA]).getValor();
				altura = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) altura[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.RPM] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.RPM]).getValor();
				rpm = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) rpm[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.WOW] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.WOW]).getValor();
				wow = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) wow[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.DEFLEXAOPROFUNDOR] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.DEFLEXAOPROFUNDOR]).getValor();
				defprof = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) defprof[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.DEFLEXAOLEME] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.DEFLEXAOLEME]).getValor();
				defleme = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) defleme[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			if(idSensores[pVideoSelecionaDados.DEFLEXAOAILERON] != -1) {
				listaTemp = GerenteDeDados.dado.get(idSensores[pVideoSelecionaDados.DEFLEXAOAILERON]).getValor();
				defaileron = new float[listaTemp.size()];
				for(int i = 0; i < listaTemp.size(); i++) defaileron[i] = listaTemp.get(i);
				listaTemp.clear();
			}
			
			escalaTempo = GerenteDeDados.getEscalaTempo();
			
			/*
			 *     float[] yaw;
    float[] pitch;
    float[] roll;
    float[] velocidade;
    float[] posicaox;
    float[] posicaoy;
    float[] pressao;
    float[] altura;
    float[] rpm;
    float[] wow;
    float[] defprof;
    float[] defleme;
    float[] defaileron;
			 * 
			 */

			
			
			/*					graficoPadrao.setEixoX(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getUnidadeDeMedida());
					graficoPadrao.setEixoY(GerenteDeDados.dado.get(id).getUnidadeDeMedida());
					graficoPadrao.setNomeGrafico(e.getActionCommand());
					graficoPadrao.setValores(GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor(), GerenteDeDados.dado.get(id).getValor());
					graficoPadrao.setId(id);
					setGraficoPainel(graficoPadrao.getPainelPadrao());*/


		
	}




	public void salvaEscalaTempo(int escala) {
		GerenteDeDados.setEscalaDeTempo(escala);
		escalaTempo = escala;
	}



	public void setSincronismo(float millisVideo, float millisDados) {
		ajusteSincrono = millisDados-millisVideo;
	}



	
	
}
