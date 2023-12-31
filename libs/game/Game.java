package libs.game;

import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private Board board;
    private final Stack<Tile> drawStack; 
    private final Player[] players;
    private final int[] scores;

    private int roundNum;
    private final int handSize;

    public Game(int playerCount, int startingRound) {
        this.drawStack = new Stack<>();
        this.board = new Board(null);
        this.players = new Player[playerCount];
        this.scores = new int[playerCount];
        this.roundNum = startingRound;
        this.handSize = calculateHandSize();

        intializePlayers();
    }

    public Game(int playerCount) {
        this(playerCount, 12);
    }


    public void play() {
        for(; this.roundNum >= 0; this.roundNum--) {
            System.out.println("Play Round " + this.roundNum);
            initializeRound();
            playRound();
        }
    }

    private void intializePlayers() {
        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new HumanPlayer(null, i, i + 1);
        }
    }

    private void initializeRound() {
        initializeDrawStack();
        initializePlayersHands();
        intializeBoard();
    }

    private void initializeDrawStack() {
        System.out.println("Initializing Draw Stack...");
        for(int primaryFace = 0; primaryFace <= 12; primaryFace++) {
            for(int secondaryFace = primaryFace; secondaryFace <= 12; secondaryFace++) {
                if(primaryFace == this.roundNum && secondaryFace == this.roundNum) {
                    continue;
                }

                this.drawStack.add(new Tile(primaryFace, secondaryFace));
            }
        }

        Collections.shuffle(drawStack);
    }

    private void initializePlayersHands() {
        System.out.println("Initializing Players' Hands...");
        for(Player player : this.players) {
            Hand hand = generateHand(handSize);
            player.setHand(hand);
        }
    }

    private Hand generateHand(int handSize) {
        Hand hand = new Hand();

        for(int i = 0; i < handSize; i++) {
            hand.add(this.drawStack.pop());
        }

        return hand;
    }

    private void intializeBoard() {
        System.out.println("Initializing Board...");
        Train[] trains = new Train[this.players.length + 1];

        for(int i = 0; i < trains.length; i++) {
            trains[i] = new Train(this.roundNum, i);
        }

        trains[0].setIsPublic(true);

        this.board = new Board(trains);
    }

    private boolean hasWinState() {
        for(Player player : this.players) {
            if(player.hasWinState()) {
                return true;
            }
        }

        return false;
    }

    private void playRound() {
        while(!hasWinState()) {
            System.out.println("Board: " + this.board.toString());
            for(Player player : players) {
                System.out.println(player.toString());
                makePlay(player);

                if(player.hasWinState()) {
                    break;
                }
            }
        }

        updateScores();
    }

    private PlayerMove makePlay(Player player) {
        int playerIndex = player.getPlayerIndex();
        ArrayList<PlayerMove> possibleMoves = generatePossiblePlayerMoves(playerIndex);

        System.out.println(possibleMoves);

        PlayerMove playerMove = player.chooseMove(this.board, possibleMoves);

        if(playerMove == null) {
            System.out.println("draw");
            player.draw(drawStack);
            this.board.setIsPublic(playerIndex, true);
        } else {
            System.out.println(String.format("play %s", playerMove.toString()));
            player.remove(playerMove.tile);
            board.makeMove(playerMove);

            if(this.board.isPublic(playerIndex)) {
                this.board.setIsPublic(playerIndex, false);
            }
        }

        return playerMove;
    }

    private ArrayList<PlayerMove> generatePossiblePlayerMoves(int playerIndex) {
        ArrayList<PlayerMove> possiblePlayerMoves = new ArrayList<>();
        Hand hand = this.players[playerIndex].getHand();

        for(Tile tile : hand.getTiles()) {
            possiblePlayerMoves.addAll(Arrays.asList(this.board.generateMoves(tile, playerIndex)));
        }

        return possiblePlayerMoves;
    }

    

    private void updateScores() {
        for(Player player : this.players) {
            scores[player.getPlayerIndex()] = player.getScore();
        }
    }

    private final int calculateHandSize() {
        int numPlayers = this.players.length;

        if(numPlayers >= 2 && numPlayers <= 4) {
            return 15;
        } else if(numPlayers >= 5 && numPlayers <= 6) {
            return 12;
        } else {
            return 10;
        }
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
    }
}
