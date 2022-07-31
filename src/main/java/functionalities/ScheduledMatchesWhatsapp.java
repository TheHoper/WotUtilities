package functionalities;

import customDataObjects.MatchToDisplay;
import wotAPI.WotApiConnector;
import wotAPI.responseJsonConverter.ScheduledMatch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class ScheduledMatchesWhatsapp {


    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {
        //TODO remove matches that are done
        // TODO only add items to list when they are send via whatsapp
        String chatTitle;
        boolean mockData;
        switch (args.length) {
            case 0:
                System.out.println("case 0");

                chatTitle = "Kino";
                mockData = true;
                break;
            case 1:
                System.out.println("case 1");

                chatTitle = args[0];
                mockData = false;
                break;
            case 2:
                System.out.println("case 2");
                chatTitle = args[0];
                mockData = args[1].equals("1");
                break;
            default:
                chatTitle = "Kino";
                mockData = true;
        }
        System.out.println(chatTitle + mockData);

        List<ScheduledMatch> previousMatches = new ArrayList<>();
        final String hamonClanID = "500218963";
        int matchesToday = 1;
        System.out.println("Service has started");
        while (true) {

            WotApiConnector con = new WotApiConnector();

            List<ScheduledMatch> newMatches = con.checkForNewMatches(hamonClanID, previousMatches,mockData);

            if (!newMatches.isEmpty()) {
                List<MatchToDisplay> matchlist = new ArrayList<>();
                for (ScheduledMatch match : newMatches) {
                    MatchToDisplay mtd = getMoreInfo(match, con);
                    mtd.setMatchNumber(matchesToday++);
                    matchlist.add(mtd);
                }
                System.out.println("new matches to send: " + matchlist);

//                WhatsappSenderTask task = new WhatsappSenderTask("HAMON Organisation", matchlist);
                WhatsappSenderTask task = new WhatsappSenderTask(chatTitle, matchlist);
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
