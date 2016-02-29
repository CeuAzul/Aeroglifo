package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class ConfigHelper {
	final static String NOMEPASTA = "Aeroglifo";
	final static String NOMECONFIG = "config";
	final static String TERMINACAO = ".properties";
	
	
	final static String K_DADOS = "Dados";
	final static String K_ORDEMDADOS = "Ordem";
	final static String K_SENSORES = "Sensores";
	
	final static String K_GRAFICO1 = "IdGrafico1";
	final static String K_GRAFICO2 = "IdGrafico2";
	final static String K_GRAFICO3 = "IdGrafico3";
	final static String K_GRAFICO4 = "IdGrafico4";
	final static String K_GRAFICO5 = "IdGrafico5";
	final static String K_GRAFICO6 = "IdGrafico6";
	final static String K_CAMINHOVIDEO = "CaminhoVideo";
	final static String K_TEMPODECOLAGEMVIDEO = "InstanteDecolagemVideo";
	final static String K_TEMPODECOLAGEMDADO = "InstanteDecolagemDado";
	final static String K_TEMPOPOUSODADO = "InstantePousoDados";
	
	final static String K_VELOCIDADEMAX = "VelocidadeMax";
	final static String K_VELOCIDADEMIN = "VelocidadeMin";
	final static String K_VELOCIDADETIPO = "VelocidadeTipo";
	final static String K_ALTITUDEMAX = "AltitudeMax";
	final static String K_ALTITUDEMIN = "AltitudeMin";
	final static String K_ALTITUDETIPO = "AltitudeTipo";
	final static String K_RPMMAX = "RpmMax";
	final static String K_RPMMIN = "RpmMin";
	final static String K_RPMTIPO = "RpmTipo";
	final static String K_PRESSAOMAX = "PressaoMax";
	final static String K_PRESSAOMIN = "PressaoMin";
	final static String K_PRESSAOTIPO = "PressaoTipo";
	final static String K_DEFPROFMAX = "DefProfMax";
	final static String K_DEFPROFMIN = "DefProfMin";
	final static String K_DEFLEMEMAX = "DefLemeMax";
	final static String K_DEFLEMEMIN = "DefLemeMin";
	final static String K_DEFAILERONMAX = "DefAileronMax";
	final static String K_DEFAILERONMIN = "DefAileronMin";
	
	
	final static String K_NOME = "Nome";
	final static String K_ORDEMDADO = "Ordem";
	final static String K_ASSOCIACAO = "DadoAssociacao";
	final static String K_TIPOVARIAVEL = "TipoVariavel";
	final static String K_SENSOR = "Sensor";
	final static String K_VALOR = "Valor";
	final static String K_EXIBIRTABELA = "ExibirTabela";
	final static String K_EXIBIRBOTAODADO = "ExibirBotao";
	final static String K_UNIDADE_DE_MEDIDA = "UnidadeMedida";
	


	
	
	/*###########################################################################################################################
	
				 	   Essa função deve cuidar da escrita das configurações gerais do programa
					   Ela deve verificar se a pasta e o arquivo existem
					   Se não existe deve criar uma pasta e um arquivo de configuração geral
					   Se existe ela deve escrever o campo, o valor e o comentário da informação		
					   
	  ###########################################################################################################################				   */
	
	public static void setCampoConfig(String campo, String valor, boolean append){
		try {
			File configFile = new File(NOMEPASTA+"\\"+NOMECONFIG+TERMINACAO);
			if (configFile.exists()) {
				System.out.println("Pasta existe");
				adicionaCampoEmPropriedade(NOMECONFIG, campo, valor, append);
			}else{
				System.out.println("Pasta não existe");
				configFile.getParentFile().mkdir();									// Cria pasta
		    	PropertiesConfiguration config;
				config = new PropertiesConfiguration(configFile);					// Cria arquivo de propriedades geral
				
		    	config.save();
				adicionaCampoEmPropriedade(NOMECONFIG, campo, valor, append);
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void addStringNoFimDoArquivo(String campo, String valor) throws IOException {
		File file = new File(NOMEPASTA+"\\"+campo+TERMINACAO);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(valor);
		bw.newLine();
		bw.close();
		
		
	}
	
	
	/*###########################################################################################################################
	
				Essa irá ser responsável pela verificação e criação de campo para as cofigurações dos dados.
				Ela vai verificar se o arquivo do dado existe, 	se exite, escreve o campo, se não, cria arquivo e escre campo
						   
	  ###########################################################################################################################						*/
	
	public static void setCampo(String nomeArquivo, String campo, String valor, boolean append){
		try {
			File configFile = new File(NOMEPASTA+"\\"+nomeArquivo+TERMINACAO);
			if (configFile.exists()) {
			//	System.out.println("Pasta existe");
				adicionaCampoEmPropriedade(nomeArquivo, campo, valor, append);	// Adiciona campo e valor
			}else{
				System.out.println("Pasta não existe");
				configFile.getParentFile().mkdir();									// Cria pasta se não existir
		    	PropertiesConfiguration config;
				config = new PropertiesConfiguration(configFile);					// Cria arquivo de propriedades geral
		    	config.save();
				adicionaCampoEmPropriedade(nomeArquivo, campo, valor, append);	//Adiciona campo e valor
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void setCampo(String nomeArquivo, String campo, List<String> valor){
		try {
			File configFile = new File(NOMEPASTA+"\\"+nomeArquivo+TERMINACAO);
			if (configFile.exists()) {
		//		System.out.println("Pasta existe");
				setCampoListEmPropriedade(nomeArquivo, campo, valor);	// Adiciona campo e valor
			}else{
				System.out.println("Pasta não existe");
				configFile.getParentFile().mkdir();									// Cria pasta se não existir
		    	PropertiesConfiguration config;
				config = new PropertiesConfiguration(configFile);					// Cria arquivo de propriedades geral
		    	config.save();
				setCampoListEmPropriedade(nomeArquivo, campo, valor);	//Adiciona campo e valor
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*############################################################################################################################
	
					 Estas funções são responsáveis APENAS pela escrita do campo de propriedade em um arquivo
					 A verificação e criação do arquivo deve ser feita antes disso
					 
	  ############################################################################################################################	*/
	
	private static void adicionaCampoEmPropriedade(String nomePropriedade, String nomeCampo, String valor, boolean append){
		try {
			
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+nomePropriedade+TERMINACAO));	// Associa nome da propriedade com arquivo
	    	config.setDelimiterParsingDisabled(false);
	    	if((append)){																										// Verifica se o campo existe
	    		config.addProperty(nomeCampo, valor);		// Crias/adiciona o valor		
	    	}else{
	    		config.setProperty(nomeCampo, valor);										// Cria/adiciona campo e valor	
	    	}

	    	config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	
	private static void setCampoListEmPropriedade(String nomePropriedade, String nomeCampo, List<String> valor){
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+nomePropriedade+TERMINACAO));	// Associa nome da propriedade com arquivo
	    	config.setProperty(nomeCampo, valor);										// Cria/adiciona campo e valor	

	    	config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/*############################################################################################################################
	
					 Função que adiciona comentário em cima de um campo e coloca espaço
					 
	  ############################################################################################################################	*/
	
	
	private static void setComentario(String nomePropriedade, String nomeCampo, String comentario){
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+nomePropriedade+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	config.getLayout().setBlancLinesBefore(nomeCampo, 1);		// Cria linha para espaçamento
	    	config.getLayout().setComment(nomeCampo, comentario);
	    	config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	/*############################################################################################################################
	
	 									Funções pega valores dos arquivos de configurações
	 
	  ############################################################################################################################	*/	

	
	public static String getCampoString(String arquivo, String campo){
		String a = null;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getString(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static Boolean getCampoBoolean(String arquivo, String campo){
		Boolean a = false;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getBoolean(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static String[] getCampoStringArray(String arquivo, String campo){
		String a[] = null;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getStringArray(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static List<Object> getCampoList(String arquivo, String campo){
		List<Object> a = null;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getList(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static int getCampoInt(String arquivo, String campo){
		int a = 0;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getInt(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static double getCampoDouble(String arquivo, String campo){
		double a = 0;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getDouble(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static float getCampoFloat(String arquivo, String campo){
		float a = 0;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getFloat(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	public static Object getCampo(String arquivo, String campo){
		Object a = null;
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	a = config.getProperty(campo);		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	
	/*############################################################################################################################
	
														Muda nome do arquivo

	  ############################################################################################################################	*/	
	
	public static void setNomeArquivo(String antigoNome, String novoNome){
		// File (or directory) with old name
	    File file = new File(NOMEPASTA+"\\"+antigoNome+TERMINACAO);

	    // File (or directory) with new name
	    File file2 = new File(NOMEPASTA+"\\"+novoNome+TERMINACAO);
	    
	    if(file2.exists()){
	    	System.out.println("Já tem arquivo com esse nome");
	    }

	    // Rename file (or directory)
	    boolean success = file.renameTo(file2);
	    if (!success) {
	        System.out.println("não deu pra renomear arquivo");
	    }
	}
	
	
	
	
	
	/*############################################################################################################################
	
														Deleta arquivo

	 ############################################################################################################################	*/		
	
	
	public static void deletaArquivo(String nomeArquivo) {
		try{
			 
		    File file = new File(NOMEPASTA+"\\"+nomeArquivo+TERMINACAO);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
		
	}
	
	
	public static void limpaCampo(String arquivo, String campo) {
		try {
	    	PropertiesConfiguration config = new PropertiesConfiguration(new File(NOMEPASTA+"\\"+arquivo+TERMINACAO));		// Associa nome da propriedade com arquivo
	    	config.clearProperty(campo);
	    	config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*############################################################################################################################
	
										Funções pré-definidas para facilitar o desenvolvimento

	  ############################################################################################################################	*/	
	
	
	public static List<String> getNomeDados(){
		List<String> lista;
		lista = new ArrayList<String>(Arrays.asList(getCampoStringArray(NOMECONFIG, K_DADOS)));
		return lista;
	}
	
	public static List<Integer> getOrdemDados(){
		String[] a = getCampoStringArray(NOMECONFIG, K_ORDEMDADOS);
		List<Integer> listaInt = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			listaInt.add(Integer.parseInt(a[i]));
		} 

		return listaInt;
	}
	
	public static List<Float> getValorDados(String arquivo){
		List<String> lista;
		List<Float> listaFloat = new ArrayList<Float>();
		lista = new ArrayList<String>(Arrays.asList(getCampoStringArray(arquivo, K_VALOR)));
		for (int i = 0; i < lista.size(); i++) {
			//System.out.println("passou aqui: AAAAAAAAAAAAAAAAAAAAAAAAAAA: "+lista.get(i));
			listaFloat.add(Float.parseFloat(lista.get(i)));
		}
		return listaFloat;
	}
	
	public static boolean existeArquivo(String nomeArquivo) {
	    File file = new File(NOMEPASTA+"\\"+nomeArquivo+TERMINACAO);
		return file.exists();
	}




	public static boolean temRegistroNoCampo(String nomeArquivo, String campo) {
    	return (getCampoString(nomeArquivo, campo) != null);
	}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}