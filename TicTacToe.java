import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class TicTacToe implements ActionListener {
  Random rand = new Random();
  JFrame frame = new JFrame();
  JPanel title_panel = new JPanel();
  JPanel button = new JPanel();
  JLabel textfield = new JLabel();
  JButton[] buttons = new JButton[9];
  JButton restart = new JButton();
  Timer restartTimer;
  boolean player1_turn;
  TicTacToe() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,800);
    frame.getContentPane().setBackground(new Color(50, 50, 50));
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);

    textfield.setBackground(new Color(25,25,25));
    textfield.setForeground(new Color(25, 255, 0));
    textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
    textfield.setHorizontalAlignment(JLabel.CENTER);
    textfield.setText("Tic-Tac-Toe");
    textfield.setOpaque(true);

    restart.setFont(new Font("Ink Free", Font.BOLD, 40));
    restart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        restartGame();
      }
    });


    restartTimer = new Timer(500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        restartGame();
      }
    });
    restartTimer.setRepeats(false);

    title_panel.setLayout(new BorderLayout());
    title_panel.setBounds(0,0,800,100);

    button.setLayout(new GridLayout(3,3));
    button.setBackground(new Color(150,150,150));

    for(int i = 0; i < 9; i++) {
      buttons[i] = new JButton();
      button.add(buttons[i]);
      buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
      buttons[i].setFocusable(false);
      buttons[i].addActionListener(this);
    }

    title_panel.add(textfield);
    frame.add(title_panel, BorderLayout.NORTH);
    frame.add(button);

    firstTurn();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < 9; i++) {
      if(e.getSource() == buttons[i]) {
        if(player1_turn) {
          if(buttons[i].getText().equals("")) {
            buttons[i].setForeground(new Color(255, 0, 0));
            buttons[i].setText("X");
            player1_turn = false;
            textfield.setText("O's turn");
          }
        }
        else {
          if(buttons[i].getText().equals("")) {
            buttons[i].setForeground(new Color(0, 0, 255));
            buttons[i].setText("O");
            player1_turn = true;
            textfield.setText("X's turn");
          }
        }
        checkWinOrDraw();
      }
    }

  }

  public void restartGame() {
    for (int i = 0; i < 9; i++) {
      buttons[i].setText("");
      buttons[i].setBackground(null);
      buttons[i].setEnabled(true);
    }
    firstTurn();
  }

  public void firstTurn() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    if(rand.nextInt(2) == 0) {
      player1_turn = true;
      textfield.setText("X's turn");
    }
    else {
      player1_turn = false;
      textfield.setText("O's turn");
    }

  }

  public void checkWinOrDraw() {
    int i = 0;
    while(buttons[i].getText() != "") {
      if (i == buttons.length - 1) {
        draw();
        break;
      }
      i++;
    }

    if((buttons[0].getText()=="X") && (buttons[1].getText()=="X") && (buttons[2].getText()=="X")) {
      xWin(0,1,2);
    }
    if((buttons[3].getText()=="X") && (buttons[4].getText()=="X") && (buttons[5].getText()=="X")) {
      xWin(3,4,5);
    }
    if((buttons[6].getText()=="X") && (buttons[7].getText()=="X") && (buttons[8].getText()=="X")) {
      xWin(6,7,8);
    }
    if((buttons[0].getText()=="X") && (buttons[3].getText()=="X") && (buttons[6].getText()=="X")) {
      xWin(0,3,6);
    }
    if((buttons[1].getText()=="X") && (buttons[4].getText()=="X") && (buttons[7].getText()=="X")) {
      xWin(1,4,7);
    }
    if((buttons[2].getText()=="X") && (buttons[5].getText()=="X") && (buttons[8].getText()=="X")) {
      xWin(2,5,8);
    }
    if((buttons[0].getText()=="X") && (buttons[4].getText()=="X") && (buttons[8].getText()=="X")) {
      xWin(0,4,8);
    }
    if((buttons[2].getText()=="X") && (buttons[4].getText()=="X") && (buttons[6].getText()=="X")) {
      xWin(2,4,6);
    }

    if((buttons[0].getText()=="O") && (buttons[1].getText()=="O") && (buttons[2].getText()=="O")) {
      oWin(0,1,2);
    }
    if((buttons[3].getText()=="O") && (buttons[4].getText()=="O") && (buttons[5].getText()=="O")) {
      oWin(3,4,5);
    }
    if((buttons[6].getText()=="O") && (buttons[7].getText()=="O") && (buttons[8].getText()=="O")) {
      oWin(6,7,8);
    }
    if((buttons[0].getText()=="O") && (buttons[3].getText()=="O") && (buttons[6].getText()=="O")) {
      oWin(0,3,6);
    }
    if((buttons[1].getText()=="O") && (buttons[4].getText()=="O") && (buttons[7].getText()=="O")) {
      oWin(1,4,7);
    }
    if((buttons[2].getText()=="O") && (buttons[5].getText()=="O") && (buttons[8].getText()=="O")) {
      oWin(2,5,8);
    }
    if((buttons[0].getText()=="O") && (buttons[4].getText()=="O") && (buttons[8].getText()=="O")) {
      oWin(0,4,8);
    }
    if((buttons[2].getText()=="O") && (buttons[4].getText()=="O") && (buttons[6].getText()=="O")) {
      oWin(2,4,6);
    }

  }

  public void draw() {
    for (int i = 0; i < 9; i++) {
      buttons[i].setEnabled(false);
    }
    textfield.setText("Tie game!");


    restartTimer.start();
  }

  public void xWin(int x, int y, int z) {
    buttons[x].setBackground(Color.GREEN);
    buttons[y].setBackground(Color.GREEN);
    buttons[z].setBackground(Color.GREEN);

    for(int i = 0; i < 9; i++) {
      buttons[i].setEnabled(false);
    }
    textfield.setText("X wins");

    restartTimer.start();

  }

  public void oWin(int x, int y, int z) {
    buttons[x].setBackground(Color.GREEN);
    buttons[y].setBackground(Color.GREEN);
    buttons[z].setBackground(Color.GREEN);

    for(int i = 0; i < 9; i++) {
      buttons[i].setEnabled(false);
    }
    textfield.setText("O wins");

    restartTimer.start();

  }
}
