package minesweeper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
//import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

@SuppressWarnings("serial")
public class board extends JFrame
{
	static final int[] cX = {0,1,1,1,0,-1,-1,-1};
	static final int[] cY = {1,1,0,-1,-1,-1,0,1};
    final int PREGAME = 0, INGAME = 1, ENDGAME = 2;
    final int EASY = 0, MEDIUM = 1, HARD = 2;
    private int[][] bombs;
    private int[][] brd;
    private int[][] prevBrd;
    int gState = PREGAME;
    int diff = HARD;
    Image unclicked;
    Image one;
    Image two;
    Image three;
    Image four;
    Image five;
    Image six;
    Image seven;
    Image eight;
    Image bomb;
    Image clicked;
    Image clickedBomb;
    Image flag;
    Image wrongFlag;
    ArrayList<Integer> changes = new ArrayList<Integer>();

    board(int diff) {
        importImages();
    	this.diff = diff;
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
        int[][] bombs = new int[height][width];
        int[][] pbrd = new int[height][width];
        this.bombs = bombs;
        this.brd = tbrd;
        this.prevBrd = pbrd;
        this.setTitle("Minesweeper");
        this.setSize(width*32+32,height*32+32);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        BoardVisual visual = new BoardVisual(width, height);
        this.setContentPane(visual);
        this.addMouseListener(new mouse());
        this.addMouseMotionListener(new mouse());
        this.setVisible(true);
    }
    
    //https://www.youtube.com/watch?v=EMu1cC2Vnis
    public class BoardVisual extends JPanel {
    	int width;
    	int height;
    	BoardVisual(int width, int height){
    		this.width = width;
    		this.height = height;
    	}
    	
    	public void paintComponent(Graphics g) {
    		System.out.println(gState);
    		if (gState == ENDGAME) {
	    		for (int i = 0; i < brd.length; i++) {
    				for (int j = 0; j < brd[0].length; j++) {
    					switch(brd[i][j]) {
    					case -3:
    	    				g.drawImage(clickedBomb, j*32, i*32, null);
    	    				break;
    	    			case -2:
    	    				g.drawImage(clicked, j*32, i*32, null);
    	    				break;
    	    			case -1:
    	    				g.drawImage(bomb, j*32, i*32, null);
    	    				break;
    	    			case 1:
    	    				g.drawImage(one, j*32, i*32, null);
    	    				break;
    	    			case 2:
    	    				g.drawImage(two, j*32, i*32, null);
    	    				break;
    	    			case 3:
    	    				g.drawImage(three, j*32, i*32, null);
    	    				break;
    	    			case 4:
    	    				g.drawImage(four, j*32, i*32, null);
    	    				break;
    	    			case 5:
    	    				g.drawImage(five, j*32, i*32, null);
    	    				break;
    	    			case 6:
    	    				g.drawImage(six, j*32, i*32, null);
    	    				break;
    	    			case 7:
    	    				g.drawImage(seven, j*32, i*32, null);
    	    				break;
    	    			case 8:
    	    				g.drawImage(eight, j*32, i*32, null);
    	    				break;
	    				}
	    			}
	    		}
    		} else if(gState == INGAME) {
    			System.out.println(changes.toString());
    			for (int i = 0; i < brd.length; i++) {
    				for (int j = 0; j < brd[0].length; j++) {
    					switch(brd[i][j]) {
			    			case -4:
			    				g.drawImage(flag, j*32, i*32, null);
			    				break;
			    			case -2:
			    				g.drawImage(unclicked, j*32, i*32, null);
			    				break;
			    			case -1:
			    				g.drawImage(unclicked, j*32, i*32, null);
			    				break;
			    			case 0:
			    				g.drawImage(clicked, j*32, i*32, null);
			    				break;
			    			case 1:
			    				g.drawImage(one, j*32, i*32, null);
			    				break;
			    			case 2:
			    				g.drawImage(two, j*32, i*32, null);
			    				break;
			    			case 3:
			    				g.drawImage(three, j*32, i*32, null);
			    				break;
			    			case 4:
			    				g.drawImage(four, j*32, i*32, null);
			    				break;
			    			case 5:
			    				g.drawImage(five, j*32, i*32, null);
			    				break;
			    			case 6:
			    				g.drawImage(six, j*32, i*32, null);
			    				break;
			    			case 7:
			    				g.drawImage(seven, j*32, i*32, null);
			    				break;
			    			case 8:
			    				g.drawImage(eight, j*32, i*32, null);
			    				break;
			    			}
    					}
    				}
    			}
    		 else {
    			g.setColor(Color.black);
        		g.fillRect(0, 0, width*32, height*32);
    			for (int i = 0; i < width; i++) {
    				for (int j = 0; j < height; j++) {
	    				g.drawImage(unclicked, i*32, j*32, null);
    				}
    			}
    		}
    	}
    }
    
    public class mouse implements MouseInputListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("clicked");
			clicked((int)Math.floor(e.getY()/32.0),(int)Math.floor(e.getX()/32.0));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
    
    private void placeMines(int startX, int startY){
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
        
        for (int i = 0; i < brd.length; i++) {
			for (int j = 0; j < brd[0].length; j++) {
				brd[i][j] = -2;
			}
		}
        
        for (int i = 0; i < num; i++){
            int y = (int)(Math.random()*brd.length);
            int x = (int)(Math.random()*brd[0].length);
            int disX = Math.abs(startX-x);
            int disY = Math.abs(startY-y);
            if (bombs[y][x] != -1 && !(disX < 1 && disY < 1)) {
                bombs[y][x] = -1;
            	brd[y][x] = -1;
            }
            else { 
                i--;
            }
        }
        copyToPrev(brd);
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
		System.out.println("click");
		if (gState == PREGAME) {
			midgame();
			placeMines(x, y);
		}
		copyToPrev(brd);
		if (brd[y][x] == -1) {
			this.endgame();
			brd[y][x] = -3;
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
		trackChanges();
		paintComponents(getGraphics());
		printArray(brd);
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
	
	void trackChanges() {
		for (int i = 0; i < brd.length; i++) {
			for (int j = 0; j < brd[0].length; j++) {
				if(brd[i][j] != prevBrd[i][j]) {
					changes.add(i);
					changes.add(j);
					System.out.println("add" + i + j);
				}
			}
		}
	}
	
	void importImages() {
		try {
			unclicked = ImageIO.read(new File("Blank.png"));
			one = ImageIO.read(new File("One.png"));
		    two = ImageIO.read(new File("Two.png"));
		    three = ImageIO.read(new File("Three.png"));
		    four = ImageIO.read(new File("Four.png"));
		    five = ImageIO.read(new File("Five.png"));
		    six = ImageIO.read(new File("Six.png"));
		    seven = ImageIO.read(new File("Seven.png"));
		    eight = ImageIO.read(new File("Eight.png"));
		    bomb = ImageIO.read(new File("Bomb.png"));
		    clickedBomb = ImageIO.read(new File("ClickedBomb.png"));
		    flag = ImageIO.read(new File("Flagged.png"));
		    clicked = ImageIO.read(new File("Clicked.png"));
		    wrongFlag = ImageIO.read(new File("wrongFlag.png"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void printArray(int[][] a) {
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a[0].length-1; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}
