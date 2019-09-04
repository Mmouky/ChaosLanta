package fr.mmouky.bot.JDA;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class JDAManager {

    private static JDA jda = buildJDA();

    public static JDA getJDA(){
        return jda;
    }

    private static JDA buildJDA(){
        try {
            return new JDABuilder(AccountType.BOT).setToken("NjE4NzQzMjYxNjI3OTQwODc1.XW_zRQ.8LoHPTZc28s1cFYh206R2LI_zc0").build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return null;
    }

}
