package linearLightsOut;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Runs the Linear Lights Out application.
 * 
 * @author Bochuan Lu. Created Oct 14, 2015.
 */
public class LinearMain {

	private static final int HEIGHT = 150;
	private static final int WIDTH = 48;
	private static final int DEFAULT_WIDTH = 150;

	/**
	 * Starts here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String nButtonsString = JOptionPane.showInputDialog("How many buttons would you like?");
		int nButtons = Integer.parseInt(nButtonsString);
		
		//Construct a frame to show the Linear Lights Out.
		final JFrame lightsOutFrame = new JFrame();

		lightsOutFrame.setSize(WIDTH * nButtons + DEFAULT_WIDTH, HEIGHT);
		lightsOutFrame.setTitle("Linear Lights Out!");

		//Constructs buttonPanel.
		final JPanel buttonPanel = new JPanel();
		
		//Constructs light buttons of given number 
		//and add them on the buttonPanel. 
		final LightButton lights = new LightButton(nButtons);
		lights.setLights();
		lights.setLightSwitch(lightsOutFrame);
		lights.addToPanel(buttonPanel);
		
		//Constructs controlPanel.
		JPanel controlPanel = new JPanel();
		
		//Constructs new game button and quit button 
		//and add them on the controlPanel.
		JButton newGameButton = new JButton("New Game");
		controlPanel.add(newGameButton);
		JButton quitButton = new JButton("Quit");
		controlPanel.add(quitButton);
		
		//Adds the controlPanel and buttonPanel to the frame.
		lightsOutFrame.add(buttonPanel, BorderLayout.NORTH);
		lightsOutFrame.add(controlPanel, BorderLayout.SOUTH);
		
		//Constructs a new ActionListener 
		//to give function to the new game button.
		ActionListener newGame = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lights.refreshLights();
				lightsOutFrame.setTitle("Linear Lights Out!");

			}

		};
		newGameButton.addActionListener(newGame);
		
		//Constructs a new ActionListener 
		//to give function to the quit button.
		ActionListener quit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		quitButton.addActionListener(quit);
		
		lightsOutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lightsOutFrame.setVisible(true);
	}
}
