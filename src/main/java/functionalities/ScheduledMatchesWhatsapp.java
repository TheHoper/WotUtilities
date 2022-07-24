package functionalities;

import customDataObjects.MatchToDisplay;
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

        matchlist.forEach(System.out::print);

    }

    private static MatchToDisplay getMoreInfo(ScheduledMatch match, WotApiConnector con) throws InterruptedException, IOException, URISyntaxException {
        String competitorClanName = con.getClanNameFromID(String.valueOf(match.getCompetitorID()));
        String provinceName = match.getProvinceName();
        String mapName = con.getMapNameFromProvinceID(match.getFrondID(), match.getProvinceID());
        Date date = new Date(match.getScheduledTime() * 1000);
        return new MatchToDisplay(competitorClanName, date, provinceName, mapName);
    }

}
