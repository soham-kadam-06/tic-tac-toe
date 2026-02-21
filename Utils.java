    import java.awt.*;
    import javax.swing.*;

    public class Utils {

        // ---------- STYLED BUTTON CREATION ----------
        public static JButton createStyledButton(String text, int width, int height) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(140, 70, 230));
            btn.setOpaque(true);
            btn.setPreferredSize(new Dimension(width, height));
            styleHover(btn, new Color(170, 100, 255), new Color(140, 70, 230));
            return btn;
        }

        private static void styleHover(JButton btn, Color hover, Color normal) {
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn.setBackground(hover);
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn.setBackground(normal);
                }
            });
        }

        // ---------- BOARD UTILITIES ----------
        public static void setBoardEnabled(JButton[][] buttons, boolean enabled) {
            for (JButton[] row : buttons)
                for (JButton btn : row)
                    btn.setEnabled(enabled);
        }

        // ---------- WINNER CHECKING + HIGHLIGHT ----------
        public static String checkWinner(JButton[][] buttons) {
            // Rows
            for (int i = 0; i < 3; i++)
                if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                    highlight(buttons, new Point(i,0), new Point(i,1), new Point(i,2));
                    return buttons[i][0].getText();
                }

            // Columns
            for (int j = 0; j < 3; j++)
                if (!buttons[0][j].getText().equals("") &&
                    buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText())) {
                    highlight(buttons, new Point(0,j), new Point(1,j), new Point(2,j));
                    return buttons[0][j].getText();
                }

            // Diagonals
            if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
                highlight(buttons, new Point(0,0), new Point(1,1), new Point(2,2));
                return buttons[0][0].getText();
            }

            if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
                highlight(buttons, new Point(0,2), new Point(1,1), new Point(2,0));
                return buttons[0][2].getText();
            }

            // Draw
            for (JButton[] row : buttons)
                for (JButton btn : row)
                    if (btn.getText().equals("")) return null;

            return "Draw";
        }

        // ---------- GREEN HIGHLIGHT FOR WINNING LINE ----------
        private static void highlight(JButton[][] buttons, Point... pts) {
            for (Point p : pts)
                buttons[p.x][p.y].setBackground(new Color(120, 255, 120));
        }

        // ---------- AI LOGIC ----------
        public static Point findBestMove(JButton[][] buttons) {
            Point move = findWinningMove(buttons, "O");
            if (move == null) move = findWinningMove(buttons, "X");
            if (move == null && buttons[1][1].getText().equals("")) return new Point(1, 1);

            int[][] order = {{0,0},{0,2},{2,0},{2,2},{0,1},{1,0},{1,2},{2,1}};
            for (int[] o : order)
                if (buttons[o[0]][o[1]].getText().equals(""))
                    return new Point(o[0], o[1]);
            return null;
        }

        private static Point findWinningMove(JButton[][] buttons, String player) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (buttons[i][j].getText().equals("")) {
                        buttons[i][j].setText(player);
                        String result = checkWinnerNoHighlight(buttons);
                        buttons[i][j].setText("");
                        if (player.equals(result)) return new Point(i, j);
                    }
            return null;
        }

        // ---------- CHECK WINNER WITHOUT HIGHLIGHT (used by AI) ----------
        private static String checkWinnerNoHighlight(JButton[][] buttons) {
            // Rows
            for (int i = 0; i < 3; i++)
                if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()))
                    return buttons[i][0].getText();

            // Columns
            for (int j = 0; j < 3; j++)
                if (!buttons[0][j].getText().equals("") &&
                    buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText()))
                    return buttons[0][j].getText();

            // Diagonals
            if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()))
                return buttons[0][0].getText();

            if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()))
                return buttons[0][2].getText();

            // Draw
            for (JButton[] row : buttons)
                for (JButton btn : row)
                    if (btn.getText().equals("")) return "";

            return "Draw";
        }
    }
