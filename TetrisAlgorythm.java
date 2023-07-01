import java.util.Random;

public class TetrisAlgorythm {
	// ミノの定数
	public static final int MINO_TYPE_LEN 	= 7; // ミノのタイプ数
	public static final int MINO_ANGLE_LEN 	= 4; // ミノの角度の種類数
	public static final int MINO_WIDTH 		= 4; // ミノの幅
	public static final int MINO_HEIGHT		= 4; // ミノの高さ
	public static final int[][][][] minoShapes = {	// 各ミノについての形についての定数
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
	// 現在のミノの状態を保持するインスタンス変数
	public int minoType 	= 0; // 現在のミノタイプ
	public int minoAngle 	= 0; // 現在のミノ角度
	public int minoX 		= 5; // 現在のミノのx座標
	public int minoY 		= 0; // 現在のミノのy座標
	
	// フィールドについての定数・変数
	public static final int FIELD_WIDTH 	= 12; // フィールドの幅
	public static final int FIELD_HEIGHT 	= 22; // フィールドの高さ
	private int [][] field 			= new int[FIELD_HEIGHT][FIELD_WIDTH]; //現在のフィールド. 0→何もなし, 1→ミノor壁が存在。

	// その他の変数
	private Random rand 			= new Random(); // ミノのタイプ、角度を決めるための乱数
	public int [][] displayBuffer 	= new int[FIELD_HEIGHT][FIELD_WIDTH];	// フィールドとミノを同時に描画するための変数。
	
    public TetrisAlgorythm() {
    	resetField(); // フィールドをリセット
    	resetMino();  // ミノの初期位置、ミノタイプを設定。
    	resetDisplayBuffer(); 
    }

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

    private void resetMino() {// ミノのX,Y座標を初期化し、ミノタイプとミノ角度を乱数で決定。
    	minoX = 5;
    	minoY = 0 ;
    	minoType = Math.abs(rand.nextInt()) % MINO_TYPE_LEN;
    	minoAngle = Math.abs(rand.nextInt()) % MINO_ANGLE_LEN;
    }
  
    public void update() {
    	if (isHit(minoX, minoY + 1, minoType, minoAngle)) {
    		assimilateMino();
    		removeLine();
    		resetMino(); // ミノの位置座標とタイプ角度を決めるメソッド呼び出し
    	} else {
    		minoY++; // ミノのY座標を+1
    	}
    	display(); // 描画メソッド呼び出し。
    }
    
    private void removeLine() {
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

	private void assimilateMino() {
   		for (int i = 0; i < MINO_HEIGHT; i++) {
			for (int j = 0; j < MINO_WIDTH; j++) {
				field[minoY + i][minoX + j] |= minoShapes[minoType][minoAngle][i][j];
			}
		}
    }
    
    private void display() {    // 描画するメソッド
    	resetDisplayBuffer(); // フィールドをバッファにコピー
    	for (int i = 0; i < MINO_HEIGHT; i++) {
    		for (int j = 0; j < MINO_WIDTH; j++) {
    			if (!(minoY + i < FIELD_HEIGHT || minoX + j < FIELD_WIDTH)) continue;
    			displayBuffer[minoY + i][minoX + j] |= minoShapes[minoType][minoAngle][i][j];
    		}
    	}
    }
    
    // ミノがフィールドと接触するかどうかを確認するメソッド
    public boolean isHit(int minoX, int minoY, int minoType, int minoAngle) {
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
}
