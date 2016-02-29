package main;
import java.awt.Color;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jfree.chart.plot.JThermometer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.LineBorder;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.version.LibVlcVersion;

import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;

import eu.hansolo.steelseries.extras.AirCompass;
import eu.hansolo.steelseries.extras.Altimeter;
import eu.hansolo.steelseries.extras.Compass;
import eu.hansolo.steelseries.extras.Horizon;
import eu.hansolo.steelseries.extras.Indicator;
import eu.hansolo.steelseries.extras.Level;
import eu.hansolo.steelseries.extras.Poi;
import eu.hansolo.steelseries.extras.Radar;
import eu.hansolo.steelseries.gauges.Linear;
import eu.hansolo.steelseries.gauges.Radial;
import eu.hansolo.steelseries.gauges.Radial1Vertical;
import eu.hansolo.steelseries.gauges.RadialBargraph;
import eu.hansolo.steelseries.tools.BackgroundColor;
import eu.hansolo.steelseries.tools.ForegroundType;
import eu.hansolo.steelseries.tools.FrameDesign;
import eu.hansolo.steelseries.tools.FrameEffect;
import eu.hansolo.steelseries.tools.FrameType;
import eu.hansolo.steelseries.tools.TicklabelOrientation;

import java.awt.Font;

import eu.hansolo.steelseries.tools.GaugeType;
import eu.hansolo.steelseries.tools.TickmarkType;
import eu.hansolo.steelseries.tools.PointerType;
import eu.hansolo.steelseries.tools.KnobStyle;
import eu.hansolo.steelseries.tools.KnobType;
import eu.hansolo.steelseries.tools.NumberFormat;
import eu.hansolo.steelseries.tools.LcdColor;
import eu.hansolo.steelseries.tools.Orientation;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import eu.hansolo.steelseries.tools.ColorDef;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class P_Video extends JFrame{
	JPanel painelVideo = new JPanel();
	Canvas canvas = new Canvas();
	private G_Video graficoVideo1;
	private G_Video graficoVideo2;
	private G_Video graficoVideo3;
	private G_Video graficoVideo4;
	private G_Video graficoVideo5;
	private G_Video graficoVideo6;
    G_distXY graficoXYGPS = new G_distXY();

	
	JPanel painelGraf1 = new JPanel();
	JPanel painelGraf2 = new JPanel();
	JPanel painelGraf3 = new JPanel();
	JPanel painelGraf4 = new JPanel();
	JPanel painelGraf5 = new JPanel();
	JPanel painelGraf6 = new JPanel();
	
	Timer timerAtualizaLabel;
	
	int velocidadeAtualizacao = 50;
	
	long tempoSistemaInicioPlay = 0;
	long tempoVideoInicioPlay = 0;
	long tempoTotalVideo = 0;
	boolean parado = true;
	JSlider slider = new JSlider(0,100,0);
	
	float fatorDeMultiplicacao = 1;
	float fpsPadrao=0;
	JPanel painelGPS = new JPanel();

	
	
	JLabel lblFps = new JLabel("FPS");
	JLabel lblTempoPassado = new JLabel("99:99:999");
	JLabel lblTempototal = new JLabel("TempoTotal");


    String caminhoVideoAtual;
    EmbeddedMediaPlayerComponent mediaPlayerComponent ;
    EmbeddedMediaPlayer embeddedMediaPlayer;
    Horizon horizonte = new Horizon();
    
	Radial barometro = new Radial();
	AirCompass bussola = new AirCompass();
    Radial velocimetro = new Radial();
	RadialBargraph rpm = new  RadialBargraph();
	Linear defProf = new Linear();
	Linear defLeme = new Linear();
	Linear defAileron = new Linear();
	Radial altimetro = new Radial();
	JLabel lblWow = new JLabel("Wow");
	JPanel painel_wow = new JPanel();


	
	String digiteValorMaximo = "Digite o valor máximo para a bagaça";
	String digiteValorMinimo = "Digite o valor mínimo para a bagaça";

    
    I_Video i;
    
	public P_Video(final I_Video i_Video) throws IOException {
		super("CaSoft");
		i = i_Video;
		
		
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), System.getProperty("user.dir") + "/lib/VLC");
		mediaPlayerComponent= new EmbeddedMediaPlayerComponent();
		embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();
		setPreferredSize(new Dimension(1280, 800));
		setTitle("Análise de vídeo");
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_1, "width 33%,height 50%");
		panel_1.setLayout(new MigLayout("", "[grow][grow]", "[][grow][grow]"));
		
		painel_wow.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.add(painel_wow, "cell 0 0 2 1,width 100%,height 10%");
		painel_wow.setLayout(new BorderLayout(0, 0));
		lblWow.setFont(new Font("DialogInput", Font.BOLD, 13));
		lblWow.setHorizontalAlignment(SwingConstants.CENTER);
		
		painel_wow.add(lblWow);
		
		JPanel painelPressao = new JPanel();
		painelPressao.setBorder(null);
		panel_1.add(painelPressao, "cell 0 1,width 50%,height 50%");
		painelPressao.setLayout(new BorderLayout(0, 0));
		
		JPanel painelAltimetro = new JPanel();
		painelAltimetro.setBorder(null);
		panel_1.add(painelAltimetro, "cell 1 1,width 50%,height 45%");
		painelAltimetro.setLayout(new BorderLayout(0, 0));
		
		JPanel painelDeflexao = new JPanel();
		panel_1.add(painelDeflexao, "cell 0 2,width 50%,height 45%");
		painelDeflexao.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));
		
		JPanel painelDefProfundor = new JPanel();
		painelDeflexao.add(painelDefProfundor, "cell 0 0,grow");
		painelDefProfundor.setLayout(new BorderLayout(0, 0));
		
		JPanel painelDefLeme = new JPanel();
		painelDeflexao.add(painelDefLeme, "cell 1 0,grow");
		painelDefLeme.setLayout(new BorderLayout(0, 0));
		
		JPanel PainelDefAileron = new JPanel();
		painelDeflexao.add(PainelDefAileron, "cell 2 0,grow");
		PainelDefAileron.setLayout(new BorderLayout(0, 0));
		
		JPanel painelRPMTemp = new JPanel();
		painelRPMTemp.setBorder(null);
		panel_1.add(painelRPMTemp, "cell 1 2,grow");
		painelRPMTemp.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_2, "width 33%,height 50%");
		panel_2.setLayout(new MigLayout("insets 0", "[grow]", "[][grow][grow]"));
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, "cell 0 0,width 100%,height 10%");
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnSelecionarVdeos = new JButton("Selecionar V\u00EDdeo");
		btnSelecionarVdeos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		    	int result = fileChooser.showOpenDialog(P_Video.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    File selectedFile = fileChooser.getSelectedFile();
		    	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    	    i_Video.carregaVideo(selectedFile.getAbsolutePath());
		    	}
			}
		});
		panel_7.add(btnSelecionarVdeos, BorderLayout.CENTER);
		painelVideo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.add(painelVideo, "cell 0 1,width 100%,height 64.5%");
		painelVideo.setLayout(new BorderLayout(0, 0));
		canvas.setMinimumSize(new Dimension(10, 10));
		canvas.setIgnoreRepaint(true);
		painelVideo.add(canvas);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, "cell 0 2,grow");
		panel_8.setLayout(new MigLayout("insets 0", "[grow][][][grow][][][]", "[][grow][][][][][]"));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, "cell 0 1 7 1,grow");
		panel_9.setLayout(new BorderLayout(0, 0));
		
		slider.setValue(0);
		panel_9.add(slider, BorderLayout.CENTER);
		slider.setMajorTickSpacing(10);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		lblTempototal.setFont(new Font("Dialog", Font.BOLD, 11));
		
		panel_9.add(lblTempototal, BorderLayout.EAST);
		lblTempoPassado.setFont(new Font("Dialog", Font.BOLD, 11));
		panel_9.add(lblTempoPassado, BorderLayout.WEST);
		lblTempoPassado.setPreferredSize(new Dimension(60, 16));
		lblTempoPassado.setHorizontalTextPosition(SwingConstants.LEADING);
		lblTempoPassado.setSize(new Dimension(60, 16));
		
		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Play.png")));
		btnPlay.setToolTipText("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playVideo();
				
				
			}
		});
		btnPlay.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(btnPlay, "cell 0 2,growx");
		
		JButton btnPause = new JButton("");
		btnPause.setToolTipText("Pause");
		btnPause.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Pausa.png")));
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (embeddedMediaPlayer.isPlaying()) {
					pauseVideo();
			//		lblFps.setText(embeddedMediaPlayer.getFps()+" FPS");

				}

			}
		});
		btnPause.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(btnPause, "cell 1 2");
		
		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Stop.png")));
		btnStop.setToolTipText("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (embeddedMediaPlayer.isPlaying()) {
					stopVideo();
				}
			}
		});
		btnStop.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(btnStop, "cell 2 2");
		
		JButton btnx = new JButton("");
		btnx.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Avanca.png")));
		btnx.setToolTipText("Avan\u00E7a v\u00EDdeo");
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fatorDeMultiplicacao = fatorDeMultiplicacao*2;
				embeddedMediaPlayer.setRate(fatorDeMultiplicacao);
				lblFps.setText(fatorDeMultiplicacao+" x");
				tempoSistemaInicioPlay = System.currentTimeMillis();
				tempoVideoInicioPlay = embeddedMediaPlayer.getTime();
			}
		});
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Lento.png")));
		button_2.setToolTipText("C\u00E2mera lenta");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fatorDeMultiplicacao = fatorDeMultiplicacao/2;
				embeddedMediaPlayer.setRate(fatorDeMultiplicacao);
				lblFps.setText(fatorDeMultiplicacao+" x");
				tempoSistemaInicioPlay = System.currentTimeMillis();
				tempoVideoInicioPlay = embeddedMediaPlayer.getTime();
				
			}
		});
		button_2.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(button_2, "flowx,cell 6 2");
		btnx.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(btnx, "cell 6 2,alignx right");
		

		panel_8.add(lblFps, "cell 0 3");
		
		JButton btnSincronizarDados = new JButton("Sincronizar dados");
		btnSincronizarDados.setIcon(new ImageIcon(P_Video.class.getResource("/icons/Video/Sincronizar.png")));
		btnSincronizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				i.exibeTelaSincronizaDados(true);
			}
		});
		btnSincronizarDados.setMargin(new Insets(2, 2, 2, 2));
		panel_8.add(btnSincronizarDados, "cell 1 3 6 1,growx");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_3, "width 33%,height 50%");
		panel_3.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		JPanel painelHorizonte = new JPanel();
		panel_3.add(painelHorizonte, "width 50%,height 50%");
		
		JPanel painelBussola = new JPanel();
		panel_3.add(painelBussola, "width 50%,height 50%");
		painelBussola.setLayout(new BorderLayout(0, 0));
		painelGPS.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.add(painelGPS, "cell 0 1,width 50%,height 50%");
		painelGPS.setLayout(new BorderLayout(0, 0));
		
		JPanel painelVelocidade = new JPanel();
		panel_3.add(painelVelocidade, "cell 1 1,width 50%,height 50%");
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "cell 0 1,width 33%,height 50%");
		panel_4.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		painelGraf1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.add(painelGraf1, "cell 0 0,width 100%,height 50%");
		painelGraf1.setLayout(new BorderLayout(0, 0));
		painelGraf4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.add(painelGraf4, "cell 0 1,width 100%,height 50%");
		painelGraf4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "cell 1 1,width 33%,height 50%");
		panel_5.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		painelGraf2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.add(painelGraf2, "cell 0 0,grow");
		painelGraf2.setLayout(new BorderLayout(0, 0));
		painelGraf5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.add(painelGraf5, "cell 0 1,grow");
		painelGraf5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "cell 2 1,width 33%,height 50%");
		panel_6.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		painelGraf3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.add(painelGraf3, "cell 0 0,grow");
		painelGraf3.setLayout(new BorderLayout(0, 0));
		painelGraf6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.add(painelGraf6, "cell 0 1,grow");
		painelGraf6.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuIniciar = new JMenu("Dados");
		menuBar.add(menuIniciar);
		
		
		JMenuItem abrir = new JMenuItem(new AbstractAction("Selecionar dados") {
		    public void actionPerformed(ActionEvent e) {
		    	i_Video.exibeTelaSelecionaDadosVideo(true);

		    }});
		menuIniciar.add(abrir);
        painelVelocidade.setLayout(new BorderLayout(0, 0));
        velocimetro.setBackgroundColor(BackgroundColor.WHITE);
        velocimetro.setThresholdBehaviourInverted(true);
		
		
		
        velocimetro.setValueAnimated(22.0);
        velocimetro.setLedBlinking(true);
        velocimetro.setValue(21.0);
        velocimetro.setTrackVisible(true);
        velocimetro.setTitleAndUnitFont(new Font("Verdana", Font.PLAIN, 9));
        velocimetro.setTicklabelOrientation(TicklabelOrientation.HORIZONTAL);
        velocimetro.setThresholdVisible(true);
        velocimetro.setMaxValue(30.0);
        velocimetro.setTrackSection(10.0);
        velocimetro.setTrackStop(35.0);
        velocimetro.setThreshold(20.0);
        velocimetro.setFrameDesign(FrameDesign.ANTHRACITE);
        velocimetro.setForegroundType(ForegroundType.FG_TYPE4);
        velocimetro.setTitle("Velocidade");
        velocimetro.setUnitString("m/s");
        velocimetro.addMouseListener(new PopRadialListener(i.pVideoSelecionaDados.VELOCIDADE));
        painelVelocidade.add(velocimetro);
        painelHorizonte.setLayout(new BorderLayout(0, 0));
        horizonte.setBackgroundColor(BackgroundColor.WHITE);
        horizonte.setFrameDesign(FrameDesign.ANTHRACITE);
        
        painelHorizonte.add(horizonte);
		bussola.setBackgroundColor(BackgroundColor.WHITE);
		bussola.setFrameDesign(FrameDesign.ANTHRACITE);
		
		painelBussola.add(bussola);
		altimetro.setBackgroundColor(BackgroundColor.WHITE);
		altimetro.setFrameDesign(FrameDesign.ANTHRACITE);
		altimetro.setGaugeType(GaugeType.TYPE2);
		altimetro.setForegroundType(ForegroundType.FG_TYPE4);
		altimetro.setTitle("Alt\u00EDmetro");
		altimetro.setPointerColor(ColorDef.BLACK);
		altimetro.setPointerType(PointerType.TYPE12);
		altimetro.setLcdUnitStringVisible(true);
		altimetro.setLcdUnitString("m");
		altimetro.setUnitString("m");
		altimetro.addMouseListener(new PopRadialListener(i.pVideoSelecionaDados.ALTURA));
		painelAltimetro.add(altimetro);
		barometro.setBackgroundColor(BackgroundColor.WHITE);
		barometro.setFrameDesign(FrameDesign.ANTHRACITE);
				
		barometro.setValue(400.0);
		barometro.setKnobStyle(KnobStyle.BLACK);
		barometro.setFrameType(FrameType.SQUARE);
		barometro.setGaugeType(GaugeType.TYPE5);
		barometro.setUnitString("Pa");
		barometro.setTitle("Press\u00E3o");
		barometro.setThreshold(5000.0);
		barometro.setMaxValue(1000.0);
		barometro.setLcdColor(LcdColor.BLACK_LCD);
		barometro.addMouseListener(new PopRadialListener(i.pVideoSelecionaDados.PRESSAO));
		painelPressao.add(barometro);
		rpm.setBackgroundColor(BackgroundColor.WHITE);
		rpm.setFrameDesign(FrameDesign.ANTHRACITE);
		rpm.setGaugeType(GaugeType.TYPE3);
		rpm.setRangeOfMeasuredValuesVisible(true);
		rpm.setPeakValueVisible(true);
		rpm.setPeakValueEnabled(true);
		rpm.setPeakValue(40.0);
		rpm.setThresholdVisible(true);
		rpm.setThreshold(70.0);
		rpm.setValueAnimated(67.0);
		rpm.setUnitString("RPM");
		rpm.setTrackSection(100.0);
		rpm.setTitle("Rotação Hélice");
		rpm.setForegroundType(ForegroundType.FG_TYPE3);
		painelRPMTemp.add(rpm);
		rpm.addMouseListener(new PopMenuBarGraphListener(i.pVideoSelecionaDados.RPM));
		defProf.setFrameVisible(false);
		defProf.setFrameDesign(FrameDesign.BLACK_METAL);
		defProf.setBackgroundColor(BackgroundColor.WHITE);
		defProf.setMinValue(-3.0);
		defProf.setMaxValue(3.0);
		defProf.setTitle("Fator de carga X");
		defProf.setUnitString("g");
		defProf.addMouseListener(new PopLinearListener(i.pVideoSelecionaDados.DEFLEXAOPROFUNDOR));
		painelDefProfundor.add(defProf);
		defLeme.setFrameVisible(false);
		defLeme.setFrameDesign(FrameDesign.BLACK_METAL);
		defLeme.setBackgroundColor(BackgroundColor.WHITE);
		defLeme.setUnitString("g");
		defLeme.setMinValue(-3.0);
		defLeme.setMaxValue(3.0);
		defLeme.setTitle("Fator de carga Y");
		defLeme.addMouseListener(new PopLinearListener(i.pVideoSelecionaDados.DEFLEXAOLEME));
		painelDefLeme.add(defLeme);
		defAileron.setFrameVisible(false);
		defAileron.setFrameDesign(FrameDesign.BLACK_METAL);
		defAileron.setBackgroundColor(BackgroundColor.WHITE);
		defAileron.setUnitString("g");
		defAileron.setMinValue(-3.0);
		defAileron.setMaxValue(3.0);
		defAileron.setTitle("Fator de carga Z");
		defAileron.addMouseListener(new PopLinearListener(i.pVideoSelecionaDados.DEFLEXAOAILERON));
		PainelDefAileron.add(defAileron);
		
		graficoVideo1 = new G_Video();
		graficoVideo1.setEixoX("teste");
		graficoVideo1.setEixoY("oi");
		graficoVideo1.setNomeGrafico("teste");
		painelGraf1.removeAll();
		painelGraf1.add(graficoVideo1.getPainelVideo());
		painelGraf1.revalidate();
		
		graficoVideo2 = new G_Video();
		graficoVideo2.setEixoX("teste");
		graficoVideo2.setEixoY("oi");
		graficoVideo2.setNomeGrafico("teste");
		painelGraf2.removeAll();
		painelGraf2.add(graficoVideo2.getPainelVideo());
		painelGraf2.revalidate();
		
		graficoVideo3 = new G_Video();
		graficoVideo3.setEixoX("teste");
		graficoVideo3.setEixoY("oi");
		graficoVideo3.setNomeGrafico("teste");
		painelGraf3.removeAll();
		painelGraf3.add(graficoVideo3.getPainelVideo());
		painelGraf3.revalidate();
		
		graficoVideo4 = new G_Video();
		graficoVideo4.setEixoX("teste");
		graficoVideo4.setEixoY("oi");
		graficoVideo4.setNomeGrafico("teste");
		painelGraf4.removeAll();
		painelGraf4.add(graficoVideo4.getPainelVideo());
		painelGraf4.revalidate();
		
		graficoVideo5 = new G_Video();
		graficoVideo5.setEixoX("teste");
		graficoVideo5.setEixoY("oi");
		graficoVideo5.setNomeGrafico("teste");
		painelGraf5.removeAll();
		painelGraf5.add(graficoVideo5.getPainelVideo());
		painelGraf5.revalidate();
		
		
		graficoVideo6 = new G_Video();
		graficoVideo6.setEixoX("teste");
		graficoVideo6.setEixoY("oi");
		graficoVideo6.setNomeGrafico("teste");
		painelGraf6.removeAll();
		painelGraf6.add(graficoVideo6.getPainelVideo());
		painelGraf6.revalidate();
		
		graficoXYGPS.setEixoX("Distância X");
		graficoXYGPS.setEixoY("Distância Y");
		graficoXYGPS.setNomeGrafico("Trajeto");
		painelGPS.removeAll();
		painelGPS.add(graficoXYGPS.getPainelVideo());
		painelGPS.revalidate();
        
		carregaPropriedadesMedidores();
        
		timerAtualizaLabel = new Timer(velocidadeAtualizacao, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            if (embeddedMediaPlayer.isPlaying()) {
	            	if (parado) {
						pauseVideo();
						lblFps.setText(embeddedMediaPlayer.getRate()+" x");
				        System.out.println("Tamanho video: "+embeddedMediaPlayer.getLength());
				        tempoTotalVideo = embeddedMediaPlayer.getLength();
				        lblTempototal.setText(getContadorPorMillis(tempoTotalVideo));
				       
					}else {
						updateTime(); //if the video is seeking we get a mess
					}
	            }
            }
        });
		
		timerAtualizaLabel.start();
	}
	
    private void carregaPropriedadesMedidores() {
		double l[] = new double[3];
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.ALTURA);
		if(l[0]!=-1) {
			altimetro.setMaxValue(l[0]);
			altimetro.setMinValue(l[1]);
			altimetro.setGaugeType(getGaugeType((int) l[2]));
		}

		
		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.VELOCIDADE);
		if(l[0]!=-1) {
			velocimetro.setMaxValue(l[0]);
			velocimetro.setMinValue(l[1]);
			velocimetro.setGaugeType(getGaugeType((int) l[2]));
		}

		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.PRESSAO);
		if(l[0]!=-1) {
			barometro.setMaxValue(l[0]);
			barometro.setMinValue(l[1]);
			barometro.setGaugeType(getGaugeType((int) l[2]));
		}

		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.RPM);
		if(l[0]!=-1) {
			rpm.setMaxValue(l[0]);
			rpm.setMinValue(l[1]);
			rpm.setGaugeType(getGaugeType((int) l[2]));
		}

		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.DEFLEXAOPROFUNDOR);
		if(l[0]!=-1) {
			defProf.setMaxValue(l[0]);
			defProf.setMinValue(l[1]);
		}

		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.DEFLEXAOLEME);
		if(l[0]!=-1) {
			defLeme.setMaxValue(l[0]);
			defLeme.setMinValue(l[1]);
		}

		
		l = Configuracoes.getParametrosMedidor(P_Video_SelecionaDados.DEFLEXAOAILERON);
		if(l[0]!=-1) {
			defAileron.setMaxValue(l[0]);
			defAileron.setMinValue(l[1]);
		}

		
	}

	protected void playVideo() {
    	if (embeddedMediaPlayer.isPlayable()) {
			if (!embeddedMediaPlayer.isPlaying()) {
				parado = false;
				tempoSistemaInicioPlay = System.currentTimeMillis();
				tempoVideoInicioPlay = embeddedMediaPlayer.getTime();
				i.escalaTempo = GerenteDeDados.getEscalaTempo();
				embeddedMediaPlayer.play();
				
			}
		}

		
	}

	protected void pauseVideo() {
        embeddedMediaPlayer.pause();
		
	}

	protected void stopVideo() {
        parado = true;
        embeddedMediaPlayer.setTime(0);
        graficoXYGPS.limpaGrafico();

		
	}

	public void updateTime () {							// Aqui entra em cada update do timer
    
        if(embeddedMediaPlayer.isPlaying())
        {
        	long millis=tempoVideoInicioPlay + ((long) ((System.currentTimeMillis()- tempoSistemaInicioPlay)*fatorDeMultiplicacao));
        	float tempoEscalado = millis;
        	if(millis >= tempoTotalVideo) {
        		stopVideo();
        		lblTempoPassado.setText(lblTempototal.getText());
        	}
        	String s = getContadorPorMillis(millis);
            //setTitle(ms.format(new Time(sec)));
        	lblTempoPassado.setText(s);
        	slider.setValue((int)((millis*100)/tempoTotalVideo));
        	if(i.escalaTempo == 0) {						// Se estiver em "Segundos"
        		tempoEscalado = millis/((float) 1000.0);
        		tempoEscalado = tempoEscalado+(i.ajusteSincrono/((float) 1000.0));
        	}else {
        		tempoEscalado = millis+i.ajusteSincrono;
        	}
	        graficoVideo1.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        graficoVideo2.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        graficoVideo3.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        graficoVideo4.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        graficoVideo5.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        graficoVideo6.adicionaMarcacaoIntervalo(tempoEscalado, "", i.escalaTempo);
	        int idTempoPerto = i.c.closest(tempoEscalado, i.tempo);
	       // System.out.println(i.yaw[idTempoPerto]+"    "+i.tempo[idTempoPerto]);
	        if(i.idSensores[i.pVideoSelecionaDados.PITCH] != -1) {
			    horizonte.setPitch(i.pitch[idTempoPerto]*(-1));
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.ROLL] != -1) {
		        horizonte.setRoll(i.roll[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.YAW] != -1) {
		        bussola.setValue(i.yaw[idTempoPerto]);
	        }
	        if((i.idSensores[i.pVideoSelecionaDados.POSICAOX] != -1)&&(i.idSensores[i.pVideoSelecionaDados.POSICAOY] != -1)) {
		        graficoXYGPS.addValores(i.posicaox[idTempoPerto], i.posicaoy[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.VELOCIDADE] != -1) {
		        velocimetro.setValue(i.velocidade[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.PRESSAO] != -1) {
		        barometro.setValue(i.pressao[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.RPM] != -1) {
		        rpm.setValue(i.rpm[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.ALTURA] != -1) {
		        altimetro.setValue(i.altura[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.DEFLEXAOPROFUNDOR] != -1) {
		        defProf.setValue(i.defprof[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.DEFLEXAOLEME] != -1) {
		        defLeme.setValue(i.defleme[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.DEFLEXAOAILERON] != -1) {
		        defAileron.setValue(i.defaileron[idTempoPerto]);
	        }
	        if(i.idSensores[i.pVideoSelecionaDados.WOW] != -1) {
	        	setaPainelWow(i.wow[idTempoPerto]);
	        }
	        


	        
	        
     //       syncTimeline=true;
     //       timeline.setValue(Math.round(mp.getPosition()*100));
     //       syncTimeline=false;
     //       notifyObservers(mp.getTime());
        }
    }

	private void setaPainelWow(float wow) {
		if(wow == 1) {
			lblWow.setText("Status do avião:  No chão");
			painel_wow.setBackground((new Color(255, 181, 150)));
		}else {
			lblWow.setText("Status do avião:  Voando");
			painel_wow.setBackground(new Color(200, 255, 158));
		}
	}

	private String getContadorPorMillis(long millis) {
		return String.format("%02d:%02d:%02d", //dont know why normal Java date utils doesn't format the time right
			      TimeUnit.MILLISECONDS.toMinutes(millis),
			      TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)), 
			      millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
	}

	public void iniciaVideo(String caminho) {		// Aqui quando clicamos em "ok" na seleção de vídeo

		caminhoVideoAtual = caminho;

        canvas.setBackground(Color.black);
     //   canvas.setSize(100, 200);

        List<String> vlcArgs = new ArrayList<String>();

        vlcArgs.add("--no-plugins-cache");
        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--no-snapshot-preview");


        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("Teste");
        canvas.setVisible(true);
        embeddedMediaPlayer.canPause();
        embeddedMediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas));
        embeddedMediaPlayer.playMedia(caminhoVideoAtual);
        tempoSistemaInicioPlay = System.currentTimeMillis();
        tempoVideoInicioPlay = 0;
        lblTempoPassado.setText("00:00:000");
		graficoXYGPS.setId(i.idSensores[i.pVideoSelecionaDados.POSICAOX],i.idSensores[i.pVideoSelecionaDados.POSICAOY]);
		i.escalaTempo = GerenteDeDados.getEscalaTempo();	
	}


	public void carregaGrafico(int numeroGrafico, String unidadeDeMedidaX, String unidadeDeMedidaY, String nomeDado, List<Float> valorX, List<Float> valorY, int id) {
		switch (numeroGrafico) {
	        case 0:
	            setaGrafico(painelGraf1, graficoVideo1, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
	        case 1:
	            setaGrafico(painelGraf2, graficoVideo2, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
	        case 2:
	            setaGrafico(painelGraf3, graficoVideo3, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
	        case 3:
	            setaGrafico(painelGraf4, graficoVideo4, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
	        case 4:
	            setaGrafico(painelGraf5, graficoVideo5, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
	        case 5:
	            setaGrafico(painelGraf6, graficoVideo6, unidadeDeMedidaX, unidadeDeMedidaY, nomeDado, valorX, valorY, id);
	            break;
		}
		
	}


	private void setaGrafico(JPanel painelGraf, G_Video grafico, String unidadeDeMedidaX, String unidadeDeMedidaY, String nomeDado, List<Float> valorX, List<Float> valorY, int id) {
		painelGraf.removeAll();
		grafico.setEixoX(unidadeDeMedidaX);
		grafico.setEixoY(unidadeDeMedidaY);
		grafico.setNomeGrafico(nomeDado);
		grafico.setValores(valorX, valorY);
		grafico.setId(id);
		painelGraf.add(grafico.getPainelVideo());
		painelGraf.revalidate();
		
	}
	
	class BotaoDireitoBargrath extends JPopupMenu {
	    JMenuItem valMin, valMax, tipoMedidor;
	    public BotaoDireitoBargrath(final Component component, final int sensor){
		    final RadialBargraph oi = (RadialBargraph) component;
	    	valMax = new JMenuItem(new AbstractAction("Editar valor máximo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMaximo, oi.getMaxValue());
			    	if(input != null) {
				    	oi.setMaxValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, Double.parseDouble(input), oi.getMinValue(), getGaugeTypeNum(oi.getGaugeType()));
			    	}

			    }});
	        add(valMax);
	        
	        valMin = new JMenuItem(new AbstractAction("Editar valor mínimo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMinimo, oi.getMinValue());
			    	if(input != null) {
				    	oi.setMinValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, oi.getMaxValue(), Double.parseDouble(input), getGaugeTypeNum(oi.getGaugeType()));
			    	}


			    }});
	        add(valMin);
	        
	        tipoMedidor = new JMenuItem(new AbstractAction("Editar tipo de medidor") {
			    public void actionPerformed(ActionEvent e) {
			    	String[] list = {"Tipo 1", "Tipo 2", "Tipo 3", "Tipo 4", "Tipo 5"};
			    	JComboBox jcb = new JComboBox(list);
			    	jcb.setEditable(true);
			        String tipo = ((String) JOptionPane.showInputDialog(null, 
			                "Selecione o tipo de medidor",
			                "Editar componente",
			                JOptionPane.QUESTION_MESSAGE, 
			                null, 
			                list, 
			                ("Tipo "+getGaugeTypeNum(oi.getGaugeType()))));
			        if(tipo != null) {
				        int selecionado = Integer.parseInt(tipo.substring(tipo.length()-1));
				        System.out.println("caiu aqui "+selecionado);
				        switch (selecionado) {
						case 1:
							oi.setGaugeType(GaugeType.TYPE1);
							break;
						case 2:
							oi.setGaugeType(GaugeType.TYPE2);
							break;
						case 3:
							oi.setGaugeType(GaugeType.TYPE3);
							break;
						case 4:
							oi.setGaugeType(GaugeType.TYPE4);
							break;
						case 5:
							oi.setGaugeType(GaugeType.TYPE5);
							break;
	
						}
				    	Configuracoes.setParametrosMedidor(sensor, oi.getMaxValue(), oi.getMinValue(), selecionado);
			        }


			    }});
	        add(tipoMedidor);
	    }
	}
	
	class PopMenuBarGraphListener extends MouseAdapter {
		int sensor;
	    public PopMenuBarGraphListener(int sensor) {
			this.sensor = sensor;
		}

		public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	    	BotaoDireitoBargrath menu = new BotaoDireitoBargrath(e.getComponent(),sensor);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}
	
	
	
	class BotaoDireitoRadial extends JPopupMenu {
	    JMenuItem valMin, valMax, tipoMedidor;
	    public BotaoDireitoRadial(final Component component, final int sensor){
		    final Radial oi = (Radial) component;
	    	valMax = new JMenuItem(new AbstractAction("Editar valor máximo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMaximo, oi.getMaxValue());
			    	if(input != null) {
				    	oi.setMaxValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, Double.parseDouble(input), oi.getMinValue(), getGaugeTypeNum(oi.getGaugeType()));
			    	}
			    }});
	        add(valMax);
	        
	        valMin = new JMenuItem(new AbstractAction("Editar valor mínimo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMinimo, oi.getMinValue());
			    	if(input != null) {
				    	oi.setMinValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, oi.getMaxValue(), Double.parseDouble(input), getGaugeTypeNum(oi.getGaugeType()));
			    	}
			    }});
	        add(valMin);
	        
	        tipoMedidor = new JMenuItem(new AbstractAction("Editar tipo de medidor") {
			    public void actionPerformed(ActionEvent e) {
			    	String[] list = {"Tipo 1", "Tipo 2", "Tipo 3", "Tipo 4", "Tipo 5"};
			    	JComboBox jcb = new JComboBox(list);
			    	jcb.setEditable(true);
			        String tipo = ((String) JOptionPane.showInputDialog(null, 
			                "Selecione o tipo de medidor",
			                "Editar componente",
			                JOptionPane.QUESTION_MESSAGE, 
			                null, 
			                list, 
			                ("Tipo "+getGaugeTypeNum(oi.getGaugeType()))));
			        if(tipo != null) {
				        int selecionado = Integer.parseInt(tipo.substring(tipo.length()-1));
				        System.out.println("caiu aqui "+selecionado);
						oi.setGaugeType(getGaugeType(selecionado));
	
				    	Configuracoes.setParametrosMedidor(sensor, oi.getMaxValue(), oi.getMinValue(), selecionado);
			        }

			    }});
	        add(tipoMedidor);
	    }
	}
	
	
	class PopRadialListener extends MouseAdapter {
		int sensor;
	    public PopRadialListener(int sensor) {
			this.sensor = sensor;
		}

		public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	    	BotaoDireitoRadial menu = new BotaoDireitoRadial(e.getComponent(), sensor);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}
	
	class BotaoDireitoLinear extends JPopupMenu {
	    JMenuItem valMin, valMax;
	    public BotaoDireitoLinear(final Component component, final int sensor){
		    final Linear oi = (Linear) component;
	    	valMax = new JMenuItem(new AbstractAction("Editar valor máximo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMaximo, oi.getMaxValue());
			    	if(input != null) {
				    	oi.setMaxValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, Double.parseDouble(input), oi.getMinValue(), -1);	// linear não tem tipo =(
			    	}

			    }});
	        add(valMax);
	        
	        valMin = new JMenuItem(new AbstractAction("Editar valor mínimo") {
			    public void actionPerformed(ActionEvent e) {
			    	String input = JOptionPane.showInputDialog(null, digiteValorMinimo, oi.getMinValue());
			    	if(input != null) {
				    	oi.setMinValue(Double.parseDouble(input));
				    	Configuracoes.setParametrosMedidor(sensor, oi.getMaxValue(), Double.parseDouble(input), -1);
			    	}

			    }});
	        add(valMin);

	    }
	}
	
	
	class PopLinearListener extends MouseAdapter {
		int sensor;
	    public PopLinearListener(int sensor) {
			this.sensor = sensor;
		}

		public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	    	BotaoDireitoLinear menu = new BotaoDireitoLinear(e.getComponent(), sensor);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}
	
	public GaugeType getGaugeType(int num) {
        switch (num) {
		case 1:
			return GaugeType.TYPE1;
		case 2:
			return GaugeType.TYPE2;
		case 3:
			return GaugeType.TYPE3;
		case 4:
			return GaugeType.TYPE4;
		case 5:
			return GaugeType.TYPE5;
		}
		return GaugeType.TYPE4;
	}

	public int getGaugeTypeNum(GaugeType t) {
		if(t.equals(GaugeType.TYPE1))
			return 1;
		if(t.equals(GaugeType.TYPE2))
			return 2;
		if(t.equals(GaugeType.TYPE3))
			return 3;
		if(t.equals(GaugeType.TYPE4))
			return 4;
		if(t.equals(GaugeType.TYPE5))
			return 5;
		else return 4;
	}

	public void setTituloMedidores() {
		if(i.idSensores[P_Video_SelecionaDados.ALTURA] != -1) {
			altimetro.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.ALTURA]).getNomeDado());
			altimetro.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.ALTURA]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.VELOCIDADE] != -1) {
			velocimetro.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.VELOCIDADE]).getNomeDado());
			velocimetro.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.VELOCIDADE]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.PRESSAO] != -1) {
			barometro.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.PRESSAO]).getNomeDado());
			barometro.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.PRESSAO]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.RPM] != -1) {
			rpm.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.RPM]).getNomeDado());
			rpm.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.RPM]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.DEFLEXAOPROFUNDOR] != -1) {
			defProf.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOPROFUNDOR]).getNomeDado());
			defProf.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOPROFUNDOR]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.DEFLEXAOLEME] != -1) {
			defLeme.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOLEME]).getNomeDado());
			defLeme.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOLEME]).getUnidadeDeMedida());
		}
		if(i.idSensores[P_Video_SelecionaDados.DEFLEXAOAILERON] != -1) {
			defAileron.setTitle(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOAILERON]).getNomeDado());
			defAileron.setUnitString(GerenteDeDados.dado.get(i.idSensores[P_Video_SelecionaDados.DEFLEXAOAILERON]).getUnidadeDeMedida());
		}
		


		
	}
	
	public static String getJarContainingFolder(Class aclass) throws Exception {
		  CodeSource codeSource = aclass.getProtectionDomain().getCodeSource();

		  File jarFile;

		  if (codeSource.getLocation() != null) {
		    jarFile = new File(codeSource.getLocation().toURI());
		  }
		  else {
		    String path = aclass.getResource(aclass.getSimpleName() + ".class").getPath();
		    String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
		    jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
		    jarFile = new File(jarFilePath);
		  }
		  return jarFile.getParentFile().getAbsolutePath();
	}

}