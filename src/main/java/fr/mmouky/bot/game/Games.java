package fr.mmouky.bot.game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Random;

public class Games {

    private static final String[] gameList = {"wolfy.fr", "petitbacenligne.net","skribbl.io"};

    public static EmbedBuilder choseGame(){
        int r = new Random().nextInt(gameList.length);

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Epreuve d'immunité");
        embed.setDescription("L'épreuve d'immunité de ce soir");
        embed.addField(gameList[r], gameList[r], false);
        embed.setFooter("Développé par Mmouky");
        embed.setColor(Color.ORANGE);

        return embed;
    }

}
