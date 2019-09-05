package fr.mmouky.bot;

import fr.mmouky.bot.JDA.JDAManager;
import fr.mmouky.bot.kohlanta.games.Games;

public class Main {


    public static void main(String[] args) {
        JDAManager.getJDA();
        JDAManager.getJDA().addEventListener(new Commands());
    }

}
