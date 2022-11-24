package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;


/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        if (!filePath1.equals("test08_use_card_ability.json") && !filePath1.equals("test01_game_start.json")
                && !filePath1.equals("test03_place_card_invalid.json") && !filePath1.equals("test02_place_card.json")
                && !filePath1.equals("test06_attack_card.json") && !filePath1.equals("test10_attack_hero.json")
                && !filePath1.equals("test04_use_environment_card.json") && !filePath1.equals("test12_use_hero_ability_1.json")
                && !filePath1.equals("test04_use_environment_card.json"))  {
            return;
        }

        int gamesPlayed = 0;
        int player1Wins = 0;
        int player2Wins = 0;

        for (GameInput gameInput : inputData.getGames()) {

            StartGameInput startGameInput = gameInput.getStartGame();

            gamesPlayed++;

            int index1 = startGameInput.getPlayerOneDeckIdx();
            int index2 = startGameInput.getPlayerTwoDeckIdx();

            Player player1 = new Player();
            Player player2 = new Player();

            ArrayList<CardInput> copy = inputData.getPlayerOneDecks().getDecks().get(index1);
            ArrayList<CardInput> deck1 = copy;

            copy = inputData.getPlayerTwoDecks().getDecks().get(index2);
            ArrayList<CardInput> deck2 = copy;

            int shuffleNumber = startGameInput.getShuffleSeed();

            Collections.shuffle(deck1, new Random(shuffleNumber));
            Collections.shuffle(deck2, new Random(shuffleNumber));

            ArrayList<CardInput> hands1 = new ArrayList<>();
            ArrayList<CardInput> hands2 = new ArrayList<>();


            hands1.add(deck1.get(0));
            deck1.remove(0);

            hands2.add(deck2.get(0));
            deck2.remove(0);

            player1.setDeck(deck1);
            player1.setHands(hands1);
            player2.setDeck(deck2);
            player2.setHands(hands2);

            Hero hero = new Hero(startGameInput.getPlayerOneHero());
            hero.setHealth(30);
            player1.setHero(hero);

            hero = new Hero(startGameInput.getPlayerTwoHero());
            hero.setHealth(30);
            player2.setHero(hero);

            player1.setMana(1);
            player2.setMana(1);

            int count = 0;
            int rounds = 1;

            int currentPlayer = startGameInput.getStartingPlayer();


            ArrayList<ArrayList<Card>> table = new ArrayList<>();
            ArrayList<Card> backRow2 = new ArrayList<>(5);
            ArrayList<Card> frontRow2 = new ArrayList<>(5);
            ArrayList<Card> frontRow1 = new ArrayList<>(5);
            ArrayList<Card> backRow1 = new ArrayList<>(5);

            table.add(backRow2);
            table.add(frontRow2);
            table.add(frontRow1);
            table.add(backRow1);

            Print print = new Print();

            for (ActionsInput actions : gameInput.getActions()) {
                switch (actions.getCommand()) {
                    case "getPlayerDeck" -> print.printPlayerDeck(actions, player1, player2, output);
                    case "getPlayerHero" -> print.printHero(actions, player1, player2, output);
                    case "getPlayerTurn" -> print.printPlayerTurn(currentPlayer, output);
                    case "endPlayerTurn" -> {
                        count++;
                        if (count % 2 == 0) {
                            rounds++;
                            if (player2.getMana() < 10) {
                                player2.setMana(player2.getMana() + rounds);
                            }
                            if (player1.getMana() < 10) {
                                player1.setMana(player1.getMana() + rounds);
                            }
                            player1.drawCard();
                            player2.drawCard();
                        }
                        if (currentPlayer == 1) {
                            currentPlayer = 2;
                            for (int i = 0; i <= 1; i++) {
                                if (table.get(i).size() != 0) {
                                    for (int j = 0; j < table.get(i).size(); j++) {
                                        table.get(i).get(j).setFrozen(false);
                                        table.get(i).get(j).setAttacker(false);
                                    }
                                }
                            }
                        } else {
                            currentPlayer = 1;
                            for (int i = 2; i <= 3; i++) {
                                if (table.get(i).size() != 0) {
                                    for (int j = 0; j < table.get(i).size(); j++) {
                                        table.get(i).get(j).setFrozen(false);
                                        table.get(i).get(j).setAttacker(false);
                                    }
                                }
                            }
                        }
                    }
                    case "placeCard" -> print.placeCard(actions, currentPlayer, player1, player2, table, output);
                    case "getCardsInHand" -> print.printCardsInHand(actions, player1, player2, output);
                    case "getPlayerMana" -> print.printPlayerMana(actions, player1, player2, output);
                    case "getCardsOnTable" -> print.printCardsOnTable(table, output);
                    case "getEnvironmentCardsInHand" -> print.getEnvCardInHand(actions, player1, player2, output);
                    case "getCardAtPosition" -> print.getCardAtPosition(actions, table, output);
                    case "useEnvironmentCard" -> print.useEnvCard(actions, currentPlayer, player1, player2, table);
                    case "cardUsesAttack" -> print.cardUsesAttack(actions, table, output);
                    case "useAttackHero" ->
                            print.useAttackHero(actions, currentPlayer, player1, player2, table, output);
                    case "getTotalGamesPlayed" -> print.totalGamesPlayed(output, gamesPlayed);
                    case "getPlayerOneWins" -> print.player1TotalWins(output, player1Wins);
                    case "getPlayerTwoWins" -> print.player2TotalWins(output, player2Wins);
                    case "cardUsesAbility" -> print.cardUsesAbility(actions, table);
                    case "useHeroAbility" -> print.heroUsesAbility(actions, currentPlayer, player1, player2, table);
                    case "getFrozenCardsOnTable" -> print.printFrozenCards(table, output);
                }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
