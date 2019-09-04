package fr.mmouky.bot.kohlanta;

import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;

public class KohLanta {

    private static ArrayList<User> users = new ArrayList<>();
    public static GameState state = GameState.STOPED;
    public static User gameMaster = null;

    public static void join(User user){
        users.add(user);
    }

    public static void leave(User user){
        users.remove(user);
    }


    public static ArrayList<User> getMembers(){
        return users;
    }

}
