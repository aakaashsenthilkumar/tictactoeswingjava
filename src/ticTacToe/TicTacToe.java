package ticTacToe;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.Random;

@SuppressWarnings("serial")
public class TicTacToe extends JFrame{
	
	//Creating buttons in the main class so that it's easy to access
	static Button TopLeftButton,TopCenterButton, TopRightButton;
	static Button LeftButton, CenterButton, RightButton;
	static Button BottomLeftButton,BottomCenterButton,BottomRightButton;
	static Button[] buttonArray = new Button[9];
	int move = 1; //to track the number of moves
	boolean marked = false; //to check if it has been marked already
	static JLabel StatusLabel; //to say if it is a draw/who won
	static String Result = "Status"; //to keep track of the result
	static boolean GridFull = true;
	static boolean gameOver =  false;

	
	public static void checkGridFull() { //checks if all the spots are marked
		if(checkMarked(buttonArray[0])) {
			GridFull = true;
		}
		for(int i = 0; i<9; i++) {
			if(checkMarked(buttonArray[i]) && GridFull == true) {
				GridFull = true;
			}
			else {
				GridFull = false;
			}
		}
	}
	
	public static void setResult(String result) {
		Result = result;
	}
	
	public static String getResult() {
		return Result;
	}
	
	public static void setStatus(String status) {
		StatusLabel.setText(status);
	}
	
	public void setMarked(Button button) {
		button.marked = true;
	}

	public void notMarked(Button button) {
		button.marked = false;
	}
	
	public static boolean checkMarked(Button button) {
		return button.marked;
	}
	
	public void increaseMove() {
		move++;
	}
	
	public int getMove() {
		return move;
	}
	
	//Buttons creating
	public static Button createButtons(Button newButton,int xPos,int yPos) {

		newButton.setBounds(xPos,yPos,75,75);
		newButton.setToolTipText("Place X");
		newButton.setBackground(Color.WHITE);
		newButton.setFont(new Font("Serif",Font.BOLD,40));
		
		return newButton;
	}
	
	//Adding elements to the frame
	public TicTacToe(){

		setTitle("Tic Tac Toe");
		setSize(500,600);
		setLayout(null);
		this.getContentPane().setBackground(Color.WHITE);
		
		JLabel headingLabel = new JLabel("Tic Tac Toe");
		headingLabel.setLocation(166,20);
		headingLabel.setSize(166,30);
		headingLabel.setFont(new Font("Serif", Font.BOLD, 30));
		add(headingLabel);
		
		StatusLabel = new JLabel("No Result");
		StatusLabel.setLocation(166,50);
		//StatusLabel.setText("None");
		StatusLabel.setSize(166,20);
		StatusLabel.setFont(new Font("Serif", Font.ITALIC, 20));
		
		add(StatusLabel);
		
		ImageIcon grid = new ImageIcon("grid.jpg");
		JLabel picture = new JLabel(grid);
		picture.setBounds(0,50,grid.getIconWidth(),grid.getIconHeight());
		add(picture);
		
		//creating buttons using the function and adding them to an array
		int xPos = 70,yPos = 120;
		buttonArray = new Button[9];
		for(int i = 0;i<9;i++) {
			buttonArray[i] = createButtons(new Button(""),xPos,yPos);
			//calculating the position of the button
			xPos+=150;
			if(i == 2 || i ==5) {
				xPos = 70;
				yPos += 140;
			}
			add(buttonArray[i]);
		}
		
		//assigning variable names to the buttons
		TopLeftButton = buttonArray[0];
		TopCenterButton = buttonArray[1];
		TopRightButton = buttonArray[2];
		LeftButton = buttonArray[3];
		CenterButton = buttonArray[4];
		RightButton = buttonArray[5];
		BottomLeftButton = buttonArray[6];
		BottomCenterButton = buttonArray[7];
		BottomRightButton = buttonArray[8];
		
		//setting that the buttons haven't been marked at first
		for(int i = 0;i<9;i++) {
			notMarked(buttonArray[i]);
		}
		
		//saying the status(won/lose/draw)
		setStatus(getResult());
		
		setVisible(true);
	}
	
