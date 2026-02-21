import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static MenuPanel menuPanel;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Tic-Tac-Toe Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.setResizable(false);
            frame.setLayout(null);

            menuPanel = new MenuPanel(() -> startGame(false), () -> startGame(true));
            gamePanel = new GamePanel(() -> showMenu());

            frame.setContentPane(menuPanel);
            frame.setVisible(true);
        });
    }

    private static void startGame(boolean vsComputer) {
        gamePanel.setVsComputer(vsComputer);
        frame.setContentPane(gamePanel);
        frame.revalidate();
        frame.repaint();
        gamePanel.resetAll();
    }

    private static void showMenu() {
        frame.setContentPane(menuPanel);
        frame.revalidate();
        frame.repaint();
        gamePanel.resetAll();
    }
}
