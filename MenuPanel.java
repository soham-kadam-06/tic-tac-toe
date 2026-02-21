import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MenuPanel extends JPanel {
    public MenuPanel(Runnable startTwoPlayer, Runnable startOnePlayer) {
        setLayout(null);

        JLabel title = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 48));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 120, 800, 80);
        add(title);

        JButton onePlayerBtn = createMenuButton("One Player", 300, 300);
        JButton twoPlayerBtn = createMenuButton("Two Player", 300, 400);

        add(onePlayerBtn);
        add(twoPlayerBtn);

        onePlayerBtn.addActionListener(e -> startOnePlayer.run());
        twoPlayerBtn.addActionListener(e -> startTwoPlayer.run());
    }

    private JButton createMenuButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 200, 60);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setBackground(new Color(230, 230, 250));
        btn.setForeground(new Color(60, 0, 120));
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(120, 0, 180), 2, true));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(new Color(200, 180, 255));
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(new Color(230, 230, 250));
            }
        });
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, new Color(80, 0, 180), 800, 800, new Color(150, 50, 255));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
