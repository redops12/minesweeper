package minesweeper;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;


public class Main{
    final static int EASY = 0, MEDIUM = 1, HARD = 2;
	public static void main(String[] args) {
		newGame();
	}
	
	static int diff = HARD;
	
	public static void newGame() {
		JFrame options = new JFrame();
		options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setSize(40, 150);
		JPanel panel = new JPanel();
		JRadioButton easy,m,h;
		ButtonGroup buttonGroup = new ButtonGroup();
		easy = new JRadioButton("Easy");
		buttonGroup.add(easy);
		panel.add(easy);
		m = new JRadioButton("Medium");
		buttonGroup.add(m);
		panel.add(m);
		h = new JRadioButton("Hard");
		h.setSelected(true);
		buttonGroup.add(h);
		panel.add(h);
		JButton play = new JButton("Play");
		play.setBounds(5, 75, 15, 10);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (easy.isSelected()) {
					setDiff(EASY);
				} else if (m.isSelected()) {
					setDiff(MEDIUM);
				} else if (h.isSelected()) {
					setDiff(HARD);
				}
				board brd = new board(diff);
				options.setVisible(false);
			}
		});
		panel.add(play);
		options.add(panel);
		options.setVisible(true);
	}
	
	static private void setDiff(int difficulty) {
		diff = difficulty;
	}
	
//	//iterate through this 8 times and add it to x and y positions to get circle around point
//	static final int[] cX = {0,1,1,1,0,-1,-1,-1};
//	static final int[] cY = {1,1,0,-1,-1,-1,0,1};
//	static boolean alive = true;
//	static int[][] board = {
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-1,-2,-1,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
//		{-2,-1,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
//		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}	
//	};
//	
//	
//
//	
//	public static void main(String[] args) {
//		Scanner scn = new Scanner(System.in);
//		print();
//		while (alive) {
//			int x = scn.nextInt();
//			int y = scn.nextInt();
//			clicked(y,x);
//			print();
//		}
//	}
//	
//	private static int check(int y, int x) {
//		int num = 0;
//		for (int i = 0; i < 8; i++) {
//			if (inBounds(y+cY[i],x+cX[i]) && board[y+cY[i]][x+cX[i]] == -1) {
//				num++;
//			}
//		}
//		return num;
//	}
//	
//	private static boolean inBounds(int y,int x) {
//		return (y<board.length && y > -1 && x < board[0].length && x > -1);
//	}
//	
//	private static void clicked(int y, int x) {
//		if (board[y][x] == -1) {
//			endgame();
//		} else {
//			board[y][x] = check(y, x);
//		}
//		if (board[y][x] == 0) {
//			for (int i = 0; i < 8; i++) {
//				if (inBounds(y+cY[i],x+cX[i]) && board[y+cY[i]][x+cX[i]] == -2) {
//					clicked(y+cY[i],x+cX[i]);
//				}					
//			}
//		}
//	}
//	
//	private static void print() {
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//				if (board[i][j] > -1) {
//					System.out.print(board[i][j]);
//				} else if (board[i][j] == -2) {
//					System.out.print("+");
//				} else if (board[i][j] == -1 && alive) {
//					System.out.print("+");
//				} else if (board[i][j] == -1 && !alive) {
//					System.out.print("*");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//	
//	private static void endgame() {
//		alive = false;
//	}
}
