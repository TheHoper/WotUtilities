package functionalities;

import customDataObjects.MatchToDisplay;
import whatsappSelenium.SeleniumComponent;
import whatsappSelenium.SeleniumKeyCommands;

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

        sb.append(" Das sind unsere Matches heute: " + SeleniumKeyCommands.newLine + " ");
        matchesToPost.forEach(matchToDisplay -> sb.append(" Match " + matchToDisplay.getMatchNumber() + " " + SeleniumKeyCommands.newLine + " " +
                "Zeit: " + matchToDisplay.getDate() + " " + SeleniumKeyCommands.newLine + " " +
                "Gegner: " + matchToDisplay.getCompetitorClan() + " " + SeleniumKeyCommands.newLine + " " +
                "Map: " + matchToDisplay.getMapName() + " " + " " + " " + SeleniumKeyCommands.newLine + " " +
                "Provinz: " + matchToDisplay.getProvinceName() +
                " " + SeleniumKeyCommands.newLine + " " + SeleniumKeyCommands.newLine + " "));

        String message = sb.toString();
        System.out.println(message);
        SeleniumComponent whatsappSender = new SeleniumComponent();

        whatsappSender.sendMessageToWhatsappGroup(chatTitle, message);
    }

}

