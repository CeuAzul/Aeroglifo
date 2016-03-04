package main;

import java.io.Externalizable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.lang.ArrayUtils;

public class I_Dado {
	ControlBox c;
	P_Dado_AdicionaDado pAdicionaDado;
	final static String[] TIPO_DE_DADOS = new String[] {"Padr�o", "�ngulo", "Milissegundo"};
	public I_Dado(ControlBox controlBox){
		c = controlBox;
		pAdicionaDado = new P_Dado_AdicionaDado();
		pAdicionaDado.setIdado(this);
		pAdicionaDado.pack();
		pAdicionaDado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pAdicionaDado.setLocationRelativeTo(null);  // *** this will center your app ***
		pAdicionaDado.setIconImages(c.icons);

		pAdicionaDado.setVisible(false);
		pAdicionaDado.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	salvaCampos(pAdicionaDado.ordemSelecionada, true);
		    	pAdicionaDado.dispose();
		    }
		});
		
	}

/* M�todo chamado quando clicamos para "Adicionar dado" 
 * 		- Deixa o tela de dados vis�vel
 * 		- Atualiza a lista de dados no painel em ordem de exibi��o
 * 		- Manda a sele��o para o ultimo �tem (novo dado)
 * 		- Coloca no modo de adicionar dados (deixa apenas bot�o "adicionar" ativado)
 * */
	public void exibeTelaAdicionaDado(boolean exibe){
		pAdicionaDado.setVisible(exibe);
		atualizaListaDeDados();
		pAdicionaDado.setOrdemAdicionarDado();
		pAdicionaDado.modoAdicionar();
	}
	
	
/* M�todo chamado quando clicamos para "Editar dado" 
 * 		- Deixa o tela de dados vis�vel
 * 		- Atualiza a lista de dados no painel em ordem de exibi��o
 * 		- Manda a sele��o para o primeiro item
 * 		- Coloca no modo de editar dados
 * */	
	public void exibeTelaEditarDado(boolean exibe){
		pAdicionaDado.setVisible(exibe);
		atualizaListaDeDados();
		pAdicionaDado.setOrdemEditarDado();
		pAdicionaDado.modoEditar();
		atualizaCampos(0);
	}

