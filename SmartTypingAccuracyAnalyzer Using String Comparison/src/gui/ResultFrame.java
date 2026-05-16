package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ResultFrame extends JFrame {

    public ResultFrame(double accuracy, double speed,
                       int matchedChars, int unmatchedChars,
                       int specialChars, int spaces, int alphanumericChars,
                       int totalTyped, long timeTaken,
                       String original, String typed) {

        setTitle("Results");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        // ── GRADE ──
        String grade = accuracy >= 95 ? "S" : accuracy >= 85 ? "A" : accuracy >= 70 ? "B" : accuracy >= 50 ? "C" : "D";
        Color gradeColor = accuracy >= 95 ? new Color(0,180,0) : accuracy >= 85 ? new Color(30,144,255)
                         : accuracy >= 70 ? new Color(255,165,0) : accuracy >= 50 ? new Color(255,100,0) : Color.RED;

        JLabel gradeLabel = new JLabel("Grade: " + grade, SwingConstants.CENTER);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gradeLabel.setForeground(gradeColor);
        gradeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel accuracyLabel = new JLabel(String.format("Accuracy: %.2f%%   |   Speed: %.2f WPM   |   Time: %ds", accuracy, speed, timeTaken), SwingConstants.CENTER);
        accuracyLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(245, 245, 250));
        topPanel.add(gradeLabel);
        topPanel.add(accuracyLabel);

        // ── CHAR BREAKDOWN TABLE ──
        String[] cols = {"Category", "Count", "% of Total Typed"};
        int total = Math.max(1, totalTyped);
        Object[][] data = {
            {"✔ Matched Characters",     matchedChars,     String.format("%.1f%%", matchedChars * 100.0 / total)},
            {"✘ Unmatched Characters",   unmatchedChars,   String.format("%.1f%%", unmatchedChars * 100.0 / total)},
            {"🔤 Alphanumeric (a-z,0-9)", alphanumericChars, String.format("%.1f%%", alphanumericChars * 100.0 / total)},
            {"␣ Spaces",                 spaces,           String.format("%.1f%%", spaces * 100.0 / total)},
            {"★ Special Characters",     specialChars,     String.format("%.1f%%", specialChars * 100.0 / total)},
            {"📝 Total Typed",            totalTyped,       "100%"},
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(32);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);

        // Color matched green, unmatched red
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (row == 0) setBackground(new Color(220, 255, 220));
                else if (row == 1) setBackground(new Color(255, 220, 220));
                else setBackground(row % 2 == 0 ? new Color(240, 248, 255) : Color.WHITE);
                setHorizontalAlignment(col == 0 ? LEFT : CENTER);
                return this;
            }
        });

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("  Character Breakdown  "));

        // ── CHAR-BY-CHAR DIFF VIEW ──
        JTextPane diffPane = new JTextPane();
        diffPane.setEditable(false);
        diffPane.setFont(new Font("Courier New", Font.PLAIN, 16));
        diffPane.setBorder(BorderFactory.createTitledBorder("  Character Match View  "));

        javax.swing.text.StyledDocument doc = diffPane.getStyledDocument();
        for (int i = 0; i < original.length(); i++) {
            javax.swing.text.SimpleAttributeSet attr = new javax.swing.text.SimpleAttributeSet();
            javax.swing.text.StyleConstants.setFontFamily(attr, "Courier New");
            javax.swing.text.StyleConstants.setFontSize(attr, 16);
            if (i < typed.length()) {
                if (typed.charAt(i) == original.charAt(i)) {
                    javax.swing.text.StyleConstants.setForeground(attr, new Color(0, 160, 0));
                    javax.swing.text.StyleConstants.setBold(attr, true);
                } else {
                    javax.swing.text.StyleConstants.setForeground(attr, new Color(200, 0, 0));
                    javax.swing.text.StyleConstants.setBackground(attr, new Color(255, 220, 220));
                    javax.swing.text.StyleConstants.setBold(attr, true);
                }
            } else {
                javax.swing.text.StyleConstants.setForeground(attr, new Color(160, 160, 160));
            }
            try { doc.insertString(doc.getLength(), String.valueOf(original.charAt(i)), attr); }
            catch (Exception ex) { /* skip */ }
        }

        JScrollPane diffScroll = new JScrollPane(diffPane);
        diffScroll.setPreferredSize(new Dimension(680, 70));

        // ── PLAY AGAIN BUTTON ──
        JButton playAgain = new JButton("Play Again");
        playAgain.setFont(new Font("Arial", Font.BOLD, 15));
        playAgain.setBackground(new Color(70, 130, 180));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFocusPainted(false);
        playAgain.addActionListener(e -> { new GameFrame(); dispose(); });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 245, 250));
        bottomPanel.add(playAgain);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 245, 250));
        centerPanel.add(tableScroll);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(diffScroll);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
