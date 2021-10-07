import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TicTacToe extends JFrame {
	static boolean playerOneTurn;
	static JPanel[][] gridCells;
	
	static JLabel[][] gridLabels;
	//  stores an array containing all the previous moves/user inputs to ensure that the user doesn't try to enter a move that was already 
	// done/performed
	static ArrayList<String> previousMoves = new ArrayList<String>();
	public TicTacToe(String title) throws IOException {
		setTitle(title);
		setLayout(new GridLayout(3, 3, 10, 10));
		
		// player 1's icon is an X
		
		// player 2's icon is an O
		
		// this program assumes the user enters valid input each time when picking a move
		
		// game is just starting off, so it would be player one's turn (this boolean will switch from back and forth)
		playerOneTurn = true;
		
		// stores a 2d array of jpanels to represent the grid cells in the 3 by 3 tictactoe game grid
		gridCells = new JPanel[3][3];
		
		gridLabels = new JLabel[3][3];
		
		// create the labels
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gridLabels[i][j] = new JLabel("(" + (i+1) + ", " + (j+1) + ")");
			}
		}
		
		// add all the labels to each of the JPanels in the 3 by 3 grid
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						gridCells[i][j] = new JPanel();
						
						gridCells[i][j].add(gridLabels[i][j]);
						
						add(gridCells[i][j]);
					}
				}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		}
	
	public static void main(String[] args) throws IOException {
		
		welcomeMessage();
		TicTacToe gameWindow = new TicTacToe("TicTacToe Game Window");
		// 9 squares/grid spaces, so 9 moves total in the game
		for (int i = 0; i < 9; i++) {
			gameWindow.pickASpace();
			// after a space has been picked, check if either player one or two has won the game
			// if not, then it has been a tie, if on the last move
			if (gameWindow.checkForWinner("X")) {
				System.out.println("Player 1 has won the game! Thanks for playing!");
				break;
			}
			
			else if (gameWindow.checkForWinner("O")) {
				System.out.println("Player 2 has won the game! Thanks for playing!");
				break;
			}
			else {
				// represents the last move. If no one has won the game by the time we are on the last move,
				// when i is equal to 8, then it is a tie.
				if (i == 8)
				System.out.println("No one won the game. It was a tie! Thanks for playing!");
			}
		}
	}
	
	private static void welcomeMessage() {
		System.out.println("Welcome to the game of Tic Tac Toe! Player 1 has the x symbol, and player 2 has the circle symbol.");
	}
	
	public boolean checkForWinner(String symbol) {
		// if any of the rows, columns, or diagonals have three matching symbols, return true representing that there is a winner
		return (checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol));
	}
	
	
	// three things to check for a winner - the rows, the columns, and the diagonals
	
	// all these methods will have to be called twice, once for the x symbol and once for the circle symbol
	
	// symbol shall either be circle or x when it is called 
	
	private boolean checkRows(String symbol) {
		
		// when checking the rows for the same symbol (to indicate a winner), the row stays the same and the column changes
		// in each if statement/condition
		
		// here the row is 0 (first row)
		if (gridLabels[0][0].getText().equals(symbol) && gridLabels[0][1].getText().equals(symbol) && gridLabels[0][2].getText().equals(symbol)) {
			return true;
		}
		
		// here the row is 1 (second row)
		if (gridLabels[1][0].getText().equals(symbol) && gridLabels[1][1].getText().equals(symbol) && gridLabels[1][2].getText().equals(symbol)) {
			return true;
		}
		
		// here the row is 2 (third row)
		if (gridLabels[2][0].getText().equals(symbol) && gridLabels[2][1].getText().equals(symbol) && gridLabels[2][2].getText().equals(symbol)) {
			return true;
		}
		
		// if neither of the three rows has three matching symbols, return false for that particular symbol/player
		return false;
	}
	
	private boolean checkColumns(String symbol) {
		
		// when checking the columns for the same symbol (to indicate a winner), the column stays the same and the row changes
		// in each if statement/condition
		
		// here the column is 0 (first column)
		if (gridLabels[0][0].getText().equals(symbol) && gridLabels[1][0].getText().equals(symbol) && gridLabels[2][0].getText().equals(symbol)) {
			return true;
		}
		
		// here the column is 1 (second column)
		if (gridLabels[0][1].getText().equals(symbol) && gridLabels[1][1].getText().equals(symbol) && gridLabels[2][1].getText().equals(symbol)) {
			return true;
		}
		
		// here the column is 2 (third column)
		if (gridLabels[0][2].getText().equals(symbol) && gridLabels[1][2].getText().equals(symbol) && gridLabels[2][2].getText().equals(symbol)) {
			return true;
		}
		
		// if neither of the three columns has three matching symbols, return false for that particular symbol/player
		return false;
	}
	
	private boolean checkDiagonals(String symbol) {
		// check the downward left diagonal starting from the leftmost and uppermost grid
		if (gridLabels[0][0].getText().equals(symbol) && gridLabels[1][1].getText().equals(symbol) && gridLabels[2][2].getText().equals(symbol)) {
			return true;
		}
		
		// check the downward right diagonal starting from the rightmost and uppermost grid
		if (gridLabels[0][2].getText().equals(symbol) && gridLabels[1][1].getText().equals(symbol) && gridLabels[2][0].getText().equals(symbol)) {
			return true;
		}
		
		// if neither of those diagonals checked out, then return false for that particular symbol/player to indicate that the
		// diagonals did not have three matching symbols
		return false;
	}
	
	private void pickASpace() {
		int row, column; 
		Scanner scanner = new Scanner(System.in);
		String input;
		// player 1 turn
		if (playerOneTurn) {
			System.out.print("Player 1 choose your space. (Enter row and column separated by a space): ");
			input = scanner.nextLine();
			previousMoves.add(input);
			row = Integer.parseInt(input.substring(0, 1));
			column = Integer.parseInt(input.charAt(2) + "");
			
			// add the player icon to the appropriate row and column based on the input
			gridLabels[row-1][column-1] = new JLabel("X");
			// rather than add an image, add a symbol text
			gridCells[row-1][column-1].removeAll();
			gridCells[row-1][column-1].add(gridLabels[row-1][column-1]);
			gridCells[row-1][column-1].revalidate();
			gridCells[row-1][column-1].repaint();
			
			// player 1's turn just finished, so set playerOneTurn to false to indicate that it is now player 2's turn next
			playerOneTurn = false;
		}
		// player 2 turn
		else {
			System.out.print("Player 2 choose your space. (Enter row and column separated by a space): ");
			input = scanner.nextLine();
			previousMoves.add(input);
			row = Integer.parseInt(input.substring(0, 1));
			column = Integer.parseInt(input.charAt(2) + "");
			
			// add the player icon to the appropriate row and column based on the input
			gridLabels[row-1][column-1] = new JLabel("O");
			// rather than add an image, add a symbol text
			gridCells[row-1][column-1].removeAll();
			gridCells[row-1][column-1].add(gridLabels[row-1][column-1]);
			gridCells[row-1][column-1].revalidate();
			gridCells[row-1][column-1].repaint();
			
			// once player 2's turn finishes, then it becomes player one's turn again
			
			playerOneTurn = true;
			
			//checkForWinner("Y");
		}
	}
}
