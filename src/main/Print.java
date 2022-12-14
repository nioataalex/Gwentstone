package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.CardInput;

import java.util.ArrayList;


public class Print {

    public Print() {
    }

    private Error error;
    private ObjectMapper mapper;

    private  ObjectNode node1;

    private ObjectNode node2;

    private ArrayNode arrayNodeColors;

    private ArrayNode arrayNode;

    private ArrayNode arrayNode1;

    /**
     * <p>
     * method that prints the deck of card of a player given
     * from input
     * @param actions action given from the input
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param output arrayNode where the answer is exported
     */
    public void printPlayerDeck(final ActionsInput actions,
                                final Player player1,
                                final Player player2,
                                final ArrayNode output) {
        error = new Error();
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerDeck");
        node1.put("playerIdx", actions.getPlayerIdx());
        ArrayList<CardInput> cards;
        if (actions.getPlayerIdx() == 1) {
            cards = player1.getDeck();
        } else {
            cards = player2.getDeck();
        }
        arrayNode = mapper.createArrayNode();
        for (CardInput card : cards) {
            node2 = mapper.createObjectNode();
            node2.put("mana", card.getMana());
            if (error.checkNotEnvironment(card.getName()) == 1) {
                node2.put("attackDamage", card.getAttackDamage());
            }
            if (card.getHealth() != 0) {
                node2.put("health", card.getHealth());
            }
            node2.put("description", card.getDescription());
            arrayNodeColors = mapper.createArrayNode();
            for (int j = 0; j < card.getColors().size(); j++) {
                arrayNodeColors.add(card.getColors().get(j));
            }
            node2.put("colors", arrayNodeColors);
            node2.put("name", card.getName());
            arrayNode.add(node2);
        }
        node1.set("output", arrayNode);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints the hero of the player given form the input
     * @param actions action given from the input
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param output arrayNode where the answer is exported
     */
    public void printHero(final ActionsInput actions,
                          final Player player1, final Player player2,
                          final ArrayNode output) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        new Hero();
        Hero heroCard;
        if (actions.getPlayerIdx() == 1) {
            heroCard = player1.getHero();
        } else {
            heroCard = player2.getHero();
        }
        node1.put("command", "getPlayerHero");
        arrayNode = mapper.createArrayNode();
        node2 = mapper.createObjectNode();
        arrayNodeColors = mapper.createArrayNode();
        for (int j = 0; j < heroCard.getColors().size(); j++) {
            arrayNodeColors.add(heroCard.getColors().get(j));
        }
        node2.put("colors", arrayNodeColors);
        node2.put("description", heroCard.getDescription());
        node2.put("name", heroCard.getName());
        node2.put("mana", heroCard.getMana());
        node2.put("health", heroCard.getHealth());
        node1.put("output", node2);
        node1.put("playerIdx", actions.getPlayerIdx());
        output.add(node1);
    }

    /**
     * <p>
     * prints the active player
     * @param currentPlayer active player
     * @param output arrayNode where the answer is exported
     */
    public void printPlayerTurn(final int currentPlayer, final ArrayNode output) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerTurn");
        node1.put("output", currentPlayer);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints the cards in hand of a player given
     * from input
     * @param actions action given from the input
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param output arrayNode where the answer is exported
     */
    public void printCardsInHand(final ActionsInput actions, final Player player1,
                                 final Player player2,
                                 final ArrayNode output) {
        error = new Error();
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getCardsInHand");
        int indexplayer = actions.getPlayerIdx();
        node1.put("playerIdx", indexplayer);
        arrayNode = mapper.createArrayNode();
        ArrayList<CardInput> hands = new ArrayList<>();
        if (indexplayer == 1) {
            hands = player1.getHands();
        } else if (indexplayer == 2) {
            hands = player2.getHands();
        }
        for (CardInput hand : hands) {
            node2 = mapper.createObjectNode();
            node2.put("mana", hand.getMana());
            if (error.checkNotEnvironment(hand.getName()) == 1) {
                node2.put("attackDamage", hand.getAttackDamage());
            }
            arrayNodeColors = mapper.createArrayNode();
            for (int j = 0; j < hand.getColors().size(); j++) {
                arrayNodeColors.add(hand.getColors().get(j));
            }
            node2.put("colors", arrayNodeColors);
            if (error.checkNotEnvironment(hand.getName()) == 1) {
                node2.put("health", hand.getHealth());
            }

            node2.put("description", hand.getDescription());
            node2.put("name", hand.getName());
            arrayNode.add(node2);
        }
        node1.set("output", arrayNode);
        output.add(node1);
    }

