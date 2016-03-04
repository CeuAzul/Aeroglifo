package main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.jfree.data.time.Second;
import org.jfree.ui.RefineryUtilities;



// http://stackoverflow.com/questions/2671496/java-when-to-use-static-methods
public class ControlBox {

P_Control painel = new P_Control(this);
P_Sobre painelSobre = new P_Sobre();
I_Txt itxt = new I_Txt(this);
I_Dado idado = new I_Dado(this);
I_Video ivideo = new I_Video(this);

int paginaAtual=1;
int totalPaginas=0;
int intervaloDeRegistros = 100;
int registrosNaUltimaPagina = 0;
final List<Image> icons = new ArrayList<Image>();
float tempoDecolagem, tempoPouso;
	public ControlBox() throws InterruptedException { 


        try {
			icons.add(ImageIO.read(P_Control.class.getResource("/icons/Main/icones/icone-16x16.png")));
			icons.add(ImageIO.read(P_Control.class.getResource("/icons/Main/icones/icone-32x32.png")));
			icons.add(ImageIO.read(P_Control.class.getResource("/icons/Main/icones/icone-64x64.png")));
			icons.add(ImageIO.read(P_Control.class.getResource("/icons/Main/icones/icone-128x128.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		painel.pack();
		painel.setIconImages(icons);
		painel.setVisible(true);
		painel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("abriu a box");
		GerenteDeDados.inicializa();
		paginaAtual = 1;
		atualizaTabela();
		SplashHelper.fechaSplash();
		painel.setAlwaysOnTop(true);
		painel.toFront();
		painel.requestFocus();
		painel.setAlwaysOnTop(false);
		painel.setExtendedState(painel.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		painelSobre = new P_Sobre();
		painelSobre.pack();
		painelSobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		painelSobre.setVisible(false);
		painelSobre.setLocationRelativeTo(null);
		painelSobre.setIconImages(icons);
		
	}

	
	public void atualizaTabela() {
		if(GerenteDeDados.config.getMaxOrdem() != -1) {				// Se tem dados cadastrados
			
			int quantidadeRegistros = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor().size();		// Quantidade de registros 12035
			int paginasInteiras = (int) (((float) quantidadeRegistros) / ((float) intervaloDeRegistros));		// Aqui pegamos o valor inteiro depois da virgula tipo 120 (de 12035 registros)
			registrosNaUltimaPagina = quantidadeRegistros - paginasInteiras*intervaloDeRegistros;				// Aqui fazemos 12035 - 12000 = 35 (35 registros na ultima folha)
			if(registrosNaUltimaPagina != 0) {
				totalPaginas = paginasInteiras+1;
			}else {
				totalPaginas = paginasInteiras;
			}
			desenhaTabela();


			
		}
			
	}
	
	private void desenhaTabela() {
		painel.limpaLista();
		for (int i = 0; i < GerenteDeDados.dado.size(); i++) {
			String nomeDado = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(i)).getNomeDado();
			List<Float> valores = new ArrayList<Float>();
			valores = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(i)).getValor();
			String[] coluna = new String[intervaloDeRegistros];
			if((paginaAtual == totalPaginas)&&(registrosNaUltimaPagina != 0)) {
				for (int j = 0; j < registrosNaUltimaPagina; j++) {
					coluna[j] = valores.get(j+(paginaAtual-1)*intervaloDeRegistros)+"";
				}
			}else {
				for (int j = 0; j < intervaloDeRegistros; j++) {
					coluna[j] = valores.get(j+(paginaAtual-1)*intervaloDeRegistros)+"";
				}
			}
			painel.addColunaTabela(nomeDado, coluna);
			
		}
		painel.arrumaTamanhoColunas();
		escondeDadosIndicadosTabela();
		painel.setaPaginaAtual(paginaAtual, totalPaginas);
		
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


	public void moveTabela(boolean praBaixo) {
		if(praBaixo) {
			paginaAtual++;
		}else {
			paginaAtual--;
		}
		desenhaTabela();
		
	}


	public void vaiPraPagina(int pagina) {
		if(pagina < 1) {
			JOptionPane.showMessageDialog(null, "Ta de sacanagem né?\n\nNão da pra eu colocar uma página menor que 1 aqui...\n\nPutz...");
			return;
		}
		if (pagina > totalPaginas) {
			JOptionPane.showMessageDialog(null, "Assim não da cara!\n\nNão posso acessar uma página maior que o número máximo de páginas ("+totalPaginas+")\n\nQuer que eu busque ela em Nárnia?\nPutz...");
			return;
		}
		paginaAtual = pagina;
		desenhaTabela();
		
		
	}
		
	



	public void vaiProTempo(float valorTempo) {
		int paginaDoTempo = 0;
		int idRegistro = closest(valorTempo, GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor());
		int selecPaginaAtual = (int)(((float) idRegistro)/(float) intervaloDeRegistros);
		int registroNaPagina = idRegistro - selecPaginaAtual*intervaloDeRegistros;
		paginaAtual = selecPaginaAtual+1;
		System.out.println(paginaAtual);
		desenhaTabela();
		painel.selecionaLinha(registroNaPagina);
	}

	
	/* Pega o número mais próximo de uma lista */ 
	public int closest(float numero, List<Float> lista) {
	    float min = Integer.MAX_VALUE;
	    float closest = numero;
	    int id = 0;

	    for (int i = 0; i < lista.size(); i++) {
	        final float diff = Math.abs(lista.get(i) - numero);

	        if (diff < min) {
	            min = diff;
	            closest = lista.get(i);
	            id = i;
	        }
	    }

	    return id;
	}
	
	/* Pega o número mais próximo de uma lista */ 
	public int closest(float numero, float[] lista) {
	    float min = Integer.MAX_VALUE;
	    float closest = numero;
	    int id = 0;

	    for (int i = 0; i < lista.length; i++) {
	        final float diff = Math.abs(lista[i] - numero);

	        if (diff < min) {
	            min = diff;
	            closest = lista[i];
	            id = i;
	        }
	    }

	    return id;
	}
		
	public int abreSelecaoIdDado(String frase) {
		int toSize = GerenteDeDados.dado.size();
        String[] stockArr = new String[toSize];

		for (int i = 0; i < toSize; i++) {
		    stockArr[i] = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(i)).getNomeDado();
		}
		
        int ordem =  ask("Selecione o dado ", frase, stockArr);
        System.out.println("veio do ask: "+ordem);
        if(ordem == -1) {
        	return -1;
        }
		return  GerenteDeDados.getIndiceTendoOrdem(ordem);
	}
		

	 public static int ask(String titulo, String frase, final String... values) {

	        int result = 0;

	        if (EventQueue.isDispatchThread()) {

	            JPanel panel = new JPanel();
	            panel.add(new JLabel(titulo));
	            DefaultComboBoxModel model = new DefaultComboBoxModel();
	            for (String value : values) {
	                model.addElement(value);
	            }
	            JComboBox comboBox = new JComboBox(model);
	            panel.add(comboBox);

	            int iResult = JOptionPane.showConfirmDialog(null, panel, frase, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	            switch (iResult) {
	                case JOptionPane.OK_OPTION:
	                    result = comboBox.getSelectedIndex();
	                    break;
	                case JOptionPane.CANCEL_OPTION:
	                    result = -1;
	                    break;
	            }

	        } else {

	            Response response = new Response(titulo, frase, values);
	                try {
						SwingUtilities.invokeAndWait(response);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                result = response.getResponse();
	            

	        }

	        return result;

	    }

	    public static class Response implements Runnable {

	        private String[] values;
	        private int response;
	        private String titulo;
	        private String frase;

	        public Response(String titulo, String frase, String... values) {
	            this.values = values;
	            this.titulo = titulo;
	            this.frase = frase;
	        }

	        @Override
	        public void run() {
	            response = ask(titulo, frase, values);
	        }

	        public int getResponse() {
	            return response;
	        }
	    }
	    
	    
		public void selecionaComoWow(boolean utilizarOSegundoComoWOW) {
			int id = 1;
			if(!utilizarOSegundoComoWOW) {
				id = abreSelecaoIdDado("Qual dado deve ser usado como Wow?");
			}
			System.out.println(id);
			procuraRegiaoDeVoo(id);
		}

		
		public void procuraRegiaoDeVoo(int id) {
			List<Float> valoresAlvoWow = new ArrayList<Float>();
			List<Float> valoresAlvoTempo = new ArrayList<Float>();
			valoresAlvoWow = GerenteDeDados.dado.get(id).getValor();
			valoresAlvoTempo = GerenteDeDados.dado.get(GerenteDeDados.getIndiceTendoOrdem(0)).getValor();
			int tamanho = valoresAlvoWow.size();
			boolean[] wow = new boolean[tamanho];
			boolean antes = true;
			List<Float> possiveisTemposDeDecolagem = new ArrayList<Float>();
			List<Float> possiveisTemposDePouso = new ArrayList<Float>();
			for (int i = 0; i < wow.length; i++) {
				float numid = valoresAlvoWow.get(i);
				if(!((numid == 1) || (numid == 0))) {
					JOptionPane.showMessageDialog(null, "Isso não é um dado de Wow (peso nas rodas)\nO dado deve conter apenas 1 e 0");
					return;
				}
				wow[i] = (1 == (valoresAlvoWow.get(i)));
				System.out.println(wow[i]);
				if((antes == true)&&(wow[i]==false)) {																			//Se antes tiver peso nas rodas...
					possiveisTemposDeDecolagem.add(valoresAlvoTempo.get(i));
				}
				if((antes == false)&&(wow[i]==true)) {
					possiveisTemposDePouso.add(valoresAlvoTempo.get(i));
				}
				antes = wow[i];
			}
			if(possiveisTemposDeDecolagem.size() > possiveisTemposDePouso.size()){	// Se tiver mais decolagens do que pousos, o ultimo instante vira o pouso
				possiveisTemposDePouso.add(valoresAlvoTempo.get(tamanho-1));
			}
			List<Float> tempoVoo = new ArrayList<Float>();
			for (int i = 0; i < possiveisTemposDeDecolagem.size(); i++) {
				tempoVoo.add(possiveisTemposDePouso.get(i) - possiveisTemposDeDecolagem.get(i));
			}
			
			System.out.println("tempo de voo: "+Collections.max(tempoVoo));
			int indexMaxTempo = tempoVoo.indexOf(Collections.max(tempoVoo));
			tempoDecolagem = possiveisTemposDeDecolagem.get(indexMaxTempo);
			tempoPouso = possiveisTemposDePouso.get(indexMaxTempo);

		//	System.out.println("número de decolagens: "+possiveisTemposDeDecolagem.get(1));
		//	System.out.println("número de pousos: "+possiveisTemposDePouso.get(1));


			}
		
	    public boolean pedeWowManual() {
	        JTextField field1 = new JTextField();
	        JTextField field2 = new JTextField();
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("Instante de decolagem"));
	        panel.add(field1);
	        panel.add(new JLabel("Instante de pouso"));
	        panel.add(field2);
	        int result = JOptionPane.showConfirmDialog(null, panel, "Selecionar região de voo",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	        	tempoDecolagem = Float.parseFloat(field1.getText());
	        	tempoPouso = Float.parseFloat(field2.getText());
	        	return true;																				//Se deu certo retorna como true
	        } else {
	            return false;
	        }
	    }


		public void exibeSobre() {
			painelSobre.setVisible(true);
			
		}
	    



}
