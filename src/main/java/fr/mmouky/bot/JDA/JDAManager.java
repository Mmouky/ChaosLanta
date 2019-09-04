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
<<<<<<< HEAD
            return new JDABuilder(AccountType.BOT).setToken("NjE4OTA2MDc0OTM3ODE5MTM4.XXAkSA.o3mEwZXcyQWU9lfkOqMAj2dyBZY").build();
=======
            return new JDABuilder(AccountType.BOT).setToken("NjE4OTA2MDc0OTM3ODE5MTM4.XXAjGw.F6MdsHKM7K5M2WkbgHQ1nJ0UzrU").build();
>>>>>>> 901f31197f11dbb807541cad50b25177e41952f6
        } catch (LoginException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}
