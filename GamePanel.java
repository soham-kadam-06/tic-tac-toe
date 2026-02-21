import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private JLabel roundWinnerLabel, turnLabel, scoreboardLabel;
    private JButton nextRoundBtn;
    private TicTacToeBoard board;
    private boolean vsComputer = false;
    private Runnable backToMenu;

    public GamePanel(Runnable backAction) {
        this.backToMenu = backAction;
        setLayout(null);

        initTopPanel();
        initBoardPanel();
        initBottomPanel();
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(20, 0));
        topPanel.setOpaque(false);
        topPanel.setBounds(0, 0, 800, 100);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JButton backBtn = Utils.createStyledButton("< Back", 140, 45);
        backBtn.addActionListener(e -> backToMenu.run());
        topPanel.add(backBtn, BorderLayout.WEST);

        turnLabel = new JLabel("Turn: X", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        turnLabel.setForeground(Color.WHITE);
        topPanel.add(turnLabel, BorderLayout.CENTER);

        JButton resetBtn = Utils.createStyledButton("Reset Board", 180, 45);
        resetBtn.addActionListener(e -> board.resetBoard());
        topPanel.add(resetBtn, BorderLayout.EAST);

        add(topPanel);
    }

    private void initBoardPanel() {
        board = new TicTacToeBoard(this);
        board.setBounds(250, 150, 300, 300);
        add(board);
    }

    private void initBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.setOpaque(false);
        bottomPanel.setBounds(0, 550, 800, 200);

        roundWinnerLabel = new JLabel("No winner yet", SwingConstants.CENTER);
        roundWinnerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        roundWinnerLabel.setForeground(Color.WHITE);
        bottomPanel.add(roundWinnerLabel);

        scoreboardLabel = new JLabel("X: 0   O: 0", SwingConstants.CENTER);
        scoreboardLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        scoreboardLabel.setForeground(Color.WHITE);
        bottomPanel.add(scoreboardLabel);

        nextRoundBtn = new JButton("Next Round");
        nextRoundBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        nextRoundBtn.setBackground(new Color(255, 220, 240));
        nextRoundBtn.setVisible(false);
        nextRoundBtn.addActionListener(e -> board.resetBoard());
        bottomPanel.add(nextRoundBtn);

        add(bottomPanel);
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
        board.setVsComputer(vsComputer);
    }

    public JLabel getTurnLabel() { return turnLabel; }
    public JLabel getRoundWinnerLabel() { return roundWinnerLabel; }
    public JLabel getScoreboardLabel() { return scoreboardLabel; }
    public JButton getNextRoundButton() { return nextRoundBtn; }

    public void resetAll() {
        board.resetAll();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, new Color(70, 0, 130), 800, 800, new Color(180, 100, 255));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
