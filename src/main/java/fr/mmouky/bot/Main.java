package fr.mmouky.bot;

import fr.mmouky.bot.JDA.JDAManager;
import net.dv8tion.jda.api.entities.Activity;

public class Main {


    public static void main(String[] args) {
        JDAManager.getJDA();
        JDAManager.getJDA().addEventListener(new Commands());
    }

}
