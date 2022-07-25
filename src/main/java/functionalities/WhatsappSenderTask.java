package functionalities;

import customDataObjects.MatchToDisplay;
import whatsappSelenium.SeleniumComponent;
import whatsappSelenium.SeleniumKeyCommands;
import wotAPI.responseJsonConverter.ScheduledMatch;

import java.util.List;

public class WhatsappSenderTask implements Runnable {

    private final String chatTitle;
    private final List<MatchToDisplay> matchesToPost;

    public WhatsappSenderTask(String chatTitle, List<MatchToDisplay> matchesToPost) {
        this.chatTitle = chatTitle;
        this.matchesToPost = matchesToPost;
    }

    @Override
    public void run() {

        StringBuilder sb = new StringBuilder();
        sb.append("Das sind unsere Matches heute: (das ist ein Test die daten sind gemocked und nicht aktuell) " + SeleniumKeyCommands.newLine + " ");
        matchesToPost.forEach(matchToDisplay -> sb.append("Uhrzeit: " + matchToDisplay.getDate() +
                ", Gegner: " + matchToDisplay.getCompetitorClan() +
                " auf " + matchToDisplay.getMapName() +
                " um Provinz " + matchToDisplay.getProvinceName() +
                " " + SeleniumKeyCommands.newLine + " "));
        String message = sb.toString();
        System.out.println(message);
        SeleniumComponent whatsappSender = new SeleniumComponent();

        whatsappSender.sendMessageToWhatsappGroup(chatTitle, message);
    }

}

