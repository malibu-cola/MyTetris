package my_tetris5;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {
	private static final int MINO_TYPE_LEN 	= 7;
	private static final int MINO_ANGLE_LEN = 4;
	private static final int FIELD_WIDTH 	  = 12;
	private static final int FIELD_HEIGHT 	= 22;
	private static final int MINO_WIDTH 	  = 4;
	private static final int MINO_HEIGHT	  = 4;
	private static final int SPEED 			    = 300;

	private Random rand = new Random();
	private Timer timer;
	private int minoType 	= 0;
	private int minoAngle = 0;
	private int minoX 		= 5;
	private int minoY 		= 0;
	private int [][] field 			    = new int[FIELD_HEIGHT][FIELD_WIDTH];
	private int [][] displayBuffer 	= new int[FIELD_HEIGHT][FIELD_WIDTH];
	
	private static final int[][][][] minoShapes = {
			{// MINO_I
				{//ANGLE_0
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 0, 0, 0},
					{1, 1, 1, 1},
					{0, 0, 0, 0},
				},
				{
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0},
				},
				{
					{0, 0, 0, 0},
					{1, 1, 1, 1},
					{0, 0, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				}
			},
			{
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{1, 1, 0, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0},
				},
				{
					{0, 0, 0, 0},
					{0, 0, 1, 1},
					{0, 1, 1, 0},
					{0, 0, 0, 0},
				},
				{
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0}
				}
			},
			{
				{
					{0, 0, 0, 0},
					{1, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 1},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{
				{
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{1, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 1},
					{0, 0, 0, 0}
				}
			},
			{
				{
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{1, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 1, 1},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{
				{
					{0, 0, 0, 0},
					{1, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0}
				},
				{
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0}
				},
				{
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0}
				}
			}
	};

	// **********************************************************************************************
	// 初期設定
	// **********************************************************************************************
    public Board(Gameform parent) {
        addKeyListener(new TAdapter()); // キーボード操作を受け付ける。キーボード操作はTAdapterクラスで定義。
    }
    public void start() {
    	resetField(); // フィールドをリセット
    	resetMino();  // ミノの初期位置、ミノタイプを設定。
    	resetDisplayBuffer(); // ディスプレイバッファーをリセット。
    	timer = new Timer(SPEED, new GameCycle()); // SPEEDごとにGameCycleを実行。
    	timer.start();
    }
    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	update();
        	repaint(); // paintComponentメソッドを呼び出して再描画してくれる。
        }
    } 
 
    // **********************************************************************************************
 	// テトリスアルゴリズム
 	// **********************************************************************************************
    private void resetDisplayBuffer() {
    	for (int i = 0; i < FIELD_HEIGHT; i++)
    		for (int j = 0; j < FIELD_WIDTH; j++)
    			displayBuffer[i][j] = field[i][j];
    }
    private void resetField() {
    	for (int i = 0; i < FIELD_HEIGHT; i++) {
    		for (int j = 0; j < FIELD_WIDTH; j++) {
    			field[i][j] = 0;
    		}
    	}
    	
    	for (int i = 0; i < FIELD_HEIGHT; i++) 
    		field[i][0] = field[i][FIELD_WIDTH - 1] = 1;
    	
    	for (int i = 0; i < FIELD_WIDTH; i++)
    		field[FIELD_HEIGHT - 1][i] = 1;
    	
    }

    private void resetMino() {
    	minoX = 5;
    	minoY = 0 ;
    	minoType = Math.abs(rand.nextInt()) % MINO_TYPE_LEN;
    	minoAngle = Math.abs(rand.nextInt()) % MINO_ANGLE_LEN;
    }
   
    
    private void update() {
    	if (isHit(minoX, minoY + 1, minoType, minoAngle)) {
    		// ヒットしたら
    		// フィールドとミノを同化させる処理
    		for (int i = 0; i < MINO_HEIGHT; i++) {
    			for (int j = 0; j < MINO_WIDTH; j++) {
    				field[minoY + i][minoX + j] |= minoShapes[minoType][minoAngle][i][j];
    			}
    		}
    		// ラインが揃ったら消す処理。
    		{
    			for (int i = 0; i < FIELD_HEIGHT - 1; i++) {
    				boolean lineFill = true;
    				for (int j = 1; j < FIELD_WIDTH - 1; j++) 
    					if (field[i][j] == 0) lineFill = false;
    				
    				if (lineFill) {
    					for (int k = i; k > 0; k--)
    						for (int j = 1; j < FIELD_WIDTH - 1; j++) 
    							field[k][j] = field[k - 1][j];
    				}
    			}
    		}
    		resetMino();
    	} else {
    		minoY++;
    	}
    	display();
    }
    
    private void display() {
    	resetDisplayBuffer();
    	for (int i = 0; i < MINO_HEIGHT; i++) {
    		for (int j = 0; j < MINO_WIDTH; j++) {
    			displayBuffer[minoY + i][minoX + j] |= minoShapes[minoType][minoAngle][i][j];
    		}
    	}
    	repaint();
    }
    private boolean isHit(int minoX, int minoY, int minoType, int minoAngle) {
    	for (int i = 0; i < MINO_HEIGHT; i++) {
    		for (int j = 0; j < MINO_WIDTH; j++) {
    			if (minoShapes[minoType][minoAngle][i][j] == 1 && field[minoY + i][minoX + j] == 1) {
    				return true;
    			}
    		}
    	}
    	return false;
    }    
    private class TAdapter extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent e) {
    		switch(e.getKeyCode()) {
    		case KeyEvent.VK_A:
    			if (!isHit(minoX - 1, minoY, minoType, minoAngle))
    				minoX--;
    			break;
    		case KeyEvent.VK_D:
    			if (!isHit(minoX + 1, minoY, minoType, minoAngle))
    				minoX++;
    			break;
    		case KeyEvent.VK_S:
    			if (!isHit(minoX, minoY + 1, minoType, minoAngle))
    				minoY++;
    			break;
    		case KeyEvent.VK_W:
    			if (!isHit(minoX, minoY, minoType, (minoAngle + 1) % MINO_ANGLE_LEN))
    				minoAngle = (minoAngle + 1) % MINO_ANGLE_LEN;
    			break;
    		}
    	}
    }
    

    // **********************************************************************************************
    // **********************************************************************************************
    // 描画メソッド
    // **********************************************************************************************
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	doDrawing(g);
    }   
    private void doDrawing(Graphics g) {
    	var size = getSize();
    	int boardTop = (int) size.getHeight() - FIELD_HEIGHT * squareHeight();
    	
    	for (int i = 0; i < FIELD_HEIGHT; i++) {
    		for (int j = 0; j < FIELD_WIDTH; j++) {
    			if (displayBuffer[i][j] == 1) {
    				drawSquare(g, j * squareWidth(), boardTop + i * squareHeight());
    			}
    		}
    	}

    }
    private int squareWidth() {
        return (int) getSize().getWidth() / FIELD_WIDTH;
    }
    private int squareHeight() {
        return (int) getSize().getHeight() / FIELD_HEIGHT;
    }
}
