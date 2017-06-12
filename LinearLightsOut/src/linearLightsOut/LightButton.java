package linearLightsOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * This class bulids the light buttons with given number and particular
 * function.
 *
 * @author Bochuan Lu. Created Oct 14, 2015.
 */
public class LightButton {
	private ArrayList<JButton> lights = new ArrayList<JButton>();
	private int lightsNum;

	/**
	 * 
	 * Contructs a default LightButton.
	 *
	 */
	public LightButton() {
		this.lightsNum = 0;
	}

	/**
	 * 
	 * Contructs a LightButton with given number.
	 *
	 * @param number
	 */
	public LightButton(int number) {
		this.lightsNum = number;
	}

	/**
	 * 
	 * Set the light buttons with given number.
	 *
	 */
	public void setLights() {
		for (int i = 0; i < this.lightsNum; i++) {
			if (Math.random() > 0.5) {
				this.lights.add(new JButton("X"));
			} else {
				this.lights.add(new JButton("O"));
			}
		}
	}

	/**
	 * 
	 * Refreshs the light buttons to build a new game.
	 *
	 */
	public void refreshLights() {
		for (int i = 0; i < this.lightsNum; i++) {
			if (Math.random() > 0.5) {
				this.lights.get(i).setText("X");
			} else {
				this.lights.get(i).setText("O");
			}
		}
	}

	/**
	 * 
	 * Adds the light buttons to a given panel.
	 *
	 * @param panel
	 */
	public void addToPanel(JPanel panel) {
		for (JButton light : this.lights) {
			panel.add(light);
		}
	}

	/**
	 * 
	 * Returns a ActionListener can switch the lights on and off and notify the
	 * player if the player wins.
	 *
	 * @param light
	 * @return
	 */
	public ActionListener setLightsFunction(final JButton light, final JFrame frame,
			final ArrayList<JButton> arrLights) {
		ActionListener lightsFunction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (light.getText().equals("X")) {
					light.setText("O");
				} else {
					light.setText("X");
				}
				boolean winning = true;
				for (JButton light : arrLights) {
					if (light.getText().equals("O")) {
						winning = false;
					}
				}
				if (winning) {
					frame.setTitle("You Win!!!");
				}

			}
		};
		return lightsFunction;
	}

	/**
	 * 
	 * Sets the switch function to light buttons, and notifies the player if the
	 * player wins the game.
	 * 
	 */
	public void setLightSwitch(JFrame frame) {
		for (int i = 0; i < this.lightsNum; i++) {
			if (i != 0 && i != this.lightsNum - 1) {
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i - 1), frame, this.lights));
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i), frame, this.lights));
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i + 1), frame, this.lights));
			} else if (i == 0) {
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i), frame, this.lights));
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i + 1), frame, this.lights));
			} else {
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i - 1), frame, this.lights));
				this.lights.get(i).addActionListener(setLightsFunction(this.lights.get(i), frame, this.lights));
			}

		}
	}
}