    /**
     /**
     * <p>
     * method that places the card of the current player on the game table,
     * if an error occurs a suggestive message will be put in the output
     * @param actions action given from the input
     * @param currentPlayer active player
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void placeCard(final ActionsInput actions,
                          final int currentPlayer, final Player player1,
                          final Player player2, final ArrayList<ArrayList<Card>> table,
                          final ArrayNode output) {
        error = new Error();
        mapper = new ObjectMapper();
        int index = actions.getHandIdx();
        new Card();
        Card cardToPlace;
        node1 = mapper.createObjectNode();
        if (currentPlayer == 1) {
            cardToPlace = player1.getHands().get(index);
            if (error.checkPlaceCard(cardToPlace.getMana(),
                    player1.getMana(), player1.getHands().get(index).getName())) {
                if (table.get(3).size() < 5 || table.get(2).size() < 5) {
                    if (error.checkRow(cardToPlace, table, 2, 3)) {
                        player1.placeCard(cardToPlace, table, 2, 3);
                        player1.setMana(player1.getMana() - cardToPlace.getMana());
                    } else {
                        mapper = new ObjectMapper();
                        node1 = mapper.createObjectNode();
                        node1.put("command", "placeCard");
                        //daca are eroare daca nu break;
                        node1.put("handIdx", index);
                        node1.put("error", "Cannot place card on table since row is full.");
                        output.add(node1);
                    }
                }
            } else if (error.checkNotEnvironment(player1.getHands().get(index).getName()) == 0) {
                mapper = new ObjectMapper();
                node1 = mapper.createObjectNode();
                node1.put("command", "placeCard");

                node1.put("handIdx", index);
                node1.put("error", "Cannot place environment card on table.");
                output.add(node1);
            } else if (cardToPlace.getMana() > player1.getMana()) {
                mapper = new ObjectMapper();
                node1 = mapper.createObjectNode();
                node1.put("command", "placeCard");
                //daca are eroare daca nu break;
                node1.put("handIdx", index);
                node1.put("error", "Not enough mana to place card on table.");
                output.add(node1);
            }
        } else if (currentPlayer == 2) {
            cardToPlace = player2.getHands().get(index);
            if (error.checkPlaceCard(cardToPlace.getMana(),
                    player2.getMana(), player2.getHands().get(index).getName())) {
                if (table.get(0).size() < 5 || table.get(1).size() < 5) {
                    if (error.checkRow(cardToPlace, table, 1, 0)) {
                        player2.placeCard(cardToPlace, table, 1, 0);
                        player2.setMana(player2.getMana() - cardToPlace.getMana());
                    } else {
                        mapper = new ObjectMapper();
                        node1 = mapper.createObjectNode();
                        node1.put("command", "placeCard");
                        //daca are eroare daca nu break;
                        node1.put("handIdx", index);
                        node1.put("error", "Cannot place card on table since row is full.");
                        output.add(node1);
                    }
                }
            } else if (error.checkNotEnvironment(player2.getHands().get(index).getName()) == 0) {
                mapper = new ObjectMapper();
                node1 = mapper.createObjectNode();
                node1.put("command", "placeCard");

                node1.put("handIdx", index);
                node1.put("error", "Cannot place environment card on table.");
                output.add(node1);
            } else if (cardToPlace.getMana() > player2.getMana()) {
                mapper = new ObjectMapper();
                node1 = mapper.createObjectNode();
                node1.put("command", "placeCard");
                //daca are eroare daca nu break;
                node1.put("handIdx", index);
                node1.put("error", "Not enough mana to place card on table.");
                output.add(node1);
            }
        }
    }

    /**
     * <p>
     * method that prints mana of a given player from the input
     * @param actions action given from the input
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param output arrayNode where the answer is exported
     */
    public void printPlayerMana(final ActionsInput actions, final Player player1,
                                final Player player2,
                                final ArrayNode output) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerMana");
        node1.put("playerIdx", actions.getPlayerIdx());
        new Player();
        Player player;
        if (actions.getPlayerIdx() == 1) {
            player = player1;
        } else {
            player = player2;
        }
        node1.put("output", player.getMana());
        output.add(node1);
    }

    /**
     * <p>
     * prints tha cards from tha games table
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void printCardsOnTable(final ArrayList<ArrayList<Card>> table,
                                  final ArrayNode output) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getCardsOnTable");
        arrayNode = mapper.createArrayNode();
        for (ArrayList<Card> cards : table) {
            arrayNode1 = mapper.createArrayNode();
            for (Card card : cards) {
                if (card.getHealth() != 0) {
                    node2 = mapper.createObjectNode();
                    node2.put("mana", card.getMana());
                    node2.put("attackDamage", card.getAttackDamage());
                    arrayNodeColors = mapper.createArrayNode();
                    for (int k = 0; k < card.getColors().size(); k++) {
                        arrayNodeColors.add(card.getColors().get(k));
                    }
                    node2.put("colors", arrayNodeColors);
                    node2.put("health", card.getHealth());
                    node2.put("description", card.getDescription());
                    node2.put("name", card.getName());
                    arrayNode1.add(node2);
                }
            }
            arrayNode.add(arrayNode1);
        }
        node1.set("output", arrayNode);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints all environment cards from the hand of
     * a player with the given index from input
     * @param actions action given from the input
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param output arrayNode where the answer is exported
     */
    public void getEnvCardInHand(final ActionsInput actions,
                                 final Player player1,
                                 final Player player2,
                                 final ArrayNode output) {
        int index = actions.getPlayerIdx();
        error = new Error();
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getEnvironmentCardsInHand");
        node1.put("playerIdx", index);
        arrayNode = mapper.createArrayNode();
        ArrayList<CardInput> hands = new ArrayList<>();
        if (index == 1) {
            hands = player1.getHands();
        } else if (index == 2) {
            hands = player2.getHands();
        }
        for (CardInput hand : hands) {
            if (error.checkNotEnvironment(hand.getName()) == 0) {
                node2 = mapper.createObjectNode();
                node2.put("mana", hand.getMana());
                node2.put("description", hand.getDescription());
                arrayNodeColors = mapper.createArrayNode();
                for (int j = 0; j < hand.getColors().size(); j++) {
                    arrayNodeColors.add(hand.getColors().get(j));
                }
                node2.put("colors", arrayNodeColors);
                node2.put("name", hand.getName());
                arrayNode.add(node2);
            }
        }
        node1.set("output", arrayNode);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints the card at a given position, if there is no
     * card at that position, it will print a suggestive message in output
     * @param actions action given from the input
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void getCardAtPosition(final ActionsInput actions,
                                  final ArrayList<ArrayList<Card>> table,
                                  final ArrayNode output) {
        int x = actions.getX();
        int y = actions.getY();
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        error = new Error();
        node1.put("command", "getCardAtPosition");
        node1.put("x", x);
        node1.put("y", y);
        if ((table.size() < x || (table.size() > x && table.get(x).size() < y))) {
            node1.put("output", "No card available at that position.");
        } else  {
            node2 = mapper.createObjectNode();
            if (error.checkNotEnvironment(table.get(x).get(y).getName()) == 1) {
                node2.put("attackDamage", table.get(x).get(y).getAttackDamage());
            }
            arrayNodeColors = mapper.createArrayNode();
            for (int j = 0; j < table.get(x).get(y).getColors().size(); j++) {
                arrayNodeColors.add(table.get(x).get(y).getColors().get(j));
            }
            node2.put("colors", arrayNodeColors);
            node2.put("description", table.get(x).get(y).getDescription());
            if (table.get(x).get(y).getHealth() != 0) {
                node2.put("health", table.get(x).get(y).getHealth());
            }
            node2.put("mana", table.get(x).get(y).getMana());
            node2.put("name", table.get(x).get(y).getName());
            node1.set("output", node2);
        }
        output.add(node1);
    }

    /**
     * <p>
     * method that takes an environment card from the given player,
     * which is found at a given index, and uses its ability to
     * a given row from the table
     * @param actions action given from the input
     * @param currentPlayer active player
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param table game table
     */
    public void useEnvCard(final ActionsInput actions, final int currentPlayer,
                           final Player player1, final Player player2,
                           final ArrayList<ArrayList<Card>> table) {
        int index = actions.getHandIdx();
        int affectedRow = actions.getAffectedRow();
        Environment effects = new Environment();
        new CardInput();
        if (currentPlayer == 1) {
            player1.useEnvCard(index, player1, effects, table, affectedRow);
        }
        if (currentPlayer == 2) {
            player2.useEnvCard(index, player2, effects, table, affectedRow);
        }
    }

    /**
     * <p>
     * method where a card with given coordinates attacks a card with given
     * coordinates. The attacker card attacks using attackDamage,
     * and the attacked card will lose health. If the card has 0 health, it
     * will be removed from the table
     * @param actions action given from the input
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void cardUsesAttack(final  ActionsInput actions,
                               final ArrayList<ArrayList<Card>> table,
                               final ArrayNode output) {
        error = new Error();
        int xAttacker = actions.getCardAttacker().getX();
        int yAttacker = actions.getCardAttacker().getY();

        int xAttacked = actions.getCardAttacked().getX();
        int yAttacked = actions.getCardAttacked().getY();

        Card cardAttacker = table.get(xAttacker).get(yAttacker);
        Card cardAttacked = table.get(xAttacked).get(yAttacked);

        if (error.checkSamePlayer(xAttacker, xAttacked)) {
            mapper = new ObjectMapper();
            node1 = mapper.createObjectNode();
            node1.put("command", "cardUsesAttack");
            node1.put("error", "Attacked card does not belong to the enemy.");
            output.add(node1);
        } else if (table.get(xAttacker).get(yAttacker).isFrozen()) {
            mapper = new ObjectMapper();
            node1 = mapper.createObjectNode();
            node1.put("command", "cardUsesAttack");
            node1.put("error", "Attacker card is frozen.");
            output.add(node1);
        } else if (table.get(xAttacker).get(yAttacker).isAttacker()) {
            mapper = new ObjectMapper();
            node1 = mapper.createObjectNode();
            node1.put("command", "cardUsesAttack");
            node1.put("error", "Attacker card has already attacked this turn.");
            output.add(node1);
        } else {
            cardAttacked.setHealth(cardAttacked.getHealth() - cardAttacker.getAttackDamage());
            if (cardAttacked.getHealth() <= 0) {
                table.remove(cardAttacked);
            }
            table.get(xAttacker).get(yAttacker).setAttacker(true);
        }
    }

    /**
     * <p>
     * method that prints in the output the total number of games
     * played by the 2 players
     * @param output arrayNode where the answer is exported
     * @param gamesPlayed  games played
     */
    public void totalGamesPlayed(final ArrayNode output, final int gamesPlayed) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getTotalGamesPlayed");
        node1.put("output", gamesPlayed);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints at the output the number of games won by
     * the first player
     * @param output arrayNode where the answer is exported
     * @param player1Wins number of games won by
     *       the first player
     */
    public  void player1TotalWins(final ArrayNode output, final int player1Wins) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerOneWins");
        node1.put("output", player1Wins);
        output.add(node1);
    }

    /**
     * <p>
     * method that prints at the output the number of games won by
     * the second player
     * @param output arrayNode where the answer is exported
     * @param player2Wins number of games won by
     *       the second player
     */
    public void player2TotalWins(final ArrayNode output, final int player2Wins) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getPlayerOneWins");
        node1.put("output", player2Wins);
        output.add(node1);
    }

    /**
     * <p>
     * method where card with given coordinates from the table attackes
     * the hero of the other player. If the hero's health is 0, then the game
     * will end, and the attacker will win
     * @param actions action given from the input
     * @param currentPlayer active player
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void useAttackHero(final ActionsInput actions, final int currentPlayer,
                              final Player player1, final Player player2,
                              final ArrayList<ArrayList<Card>> table,
                              final ArrayNode output) {
        int xAttacker = actions.getCardAttacker().getX();
        int yAttacker = actions.getCardAttacker().getY();
        Card cardAttacker =  table.get(xAttacker).get(yAttacker);
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        if (currentPlayer == 2) {
            player1.getHero().setHealth(player1.getHero().getHealth()
                    - cardAttacker.getAttackDamage());
            if (player1.getHero().getHealth() <= 0) {
                node1.put("gameEnded", "Player two killed the enemy hero.");
                output.add(node1);
            }
        } else if (currentPlayer == 1) {
            player2.getHero().setHealth(player2.getHero().getHealth()
                    - cardAttacker.getAttackDamage());
            player2.setHero(player2.getHero());
            if (player2.getHero().getHealth() <= 0) {
                node1.put("gameEnded", "Player one killed the enemy hero.");
                output.add(node1);
            }
        }
    }


    /**
     * <p>
     * method where hero of the current players attacks the given row
     * from the table, using his ability
     * @param actions action given from the input
     * @param currentPlayer active player
     * @param player1 object for the first player
     * @param player2 object for the second player
     * @param table game table
     */
    public void heroUsesAbility(final ActionsInput actions, final int currentPlayer,
                                final Player player1, final Player player2,
                                final ArrayList<ArrayList<Card>> table) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "useHeroAbility");
        int affectedRow = actions.getAffectedRow();
        node1.put("command", affectedRow);
        if (currentPlayer == 1) {
            player1.heroUsesAbility(player1, table, affectedRow);
        } else if (currentPlayer == 2) {
            player2.heroUsesAbility(player2, table, affectedRow);
        }
    }

    /**
     * <p>
     * method that prints all the frozen cards from the table
     * @param table game table
     * @param output arrayNode where the answer is exported
     */
    public void printFrozenCards(final ArrayList<ArrayList<Card>> table, final ArrayNode output) {
        mapper = new ObjectMapper();
        node1 = mapper.createObjectNode();
        node1.put("command", "getFrozenCardsOnTable");
        arrayNode = mapper.createArrayNode();
        int existFrozen = 0;
        for (ArrayList<Card> cards : table) {
            for (Card card : cards) {
                if (card.isFrozen()) {
                    existFrozen++;
                }
            }
        }
        if (existFrozen != 0) {
            for (ArrayList<Card> cards : table) {
                arrayNode1 = mapper.createArrayNode();
                for (Card card : cards) {
                    if (card.isFrozen()) {
                        node2 = mapper.createObjectNode();
                        node2.put("mana", card.getMana());
                        node2.put("attackDamage", card.getAttackDamage());
                        arrayNodeColors = mapper.createArrayNode();
                        for (int k = 0; k < card.getColors().size(); k++) {
                            arrayNodeColors.add(card.getColors().get(k));
                        }
                        node2.put("colors", arrayNodeColors);
                        node2.put("health", card.getHealth());
                        node2.put("description", card.getDescription());
                        node2.put("name", card.getName());
                        arrayNode1.add(node2);
                    }
                    arrayNode.add(arrayNode1);
                }
            }
            node1.set("output", arrayNode);
        } else {
            arrayNode1 = mapper.createArrayNode();
            node1.set("output", arrayNode);
        }
        output.add(node1);
    }

    /**
     * <p>
     * method where a card with given coordinates uses its ability
     * on another card with given coordinates
     * @param actions action given from the input
     * @param table game table
     */
    public void cardUsesAbility(final ActionsInput actions,
                                final ArrayList<ArrayList<Card>> table) {
        int xAttacker =  actions.getCardAttacker().getX();
        int yAttacker =  actions.getCardAttacker().getY();
        int xAttacked =  actions.getCardAttacked().getX();
        int yAttacked =  actions.getCardAttacked().getY();

        Minion minion = new Minion();
        minion.useAbility(xAttacker, yAttacker, xAttacked, yAttacked, table);

        if (table.get(xAttacked).get(yAttacked).getHealth() <= 0) {
            table.get(xAttacked).remove(yAttacked);
        }
    }
}
