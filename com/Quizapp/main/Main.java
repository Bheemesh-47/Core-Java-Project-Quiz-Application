package com.Quizapp.main;
import com.Quizapp.model.Player;
import com.Quizapp.ui.QuizFrame;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String city = JOptionPane.showInputDialog("Enter your city:");
        String state = JOptionPane.showInputDialog("Enter your state:");
        if (name == null || city == null || state == null || name.isEmpty() || city.isEmpty() || state.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All details are required to start the quiz.");
            System.exit(0);
        }
        Player player = new Player(name, city, state);
        SwingUtilities.invokeLater(() -> new QuizFrame(player));
    }
}
