import java.awt.*;
import javax.swing.*;

public class TicTacToeBoard extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private String currentTurn = "X";
    private boolean roundActive = true;
    private boolean vsComputer = false;
    private int xScore = 0, oScore = 0;

    private GamePanel parent;

    public TicTacToeBoard(GamePanel parent) {
        this.parent = parent;
        setLayout(new GridLayout(3, 3));
        setOpaque(false);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton("");
                b.setFont(new Font("Segoe UI", Font.BOLD, 70));
                b.setBackground(new Color(240, 230, 255));
                b.setFocusPainted(false);
                b.setBorder(BorderFactory.createLineBorder(new Color(100, 0, 180), 3, true));
                final int row = i, col = j;
                b.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = b;
                add(b);
            }
        }
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    private void handleMove(int row, int col) {
        if (!roundActive) return;
        JButton btn = buttons[row][col];
        if (btn.getText().equals("")) {
            btn.setText(currentTurn);
            btn.setForeground(currentTurn.equals("X") ? Color.BLUE : Color.RED);

            String result = Utils.checkWinner(buttons);
            if (result != null) handleGameEnd(result);
            else {
                currentTurn = currentTurn.equals("X") ? "O" : "X";
                parent.getTurnLabel().setText("Turn: " + currentTurn);

                if (vsComputer && currentTurn.equals("O") && roundActive) {
                    Timer t = new Timer(500, e -> computerMove());
                    t.setRepeats(false);
                    t.start();
                }
            }
        }
    }

    private void computerMove() {
        if (!roundActive) return;
        Point move = Utils.findBestMove(buttons);
        if (move != null) {
            buttons[move.x][move.y].setText("O");
            buttons[move.x][move.y].setForeground(Color.RED);

            String result = Utils.checkWinner(buttons);
            if (result != null) handleGameEnd(result);
            else {
                currentTurn = "X";
                parent.getTurnLabel().setText("Turn: X");
            }
        }
    }

    private void handleGameEnd(String result) {
        roundActive = false;
        Utils.setBoardEnabled(buttons, false);
        parent.getNextRoundButton().setVisible(true);

        if (result.equals("Draw")) {
            parent.getRoundWinnerLabel().setText("It's a Draw!");
        } else {
            parent.getRoundWinnerLabel().setText("Winner: " + result);
            if (result.equals("X")) xScore++;
            else oScore++;
            parent.getScoreboardLabel().setText("X: " + xScore + "   O: " + oScore);
        }
    }

    public void resetBoard() {
        for (JButton[] row : buttons)
            for (JButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
                btn.setBackground(new Color(240, 230, 255));
            }
        currentTurn = "X";
        parent.getTurnLabel().setText("Turn: X");
        parent.getRoundWinnerLabel().setText("No winner yet");
        parent.getNextRoundButton().setVisible(false);
        roundActive = true;
    }

    public void resetAll() {
        xScore = oScore = 0;
        resetBoard();
        parent.getScoreboardLabel().setText("X: 0   O: 0");
    }
}
