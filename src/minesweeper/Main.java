package minesweeper;

public class Main {
	//iterate through this 8 times and add it to x and y positions to get circle around point
	static final int[] cX = {0,1,1,1,0,-1,-1,-1};
	static final int[] cY = {1,1,0,-1,-1,-1,0,1};

	public static void main(String[] args) {
		
	}
	
	private int check(int[][] board, int y, int x) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			if (board[y+cY[i]][x+cX[i]] == -1) {
				num++;
			}
		}
		return num;
	}
	
	private void clicked(int[][] board, int y, int x) {
		if (board[y][x] == -1) {
			this.endgame();
		} else {
			board[y][x] = this.check(board, y, x);
		}
		if (board[y][x] == 0) {
			board[y][x] = -2;
			for (int i = 0; i < 8; i++) {
				if (board[y+cY[i]][x+cX[i]] != -2) {
					clicked(board, y+cY[i],x+cX[i]);
				}					
			}
		}
	}
	
	private void endgame() {}
}
