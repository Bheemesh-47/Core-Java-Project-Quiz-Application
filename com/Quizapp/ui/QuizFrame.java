package com.Quizapp.ui;
import com.Quizapp.logic.GameEngine;
import com.Quizapp.logic.LifelineManager;
import com.Quizapp.model.Player;
import com.Quizapp.model.Question;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class QuizFrame extends JFrame implements ActionListener {
    private GameEngine engine;
    private Question currentQuestion;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JButton fiftyFiftyBtn, audiencePollBtn, skipBtn, quitBtn;
    private JLabel rewardLabel, greetingLabel, nextRewardLabel, playingForLabel;
    public QuizFrame(Player player) {
        engine = new GameEngine(player);
        setTitle("🎓 Quiz Master - Challenge Your Brain!");
        setSize(800, 600);
        setLayout(new BorderLayout(15, 15));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 250));
        // 🟣 Top Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(52, 73, 94));
        topPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        greetingLabel = new JLabel();
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        topPanel.add(greetingLabel);
        topPanel.add(questionLabel);
        add(topPanel, BorderLayout.NORTH);
        // 🟡 Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(new Color(245, 245, 250));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 16));
            optionButtons[i].setBackground(Color.WHITE);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            optionButtons[i].addActionListener(this);
            centerPanel.add(optionButtons[i]);
        }
        add(centerPanel, BorderLayout.CENTER);
        // 🟢 Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        bottomPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        bottomPanel.setBackground(new Color(230, 240, 255));
        fiftyFiftyBtn = createLifelineButton("🎯 50-50", new Color(26, 188, 156));
        audiencePollBtn = createLifelineButton("📊 Audience Poll", new Color(52, 152, 219));
        skipBtn = createLifelineButton("⏭️ Skip", new Color(155, 89, 182));
        quitBtn = createLifelineButton("🚪 Quit", new Color(231, 76, 60));
        rewardLabel = new JLabel();
        rewardLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        rewardLabel.setForeground(new Color(44, 62, 80));
        nextRewardLabel = new JLabel();
        nextRewardLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nextRewardLabel.setForeground(new Color(44, 62, 80));
        playingForLabel = new JLabel();
        playingForLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        playingForLabel.setForeground(new Color(44, 62, 80));
        bottomPanel.add(fiftyFiftyBtn);
        bottomPanel.add(audiencePollBtn);
        bottomPanel.add(skipBtn);
        bottomPanel.add(quitBtn);
        bottomPanel.add(rewardLabel);
        bottomPanel.add(nextRewardLabel);
        bottomPanel.add(playingForLabel);
        add(bottomPanel, BorderLayout.SOUTH);
        loadNextQuestion();
        setVisible(true);
    }
    private JButton createLifelineButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        return button;
    }
    private void loadNextQuestion() {
        if (!engine.hasNextQuestion()) {
            showFinalResult(true);
            return;
        }
        currentQuestion = engine.getNextQuestion();
        greetingLabel.setText("👋 Hello, " + engine.getPlayer().getName() + "! Here's Question " + engine.getCurrentQuestionIndex() + ":");
        questionLabel.setText("❓ " + currentQuestion.getQuestionText());
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setEnabled(true);
            optionButtons[i].setBackground(Color.WHITE);
        }
        // Show current and next rewards
        int currentReward = engine.getPlayer().getCurrentReward();
        int nextReward = engine.getNextRewardAmount();
        rewardLabel.setText("🏆 Current Reward: ₹" + currentReward);
        nextRewardLabel.setText("💡 Playing for: ₹" + nextReward);
        // Show playing for amount (total amount the player could win next)
