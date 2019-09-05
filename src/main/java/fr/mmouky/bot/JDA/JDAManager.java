package fr.mmouky.bot.JDA;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.security.auth.login.LoginException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class JDAManager {

    private static JDA jda = buildJDA();

    public static JDA getJDA(){
        return jda;
    }

    private static JDA buildJDA(){
        try {

            Document botFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("res/bot.xml").getPath());
            Element racine = botFile.getDocumentElement();
            NodeList nodes = racine.getChildNodes();

            for(int i = 0;i<nodes.getLength();i++){
                if(nodes.item(i).getNodeName().equalsIgnoreCase("token")){
                    return new JDABuilder(AccountType.BOT).setToken(nodes.item(i).getTextContent()).build();
                }
            }
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
