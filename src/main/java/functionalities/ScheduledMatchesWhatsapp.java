package functionalities;

import customDataObjects.MatchToDisplay;
import whatsappSelenium.SeleniumComponent;
import whatsappSelenium.SeleniumKeyCommands;
import wotAPI.WotApiConnector;
import wotAPI.responseJsonConverter.ScheduledMatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class ScheduledMatchesWhatsapp {


    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {
        //TODO remove matches that are done
        List<ScheduledMatch> previousMatches = new ArrayList<>();
        System.out.println("Service has started");
        while (true) {
            final String hamonClanID = "500218963";

            WotApiConnector con = new WotApiConnector();

            List<ScheduledMatch> newMatches = con.checkForNewMatches(hamonClanID, previousMatches);

            if (!newMatches.isEmpty()) {
                List<MatchToDisplay> matchlist = new ArrayList<>();
                for (ScheduledMatch match : newMatches) {
                    MatchToDisplay mtd = getMoreInfo(match, con);
                    matchlist.add(mtd);
                }
                System.out.println("new matches to send: " + matchlist);

                WhatsappSenderTask task = new WhatsappSenderTask("Kino", matchlist);
                Thread t = new Thread(task);
                t.start();

            } else {
                System.out.println("no new matches to send ");
            }

            previousMatches.addAll(newMatches);
            System.out.println("sleeping now ");
            Thread.sleep(120000);
        }
    }

    private static MatchToDisplay getMoreInfo(ScheduledMatch match, WotApiConnector con) throws InterruptedException, IOException, URISyntaxException {
        String competitorClanName = con.getClanNameFromID(String.valueOf(match.getCompetitorID()));
        String provinceName = match.getProvinceName();
        String mapName = con.getMapNameFromProvinceID(match.getFrondID(), match.getProvinceID());
        Date date = new Date(match.getScheduledTime() * 1000);
        return new MatchToDisplay(competitorClanName, date, provinceName, mapName);
    }

}
