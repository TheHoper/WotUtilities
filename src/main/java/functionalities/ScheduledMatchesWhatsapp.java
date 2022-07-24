package functionalities;

import customDataObjects.MatchToDisplay;
import whatsappSelenium.SeleniumComponent;
import wotAPI.WotApiConnector;
import wotAPI.responseJsonConverter.ScheduledMatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduledMatchesWhatsapp {
    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {
        final String hamonClanID = "500218963";
        WotApiConnector con = new WotApiConnector();
        List<ScheduledMatch> schduledMatches = con.getScheduledMatches(hamonClanID);
        List<MatchToDisplay> matchlist = new ArrayList<>();

        for (ScheduledMatch match : schduledMatches) {
            MatchToDisplay mtd = getMoreInfo(match, con);
            matchlist.add(mtd);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Das sind unsere Matches heute (das ist ein Test die daten sind gemocked und nicht aktuell) \r");
        matchlist.forEach(matchToDisplay -> sb.append("wir spielen gegen: " + matchToDisplay.getCompetitorClan() +" auf "+matchToDisplay.getMapName()+ " um Provinz "+matchToDisplay.getProvinceName()+" um so viel Uhr "+ matchToDisplay.getDate() +"\r"));
        String message = sb.toString();
        SeleniumComponent whatsappSender = new SeleniumComponent();
        whatsappSender.sendMessageToWhatsappGroup("Kino",message);

    }

    private static MatchToDisplay getMoreInfo(ScheduledMatch match, WotApiConnector con) throws InterruptedException, IOException, URISyntaxException {
        String competitorClanName = con.getClanNameFromID(String.valueOf(match.getCompetitorID()));
        String provinceName = match.getProvinceName();
        String mapName = con.getMapNameFromProvinceID(match.getFrondID(), match.getProvinceID());
        Date date = new Date(match.getScheduledTime() * 1000);
        return new MatchToDisplay(competitorClanName, date, provinceName, mapName);
    }

}
