package fr.mmouky.bot.game;

import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;

public class KohLanta {

    private static ArrayList<User> users = new ArrayList<>();
    public static GameState state = GameState.STOPED;

    public static void join(User user){
        users.add(user);
    }

    public static ArrayList<User> getMembers(){
        return users;
    }

}
