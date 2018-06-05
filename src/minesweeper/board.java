package minesweeper;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

public class board extends JFrame
{
	static final int[] cX = {0,1,1,1,0,-1,-1,-1};
	static final int[] cY = {1,1,0,-1,-1,-1,0,1};
    final int PREGAME = 0, INGAME = 1, ENDGAME = 2;
    final int EASY = 0, MEDIUM = 1, HARD = 2;
    private int[][] brd;
    private int[][] prevBrd;
    int gState = PREGAME;
    Canvas canvas;
    board(int diff) {
        int width;
        int height;
        switch (diff){
            case HARD:
                width = 30;
                height = 16;
                break;
            case MEDIUM:
                width = 16;
                height = 16;
                break;
            case EASY: 
                width = 9;
                height = 9;
                break;
            default:
                width = 30;
                height = 16;
                break;
        }    
        int[][] tbrd = new int[height][width];
        brd = tbrd;
        copyToPrev(brd);
        this.setTitle("Minesweeper");
        this.setSize(width*41+1,height*41+1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Image image = new Image();
        this.setContentPane(image);
        this.setVisible(true);
    }
    //https://www.youtube.com/watch?v=EMu1cC2Vnis
    public class Image extends JPanel {
    	public void paintComponent(Graphics g) {
    		
    	}
    }
    
    private void placeMines(int diff, int startX, int startY){
        int num;
        switch (diff){
            case HARD:
                num = 99;
                break;
            case MEDIUM:
                num = 40;
                break;
            case EASY: 
                num = 10;
                break;
            default:
                num = 99;
                break;
        }
        
        for (int i = 0; i < num; i++){
            int y = (int)(Math.random()*brd.length);
            int x = (int)(Math.random()*brd[0].length);
            int disX = Math.abs(startX-x);
            int disY = Math.abs(startY-y);
            if (brd[y][x] != -1 && disX > 1 && disY > 1)
                brd[y][x] = -1;
            else 
                i--;
        }
    }
    
	private int check(int y, int x) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			if (this.inBounds(y+cY[i],x+cX[i]) && brd[y+cY[i]][x+cX[i]] == -1) {
				num++;
			}
		}
		return num;
	}
	
	private boolean inBounds(int y,int x) {
		return (y<brd.length && y > -1 && x < brd[0].length && x > -1);
	}
	
	private void clicked(int y, int x) {
		if (brd[y][x] == -1) {
			this.endgame();
		} else {
			brd[y][x] = check(y, x);
		}
		if (brd[y][x] == 0) {
			for (int i = 0; i < 8; i++) {
				if (inBounds(y+cY[i],x+cX[i]) && brd[y+cY[i]][x+cX[i]] == -2) {
					clicked(y+cY[i],x+cX[i]);
				}					
			}
		}
	}
	
	private void endgame() {
		this.gState = ENDGAME;
	}
	
	private void midgame() {
		this.gState = INGAME;
	}
	
	void copyToPrev(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				this.prevBrd[i][j] = board[i][j];
			}
		}
	}
	
	void update() {
		switch(gState) {
		case PREGAME:
		this.
			
		
	}
}
