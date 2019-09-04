package fr.mmouky.bot;

import fr.mmouky.bot.JDA.JDAManager;
import fr.mmouky.bot.game.GameState;
import fr.mmouky.bot.game.KohLanta;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
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

                if (args[0].equalsIgnoreCase("join")) {
                    if (!KohLanta.getMembers().contains(e.getAuthor())) {
                        KohLanta.join(e.getAuthor());
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + " a rejoint la partie !").queue();
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    EmbedBuilder list = new EmbedBuilder();
                    list.setTitle("Liste des participants :");
                    for (User user : KohLanta.getMembers()) {
                        list.addField(user.getName(), user.getAsMention(), true);
                    }
                    list.setColor(Color.RED);
                    e.getChannel().sendMessage(list.build()).queue();
                } else if (args[0].equalsIgnoreCase("stop")) {
                    if(KohLanta.state == GameState.STARTED) {
                        KohLanta.state = GameState.STOPED;
                        JDAManager.getJDA().getPresence().setActivity(null);
                        JDAManager.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
                        e.getChannel().sendMessage("La partie vient d'être interrompue !").queue();
                    }
                }else if (args[0].equalsIgnoreCase("start")) {
                    if(KohLanta.state == GameState.STOPED){
                        KohLanta.state = GameState.STARTED;
                        JDAManager.getJDA().getPresence().setActivity(Activity.playing("Koh Lanta"));
                        JDAManager.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                        e.getChannel().sendMessage("La partie vient de commencer !").queue();
                    }
                }
            }
        }
    }

}
