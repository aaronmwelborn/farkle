package farkle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitGame implements ActionListener {
    JPanel initPanel;
    JPanel humanPlayerPanel = new JPanel();
    JPanel computerPlayerPanel = new JPanel();
    JTextField humanPlayersCount = new JTextField(2);
    JTextField computerPlayersCount = new JTextField(2);
    JComboBox<Difficulty> difficultySelection = new JComboBox<>(Difficulty.values());


    public InitGame() {
        initPanel = new JPanel();
        initPanel.setLayout(new GridLayout(3,1));

        humanPlayerPanel.setLayout(new FlowLayout());
        computerPlayerPanel.setLayout(new FlowLayout());

        humanPlayerPanel.add(new JLabel("Number of players: "));
        humanPlayerPanel.add(humanPlayersCount);

        computerPlayerPanel.add(new JLabel("Number of Computer Players: "));
        computerPlayerPanel.add(computerPlayersCount);

//        computerPlayerPanel.add(difficultySelection);

        initPanel.add(humanPlayerPanel);
        initPanel.add(computerPlayerPanel);
    }

    public JPanel getComponent() {
        return this.initPanel;
    }

    public JTextField getHumanPlayersCount() {
        return humanPlayersCount;
    }

    public JTextField getComputerPlayersCount() {
        return computerPlayersCount;
    }

    public Difficulty getDifficultySelection() {
        return Difficulty.EASY;
//        return difficultySelection.getItemAt(difficultySelection.getSelectedIndex());
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
