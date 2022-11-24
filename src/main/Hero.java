package main;

import fileio.CardInput;

import java.util.ArrayList;

public class Hero extends Card{

    public Hero(CardInput hero) {
        setName(hero.getName());
        setColors(hero.getColors());
        setDescription(hero.getDescription());
        setMana(hero.getMana());
        setHealth(hero.getHealth());
    }

    public void lordRoyce(ArrayList<Card> row) {
        int biggestAttack = 0;
        for (Card card : row)
            if (card.getAttackDamage() > biggestAttack)
                biggestAttack = card.getAttackDamage();
        for (Card card : row)
            if (card.getAttackDamage() == biggestAttack) {
                card.setFrozen(true);
                break;
            }
    }

    public void empressThorina(ArrayList<Card> row) {
        int biggestHealth = 0;
        for (Card card : row)
            if (card.getHealth() > biggestHealth)
                biggestHealth = card.getHealth();
        for (Card card : row)
            if (card.getHealth() == biggestHealth) {
                card.setHealth(0);
                break;
            }
    }

    public void kingMudface(ArrayList<Card> row) {
        for (Card card : row) {
            card.setHealth(card.getHealth());
        }
    }

    public void generalKocioraw(ArrayList<Card> row) {
        for (Card card : row) card.setAttackDamage(card.getAttackDamage() + 1);
    }

    public Hero() {
    }

    public void useHeroAbility(String name, ArrayList<Card> row) {
        if(name.compareTo("Lord Royce") == 0) {
            lordRoyce(row);
        } else if(name.compareTo("Empress Thorina") == 0) {
            empressThorina(row);
        } else if(name.compareTo("King Mudface") == 0) {
            kingMudface(row);
        } else if(name.compareTo("General Kocioraw") == 0) {
            generalKocioraw(row);
        }
    }
}
