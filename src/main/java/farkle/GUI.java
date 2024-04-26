package farkle;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUI {
    List<MainGame> playerList;
    int playerTurnCount;

    InitGame initGame;
    Container southContainer;

    JFrame frame;
    JPanel cardsPanel;
    CardLayout cardLayout;

    JButton submitButton = new JButton("Submit");
    JButton endTurnButton = new JButton("End Turn");


    public GUI() {
        this.playerList = new ArrayList<>();
        playerTurnCount = 0;

        initGame = new InitGame();

        frame = new JFrame("Farkle!");
        endTurnButton.addActionListener(event -> nextPlayer());

        loadInitGame(initGame.getComponent());

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.add(initGame.getComponent(), "Setup");

        frame.setSize(1400, 350);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(cardsPanel);
    }

    private void loadInitGame(JPanel initPanel) {
        submitButton.addActionListener(event -> {
            int playerCount = Integer.parseInt(
                    Optional.ofNullable(initGame.getHumanPlayersCount().getText())
                            .filter(s -> !s.isBlank())
                            .orElse("0"));
            int computerCount = Integer.parseInt(
                    Optional.ofNullable(initGame.getComputerPlayersCount().getText())
                            .filter(s -> !s.isBlank())
                            .orElse("0"));

            // get human players
            for (int i = 0; i < playerCount; i++) {
                String playerName = "Player " + (i+1);
                JButton endTurnButton = new JButton("End Turn");
                endTurnButton.addActionListener(buttonEvent -> nextPlayer());
                MainGame playerGame = new MainGame(new HumanPlayer(playerName), endTurnButton);
                playerList.add(playerGame);
                cardsPanel.add(playerGame.getComponent(), playerGame.getPlayerId());
                cardsPanel.revalidate();
            }

            // get computer players
            for (int i = 0; i < computerCount; i++) {
                String name = "Computer " + (i+1);
                JButton endTurnButton = new JButton("End Turn");
                endTurnButton.addActionListener(buttonEvent -> nextPlayer());
                MainGame computerGame =
                        new MainGame(new ComputerPlayer(name, initGame.getDifficultySelection()),
                                endTurnButton);
                playerList.add(computerGame);
                cardsPanel.add(computerGame.getComponent(), computerGame.getPlayerId());
                cardsPanel.revalidate();
            }

            // Show initial player's turn
            cardLayout.show(cardsPanel, playerList.get(playerTurnCount).getPlayerId());
        });
        southContainer = new Container();
        southContainer.setLayout(new FlowLayout());
        southContainer.add(submitButton);
        initPanel.add(southContainer);
    }

    private void nextPlayer() {
        playerTurnCount = playerTurnCount < (playerList.size()-1) ? playerTurnCount+1 : 0;
        cardLayout.show(cardsPanel, playerList.get(playerTurnCount).getPlayerId());
    }
}