package main;

import fileio.CardInput;

import java.util.ArrayList;

public class Hero extends Card {

    /**
     * <p>
     * takes on the attributes of a card from the input and
     * gives them to an object called Hero
     * @param hero card from the input
     */
    public Hero(final CardInput hero) {
        setName(hero.getName());
        setColors(hero.getColors());
        setDescription(hero.getDescription());
        setMana(hero.getMana());
        setHealth(hero.getHealth());
    }

    /**
     * <p>
     * method that uses the special ability of Lord Royce, to
     * freeze the card with the biggest attack from the given row
     * @param row where the ability is used
     */
    public void lordRoyce(final ArrayList<Card> row) {
        int biggestAttack = 0;
        for (Card card : row) {
            if (card.getAttackDamage() > biggestAttack) {
                biggestAttack = card.getAttackDamage();
            }
        }
        for (Card card : row) {
            if (card.getAttackDamage() == biggestAttack) {
                card.setFrozen(true);
                break;
            }
        }
    }

    /**
     * <p>
     * method that uses the special ability of Empress Thorina, to
     * destroy the card with the biggest health from the given row
     * @param row where the ability is used
     */
    public void empressThorina(final ArrayList<Card> row) {
        int biggestHealth = 0;
        for (Card card : row) {
            if (card.getHealth() > biggestHealth) {
                biggestHealth = card.getHealth();
            }
        }
        for (Card card : row) {
            if (card.getHealth() == biggestHealth) {
                card.setHealth(0);
                break;
            }
        }
    }

    /**
     * <p>
     * method that uses the special ability of King Mudface, to
     * increase the life of the cards from the given row
     * @param row where the ability is used
     */
    public void kingMudface(final ArrayList<Card> row) {
        for (Card card : row) {
            card.setHealth(card.getHealth());
        }
    }

    /**
     * <p>
     * method that uses the special ability of General Kocioraw, to
     * increase the attack of the cards from the given row
     * @param row where the ability is used
     */
    public void generalKocioraw(final ArrayList<Card> row) {
        for (Card card : row) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }

    public Hero() {
    }

    /**
     * <p>
     * method which verifies the type of Hero Card and applies its
     * special ability
     * @param name used to verfify what type of hero card is
     * @param row where the ability is used
     */
    public void useHeroAbility(final String name, final ArrayList<Card> row) {
        if (name.compareTo("Lord Royce") == 0) {
            lordRoyce(row);
        } else if (name.compareTo("Empress Thorina") == 0) {
            empressThorina(row);
        } else if (name.compareTo("King Mudface") == 0) {
            kingMudface(row);
        } else if (name.compareTo("General Kocioraw") == 0) {
            generalKocioraw(row);
        }
    }
}