//        int totalReward = engine.calculateTotalReward();
//        playingForLabel.setText("💰 Prize Amount: ₹" + totalReward);
    }
    private void showFinalResult(boolean won) {
        int reward = won ? engine.getPlayer().getCurrentReward() : engine.getSafeReward();
        int attempted = engine.getCurrentQuestionIndex();
        JOptionPane.showMessageDialog(this,
                "🎉 Congrats " + engine.getPlayer().getName() + " from " + engine.getPlayer().getCity() +
                        ", " + engine.getPlayer().getState() +
                        "!\nYou have won ₹" + reward + "!" +
                        "\n📚 You attempted " + attempted + " questions.",
                "🎊 Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < optionButtons.length; i++) {
            if (source == optionButtons[i]) {
                if (i == currentQuestion.getCorrectAnswerIndex()) {
                    engine.updateReward();
                    for (JButton btn : optionButtons) {
                        btn.setEnabled(false);
                        if (btn == optionButtons[i]) {
                            btn.setBackground(new Color(144, 238, 144));
                        } else {
                            btn.setBackground(Color.LIGHT_GRAY);
                        }
                    }
                    JButton nextButton = new JButton("Next ➡️");
                    nextButton.setBackground(new Color(52, 152, 219));
                    nextButton.setForeground(Color.WHITE);
                    nextButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                    JOptionPane optionPane = new JOptionPane("✅ Correct Answer!",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.DEFAULT_OPTION,
                            null,
                            new Object[]{}, null);
                    JDialog dialog = optionPane.createDialog(this, "🎉 Success");
                    dialog.setLayout(new BorderLayout());
                    dialog.add(optionPane, BorderLayout.CENTER);
                    dialog.add(nextButton, BorderLayout.SOUTH);
                    dialog.setSize(300, 150);
                    nextButton.addActionListener(ev -> {
                        dialog.dispose();
                        loadNextQuestion();
                    });
                    dialog.setVisible(true);
                } else {
                    for (JButton btn : optionButtons) {
                        btn.setEnabled(false);
                        if (btn == optionButtons[i]) {
                            btn.setBackground(new Color(255, 99, 71));
                        } else if (btn == optionButtons[currentQuestion.getCorrectAnswerIndex()]) {
                            btn.setBackground(new Color(144, 238, 144));
                        }
                    }
                    showFinalResult(false);
                }
                return;
            }
        }
        // Lifeline: 50-50
        if (source == fiftyFiftyBtn && !engine.getPlayer().isLifelineUsed(0)) {
            String[] reduced = LifelineManager.applyFiftyFifty(currentQuestion);
            for (JButton btn : optionButtons) {
                if (!(btn.getText().equals(reduced[0]) || btn.getText().equals(reduced[1]))) {
                    btn.setEnabled(false);
                    btn.setBackground(Color.LIGHT_GRAY);
                }
            }
            engine.getPlayer().useLifeline(0);
            fiftyFiftyBtn.setBackground(Color.DARK_GRAY);
        }
        // Lifeline: Audience Poll
        if (source == audiencePollBtn && !engine.getPlayer().isLifelineUsed(1)) {
            int index = LifelineManager.applyAudiencePoll(currentQuestion);
            JOptionPane.showMessageDialog(this,
                    "📊 Audience suggests Option " + (index + 1) + " is most likely correct.",
                    "Audience Poll", JOptionPane.INFORMATION_MESSAGE);
            engine.getPlayer().useLifeline(1);
            audiencePollBtn.setBackground(Color.DARK_GRAY);
        }
        // Lifeline: Skip
        if (source == skipBtn && !engine.getPlayer().isLifelineUsed(2)) {
            engine.getPlayer().useLifeline(2);
            skipBtn.setBackground(Color.DARK_GRAY);
            JOptionPane.showMessageDialog(this,
                    "⏭️ You skipped this question!",
                    "Question Skipped", JOptionPane.INFORMATION_MESSAGE);
            loadNextQuestion();
        }
        // Quit
        if (source == quitBtn) {
            if (engine.getCurrentQuestionIndex() >= 5) {
                JOptionPane.showMessageDialog(this,
                        "🚪 You chose to quit the game.\n🏆 Final reward: ₹" + engine.getPlayer().getCurrentReward(),
                        "You Quit!", JOptionPane.INFORMATION_MESSAGE);
                showFinalResult(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "⚠️ You cannot quit before answering at least 5 questions.",
                        "Quit Restricted", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
