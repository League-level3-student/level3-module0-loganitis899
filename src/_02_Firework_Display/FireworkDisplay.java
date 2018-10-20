package _02_Firework_Display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FireworkDisplay extends JPanel implements ActionListener {
	public static int WIDTH = 720;
	public static int HEIGHT = 480;

	private JFrame window;
	private JPanel buttonPanel;
	private JButton squirtButton;
	private Timer timer;

	ArrayList<Firework> Fireworks = new ArrayList<Firework>();

	public static void main(String[] args) {
		new FireworkDisplay().start();
	}

	public void start() {

		window = new JFrame();
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);

		WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
		HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		buttonPanel = new JPanel();
		
		
		
		window.setBackground(Color.BLUE);
		squirtButton = new JButton("FIRE");
		squirtButton.addActionListener((e) -> {
			fire();
		});

		buttonPanel.add(squirtButton);
		window.add(buttonPanel, BorderLayout.SOUTH);

		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		timer = new Timer(1000 / 60, this);
	}

	private void fire() {
		// squirtButton.setEnabled(false);
		Firework fw = new Firework();
		Fireworks.add(fw);
		for (int i = 0; i < 100; i++) {
			fw.launch();
		}

		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		Random Randy = new Random();
		int randy = Randy.nextInt(255);
		int rondy = Randy.nextInt(255);
		int Rcndy = Randy.nextInt(255);
		Color color = new Color(randy, rondy, Rcndy);
		g.setColor(color);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (Firework s : Fireworks) {
			s.drawSparks(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		for (Firework s : Fireworks) {
			s.updateSparks();
		}

		boolean reset = true;
		for (Firework s : Fireworks) {
			if (!s.dead) {
				reset = false;
			}
		}
		if (reset) {
			timer.stop();
			squirtButton.setEnabled(true);
		}
	}
}
