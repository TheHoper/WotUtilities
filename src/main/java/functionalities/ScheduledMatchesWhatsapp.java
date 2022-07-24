package functionalities;

import customDataObjects.MatchToDisplay;
import wotAPI.WotApiConnector;
import wotAPI.responseJsonConverter.ScheduledMatch;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ScheduledMatchesWhatsapp {
    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {
        final String hamonClanID = "500218963";
        WotApiConnector con = new WotApiConnector();
        List<ScheduledMatch> schduledMatches = con.getScheduledMatches(hamonClanID);
        List<MatchToDisplay> matchlist = new ArrayList<>();

        for(ScheduledMatch match : schduledMatches){





            matchlist.add(new MatchToDisplay());
        }


    }

}
