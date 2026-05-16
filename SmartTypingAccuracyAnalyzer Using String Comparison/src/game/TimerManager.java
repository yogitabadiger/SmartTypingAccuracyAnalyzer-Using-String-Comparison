package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerManager {

    private int timeLeft;
    private Timer timer;

    public TimerManager(int seconds, JLabel timerLabel) {

        this.timeLeft = seconds;

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                timeLeft--;

                timerLabel.setText("Time Left: " + timeLeft + " sec");

                if (timeLeft <= 0) {
                    timer.stop();
                }
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}