package fr.mmouky.bot;

import fr.mmouky.bot.JDA.JDAManager;
import fr.mmouky.bot.kohlanta.GameState;
import fr.mmouky.bot.kohlanta.KohLanta;
import fr.mmouky.bot.kohlanta.games.Games;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Commands extends ListenerAdapter {

    public static char prefix = '/';

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String msg = e.getMessage().getContentRaw();

        if (!e.getAuthor().isBot()) {
            if (msg.charAt(0) == prefix) {
                msg = msg.substring(1);

                String[] args = msg.split(" ");

                //e.getGuild().getTextChannelsByName("chaos-lanta", true).get(0).sendMessage("test").queue();
                //e.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("test").queue());

                e.getMessage().delete().queue();

                switch (args[0]) {
                    case "join":
                        if (e.getAuthor() != KohLanta.gameMaster) {
                            if (!KohLanta.getMembers().contains(e.getAuthor())) {
                                KohLanta.join(e.getAuthor());
                                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " rejoint l'aventure !").queue();
                            }
                        }else{
                            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", vous ne pas être maître du jeu et participer à la partie !").queue();
                        }
                        break;
                    case "leave":
                        if (KohLanta.getMembers().contains(e.getAuthor())) {
                            KohLanta.leave(e.getAuthor());
                            e.getChannel().sendMessage(e.getAuthor().getAsMention() + " quitte l'aventure !").queue();
                        }
                        break;
                    case "list":
                        EmbedBuilder list = new EmbedBuilder();
                        list.setTitle("Liste des participants :");
                        for (User user : KohLanta.getMembers()) {
                            list.addField(user.getName(), user.getAsMention(), true);
                        }
                        list.setColor(Color.RED);
                        e.getChannel().sendMessage(list.build()).queue();
                        break;
                    case "start":
                        if (KohLanta.gameMaster != null) {
                            if (KohLanta.state == GameState.STOPED) {
                                KohLanta.state = GameState.STARTED;
                                JDAManager.getJDA().getPresence().setActivity(Activity.playing("Koh Lanta"));
                                JDAManager.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                                e.getChannel().sendMessage("La partie vient de commencer !").queue();
                            }
                        } else {
                            e.getChannel().sendMessage("Vous devez définir un maître du jeu avant de lancer la partie !").queue();
                        }
                        break;
                    case "stop":
                        if (KohLanta.state == GameState.STARTED) {
                            KohLanta.state = GameState.STOPED;
                            JDAManager.getJDA().getPresence().setActivity(null);
                            JDAManager.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
                            e.getChannel().sendMessage("La partie vient d'être interrompue !").queue();
                        }
                        break;
                    case "gm":
                        if (args.length > 1) {
                            for (Member user : e.getGuild().getMembers()) {
                                if (user.getAsMention().equalsIgnoreCase(args[1])) {
                                    if (KohLanta.getMembers().contains(user.getUser())) {
                                        e.getChannel().sendMessage(user.getAsMention() + " ne peut pas être maître du jeu et participer à la partie !").queue();
                                        return;
                                    } else {
                                        KohLanta.gameMaster = user.getUser();
                                        e.getChannel().sendMessage("Le maître du jeu a été défini sur : " + user.getAsMention()).queue();
                                        return;
                                    }
                                }
                            }
                            e.getChannel().sendMessage("Le tag " + args[1] + " n'a pas été trouvé !").queue();
                        } else {
                            if (KohLanta.gameMaster == null) {
                                e.getChannel().sendMessage("Le maître du jeu n'a pas encore été défini !").queue();
                            } else {
                                e.getChannel().sendMessage(KohLanta.gameMaster.getAsMention() + " est le maître du jeu !").queue();
                            }
                        }
                        break;
                    case "game":
                        if(args.length == 1){

                        }else{
                            if(args[1].equalsIgnoreCase("list")){
                                e.getChannel().sendMessage(Games.list().build()).queue();
                            }else if(args[1].equalsIgnoreCase("play")){
                                e.getChannel().sendMessage(Games.choseGame().build()).queue();
                            }
                        }
                        break;
                    case "votestart":
                        for(User user : KohLanta.getMembers()) {
                            user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("test").queue());
                        }
                        break;
                    case "help":
                        EmbedBuilder help = new EmbedBuilder();
                        help.setTitle("Liste des commandes :");
                        help.addField(prefix + "join", "Rejoindre la partie", false);
                        help.addField(prefix + "leave", "Quitter la partie", false);
                        help.addField(prefix + "list", "Afficher la liste des participants", false);
                        help.addField(prefix + "start", "Démarrer la partie", false);
                        help.addField(prefix + "stop", "Arrêter la partie", false);
                        help.addField(prefix + "gm", "Définir le maître du jeu", false);

                        help.setColor(Color.GREEN);
                        e.getChannel().sendMessage(help.build()).queue();
                        break;
                    default:
                        e.getChannel().sendMessage("Cette commande n'existe pas, pour consulter la liste des commandes : /help").queue();
                        break;
                }
            }
        }
    }

}
