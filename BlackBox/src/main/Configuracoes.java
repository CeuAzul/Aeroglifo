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
	
	//Load nome dados
	//Load ordemDados
	
	
}
