package chapter2.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chapter2.agent_AB.Agent;
import chapter2.agent_AB.AgentProgram;
import chapter2.agent_AB.Environment;
import chapter2.agent_AB.Environment.LocationState;

public class Enviroment2D extends JFrame {
	JPanel jpMain = new JPanel();
	JButton[][] bt;
	int score, step;
	String action;
	Point point;

	JLabel lbScore;

	public Enviroment2D(int x, int y) {

		setLayout(new BorderLayout());
		JPanel jpTop = new JPanel();
		jpTop.setLayout(new GridLayout(6, 2, 3, 3));
//		add(jpTop, BorderLayout.EAST);
//		jpTop.setBackground(Color.DARK_GRAY);

		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		JLabel lbX = new JLabel("-   X");
		JLabel lbY = new JLabel("-   y");
		JLabel lbWall = new JLabel("-   wall");
		JLabel lbDirty = new JLabel("-   dirty");
		JLabel lbSpeed = new JLabel("-   Speed");

		JTextField txtX = new JTextField();
		JTextField txtY = new JTextField();
		JTextField txtWall = new JTextField();
		JTextField txtDirty = new JTextField();
		JTextField txtSpeed = new JTextField();

		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x =Integer.valueOf( txtX.getText() );
				int y =Integer.valueOf(txtY.getText() );
				Double w =Double.valueOf(txtWall.getText());
				Double d =Double.valueOf(txtDirty.getText());
				int s =Integer.valueOf(txtSpeed.getText());
				System.out.println(x+" ");
				System.out.println(x+" ");
				System.out.println(w+" ");
				System.out.println(d+" ");
				System.out.println(s+" ");
//				
				Environment env = new Environment(x,y,w,d,s);
				Agent agent = new Agent(new AgentProgram(env));
				env.addAgent(agent, new Point(0, 0));
				
				env.stepUntilDone();
				
			}
		});
		
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jpTop.add(start);
		jpTop.add(stop);
		jpTop.add(lbX);
		jpTop.add(txtX);
		jpTop.add(lbY);
		jpTop.add(txtY);
		jpTop.add(lbWall);
		jpTop.add(txtWall);
		jpTop.add(lbDirty);
		jpTop.add(txtDirty);
		jpTop.add(lbSpeed);
		jpTop.add(txtSpeed);

		JPanel jpBot = new JPanel();
		add(jpBot, BorderLayout.SOUTH);
		lbScore = new JLabel("Score : " + score + "Step : " + step);
		jpBot.add(lbScore);

//			JPanel jpMain = new JPanel();
		add(jpMain);
		jpMain.setLayout(new GridLayout(x, y, 5, 5));

		bt = new JButton[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				bt[i][j] = new JButton();
				bt[i][j].setEnabled(false);
				jpMain.add(bt[i][j]);
			}
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 500);
		setVisible(true);
	}

	public void updateMap(int x, int y, Map<Point, Environment.LocationState> map, Point agentPoint) {
		// TODO Auto-generated method stub
		score = Environment.score;
		step = Environment.step;
		lbScore.setText(
				"Score : " + score + "    Step : " + step );
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				if (agentPoint.equals(new Point(i, j))) {

					bt[i][j].setBackground(Color.red);
				} else if (map.get(new Point(i, j)).equals(LocationState.WALL)) {
					bt[i][j].setBackground(Color.black);
				} else if (map.get(new Point(i, j)).equals(LocationState.DIRTY)) {
					bt[i][j].setBackground(Color.orange);
				} else {
					bt[i][j].setBackground(new Color(154, 250, 180));
				}

			}

		}
	}
	public static void main(String[] args) {
//		new Enviroment2D(4, 4);
	}

}
