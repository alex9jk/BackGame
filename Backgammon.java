import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Backgammon extends JFrame implements ActionListener{
/*global attributes*/
   private JButton jbRoll;
   private JTextField jtfValue;
   private JTextField jtfTurn;
   private JButton[][] board = new JButton[5][24];

   private int xPos = 0;
   private int yPos = 0;
   private int playerCount = 1;
   private int redCount = 0;
   private int blackCount = 0;   
    
   
   private Icon redPiece = new ImageIcon("red.png");
   private Icon blackPiece = new ImageIcon("black.png");
   private Icon blank = new ImageIcon("blank.png");
   private int diceRoll =0;
   private String choice;
   private JButton btn;
   private JPanel jpBoard = new JPanel(new GridLayout(5,24));
   private int player = 1;

   public static void main(String[]args){
      new Backgammon();
   } // end main
   
   /*main constructor
   2 nested for loops to set up board
   call Piece class which creates a jbutton
   set action command and 
   */
   public Backgammon(){
   
      for ( int i = 0; i<board.length; i++){
                          
         for( int j = 0; j<24; j++){
         
            
            board[i][j] = new Piece(i, j);
            
           
            board[i][j].setActionCommand("blank");
            board[i][j].addActionListener(this);
            board[i][j].setEnabled(false);
            jpBoard.add(board[i][j]);
               
               
            
         } // end j for
            
            
      } // end i for
      
      
      resetBoard();
       
      JPanel main = new JPanel(new GridLayout(0, 1));
         setTitle("Backgammon");
    
    // JMenuBar 
      JMenuBar jmb = new JMenuBar();
      setJMenuBar(jmb);
         
         // Create File Menu
      JMenu jmFile = new JMenu("File");
         
            // Create File menu choices
      JMenuItem jmiReset = new JMenuItem("Reset");
      jmiReset.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  resetBoard();
               }});
         
      JMenuItem jmiExit = new JMenuItem("Exit");
      jmiExit.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  System.exit(0);
               }});
               
            // Add File menu choices to the menu
      jmFile.add(jmiReset);
      jmFile.add(jmiExit);
         
         //  Create About Menu
      JMenu jmAbout = new JMenu("About");
         
            // Create About menu choices
      JMenuItem jmiRules = new JMenuItem("Rules");
         jmiRules.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null, "Our version of backgammon requires red to move all pieces to the 6 rightmost columns on the board\n" + 
                              "while black has to move all pieces to the 6 leftmost columns on the board. Players will roll\n" + 
                              "using the button provided then select a piece of their respective color. The piece will automatically move\n" +
                              "to the desired location in accordance with the dice roll. Players cannot move in the same vertical column as\n" +
                              "their opponent. First player to reach the goal area with all pieces wins!");
               }});
      JMenuItem jmiAbout = new JMenuItem("About");
         jmiAbout.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  JOptionPane.showMessageDialog(null, "Version 1.0\nWritten by Caleb Maynard, Alex Kramer.\nEnjoy!");
               }});
            
            // Add About menu choices to the menu
      jmAbout.add(jmiRules);
      jmAbout.add(jmiAbout);
         
         // Add JMenus to JMenuBar
      jmb.add(jmFile);
      jmb.add(jmAbout);
      main.add(jpBoard);
      JPanel jpDiceRoll = new JPanel();
      
         // Dice roll button and text field
      jbRoll = new JButton("Roll!");
        
      jtfValue = new JTextField(10); // add label
      jtfTurn = new JTextField(10); // add label
      jtfTurn.setText("Red");
      jtfTurn.setHorizontalAlignment(JTextField.CENTER);
      jtfValue.setEnabled(false);
      jtfTurn.setEnabled(false);
         // Add components to JPanel
      jpDiceRoll.add(jbRoll);
      jpDiceRoll.add(jtfValue);
      jpDiceRoll.add(jtfTurn);
      
      jbRoll.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               DiceRoll();
               enableButtons();
               jbRoll.setEnabled(false); 
            }});
         
         // Add panels to frame
      add(main, BorderLayout.NORTH);
      add(jpDiceRoll, BorderLayout.SOUTH);
         
         
         
   
         // Window settings and visible
         
        
      setLocationRelativeTo(null);
      pack();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   } // end constructor
   
   public void DiceRoll(){
      diceRoll =(int)(Math.random()*6+1);
      jtfValue.setText(String.valueOf(diceRoll));
   
   }
   
   
   public void resetBoard(){
      
      try{
      
      jbRoll.setEnabled(true);
      jtfValue.setText(null);
      jtfTurn.setText("Red");
      }
      catch(NullPointerException npe){}
      for ( int i = 0; i<board.length; i++){
                          
         for( int j = 0; j<24; j++){
            
            board[i][j].setIcon(blank);
            board[i][j].setActionCommand("blank");
            board[i][j].addActionListener(this);
            board[i][j].setEnabled(false);
            jpBoard.add(board[i][j]);
               
         
         } // end j for
            
            
      } // end i for
      
     
      
      for(int i =0; i < 2; i++){
         board[i][0].setActionCommand("red");
         board[i][0].setIcon(redPiece);
      }
     
     
      
      for(int i =0; i < 5; i++){
         board[i][5].setActionCommand("black");
         board[i][5].setIcon(blackPiece);
      }
     
    
      
      for(int i =0; i < 3; i++){
         board[i][7].setActionCommand("black");
         board[i][7].setIcon(blackPiece);
      }
     
    
      
      for(int i =0; i < 5; i++){
         board[i][11].setActionCommand("red");
         board[i][11].setIcon(redPiece);
      }
     
      for(int i=0; i < 5; i++){
         board[i][12].setActionCommand("black");
         board[i][12].setIcon(blackPiece);
      }      
     
      for(int i=0; i < 3; i++){
         board[i][16].setActionCommand("red");
         board[i][16].setIcon(redPiece);
      }   
     
      for(int i=0; i < 5; i++){
         board[i][18].setActionCommand("red");
         board[i][18].setIcon(redPiece);
      }   
     
      for(int i=0; i < 2; i++){
         board[i][23].setActionCommand("black");
         board[i][23].setIcon(blackPiece);
      }  
   
   }

   public void findButton(Object c){
   
   
      for(int i=0; i<5; i++){
         for(int j=0; j< 24; j++){
         
         
            if(c.equals(board[i][j])){
               System.out.println(i + " " + j + " clicked");
               xPos = i;
               yPos = j;
               
            
            
            }
         }
      
      }
      
      if(playerCount == 1){ 
         redMove();
         // enter red goal code
      }
      else if(playerCount == 2){
         blackMove();
         // enter black goal code
      }
      
   
   }
   
   public void redMove(){
      try{
         if(diceRoll != -1){
            jbRoll.setEnabled(false);
            if(playerCount == 1) {
               redCount = 0;
               if(board[xPos][yPos].getActionCommand() == "red"){                         
                  for(int i = 0; i<5; i++){   
                     if(board[i][yPos + diceRoll].getActionCommand() == "black"){
                        JOptionPane.showMessageDialog(null, "You can't move that piece!\nOpponent Piece in the way");   
                        break;                                       
                     }
                  }
               
                  if(board[0][yPos+ diceRoll].getActionCommand() =="blank"){                               
                     for(int i = 0; i<5; i++){
                        if(redCount == 0){
                           board[i][yPos+diceRoll].setIcon(redPiece);
                           board[i][yPos+diceRoll].setActionCommand("red");
                           board[xPos][yPos].setIcon(blank);
                           board[xPos][yPos].setActionCommand("blank");
                           playerCount++;
                           redCount++;
                           jtfTurn.setText("Black");
                           jbRoll.setEnabled(true);  
                           jtfValue.setText(null); 
                           diceRoll = -1; 
                           redGoal();
                           JOptionPane.showMessageDialog(null, "Black turn.");
                            
                        } 
                     } 
                  
                  } 
                  else if(board[4][yPos + diceRoll].getActionCommand() == "red"){
                     JOptionPane.showMessageDialog(null, "Row is full of red pieces.\nMove a different piece.");
                  }  
                  else if(board[0][yPos + diceRoll].getActionCommand() == "red"){
                     for(int i = 1; i<5; i++){
                        if(board[i][yPos + diceRoll].getActionCommand() == "blank" && redCount == 0){
                           board[i][yPos+diceRoll].setIcon(redPiece);
                           board[i][yPos+diceRoll].setActionCommand("red");
                           board[xPos][yPos].setIcon(blank);
                           board[xPos][yPos].setActionCommand("blank");
                           playerCount++;
                           redCount++;                  
                        }
                     }  
                  
                     jtfTurn.setText("Black");
                     jbRoll.setEnabled(true);    
                     jtfValue.setText(null);
                     diceRoll = -1; 
                     redGoal();
                     JOptionPane.showMessageDialog(null, "Black turn."); 
                     
                  }          
               
               }
               else{
                  JOptionPane.showMessageDialog(null, "Not your Piece! Move a red piece!");   
               }  
            
            
            }
         }
      
         
      }
      catch(ArrayIndexOutOfBoundsException aioobe){
         JOptionPane.showMessageDialog(null, "That move is out of bounds. Move a different piece.");   
      }
   }
   
   public void blackMove(){
      try{
         if(diceRoll != -1){
            jbRoll.setEnabled(false);
            if(playerCount == 2) {
               blackCount = 0;
               if(board[xPos][yPos].getActionCommand() == "black"){                         
                  
                  for(int i = 0; i<5; i++){   
                     if(board[i][yPos - diceRoll].getActionCommand() == "red"){
                        JOptionPane.showMessageDialog(null, "You can't move that piece!\nOpponent Piece in the way");   
                        break;                                       
                     }
                  }
                  if((board[0][yPos - diceRoll].getActionCommand() =="blank")) {                       
                     for(int i = 0; i<5; i++){
                        if(blackCount == 0){
                           board[i][yPos-diceRoll].setIcon(blackPiece);
                           board[i][yPos-diceRoll].setActionCommand("black");
                           board[xPos][yPos].setIcon(blank);
                           board[xPos][yPos].setActionCommand("blank");
                           playerCount--;
                           blackCount++; 
                           jtfTurn.setText("Red");
                           jbRoll.setEnabled(true);
                           jtfValue.setText(null);
                           diceRoll = -1; 
                           blackGoal();
                           JOptionPane.showMessageDialog(null, "Red turn.");   
                           
                        }
                     }
                  
                  } 
                  else if(board[4][yPos - diceRoll].getActionCommand() == "black"){
                     JOptionPane.showMessageDialog(null, "Row is full of black pieces.\nMove a different piece.");
                  }  
                  else if(board[0][yPos - diceRoll].getActionCommand() == "black"){
                     for(int i = 1; i<5; i++){
                        if(board[i][yPos - diceRoll].getActionCommand() == "blank" && blackCount == 0){
                           board[i][yPos-diceRoll].setIcon(blackPiece);
                           board[i][yPos-diceRoll].setActionCommand("black");
                           board[xPos][yPos].setIcon(blank);
                           board[xPos][yPos].setActionCommand("blank");
                           playerCount--;
                           blackCount++;
                        
                        }
                     } 
                     jtfTurn.setText("Red");
                     jbRoll.setEnabled(true); 
                     diceRoll = -1;  
                     blackGoal();
                     JOptionPane.showMessageDialog(null, "Red turn."); 
                     jtfValue.setText(null);
                  } 
               
               }   
            }
            
            else{
               JOptionPane.showMessageDialog(null, "Not your Piece! Move a black piece!");   
               
            }
         } 
         blackGoal();
      }
      catch(ArrayIndexOutOfBoundsException aioobe){
         JOptionPane.showMessageDialog(null, "That move is out of bounds. Move a different piece.");   
      }         
   }
   
   public void redGoal(){
      int redGoalCount = 0;
      int choice = 0;
      for(int i = 18; i<24; i++){
         for( int j = 0; j<5; j++){
            if(board[j][i].getActionCommand() == "red"){
               redGoalCount++;
            }
         }
      }  
      
      if(redGoalCount == 15){
         choice = JOptionPane.showConfirmDialog(null, "Red player wins!\nPlay again?", "WINNER!", JOptionPane.YES_NO_OPTION);
         if(choice == JOptionPane.YES_OPTION){
            playerCount = 1;
            resetBoard();
            
         }
         else if(choice == JOptionPane.NO_OPTION){
            System.exit(0);
         }
      }
   }
   public void blackGoal(){
      int blackGoalCount = 0;
      int choice = 0;
      for(int i = 0; i<6; i++){
         for( int j = 0; j<5; j++){
            if(board[j][i].getActionCommand() == "black"){
               blackGoalCount++;
            }
         }
      }  
      
      if(blackGoalCount == 15){
         choice = JOptionPane.showConfirmDialog(null, "Black player wins!\nPlay again?", "WINNER!", JOptionPane.YES_NO_OPTION);
         if(choice == JOptionPane.YES_OPTION){
            playerCount = 1;
            resetBoard();
            
         }
         else if(choice == JOptionPane.NO_OPTION){
            System.exit(0);
         }
      }
   }

   public void enableButtons(){
      for(int i = 0; i<5; i++){
         for(int j = 0; j<24; j++){
            board[i][j].setEnabled(true);
         }  
      }
   }
   
   public void actionPerformed(ActionEvent ae){
      //while(keepGoing == true){ 
      findButton(ae.getSource());
       
      //} 
   }
   
   class Piece extends JButton {
      
      private int xPos;
      private int yPos;
      //  private int player;
      
      // Constructor
      public Piece(int _xpos, int _ypos){
      
         xPos =_xpos;
         yPos = _ypos;
         // player = _player;
         
         btn = new JButton();
         btn.setSize(20,20);
         btn.setIcon(blank);
      
      }
   } // end class Piece
 
} // end class Group_4_Backgammon