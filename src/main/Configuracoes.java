package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Configuracoes {
	List<String> nomeDados = new ArrayList<String>();
	List<Integer> ordemDados = new ArrayList<Integer>();	
	
	public List<String> getDados() {
		return nomeDados;
	}

	public int adicionaDado(String nomeDado){									//Retorna o número do indice do dado adicionado
		nomeDados.add(nomeDado);
		ConfigHelper.setCampoConfig(ConfigHelper.K_DADOS, nomeDado, true);
		return nomeDados.size() -1;
	}
	
	public void adicionaOrdemDados(int ordem){
		ordemDados.add(ordem);
	}
	
	public int getMaxOrdem(){
		int num = -1;
		System.out.println("Lista ordem de dados está vazia "+ordemDados.isEmpty());
		if(!ordemDados.isEmpty()) {
			System.out.println("Lista ordem de dados está vazia "+ordemDados.get(0));
			num = Collections.max(ordemDados);
		}
		return num;
	}
	
	public int getOrdem(int id){
		return ordemDados.get(id);
	}
	
	public List<Integer> getOrdem(){
		return ordemDados;
	}

	
	public void renomeiaDado(int id, String novoNome){
		nomeDados.set(id, novoNome);
		ConfigHelper.setCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_DADOS, nomeDados);
	}
	
	public void deletaDado(int id){
		nomeDados.remove(id);
		ordemDados.remove(id);
		ConfigHelper.setCampo(ConfigHelper.NOMECONFIG,  ConfigHelper.K_DADOS , nomeDados);

	}

	public void setOrdem(int id, int ordem) {
		ordemDados.set(id, ordem);
		
	}
	
	public boolean existeArquivoConfig() {
		return ConfigHelper.existeArquivo(ConfigHelper.NOMECONFIG);
	}

	public boolean temRegistroDeNomeDadoNoArquivo() {
		return ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_DADOS);
	}
	
	public void carregaNomeDadosArquivoConfig() {
		nomeDados = ConfigHelper.getNomeDados();
	}
	
	public static void setGraficosId(int id1, int id2, int id3, int id4, int id5, int id6) {
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO1, id1+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO2, id2+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO3, id3+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO4, id4+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO5, id5+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO6, id6+"", false);
	}
	
	public static void setGraficosId(int id, int grafico) {
		switch (grafico) {
		case 0:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO1, id+"", false);
			break;
		case 1:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO2, id+"", false);
			break;
		case 2:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO3, id+"", false);
			break;
		case 3:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO4, id+"", false);
			break;
		case 4:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO5, id+"", false);
			break;
		case 5:
			ConfigHelper.setCampoConfig(ConfigHelper.K_GRAFICO6, id+"", false);
			break;
		}
	}
	
	public static void setTempoDecolagemEPouso(float decolagem, float pouso) {
		ConfigHelper.setCampoConfig(ConfigHelper.K_TEMPODECOLAGEMDADO, decolagem+"", false);
		ConfigHelper.setCampoConfig(ConfigHelper.K_TEMPOPOUSODADO, pouso+"", false);
	}
	
	
	public static void setTempoDecolagemVideo(float decolagem) {
		ConfigHelper.setCampoConfig(ConfigHelper.K_TEMPODECOLAGEMVIDEO, decolagem+"", false);
	}
	
	public static int[] getGraficosId() {
		int ids[] = new int[6];
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO1)) {
			ids[0] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO1);
		}else {	ids[0] = -1;}
		
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO2)) {
			ids[1] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO2);
		}else {	ids[1] = -1;}
		
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO3)) {
			ids[2] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO3);
		}else {	ids[2] = -1;}
		
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO4)) {
			ids[3] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO4);
		}else {	ids[3] = -1;}
		
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO5)) {
			ids[4] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO5);
		}else {	ids[4] = -1;}
		
		if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO6)) {
			ids[5] = ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_GRAFICO6);
		}else {	ids[5] = -1;}
		
		return ids;

	}
	
	public static float getTempoDecolagemDado() {
		return 	ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_TEMPODECOLAGEMDADO);
	}
	
	public static float getTempoPousoDados() {
		return 	ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_TEMPOPOUSODADO);
	}
	
	public static float getTempoDecolagemVideo() {
		return 	ConfigHelper.getCampoInt(ConfigHelper.NOMECONFIG, ConfigHelper.K_TEMPODECOLAGEMVIDEO);
	}

	public static void setParametrosMedidor(int sensor, double max, double min, int tipo) {
		switch (sensor) {
		case P_Video_SelecionaDados.VELOCIDADE:

			ConfigHelper.setCampoConfig(ConfigHelper.K_VELOCIDADEMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_VELOCIDADEMIN, min+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_VELOCIDADETIPO, tipo+"", false);
			break;
		case P_Video_SelecionaDados.ALTURA:
			ConfigHelper.setCampoConfig(ConfigHelper.K_ALTITUDEMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_ALTITUDEMIN, min+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_ALTITUDETIPO, tipo+"", false);
			break;
		case P_Video_SelecionaDados.PRESSAO:
			ConfigHelper.setCampoConfig(ConfigHelper.K_PRESSAOMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_PRESSAOMIN, min+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_PRESSAOTIPO, tipo+"", false);
			break;
		case P_Video_SelecionaDados.RPM:
			ConfigHelper.setCampoConfig(ConfigHelper.K_RPMMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_RPMMIN, min+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_RPMTIPO, tipo+"", false);
			break;
		case P_Video_SelecionaDados.DEFLEXAOPROFUNDOR:
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFPROFMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFPROFMIN, min+"", false);
			break;
		case P_Video_SelecionaDados.DEFLEXAOLEME:
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFLEMEMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFLEMEMIN, min+"", false);
			break;
		case P_Video_SelecionaDados.DEFLEXAOAILERON:
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFAILERONMAX, max+"", false);
			ConfigHelper.setCampoConfig(ConfigHelper.K_DEFAILERONMIN, min+"", false);
			break;
		}
		
	}
	
	public static double[] getParametrosMedidor(int sensor) {
		double[] l = new double[3];
		l[0] = -1;
		l[1] = -1;
		l[2] = -1;
		switch (sensor) {
		case P_Video_SelecionaDados.VELOCIDADE:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_VELOCIDADEMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_VELOCIDADEMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_VELOCIDADEMIN);
				l[2] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_VELOCIDADETIPO);
			}
			break;
		case P_Video_SelecionaDados.ALTURA:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_ALTITUDEMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_ALTITUDEMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_ALTITUDEMIN);
				l[2] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_ALTITUDETIPO);
			}
			break;
		case P_Video_SelecionaDados.PRESSAO:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_PRESSAOMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_PRESSAOMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_PRESSAOMIN);
				l[2] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_PRESSAOTIPO);
			}
			break;
		case P_Video_SelecionaDados.RPM:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_RPMMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_RPMMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_RPMMIN);
				l[2] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_RPMTIPO);
			}
			break;
		case P_Video_SelecionaDados.DEFLEXAOPROFUNDOR:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFPROFMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFPROFMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFPROFMIN);
				l[2] = -1;
			}
			break;
		case P_Video_SelecionaDados.DEFLEXAOLEME:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFLEMEMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFLEMEMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFLEMEMIN);
				l[2] = -1;
			}
			break;
		case P_Video_SelecionaDados.DEFLEXAOAILERON:
			if(ConfigHelper.temRegistroNoCampo(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFAILERONMAX)) {
				l[0] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFAILERONMAX);
				l[1] = ConfigHelper.getCampoDouble(ConfigHelper.NOMECONFIG, ConfigHelper.K_DEFAILERONMIN);
				l[2] = -1;
			}
			break;
		}
		return l;
	}
	
	
}
