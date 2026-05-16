package gui;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {

        setTitle("Smart Typing Accuracy Analyzer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Smart Typing Accuracy Analyzer", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton startButton = new JButton("Start Game");

        startButton.addActionListener(e -> {
            new GameFrame();
            dispose();
        });

        setLayout(new BorderLayout());

        add(title, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}