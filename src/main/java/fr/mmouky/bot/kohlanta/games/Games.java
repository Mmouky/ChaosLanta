package fr.mmouky.bot.kohlanta.games;

import fr.mmouky.bot.kohlanta.KohLanta;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Random;

public class Games {

    private static final Game[] gameList = {new Game("Loup-Garou","http://www.wolfy.fr"), new Game("Petit Bac","http://www.petitbacenligne.net"),new Game("Skribbl.io","http://www.skribbl.io")};

    public static EmbedBuilder choseGame(){
        int r = new Random().nextInt(gameList.length);

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Une épreuve a été tirée :");
        embed.addField(gameList[r].getLabel(), gameList[r].getUrl(), false);

        StringBuilder builder = new StringBuilder();

        for(User user : KohLanta.getMembers()){
            builder.append(user.getAsMention()+" ");
        }

        embed.addField("Liste des participants", builder.toString(), false);
        embed.setColor(Color.ORANGE);

        return embed;
    }

    public static EmbedBuilder list(){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Liste des jeux disponibles :");
        for(Game g : gameList){
            embed.addField(g.getLabel(), g.getUrl(), false);
        }
        embed.setColor(Color.ORANGE);
        return embed;
    }

}
