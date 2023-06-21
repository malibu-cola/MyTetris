package my_tetris5;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Gameform extends JFrame {
	public Gameform() { // この辺の挙動はあんまり知らなくてよし。
		Board board = new Board(this); // Boardインスタンスを作成
		board.setFocusable(true);		// boardを可視化
		add(board);						//	JFrame(GUIのフレーム)にboardを追加
		board.start();					// boardのスタートメソッドを実行
		
		setTitle("Tetris");				// JFrameのタイトルをTetrisに設定	
		setSize(400, 800);				// JFrameのサイズを400*800に設定
		setDefaultCloseOperation(EXIT_ON_CLOSE); // バツ押した時の処理
		setLocationRelativeTo(null);	// 実行開始した時のJFrameの１
	}

	public static void main(String[] args) {
		// 　Gameformクラスを開始。（詳細は知らなくてOK）
		EventQueue.invokeLater(() -> {
			Gameform form= new Gameform();
			form.setVisible(true);
		});
	}

}

