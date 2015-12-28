package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dado {
	String nomeDado = "";
	int ordemDado;
	boolean associado;
	String tipoVariavel;
	String sensor;
	List<String> valor = new ArrayList<String>();
	boolean exibeTabela;
	boolean exibeBotao;
	String unidadeDeMedida;
	int id;								//Identificador que será usado para ligar com arquivo de configuração
	



	/* GETTERS */
	public int getId() {
		return id;
	}
	
	public String getNomeDado() {
		return nomeDado;
	}
	
	public int getOrdemDado() {
		return ordemDado;
	}
	
	public boolean isAssociado() {
		return associado;
	}
	
	public String getTipoVariavel() {
		return tipoVariavel;
	}
	
	public String getSensor() {
		return sensor;
	}
	
	public List<String> getValor() {
		return valor;
	}
	
	public boolean isExibeTabela() {
		return exibeTabela;
	}
	
	public boolean isExibeBotao() {
		return exibeBotao;
	}
	
	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	
	
	/* SETTERS */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNomeDado(String nomeDado) {
		if(this.nomeDado.equals("")){													//Se o nome está vazio (não foi escrito ainda)
			ConfigHelper.setCampo(nomeDado, ConfigHelper.K_NOME, nomeDado, false);
			this.nomeDado = nomeDado;
		}else{
			ConfigHelper.setNomeArquivo(this.nomeDado, nomeDado);						//Renomeia arquivo de configuração
			ConfigHelper.setCampo(nomeDado, ConfigHelper.K_NOME, nomeDado, false);
			this.nomeDado = nomeDado;
		}
	}
	
	public void setOrdemDado(int ordemDado) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_ORDEMDADO, ""+ordemDado, false);
		this.ordemDado = ordemDado;
	}
	
	public void setAssociado(boolean associado) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_ASSOCIACAO, ""+associado, false);
		this.associado = associado;
	}
	
	public void setTipoVariavel(String tipoVariavel) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_TIPOVARIAVEL, tipoVariavel, false);
		this.tipoVariavel = tipoVariavel;
	}
	
	public void setSensor(String sensor) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_SENSOR, sensor, false);
		this.sensor = sensor;
	}
	
	public void addValor(String valor) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_VALOR, valor, true);
		this.valor.add(valor);
	}
	
	
	
	public void limpaValor() {
		ConfigHelper.limpaCampo(nomeDado, ConfigHelper.K_VALOR);
		this.valor.clear();
	}
	
	public void setExibeTabela(boolean exibeTabela) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_EXIBIRTABELA, ""+exibeTabela, false);
		this.exibeTabela = exibeTabela;
	}
	
	public void setExibeBotao(boolean exibeBotao) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_EXIBIRBOTAODADO, ""+exibeBotao, false);
		this.exibeBotao = exibeBotao;
	}
	
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		ConfigHelper.setCampo(nomeDado, ConfigHelper.K_UNIDADE_DE_MEDIDA, unidadeDeMedida, false);
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	

	/* Função para carregamento:
	 * 		Ela é chamada no carregamento do sistema quando queremos associar as informações que estão nos arquivos
	 * 		com as informações do programa 
	 */
	
	public boolean carrega(String nomeDado) {
		boolean deuBoa;
		if(ConfigHelper.existeArquivo(nomeDado)) {
			deuBoa = true;
			this.nomeDado = ConfigHelper.getCampoString(nomeDado, ConfigHelper.K_NOME);
			System.out.println(this.nomeDado);
			this.associado = ConfigHelper.getCampoBoolean(nomeDado, ConfigHelper.K_ASSOCIACAO);
			this.ordemDado = ConfigHelper.getCampoInt(nomeDado, ConfigHelper.K_ORDEMDADO);
			this.tipoVariavel = ConfigHelper.getCampoString(nomeDado, ConfigHelper.K_TIPOVARIAVEL);
			this.sensor = ConfigHelper.getCampoString(nomeDado, ConfigHelper.K_SENSOR);
			this.exibeTabela = ConfigHelper.getCampoBoolean(nomeDado, ConfigHelper.K_EXIBIRTABELA);
			this.exibeBotao = ConfigHelper.getCampoBoolean(nomeDado, ConfigHelper.K_EXIBIRBOTAODADO);
			this.unidadeDeMedida = ConfigHelper.getCampoString(nomeDado, ConfigHelper.K_UNIDADE_DE_MEDIDA);	
			this.valor = ConfigHelper.getValorDados(nomeDado);
		}else{
			deuBoa = false;
			System.out.println("Essa porra de dado (arquivo) não existe");
		}
		return deuBoa;
	}
	
	

	public void deleta() {
		ConfigHelper.deletaArquivo(nomeDado);
	}

/* 			Função utilizada para associar os valores do .csv com os arquivos pela primeira vez */
	
	public void associaValorAoPrograma(String valorNovo) {
		this.valor.add(valorNovo);
		System.out.println("numero de valores associados: "+valorNovo.length());
		
		
	}
	


	

}
