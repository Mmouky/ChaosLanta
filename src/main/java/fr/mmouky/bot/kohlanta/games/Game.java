package fr.mmouky.bot.kohlanta.games;

public class Game {

    private String label, url;

    public Game(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getLabel(){
        return label;
    }

    public String getUrl(){
        return url;
    }
}
