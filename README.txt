322CA - Nioață Alexandra
November 2022

Homework 1 - GwentStone

The program is an implementantion of a card video game, which is a combination
between Gwent and Hearthstone.
The input data is being loaded from test file, in JSON format, and transformed
into objects. There are a few commands that needs to be executed and I will talk
separately about them. The result from each action is displayed in an output file,
in JSON format.
Before reading the actions, various preparations are made, 
for example the set of cards that each of the 2 players will use in
this game is chosen, the hero is chosen, the player who starts, also.

Commands:

1. getPlayerDeck - prints the deck of card of a player given from input

2. getPlayerHero - prints the hero of the player given form the input

3. endPlayerTurn - when a turn is over, preparations are performed, such as 
unfreezing cards, increasing mana, and drawing a new card.

4. placeCard - places the card of the current player on the game table,
if an error occurs a suggestive message will be put in the output

5. getCardsInHand - prints the cards in hand of a player given

6. getPlayerMana -  prints mana of a given player from the input

7. getCardsOnTable - prints the cards from tha games table

8. getEnvironmentCardsInHand -  prints all environment cards from 
the hand of a player with the given index from input

9. getCardAtPosition - prints the card at a given position, if there is no
 card at that position, it will print a suggestive message in output

10. useEnvironmentCard - prints the card at a given position, if there is no
card at that position, it will print a suggestive message in output

11. cardUsesAttack - a card with given coordinates attacks a card with given
coordinates. The attacker card attacks using attackDamage, and the attacked card
will lose health. If the card has 0 health, it will be removed from the table.

12. useAttackHero - card with given coordinates from the table attackes
the hero of the other player. If the hero's health is 0, then the game
will end, and the attacker will win

13. getTotalGamesPlayed - prints in the output the total number of games
played by the 2 players

14. getPlayerOneWins - prints at the output the number of games won by
the first player

15. getPlayerTwoWins - prints at the output the number of games won by
the second player

16. cardUsesAbility - card with given coordinates uses its ability
on another card with given coordinates

17. useHeroAbility - hero of the current players attacks the given row
from the table, using his ability

18. getFrozenCardsOnTable - prints all the frozen cards from the table

19. getPlayerTurn - prints the active player

Each ability and each error that might occur are very well explained on ocw.

Classes:

1. Card:
- contains private variables that represents the attributes of card: 
mana, attackDamage, health, description, colors, name
- two booleans frozen (card is frozen by a special ability) and attacker (the
card has attacked this turn)
- setter and getters
- method setCard, where a card receives attributes from another card

2. Environment:
- used for the special abilities of the enviroments cards: Firestom, Winterfell
and HeartHound.
- extends the Player class
- method useEnvcard, which applies the special ability of the given card

3. Error:
- I wanted to use this class for the error that might occur, but unfortunately, 
I did not have time to finish my homework, due to some personal problems
- instead I use it to verifi different cases like if a row belongs to the 
current player

4. Hero:
- class especially made for the hero cards, extends Card class
- there are methods for the special abilities of the hero (Lord Royce, Empress
Thorina, King Mudface, General Kocioraw)
- method useHeroAbility, verifies the type of Hero Card and applies its
special ability

5. Minion:
- class especially made for the minion cards, extends Card class
- method checkTypeMinion, that checks if the minion has a special ability
or not
- method useAbility, where a minion card with the given coordinates uses
its special ability on a card with the given coordinates

6. Player:
- contains private variables deck, hands and mana, hero and frozen, which 
represents what the player needs in a game
- getters and setters
- method drawCard, that takes a card from the deck and puts it in the hand
- method placeCard, where a card is being placed on the table
- method heroUsesAbility, where the hero's ability of a given player is
being used
- method useEnvCard, where an environment card from the hand of a given player
with the given index, uses it's ability on a given row from the game table


7. Print:
- class that prints every command that is given by the input in the output


Feedback:
- the homework was very interesting, but I feel like the time was too short
for me and there was so much code to write, I mean it took me 3 days just to
pass the first test.
- I loved the fact that every command, and every error were very well explained
- I found very hard the part with the output and the input, because I was new
to JSON format
- I managed to do tests 1, 2, 3, 4, 6, 8, 10 12
