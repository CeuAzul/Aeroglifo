package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class GerenteDeDados {
	static List<Dado> dado = new ArrayList<Dado>();
	static Configuracoes config = new Configuracoes();
	
	public static final int ERRO_CARACTER_INVALIDO = -1;
	public static final int ERRO_ARQUIVO_JA_EXISTE = -2;
	public static final int ERRO_ARQUIVO_SEM_NOME = -3;
	public static final int DEU_BOA = 1;
	
	public static final int SEM_ESCALA = -1;
	public static final int SEGUNDOS = 0;
	public static final int MILISSEGUNDOS = 1;

	
	
	/* Este método verifica se o arquivo de configuração exite, se tem dados.
	 * se tiver ele carrega os dados nos vetores e associa os arquivos
	 */
	public static void inicializa() {
		if(config.existeArquivoConfig()) {						// Arquivo config existe?
			if(config.temRegistroDeNomeDadoNoArquivo()) {		// Campo nomeDado existe?
				config.carregaNomeDadosArquivoConfig();			// Pega nome dos dados que estão no arquivo de configuração e associa ao "config"
			    carregaDados();									// Carrega Arquivos dos dados no programa
			    conectaIds();									// Conecta id do config -> dado
			    conetectaOrdemDadoConfig();						// Conecta ordem dado -> config
			}				
		}else {
			System.out.println("Arquivo de configuração não existe (nada foi escrito ainda)");
		}
	}
	


	public static void adicionaDado(String nomeDado, boolean associado, String tipoVariavel, String sensor, boolean exibeTabela, boolean exibeBotao, String unidadeDeMedida){
		int indice = config.adicionaDado(nomeDado);
		int ultimaOrdem = config.getMaxOrdem();
		config.adicionaOrdemDados(ultimaOrdem+1);
		Dado d = new Dado();
		d.setNomeDado(nomeDado);
		d.setId(indice);
		d.setAssociado(associado);
		d.setOrdemDado(ultimaOrdem+1);
		d.setTipoVariavel(tipoVariavel);
		d.setSensor(sensor);
		d.setExibeBotao(exibeBotao);
		d.setExibeTabela(exibeTabela);
		d.setUnidadeDeMedida(unidadeDeMedida);
		
		
		
		dado.add(d);

	}
	
	public static void renomeiaDado(int id, String novoNome){
		config.renomeiaDado(id, novoNome);
		dado.get(id).setNomeDado(novoNome);
	}
	
/*	Função reponsável por fazer tudo relacionado a exclusão de dado nas classes de dados, configuração e arquivos de configuração
 * 		- Se quiser deletar um dado, só chamar isso aqui e correr para o abraço	*/	
	public static void deletaDado(int id){
		config.deletaDado(id);										// Deleta dos arquivos de configuração
		int ordem = dado.get(id).getOrdemDado();					// Armazena a ordem do dado
		dado.get(id).deleta();
		dado.remove(id);
		conectaIds();
		arrumaOrdemDeletada(ordem);
		conetectaOrdemConfigDado();
	}
	
/*	Deleta todos os dados	*/	
	public static void deletaTodosDados(){
		int tamanho = dado.size();
		for (int i = 0; i < tamanho; i++) {
			
			deletaDado(0);
		}
	}

	
/*	Função que faz toda inteligência por trás da troca de ordem de dado	
 * 		- Se quiser trocar a ordem de exibição de um dado só chamar essa função e correr par ao abraço	*/	
	public static void trocaOrdem(int id, int novaOrdem) {
		int antigaOrdem = config.getOrdem(id);
		if(antigaOrdem > novaOrdem) {
			for (int i = 0; i < dado.size(); i++) {
				if((config.getOrdem(i) >= novaOrdem)&&(config.getOrdem(i) < antigaOrdem )) {
					config.setOrdem(i, config.getOrdem(i)+1);
				}
			}
		config.setOrdem(id, novaOrdem);
		}else {
			for (int i = 0; i < dado.size(); i++) {
				if((config.getOrdem(i) > antigaOrdem)&&(config.getOrdem(i) <= novaOrdem )) {
					config.setOrdem(i, config.getOrdem(i)-1);
				}
			}
		config.setOrdem(id, novaOrdem);
		}
		conetectaOrdemConfigDado();
	}
	
	
	public static int verificaProblemaNoNomeDoDado(String nome) {
		if(nome.length() != 0) {
			if(validateFileName(nome)) {
				if(!temNomeRepetido(nome)) {
					return DEU_BOA;
				}else {
					return ERRO_ARQUIVO_JA_EXISTE;
				}
			}else {
				return ERRO_CARACTER_INVALIDO;
			}
		}else {
			return ERRO_ARQUIVO_SEM_NOME;
		}
		
		
	}
	
	
	public static int getEscalaTempo() {
		String escrito = dado.get(getIndiceTendoOrdem(0)).getUnidadeDeMedida();
		if(escrito.isEmpty()) {
			return -1;
		}
		if(escrito.toLowerCase().startsWith("s")) {
			return 0;
		}else if(escrito.toLowerCase().startsWith("m")) {
			return 1;
		}else {
			return -1;
		}
	}
	
	
	private static boolean temNomeRepetido(String nome) {
		return config.getDados().contains(nome);
	}
	
	private static boolean validateFileName(String fileName) {
		System.out.println("TEM CARACTER ESPECIAL em "+fileName+"? "+fileName.matches(".*[\\\\/:*?\"<>|,].*"));
	    return (!fileName.matches(".*[\\\\/:*?\"<>|,].*"));
	}

	public static String getValidFileName(String fileName) {
	    String newFileName = fileName.replaceAll("[\\\\/:*?\"<>|,]", "_");
	    if(newFileName.length()==0)
	        throw new IllegalStateException(
	                "File Name " + fileName + " results in a empty fileName!");
	    return newFileName;
	}
	
	
	/* Função que conecta o id do dado com o id das configuração */
	public static void conectaIds() {
		for (int i = 0; i < dado.size(); i++) {
			dado.get(i).setId(i);
		}
	}
	
	
	/* Reordena posição de exibição baseado em um ordem deletada (no arquivo config*/
	public static void arrumaOrdemDeletada(int ordemDeletada) {
		for (int i = 0; i < dado.size(); i++) {
			if(config.getOrdem(i) > ordemDeletada) {
				config.setOrdem(i, config.getOrdem(i) - 1);
			}
		}
		
	}
	
	/* Sincroniza ordem de dados no sentido Config -> Dado */
	public static void conetectaOrdemConfigDado() {
		for (int i = 0; i < dado.size(); i++) {
			dado.get(i).setOrdemDado(config.getOrdem(i));
		}
	}
	/* Sincroniza ordem de dados no sentido dado -> Config */
	public static void conetectaOrdemDadoConfig() {
		if(config.getMaxOrdem() == -1) {										// Se o campo de ordem não tiver sido preenchido ainda
			for (int i = 0; i < dado.size(); i++) {
				config.adicionaOrdemDados(dado.get(i).getOrdemDado());		// Adiciona dados aos campos
			}
		}else {																// Se não...
			for (int i = 0; i < dado.size(); i++) {
				config.setOrdem(i, dado.get(i).getOrdemDado());				// Apenas muda os valores que eles tem
			}
		}
	}
	
	/* Sincroniza dados no sentido Arquivo de dado -> dado no programa baseado em nomeDados do arquivo config*/	
	private static void carregaDados() {
		List<String> lista = config.getDados();		// Pega lista de nome das configurações
		for (int i = 0; i < lista.size(); i++) {
			Dado d = new Dado();
			boolean deuBoa = d.carrega(lista.get(i));
			if(deuBoa) {
				dado.add(d);
				System.out.println("Carregou: "+lista.get(i));
			}

		}
	}
	
	/* Passa os valores das listas de vetores para os arquivos de configuração */
	
	public static void associaValoresDoProgramaAosValoresDosArquivosDeConfiguracao() {
		for (int i = 0; i < dado.size(); i++) {
			List<String> listaString = new ArrayList<String>();
			for (int j = 0; j < dado.get(i).valor.size(); j++) {
				listaString.add(dado.get(i).valor.get(j)+"");
			}
			ConfigHelper.setCampo(dado.get(i).getNomeDado(), ConfigHelper.K_VALOR, listaString);
			dado.get(i).valor.clear();
		}
		
	}
	
	
/*	Método que procura a ordem de exibição de um dado, e retorna o identificador deste mesmo dado */	
	
	
	public static int getIndiceTendoOrdem(int ordem) {
		return ArrayUtils.indexOf(GerenteDeDados.config.getOrdem().toArray(), ordem);
	}



	public static void setEscalaDeTempo(int escala) {
		if(escala == SEGUNDOS) {
			GerenteDeDados.dado.get(getIndiceTendoOrdem(0)).setUnidadeDeMedida("Segundos");
		}else if(escala ==MILISSEGUNDOS) {
			GerenteDeDados.dado.get(getIndiceTendoOrdem(0)).setUnidadeDeMedida("Milisegundos");
		}else{
			GerenteDeDados.dado.get(getIndiceTendoOrdem(0)).setUnidadeDeMedida("");
		}
		
	}

}
