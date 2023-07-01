
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {
	private static final int SPEED 	= 300;
	private Timer timer;
	private TetrisAlgorythm tetris = null;

	// **********************************************************************************************
	// 初期設定
	// **********************************************************************************************

	public Board(Gameform parent) {	// コンストラクタ
        addKeyListener(new TAdapter()); // キーボード操作を受け付ける。キーボード操作はTAdapterクラス(下の方)で定義。
    }
    public void start() {	// Board をスタートさせる。
    	tetris = new TetrisAlgorythm();
    	timer = new Timer(SPEED, new GameCycle()); // SPEEDごとにGameCycleを実行。
    	timer.start();
    }
    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	tetris.update();	// テトリスのupdateメソッドを呼び出し。
        	repaint(); 			// paintComponentメソッドの呼び出し
        }
    } 
 
	// **********************************************************************************************
    // キーボード処理
    // **********************************************************************************************
    private class TAdapter extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent e) {
    		switch(e.getKeyCode()) {
    		// "A"が入力されたら,X座標を-1(左に移動)したい。
    		// ヒットしているかどうか確かめて、ヒットしなければXを-1
    		case KeyEvent.VK_A:
    			if (!tetris.isHit(tetris.minoX - 1, tetris.minoY, tetris.minoType, tetris.minoAngle))
    				tetris.minoX--;
    			break;
    		// "D"が入力されたら右に移動したい。(同上)
    		case KeyEvent.VK_D:
    			if (!tetris.isHit(tetris.minoX + 1, tetris.minoY, tetris.minoType, tetris.minoAngle))
    				tetris.minoX++;
    			break;
    		// "S"が入力されたら下に移動したい(同上)
    		case KeyEvent.VK_S:
    			if (!tetris.isHit(tetris.minoX, tetris.minoY + 1, tetris.minoType, tetris.minoAngle))
    				tetris.minoY++;
    			break;
    		// "W"が入力されたら、回転したい
    		// minoAngle + 1する。
    		case KeyEvent.VK_W:
    			if (!tetris.isHit(tetris.minoX, tetris.minoY, tetris.minoType, (tetris.minoAngle + 1) % TetrisAlgorythm.MINO_ANGLE_LEN))
    				tetris.minoAngle = (tetris.minoAngle + 1) % TetrisAlgorythm.MINO_ANGLE_LEN;
    			break;
    		}
    	}
    }
    
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
    	int boardTop = (int) size.getHeight() - TetrisAlgorythm.FIELD_HEIGHT * squareHeight();
    	
    	for (int i = 0; i < TetrisAlgorythm.FIELD_HEIGHT; i++) {
    		for (int j = 0; j < TetrisAlgorythm.FIELD_WIDTH; j++) {
    			if (tetris.displayBuffer[i][j] == 1) {
    				drawSquare(g, j * squareWidth(), boardTop + i * squareHeight());
    			}
    		}
    	}
    }
    private int squareWidth() {
        return (int) getSize().getWidth() / TetrisAlgorythm.FIELD_WIDTH;
    }
    private int squareHeight() {
        return (int) getSize().getHeight() / TetrisAlgorythm.FIELD_HEIGHT;
    }
    private void drawSquare(Graphics g, int x, int y) {
    	// 座標と幅から四角形を描くメソッド
    	// g.fillRect(x座標, y座標, 横幅, 縦幅)
    	g.fillRect(x + 1, y + 1, squareWidth() - 1, squareHeight() - 1 );
    }
}
