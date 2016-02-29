package main;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import org.jfree.ui.RefineryUtilities;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.fonts.FontPolicy;
import org.pushingpixels.substance.api.fonts.FontSet;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel;

import javax.swing.SwingUtilities;

public class Main{
	
    
	public static void main(String[] args)  throws InterruptedException {
		SplashHelper.iniciaSplash();
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        try {

	      //    UIManager.setLookAndFeel(new SubstanceCeruleanLookAndFeel());
		 //         UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
	        	UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
	        	UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());

	       // 	UIManager.put(SubstanceLookAndFeel.COLORIZATION_FACTOR, 1.0);
	        	changeFonts(new Font("Lucida Sans", Font.PLAIN, 12));

	        } catch (Exception e) {
	          System.out.println("Substance Graphite failed to initialize");
	        }
			try {
				ControlBox control = new ControlBox();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						//Create simulation control
		
	      }
	    });

		//ControlBox control = new ControlBox();						//Create simulation control
	}
	
	static void changeFonts(Font sf){
		UIManager.put("Button.font",sf); 
		UIManager.put("ToggleButton.font",sf); 
		UIManager.put("RadioButton.font",sf); 
		UIManager.put("CheckBox.font",sf); 
		UIManager.put("ColorChooser.font",sf); 
		UIManager.put("ToggleButton.font",sf); 
		UIManager.put("ComboBox.font",sf); 
		UIManager.put("ComboBoxItem.font",sf); 
		UIManager.put("InternalFrame.titleFont",sf); 
		UIManager.put("Label.font",sf); 
		UIManager.put("List.font",sf); 
		UIManager.put("MenuBar.font",sf); 
		UIManager.put("Menu.font",sf); 
		UIManager.put("MenuItem.font",sf); 
		UIManager.put("RadioButtonMenuItem.font",sf); 
		UIManager.put("CheckBoxMenuItem.font",sf); 
		UIManager.put("PopupMenu.font",sf); 
		UIManager.put("OptionPane.font",sf); 
		UIManager.put("Panel.font",sf); 
		UIManager.put("ProgressBar.font",sf); 
		UIManager.put("ScrollPane.font",sf); 
		UIManager.put("Viewport",sf); 
		UIManager.put("TabbedPane.font",sf); 
		UIManager.put("TableHeader.font",sf); 
		UIManager.put("TextField.font",sf); 
		UIManager.put("PasswordFiled.font",sf); 
		UIManager.put("TextArea.font",sf); 
		UIManager.put("TextPane.font",sf); 
		UIManager.put("EditorPane.font",sf); 
		UIManager.put("TitledBorder.font",sf); 
		UIManager.put("ToolBar.font",sf); 
		UIManager.put("ToolTip.font",sf); 
		UIManager.put("Tree.font",sf); 

	//	config.applyLookAndFeel(config.LOOK_AND_FEEL);
	}
	
	
}
	  
