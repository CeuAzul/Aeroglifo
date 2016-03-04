package main;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;



public class SplashHelper {
	   final static InputStream imageStream4 = Main.class.getResourceAsStream("/icons/Main/Splash Screen mais Branca massa.png");
	   final static ProgressSplashScreen splashScreen4 = new ProgressSplashScreen(imageStream4);
	   
	public static void iniciaSplash()  throws InterruptedException {
	   splashScreen4.showProgress(1, 1);

	}
	
	public static void moveBarra(final int alvo, final int tempoAproximado)  throws InterruptedException {

	}
	
	public static void fechaSplash()  throws InterruptedException {
		   splashScreen4.close();
	}
	

}
