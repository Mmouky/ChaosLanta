package fr.mmouky.bot.kohlanta.games;

import fr.mmouky.bot.kohlanta.KohLanta;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Games {

    private static final String PATH = "res/games.xml";

    public static EmbedBuilder choseGame() {
        int r = new Random().nextInt(getList().size());

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Une épreuve a été tirée :");
        embed.addField(getList().get(r).getLabel(), getList().get(r).getUrl(), false);

        StringBuilder builder = new StringBuilder();

        for (User user : KohLanta.getMembers()) {
            builder.append(user.getAsMention() + " ");
        }

        embed.addField("Liste des participants", builder.toString(), false);
        embed.setColor(Color.ORANGE);

        return embed;
    }

    public static EmbedBuilder showList() {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Liste des jeux disponibles :");
        for (Game g : getList()) {
            embed.addField(g.getLabel(), g.getUrl(), false);
        }
        embed.setColor(Color.ORANGE);
        return embed;
    }

    public static void addGame(String url, String label) {
        try {
            Document file = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(PATH).getPath());

            Element newGame = file.createElement("game");
            newGame.setAttribute("url", url);
            newGame.appendChild(file.createTextNode(label));

            file.getDocumentElement().appendChild(newGame);

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(file), new StreamResult(new File(PATH)));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Game> getList() {
        try {
            Document file = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(PATH).getPath());
            Element racine = file.getDocumentElement();
            NodeList nodes = racine.getChildNodes();

            ArrayList<Game> temp = new ArrayList<Game>();

            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getNodeName().equalsIgnoreCase("game")) {
                    temp.add(new Game(nodes.item(i).getTextContent(), ((Element) nodes.item(i)).getAttribute("url")));
                }
            }
            return temp;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;

    }


}
