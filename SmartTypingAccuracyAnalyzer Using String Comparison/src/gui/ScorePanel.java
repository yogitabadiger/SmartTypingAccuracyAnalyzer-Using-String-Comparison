package gui;

import java.awt.*;
import javax.swing.*;

public class ScorePanel extends JPanel {

    private JLabel accuracyLabel;
    private JLabel speedLabel;
    private JLabel matchedLabel;
    private JLabel unmatchedLabel;
    private JLabel alphanumericLabel;
    private JLabel spacesLabel;
    private JLabel specialLabel;

    public ScorePanel() {

        setLayout(new GridLayout(7, 1));

        accuracyLabel     = new JLabel("Accuracy: 0%");
        speedLabel        = new JLabel("Speed: 0 WPM");
        matchedLabel      = new JLabel("Matched Characters: 0");
        unmatchedLabel    = new JLabel("Unmatched Characters: 0");
        alphanumericLabel = new JLabel("Alphanumeric Characters: 0");
        spacesLabel       = new JLabel("Spaces: 0");
        specialLabel      = new JLabel("Special Characters: 0");

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        for (JLabel lbl : new JLabel[]{accuracyLabel, speedLabel, matchedLabel,
                unmatchedLabel, alphanumericLabel, spacesLabel, specialLabel}) {
            lbl.setFont(labelFont);
            add(lbl);
        }
    }

    public void updateScores(double accuracy, double speed,
                             int matchedChars, int unmatchedChars,
                             int specialChars, int spaces, int alphanumericChars) {

        accuracyLabel.setText("Accuracy: " + String.format("%.2f", accuracy) + "%");
        speedLabel.setText("Speed: " + String.format("%.2f", speed) + " WPM");
        matchedLabel.setText("Matched Characters: " + matchedChars);
        unmatchedLabel.setText("Unmatched Characters: " + unmatchedChars);
        alphanumericLabel.setText("Alphanumeric Characters: " + alphanumericChars);
        spacesLabel.setText("Spaces: " + spaces);
        specialLabel.setText("Special Characters: " + specialChars);
    }
}
