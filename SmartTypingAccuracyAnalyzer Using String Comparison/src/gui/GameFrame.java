package gui;

import algorithms.AccuracyCalculator;
import algorithms.SpeedCalculator;
import game.SentenceManager;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private long startTime;
    private String sentence;
    private JTextPane inputPane;
    private JLabel liveAccuracyLabel;
    private JLabel liveMatchedLabel;
    private JLabel liveUnmatchedLabel;

    public GameFrame() {

        setTitle("Smart Typing Accuracy Analyzer");
        setSize(800, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        sentence = SentenceManager.getRandomSentence();

        // ── TOP: sentence display (plain, no coloring here) ──
        JTextPane sentencePane = new JTextPane();
        sentencePane.setEditable(false);
        sentencePane.setFont(new Font("Courier New", Font.PLAIN, 20));
        sentencePane.setBackground(new Color(230, 230, 240));
        sentencePane.setBorder(BorderFactory.createTitledBorder("  Type this sentence:  "));
        sentencePane.setText(sentence);

        // ── MIDDLE: user input area ──
        inputPane = new JTextPane();
        inputPane.setFont(new Font("Courier New", Font.PLAIN, 20));
        inputPane.setBorder(BorderFactory.createTitledBorder("  Your input:  "));
        JScrollPane inputScroll = new JScrollPane(inputPane);
        inputScroll.setPreferredSize(new Dimension(780, 120));

        // ── LIVE STATS BAR ──
        JPanel livePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 4));
        livePanel.setBackground(new Color(220, 235, 255));
        livePanel.setBorder(BorderFactory.createTitledBorder("  Live Stats  "));

        liveAccuracyLabel  = makeLiveLabel("Accuracy: --%");
        liveMatchedLabel   = makeLiveLabel("✔ Matched: 0");
        liveUnmatchedLabel = makeLiveLabel("✘ Unmatched: 0");

        livePanel.add(liveAccuracyLabel);
        livePanel.add(liveMatchedLabel);
        livePanel.add(liveUnmatchedLabel);

        // ── SUBMIT BUTTON ──
        JButton submitButton = new JButton("Submit & See Results");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(220, 40));

        // ── LIVE STATS UPDATE ON KEYPRESS (numbers only, no color view) ──
        inputPane.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateLive(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateLive(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        startTime = System.currentTimeMillis();

        submitButton.addActionListener(e -> {
            long endTime = System.currentTimeMillis();
            long totalSeconds = Math.max(1, (endTime - startTime) / 1000);
            String typed = inputPane.getText();

            double accuracy    = AccuracyCalculator.calculateAccuracy(sentence, typed);
            double speed       = SpeedCalculator.calculateWPM(typed, totalSeconds);
            int matched        = AccuracyCalculator.countMatched(sentence, typed);
            int unmatched      = AccuracyCalculator.countUnmatched(sentence, typed);
            int specialChars   = AccuracyCalculator.countSpecialChars(typed);
            int spaces         = AccuracyCalculator.countSpaces(typed);
            int alphanumeric   = AccuracyCalculator.countAlphanumeric(typed);
            int totalTyped     = typed.length();
            long timeTaken     = totalSeconds;

            new ResultFrame(accuracy, speed, matched, unmatched,
                    specialChars, spaces, alphanumeric, totalTyped, timeTaken, sentence, typed);
            dispose();
        });

        // ── LAYOUT ──
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 245, 250));
        centerPanel.add(new JScrollPane(sentencePane));
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(inputScroll);
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(livePanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(245, 245, 250));
        bottomPanel.add(submitButton);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel makeLiveLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        return lbl;
    }

    private void updateLive() {
        String typed = inputPane.getText();
        int matched   = AccuracyCalculator.countMatched(sentence, typed);
        int unmatched = AccuracyCalculator.countUnmatched(sentence, typed);
        double acc    = AccuracyCalculator.calculateAccuracy(sentence, typed);

        liveAccuracyLabel.setText(String.format("Accuracy: %.1f%%", acc));
        liveMatchedLabel.setText("✔ Matched: " + matched);
        liveUnmatchedLabel.setText("✘ Unmatched: " + unmatched);
    }
}