/*	Fun��o salva os atuais valores dos campos nos Dados e Configuracao, e tamb�m nos arquivos
*		- Antes de tudo a fun��o verifica se a op��o "Novo dado" est� selecionada, se tiver, n�o salva nada (pois no "novo dado" precisa clicar no bot�o adicionar, ele � apenas um componente fict�cio da lista)
*/		
	public void salvaCampos(int ordemSelecionada, boolean atualizaTabela) {
		if(pAdicionaDado.ordemSelecionada != pAdicionaDado.list.getModel().getSize() - 1) {
			int id = GerenteDeDados.getIndiceTendoOrdem(ordemSelecionada);
			String nomeDado = pAdicionaDado.getNomeCampo();
			
			if(!GerenteDeDados.config.getDados().get(id).equals(nomeDado)) {
				int problema = GerenteDeDados.verificaProblemaNoNomeDoDado(nomeDado);

				if(problema == GerenteDeDados.ERRO_CARACTER_INVALIDO) {
					if (JOptionPane.showConfirmDialog(null, "Caracteres inv�lidos no nome do dado ( \\/:*?\"<>|, )\nPosso trocar esses caracteres por \"_\"?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    nomeDado = GerenteDeDados.getValidFileName(nomeDado);

					} else {
						return;
					}
					
				}
				while(problema == GerenteDeDados.ERRO_ARQUIVO_JA_EXISTE) {
					if (JOptionPane.showConfirmDialog(null, "J� exite um dado com esse nome.\nDeseja trocar o nome para \""+nomeDado+"1\"", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    nomeDado = nomeDado+1;
					    problema =  GerenteDeDados.verificaProblemaNoNomeDoDado(nomeDado);
					} else {
						return;
					}
				}
				if(problema == GerenteDeDados.ERRO_ARQUIVO_SEM_NOME) {
					 final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel,"Porra, dado ta sem nome... Assim fica dif�cil pra mim n�?...");
					 return;
				}
				
				
				
				
				GerenteDeDados.renomeiaDado(id, nomeDado);
			}
			GerenteDeDados.dado.get(id).setSensor(pAdicionaDado.getSensorCampo().replace(",", "_"));
			GerenteDeDados.dado.get(id).setUnidadeDeMedida(pAdicionaDado.getUnidadeMedida().replace(",", "_"));
			GerenteDeDados.dado.get(id).setTipoVariavel(TIPO_DE_DADOS[pAdicionaDado.getTipoDeDadoCampo()]);
			GerenteDeDados.dado.get(id).setExibeTabela(pAdicionaDado.getExibeTabelaCampo());
			GerenteDeDados.dado.get(id).setExibeBotao(pAdicionaDado.getExibeBotaoCampo());
			if (atualizaTabela) {
				c.atualizaTabela();
			}
		}
	}	
	
	
	
/*	Fun��o que vai pegar a lista de nome de dados, e a lista da ordem dos dados, e mandar pro painel
 * 		- A fun��o "setListaDados" do painel vai arrumar cada dado em ordem de exibi��o e colocar na lista pra mostrar pro usu�rio
*/		
	public void atualizaListaDeDados() {
		pAdicionaDado.setListaDados(GerenteDeDados.config.getDados(), GerenteDeDados.config.getOrdem());	
	}
	
	
	
/*	M�todo adiciona dados baseado nos campos de textos do painel	
*/
	public void adicionaDado(String nomeDado, String sensor, String unidadeDeMedida, String tipoDeVariavel, boolean exibeTabela, boolean exibeBotao) {
		int problema = GerenteDeDados.verificaProblemaNoNomeDoDado(nomeDado);
		System.out.println("PROBLEMA � ESSE: "+ problema);
		if(problema == GerenteDeDados.ERRO_CARACTER_INVALIDO) {
			if (JOptionPane.showConfirmDialog(null, "Caracteres inv�lidos no nome do dado ( \\/:*?\"<>|, )\nPosso trocar esses caracteres por \"_\"?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    nomeDado = GerenteDeDados.getValidFileName(nomeDado);
			} else {
				return;
			}
			
		}
		while(problema == GerenteDeDados.ERRO_ARQUIVO_JA_EXISTE) {
			if (JOptionPane.showConfirmDialog(null, "J� exite um dado com esse nome.\nDeseja trocar o nome para \""+nomeDado+"1\"", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    nomeDado = nomeDado+1;
			    problema = GerenteDeDados.verificaProblemaNoNomeDoDado(nomeDado);
			} else {
				return;
			}
		}
		if(problema == GerenteDeDados.ERRO_ARQUIVO_SEM_NOME) {
			 final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel,"Porra, dado ta sem nome... Assim fica dif�cil pra mim n�?...");
			 return;
		}
		
		sensor = sensor.replace(",", "_");
		unidadeDeMedida = unidadeDeMedida.replace(",", "_");
		GerenteDeDados.adicionaDado(nomeDado, false, tipoDeVariavel, sensor, exibeTabela, exibeBotao, unidadeDeMedida);
		atualizaListaDeDados();
		pAdicionaDado.setOrdemAdicionarDado();
		pAdicionaDado.modoAdicionar();
		c.atualizaTabela();
	}

	
	
/*	M�todo para atualiza��o dos campos de texto quando o usu�rio seleciona um novo dado da lista
 * 		- A fun��o tem como entrada o indice DA LISTA, ou seja, a ordem de exibi��o do dado
 * 		- Ela converte essa ordem na sele��o (para descobrir de fato, qual dado est� se referindo)
 * 		- Seta todos os campos com os devidos valores gravados em "dado" do Gerenciador de dados		
 * */
	public void atualizaCampos(int ordemSelecionada) {
		if(pAdicionaDado.ordemSelecionada != pAdicionaDado.list.getModel().getSize() - 1) {
			int indexSelecionado = GerenteDeDados.getIndiceTendoOrdem(ordemSelecionada);
			pAdicionaDado.setNomeCampo(GerenteDeDados.dado.get(indexSelecionado).getNomeDado());
			pAdicionaDado.setSensorCampo(GerenteDeDados.dado.get(indexSelecionado).getSensor());
			pAdicionaDado.setUnidadeMedida(GerenteDeDados.dado.get(indexSelecionado).getUnidadeDeMedida());
			pAdicionaDado.setTipoDeDadoCampo(ArrayUtils.indexOf(TIPO_DE_DADOS, GerenteDeDados.dado.get(indexSelecionado).getTipoVariavel()));
			pAdicionaDado.setExibeTabelaCampo(GerenteDeDados.dado.get(indexSelecionado).isExibeTabela());
			pAdicionaDado.setExibeBotaoCampo(GerenteDeDados.dado.get(indexSelecionado).isExibeBotao());
		}else {
			pAdicionaDado.limpaCampos();
		}
		
	}

/*	M�todos para troca de ordem (Como funciona?)
 * 		- A fun��o � chamada pelo bot�o l� no painel onde � passado a ordem do dado (posi��o a qual ele est� na lista)
 * 		- Chegando na fun��o, ele procura o indice o qual aquela ordem pertence (encontra dado que ocupa aquela ordem)
 * 		- Chama a fun��o "troca ordem" do gerenciador de dado (fun��o ir� fazer toda parte inteligente de troca de ordem e ir� alterar os arquivos de configura��o)
 * 		- Com os arquivos de configura��es alteradas, chamamos a fun��o "atualizaListaDados" pra pegar os dados atualizados e colocar no painel
 * 		- Como a ordem foi mudada, mudamos tamb�m a sele��o da lista que aparece no painel com a fun��o "set ordem", para que o item de cima da lista esteja em azul
 * 		- chama a fun��o "modoEditar()" para fazer altera��es b�sicas com a mudan�a de sele��o (tal qual descelecionar o bot�o "pra cima" se j� tiver na posi��o mais de cima poss�vel)
 * */
	public void elevaOrdem(int selectedOrdem) {
		GerenteDeDados.trocaOrdem(GerenteDeDados.getIndiceTendoOrdem(selectedOrdem), GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(selectedOrdem)).getOrdemDado() -1);
		atualizaListaDeDados();
		pAdicionaDado.setOrdem(selectedOrdem-1);
		atualizaCampos(selectedOrdem-1);
		pAdicionaDado.modoEditar();
	}

	public void abaixaOrdem(int selectedOrdem) {
		GerenteDeDados.trocaOrdem(GerenteDeDados.getIndiceTendoOrdem(selectedOrdem), GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(selectedOrdem)).getOrdemDado() +1);
		atualizaListaDeDados();
		pAdicionaDado.setOrdem(selectedOrdem+1);
		atualizaCampos(selectedOrdem+1);
		pAdicionaDado.modoEditar();
	}
	
	


	
	
/*	Deleta dado 
 * 	*/	
	
	public void deletaDado(int ordemSelecionada) {
		int id = GerenteDeDados.getIndiceTendoOrdem(ordemSelecionada);
		GerenteDeDados.deletaDado(id);
		atualizaListaDeDados();
		pAdicionaDado.setOrdemAdicionarDado();
		pAdicionaDado.modoAdicionar();
		c.atualizaTabela();
		
	}

}
