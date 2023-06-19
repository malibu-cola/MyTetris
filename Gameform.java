package my_tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Gameform extends JFrame {
	public Gameform() {
		Board board = new Board(this);
		board.setFocusable(true);
		add(board);
		board.start();
		
		setTitle("Tetris");
		setSize(400, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Gameform form= new Gameform();
			form.setVisible(true);
		});
	}

}

