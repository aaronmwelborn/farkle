package farkle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainGame implements ActionListener {
    private final Player player;
    private final String playerId;

    JPanel mainPanel = new JPanel();
    Container diceContainer = new Container();
    Container buttonContainer = new Container();
    Container southContainer = new Container();

    JButton[] diceButtons = new JButton[6];
    ImageIcon[] imageIcons = new ImageIcon[6];
    JButton rollButton = new JButton("Roll");
    JButton scoreButton = new JButton("Score");
    JButton stopButton = new JButton("Stop");
    JLabel currentScoreLabel = new JLabel("Current Score: 0");
    JLabel totalScoreLabel = new JLabel("Total Score: 0");
    JLabel currentRoundLabel = new JLabel("Current Round: 0");

    int[] buttonState = new int[6];
    int[] dieValue = new int[6];
    final int PLAYABLE_DIE = 0;
    final int SCORE_DIE = 1;
    final int LOCKED_DIE = 2;
    int currentRound = 1;


    public MainGame(Player player, JButton endTurnButton) {
        this.player = player;
        this.playerId = UUID.randomUUID().toString();

        // Dice Images
        imageIcons[0] = new ImageIcon("src/main/java/farkle/img/D1.png");
        imageIcons[1] = new ImageIcon("src/main/java/farkle/img/D2.png");
        imageIcons[2] = new ImageIcon("src/main/java/farkle/img/D3.png");
        imageIcons[3] = new ImageIcon("src/main/java/farkle/img/D4.png");
        imageIcons[4] = new ImageIcon("src/main/java/farkle/img/D5.png");
        imageIcons[5] = new ImageIcon("src/main/java/farkle/img/D6.png");
        diceContainer.setLayout(new GridLayout(1, 6));

        for (int i = 0; i < diceButtons.length; i++) {
            int itr = i;
            diceButtons[i] = new JButton();
            diceButtons[i].setIcon(imageIcons[i]);
            diceButtons[i].setEnabled(false);
            diceButtons[i].addActionListener(event -> handleDiceButtonPressed(itr));
            diceButtons[i].setBackground(Color.LIGHT_GRAY);
            diceContainer.add(diceButtons[i]);
        }

        buttonContainer.setLayout(new GridLayout(1, 4));

        rollButton.addActionListener(event -> handleRollButtonPressed());
        stopButton.addActionListener(event -> handleStopButtonPressed());
        scoreButton.addActionListener(event -> handleScoreButtonPressed());
        endTurnButton.addActionListener(event -> handleStopButtonPressed());

        buttonContainer.add(rollButton);
        buttonContainer.add(stopButton);
        buttonContainer.add(scoreButton);
        buttonContainer.add(endTurnButton);

        scoreButton.setEnabled(false);
        stopButton.setEnabled(false);
        rollButton.setEnabled(true);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50);
        southContainer.setLayout(flowLayout);
        southContainer.add(new JLabel(player.getName()));
        southContainer.add(currentScoreLabel);
        southContainer.add(totalScoreLabel);
        southContainer.add(currentRoundLabel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(diceContainer, BorderLayout.CENTER);
        mainPanel.add(buttonContainer, BorderLayout.NORTH);
        mainPanel.add(southContainer, BorderLayout.SOUTH);

    }

    public JPanel getComponent() {
        return mainPanel;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

    private void handleDiceButtonPressed(int i) {
        if (buttonState[i] == PLAYABLE_DIE) {
            diceButtons[i].setBackground(Color.BLUE);
            buttonState[i] = SCORE_DIE;
        } else {
            diceButtons[i].setBackground(Color.LIGHT_GRAY);
            buttonState[i] = PLAYABLE_DIE;
        }
    }

    private void handleStopButtonPressed() {
        int totalScore = player.getTotalScore();
        player.setTotalScore(totalScore += player.getCurrentScore());
        player.setCurrentScore(0);
        currentScoreLabel.setText("Player Score: " + player.getCurrentScore());
        totalScoreLabel.setText("Total Score: " + totalScore);
        currentRound++;
        currentRoundLabel.setText("Current Round: " + currentRound);
        resetDice();
    }

    private void handleScoreButtonPressed() {
        int[] valueCount = new int[7];

        for (int i = 0; i < diceButtons.length; i++) {
            if (buttonState[i] == SCORE_DIE) {
                valueCount[dieValue[i] + 1]++;
            }
        }

        if (isInvalidSelectedDie(valueCount)) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid Die Selected");
        } else if (isFarkle(valueCount)) {
            Object[] options = {"Yes", "No"};

            int dialogChoice = JOptionPane.showOptionDialog(
                    mainPanel,
                    "Forfeit Score?",
                    "You Farkled!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (dialogChoice == JOptionPane.YES_OPTION) {
                player.setCurrentScore(0);
                currentRound++;
                currentScoreLabel.setText("Current Score: " + player.getCurrentScore());
                currentRoundLabel.setText("Current Round: " + currentRound);

                resetDice();
            }
        } else {
            player.setCurrentScore(getScoreFromDiceValues(valueCount));
            currentScoreLabel.setText("Current Score: " + player.getCurrentScore());

            for (int i = 0; i < diceButtons.length; i++) {
                if (buttonState[i] == SCORE_DIE) {
                    buttonState[i] = LOCKED_DIE;
                    diceButtons[i].setBackground(Color.GREEN);
                }
                diceButtons[i].setEnabled(false);
            }
            int lockedCount = 0;
            for (int i = 0; i < diceButtons.length; i++) {
                if (buttonState[i] == LOCKED_DIE) {
                    lockedCount++;
                }
            }
            if (lockedCount == 6) {
                for (int i = 0; i < diceButtons.length; i++) {
                    buttonState[i] = PLAYABLE_DIE;
                    diceButtons[i].setBackground(Color.LIGHT_GRAY);
                }
            }
            rollButton.setEnabled(true);
            scoreButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }

    private void handleRollButtonPressed() {
        // Roll dice
        for (int i = 0; i < diceButtons.length; i++) {
            if (buttonState[i] == PLAYABLE_DIE) {
                int choice = (int) (Math.random() * 6);
                dieValue[i] = choice;
                diceButtons[i].setIcon(imageIcons[choice]);
                diceButtons[i].setEnabled(true);
                rollButton.setEnabled(false);
                scoreButton.setEnabled(true);
                stopButton.setEnabled(true);
            }
        }

        if (player instanceof ComputerPlayer) {
            // Perform computer turn
            // find all dice that score

            // Select all ones and fives
            for (int i = 0; i < dieValue.length; i++) {
                int value = dieValue[i] + 1;
                if (value == 1 || value == 5) {
                    handleDiceButtonPressed(i);
                }
            }

            if (Arrays.stream(buttonState).anyMatch(die -> die == SCORE_DIE)) {
                handleScoreButtonPressed();
                handleRollButtonPressed();
            } else {

                String message = player.getName() + " scored " + player.getCurrentScore() + "\n";

                JOptionPane.showMessageDialog(
                        mainPanel,
                        message,
                        player.getName() + "'s turn is complete.",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);
            }

        }
    }

    void resetDice() {
        for (int a = 0; a < diceButtons.length; a++) {
            diceButtons[a].setEnabled(false);
            buttonState[a] = PLAYABLE_DIE;
            diceButtons[a].setBackground(Color.LIGHT_GRAY);
        }
        rollButton.setEnabled(true);
        scoreButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    private static boolean isFarkle(int[] valueCount) {
        return valueCount[1] == 0 && valueCount[2] == 0 && valueCount[3] == 0 &&
                valueCount[4] == 0 && valueCount[5] == 0 && valueCount[6] == 0;
    }

    private static boolean isInvalidSelectedDie(int[] valueCount) {
        return (valueCount[2] > 0 && valueCount[2] < 3) || (valueCount[3] > 0 && valueCount[3] < 3)
                || (valueCount[4] > 0 && valueCount[4] < 3) || (valueCount[6] > 0 && valueCount[6] < 3);
    }

    private int getScoreFromDiceValues(int[] valueCount) {
        int currentScore = player.getCurrentScore();

        if (valueCount[1] >= 3) {
            currentScore += (valueCount[1] - 2) * 1000;
        }
        if (valueCount[2] >= 3) {
            currentScore += (valueCount[2] - 2) * 200;
        }
        if (valueCount[3] >= 3) {
            currentScore += (valueCount[3] - 2) * 300;
        }
        if (valueCount[4] >= 3) {
            currentScore += (valueCount[4] - 2) * 400;
        }
        if (valueCount[5] >= 3) {
            currentScore += (valueCount[5] - 2) * 500;
        }
        if (valueCount[6] >= 3) {
            currentScore += (valueCount[6] - 2) * 600;
        }
        if (valueCount[1] < 3) {
            currentScore += valueCount[1] * 100;
        }
        if (valueCount[5] < 3) {
            currentScore += valueCount[5] * 50;
        }
        return currentScore;
    }
}
