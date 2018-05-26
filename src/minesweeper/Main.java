package minesweeper;
import java.util.Scanner;

public class Main {
	//iterate through this 8 times and add it to x and y positions to get circle around point
	static final int[] cX = {0,1,1,1,0,-1,-1,-1};
	static final int[] cY = {1,1,0,-1,-1,-1,0,1};
	static boolean alive = true;
	static int[][] board = {
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-1,-2,-2,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-1,-2,-1,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
		{-2,-1,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},	
		{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2}	
	};
	
	

	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		print();
		while (alive) {
			int x = scn.nextInt();
			int y = scn.nextInt();
			clicked(y,x);
			print();
		}
	}
	
	private static int check(int y, int x) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			if (inBounds(y+cY[i],x+cX[i]) && board[y+cY[i]][x+cX[i]] == -1) {
				num++;
			}
		}
		return num;
	}
	
	private static boolean inBounds(int y,int x) {
		return (y<board.length && y > -1 && x < board[0].length && x > -1);
	}
	
	private static void clicked(int y, int x) {
		if (board[y][x] == -1) {
			endgame();
		} else {
			board[y][x] = check(y, x);
		}
		if (board[y][x] == 0) {
			for (int i = 0; i < 8; i++) {
				if (inBounds(y+cY[i],x+cX[i]) && board[y+cY[i]][x+cX[i]] == -2) {
					clicked(y+cY[i],x+cX[i]);
				}					
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] > -1) {
					System.out.print(board[i][j]);
				} else if (board[i][j] == -2) {
					System.out.print("+");
				} else if (board[i][j] == -1 && alive) {
					System.out.print("+");
				} else if (board[i][j] == -1 && !alive) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void endgame() {
		alive = false;
	}
}
