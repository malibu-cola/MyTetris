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
	// ミノについての定数
	private static final int MINO_TYPE_LEN 	= 7; // ミノのタイプ数
	private static final int MINO_ANGLE_LEN = 4; // ミノの角度の種類数
	private static final int MINO_WIDTH 	= 4; // ミノの幅
	private static final int MINO_HEIGHT	= 4; // ミノの高さ
	// 各ミノについての形についての定数
	private static final int[][][][] minoShapes = {
			{// MINO_I
				{//ANGLE_0
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{0, 0, 0, 0},
					{1, 1, 1, 1},
					{0, 0, 0, 0},
				},
				{// ANGLE_180
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0},
				},
				{// ANGLE_270
					{0, 0, 0, 0},
					{1, 1, 1, 1},
					{0, 0, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{//MINO_O
				{//ANGLE_0
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_270
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				}
			},
			{// MINO_S
				{//ANGLE_0
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{1, 1, 0, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0},
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 0, 1, 1},
					{0, 1, 1, 0},
					{0, 0, 0, 0},
				},
				{// ANGLE_270
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0}
				}
			},
			{//MINO_Z
				{//ANGLE_0
					{0, 0, 0, 0},
					{1, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 1},
					{0, 0, 0, 0}
				},
				{// ANGLE_270
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{//MINO_J
				{//ANGLE_0
					{0, 0, 1, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{1, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 1, 0, 0}
				},
				{// ANGLE_270
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 1},
					{0, 0, 0, 0}
				}
			},
			{//MINO_L
				{//ANGLE_0
					{0, 1, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{1, 1, 1, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 1, 0}
				},
				{// ANGLE_270
					{0, 0, 0, 0},
					{0, 1, 1, 1},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				}
			},
			{//MINO_T
				{//ANGLE_0
					{0, 0, 0, 0},
					{1, 1, 1, 0},
					{0, 1, 0, 0},
					{0, 0, 0, 0}
				},
				{// ANGLE_90
					{0, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 1, 1, 0},
					{0, 1, 0, 0}
				},
				{// ANGLE_180
					{0, 0, 0, 0},
					{0, 0, 1, 0},
					{0, 1, 1, 1},
					{0, 0, 0, 0}
				},
				{// ANGLE_270
					{0, 0, 1, 0},
					{0, 1, 1, 0},
					{0, 0, 1, 0},
					{0, 0, 0, 0}
				}
			}
	};

	// フィールドについての定数
	private static final int FIELD_WIDTH 	= 12; // フィールドの幅
	private static final int FIELD_HEIGHT 	= 22; // フィールドの高さ
	
	// その他の変数・定数
	private static final int SPEED 			= 300;// ミノの落ちる速さ（小→速, 大→遅）
	private Random rand 					= new Random(); // ミノのタイプ、角度を決めるための乱数
	private Timer timer; // 知らなくてよし！

	// 現在のミノの状態を保持するインスタンス変数
	private int minoType 	= 0; // 現在のミノタイプ
	private int minoAngle 	= 0; // 現在のミノ角度
	private int minoX 		= 5; // 現在のミノのx座標
	private int minoY 		= 0; // 現在のミノのy座標
	
	// 現在のフィールドを保持するインスタンス変数
	private int [][] field 			= new int[FIELD_HEIGHT][FIELD_WIDTH]; 
	// FIELD_HEIGHT * FIELD_WIDTH を0で初期化.
	// もし0だったら何もなし。
	// 1だったらブロック(ミノ)が存在する。

	// フィールドとミノを同時に描画するためのインスタンス変数。
	private int [][] displayBuffer 	= new int[FIELD_HEIGHT][FIELD_WIDTH];
	// ミノは毎秒動くので、動くミノとフィールドを毎秒更新する。
	// フィールドとミノとのOR演算でつくる。（わからなかったら図解）
	
	// **********************************************************************************************
	// 初期設定
	// **********************************************************************************************
	
	// コンストラクタ(キーボード操作を受け付ける)
	public Board(Gameform parent) {
        addKeyListener(new TAdapter()); // キーボード操作を受け付ける。キーボード操作はTAdapterクラス(下の方)で定義。
    }
	
	// タイマーが動いている間、繰り返してくれるメソッド(むずいと思うので聞いてほしい)
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
        	update();	// テトリスのupdateメソッドを呼び出し。
        	repaint(); 	// paintComponentメソッドの呼び出し(厳密には少し違うらしいが...)
        }
    } 
 
    // ディスプレイバッファーをフィールドと同じように初期化する。
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

    
    // ミノのX,Y座標を初期化し、ミノタイプとミノ角度を乱数で決定。
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
    		resetMino(); // ミノの位置座標とタイプ角度を決めるメソッド呼び出し
    	} else {
    		minoY++; // ミノのY座標を+1
    	}
    	display(); // 描画してくれるメソッド呼び出し。
    }
    
    // 描画してくれるメソッド
    
    private void display() {
    	resetDisplayBuffer(); // フィールドをバッファにコピー
    	// フィールドとミノをOR演算
    	for (int i = 0; i < MINO_HEIGHT; i++) {
    		for (int j = 0; j < MINO_WIDTH; j++) {
    			displayBuffer[minoY + i][minoX + j] |= minoShapes[minoType][minoAngle][i][j];
    		}
    	}
    	// 描画(252行目と同じ)
    	repaint();
    }
    
    // ミノがフィーールドと接触するかどうかを確認するメソッド
    private boolean isHit(int minoX, int minoY, int minoType, int minoAngle) {
    	// ミノがあり、かつフィールドがあればヒットする。
    	for (int i = 0; i < MINO_HEIGHT; i++) {
    		for (int j = 0; j < MINO_WIDTH; j++) {
    			if (minoShapes[minoType][minoAngle][i][j] == 1 && field[minoY + i][minoX + j] == 1) {
    				return true;// ヒットすればtrueを返す。
    			}
    		}
    	}
    	// ヒットしなければfalseを返す
    	return false;
    }    
    
    // キーボード処理を受け付けるクラス・メソッド
    private class TAdapter extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent e) {
    		switch(e.getKeyCode()) {
    		// "A"が入力されたら,X座標を-1(左に移動)したい。
    		// ヒットしているかどうか確かめて、ヒットしなければXを-1
    		case KeyEvent.VK_A:
    			if (!isHit(minoX - 1, minoY, minoType, minoAngle))
    				minoX--;
    			break;
    		// "D"が入力されたら右に移動したい。(同上)
    		case KeyEvent.VK_D:
    			if (!isHit(minoX + 1, minoY, minoType, minoAngle))
    				minoX++;
    			break;
    		// "S"が入力されたら下に移動したい(同上)
    		case KeyEvent.VK_S:
    			if (!isHit(minoX, minoY + 1, minoType, minoAngle))
    				minoY++;
    			break;
    		// "W"が入力されたら、回転したい
    		// minoAngle + 1する。
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
    private void drawSquare(Graphics g, int x, int y) {
    	// 座標と幅から四角形を描くメソッド
    	// g.fillRect(x座標, y座標, 横幅, 縦幅)
    	g.fillRect(x + 1, y + 1, squareWidth() - 1, squareHeight() - 1 );
    }
}