	//function for the opponent to move(COMPUTER)
	public void opponentMove(int move, Button button) {
	
		Random generator = new Random();
		int buttonChoice;
		
		//making sure there is space on the grid
		checkGridFull();
		if(!GridFull) {
			//the first move will be random
			if(getMove() == 1) {
				do{
					buttonChoice = generator.nextInt(9);
				}while(checkMarked(buttonArray[buttonChoice]));
				
				buttonArray[buttonChoice].setText("O");
				setMarked(buttonArray[buttonChoice]);
				
			}
			else{
				//Checking situations to block the player
				if(TopLeftButton.getText() == "X" && TopRightButton.getText() == "X" && !checkMarked(TopCenterButton)) {
					TopCenterButton.setText("O");
					setMarked(TopCenterButton);
				}
				else if(TopLeftButton.getText() == "X" && TopCenterButton.getText() == "X" && !checkMarked(TopRightButton)) {
					TopRightButton.setText("O");
					setMarked(TopRightButton);
				}
				else if(TopLeftButton.getText() == "X" && BottomRightButton.getText() == "X" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(TopLeftButton.getText() == "X" && CenterButton.getText() == "X" && !checkMarked(BottomRightButton)) {
					BottomRightButton.setText("O");
					setMarked(BottomRightButton);
				}
				else if(TopLeftButton.getText() == "X" && BottomRightButton.getText() == "X" && !checkMarked(LeftButton)) {
					LeftButton.setText("O");
					setMarked(LeftButton);
				}
				else if(TopLeftButton.getText() == "X" && LeftButton.getText() == "X" && !checkMarked(BottomLeftButton)) {
					BottomLeftButton.setText("O");
					setMarked(BottomLeftButton);
				}
				else if(TopLeftButton.getText() == "X" && BottomLeftButton.getText() == "X" && !checkMarked(LeftButton)) {
					LeftButton.setText("O");
					setMarked(LeftButton);
				}
				else if(TopCenterButton.getText() == "X" && BottomCenterButton.getText() == "X" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(TopCenterButton.getText() == "X" && CenterButton.getText() == "X" && !checkMarked(BottomCenterButton)) {
					BottomCenterButton.setText("O");
					setMarked(BottomCenterButton);
				}
				else if(TopRightButton.getText() == "X" && RightButton.getText() == "X" && !checkMarked(BottomRightButton)) {
					BottomRightButton.setText("O");
					setMarked(BottomRightButton);
				}
				else if(TopRightButton.getText() == "X" && BottomRightButton.getText() == "X" && !checkMarked(RightButton)) {
					RightButton.setText("O");
					setMarked(RightButton);
				}
				else if(BottomLeftButton.getText() == "X" && BottomRightButton.getText() == "X" && !checkMarked(BottomCenterButton)) {
					BottomCenterButton.setText("O");
					setMarked(BottomCenterButton);
				}
				else if(BottomRightButton.getText() == "X" && RightButton.getText() == "X" && !checkMarked(TopRightButton)) {
					TopRightButton.setText("O");
					setMarked(TopRightButton);
				}
				else if(TopRightButton.getText() == "X" && BottomLeftButton.getText() == "X" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(RightButton.getText() == "X" && LeftButton.getText() == "X" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				
				// situations to try to get 3 O's in a row
				else if(TopLeftButton.getText() == "O" && TopRightButton.getText() == "O" && !checkMarked(TopCenterButton)) {
					TopCenterButton.setText("O");
					setMarked(TopCenterButton);
				}
				else if(TopLeftButton.getText() == "O" && TopCenterButton.getText() == "O" && !checkMarked(TopRightButton)) {
					TopRightButton.setText("O");
					setMarked(TopRightButton);
				}
				else if(TopLeftButton.getText() == "O" && BottomRightButton.getText() == "O" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(TopLeftButton.getText() == "O" && CenterButton.getText() == "O" && !checkMarked(BottomRightButton)) {
					BottomRightButton.setText("O");
					setMarked(BottomRightButton);
				}
				else if(TopLeftButton.getText() == "O" && BottomRightButton.getText() == "O" && !checkMarked(LeftButton)) {
					LeftButton.setText("O");
					setMarked(LeftButton);
				}
				else if(TopLeftButton.getText() == "O" && LeftButton.getText() == "O" && !checkMarked(BottomLeftButton)) {
					BottomLeftButton.setText("O");
					setMarked(BottomLeftButton);
				}
				else if(TopLeftButton.getText() == "O" && BottomLeftButton.getText() == "O" && !checkMarked(LeftButton)) {
					LeftButton.setText("O");
					setMarked(LeftButton);
				}
				else if(TopCenterButton.getText() == "O" && BottomCenterButton.getText() == "O" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(TopCenterButton.getText() == "O" && CenterButton.getText() == "O" && !checkMarked(BottomCenterButton)) {
					BottomCenterButton.setText("O");
					setMarked(BottomCenterButton);
				}
				else if(TopRightButton.getText() == "O" && RightButton.getText() == "O" && !checkMarked(BottomRightButton)) {
					BottomRightButton.setText("O");
					setMarked(BottomRightButton);
				}
				else if(TopRightButton.getText() == "O" && BottomRightButton.getText() == "O" && !checkMarked(RightButton)) {
					RightButton.setText("O");
					setMarked(RightButton);
				}
				else if(BottomLeftButton.getText() == "O" && BottomRightButton.getText() == "O" && !checkMarked(BottomCenterButton)) {
					BottomCenterButton.setText("O");
					setMarked(BottomCenterButton);
				}
				else if(BottomRightButton.getText() == "O" && RightButton.getText() == "O" && !checkMarked(TopRightButton)) {
					TopRightButton.setText("O");
					setMarked(TopRightButton);
				}
				else if(TopRightButton.getText() == "O" && BottomLeftButton.getText() == "O" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				else if(RightButton.getText() == "O" && LeftButton.getText() == "O" && !checkMarked(CenterButton)) {
					CenterButton.setText("O");
					setMarked(CenterButton);
				}
				//When there are no patterns, mark a random spot
				else {
					do{
						buttonChoice = generator.nextInt(9);
					}while(checkMarked(buttonArray[buttonChoice]));
					
					buttonArray[buttonChoice].setText("O");
					setMarked(buttonArray[buttonChoice]);
				}
			}
		}
	}
	
	//adding action listener to the buttons
	private class Button extends JButton implements ActionListener{
			
		boolean marked = false;
		
		private Button(String text) {
			super(text);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) { //when a button is clicked
			// TODO Auto-generated method stub
			
			if(!checkMarked(this) && !gameOver) { //if it has not been marked before already
				
				this.setText("X");
				setMarked(this);
				increaseMove(); //updating the number of moves
				checkResult(0); //updating if anyone has won yet
				setStatus(getResult());
				//check if there is space on the grid and the game is not over yet
				checkGridFull();
				if(!GridFull && !gameOver) {
					 opponentMove(getMove(),this);
				}
				//updating the result again after the computer plays
				checkResult(0);
				setStatus(getResult());
				
			}
		}
	}
	
	String result = "";
	
	//to check if anyone as won yet
	public static void checkResult(int currentBox) {
		String result=getResult();
		if(currentBox == 6) { //at this point, we would have checked everything already
			return;
		}
		
		//HORIZONTAL CHECK
		if(currentBox == 0 || currentBox == 3 || currentBox == 6) {
			if(buttonArray[currentBox].getText() == "X" && buttonArray[currentBox+1].getText() == "X" && buttonArray[currentBox+2].getText() == "X") {
				result = "Player Wins";
			}
			else if(buttonArray[currentBox].getText() == "O" && buttonArray[currentBox+1].getText() == "O" && buttonArray[currentBox+2].getText() == "O") {
				result = "Computer Wins";
			}
			else {
				result = "draw";
			}
		}
		
		//VERTICAL CHECK
		if(currentBox == 0 || currentBox == 1 || currentBox == 2) {
			if(buttonArray[currentBox].getText() == "X" && buttonArray[currentBox+3].getText() == "X" && buttonArray[currentBox+6].getText() == "X") {
				result = "Player Wins";
			}
			else if(buttonArray[currentBox].getText() == "O" && buttonArray[currentBox+3].getText() == "O" && buttonArray[currentBox+6].getText() == "O") {
				result = "Computer Wins";
			}
			else {
				result = "draw";
			}
		
		}
		
		//DIAGONAL CHECK
		if((TopRightButton.getText() == "X" && CenterButton.getText() == "X" && BottomLeftButton.getText() == "X") || (TopLeftButton.getText() == "X" && CenterButton.getText() == "X" && BottomRightButton.getText() == "X")) {
			result = "Player Wins";
		}
		else if((TopRightButton.getText() == "O" && CenterButton.getText() == "O" && BottomLeftButton.getText() == "O") || (TopLeftButton.getText() == "O" && CenterButton.getText() == "O" && BottomRightButton.getText() == "O")) {
			result = "Computer Wins";
		}
		
		if(result == "Computer Wins" || result == "Player Wins") {
			gameOver = true;
		}
		
		//updating the result about if anyone won
		setResult(result);
		
		if(!gameOver) {
			//recursion to check all the spots on the grid
			currentBox++;
			checkResult(currentBox);
		}
	}
	
	public static void main(String[] args) {
		
		//calling the game
		new TicTacToe();
			
	}

}
