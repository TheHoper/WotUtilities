package wotAPI;

import wotAPI.responseJsonConverter.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WotApiConnector {


    private final String applicationID = "d9270406f542efcb6d950ff1f9e24bab";

    public List<ScheduledMatch> checkForNewMatches(String clanID, List<ScheduledMatch> previousMatches) throws InterruptedException, IOException, URISyntaxException {

        List<ScheduledMatch> currentlySchduledMatches = getScheduledMatches(clanID);

        List<ScheduledMatch> newScheduledMatches = new ArrayList<>();

        for (ScheduledMatch match : currentlySchduledMatches) {
            if (!previousMatches.contains(match)) {
                newScheduledMatches.add(match);
            }
        }
        return newScheduledMatches;
    }


    public List<ScheduledMatch> getScheduledMatches(String clanID) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(buildClanScheduledMatchesUri(applicationID, clanID))
                .GET()
                .build();

        boolean mocked = false;
        JsonObject obj;
        if (mocked) {
            StringBuilder sb = new StringBuilder();
            File fileResponseMatch = new File("src/main/resources/responseMatches.json");
            FileReader fr = new FileReader(fileResponseMatch);
            BufferedReader br = new BufferedReader(fr);
            br.lines().forEach(l -> sb.append(l + "\n"));

            String jsonString = sb.toString();

            obj = new Gson().fromJson(jsonString, JsonObject.class);
        } else {
            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
            obj = new Gson().fromJson(response.body(), JsonObject.class);
        }

        Gson gson = new Gson();
        Response<ScheduledMatch> respWithSchedMatches = gson.fromJson(obj.getAsJsonObject(), ScheduledMatchesResponse.class);
        return respWithSchedMatches.getData();
    }


    public String getClanNameFromID(String clanID) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(buildClanNameUri(applicationID, clanID))
                .GET()
                .build();

        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        String clanTagPattern = "\"tag\":\"(.*?)\"";
        Pattern p = Pattern.compile(clanTagPattern);
        Matcher m = p.matcher(response.body());
        String clantag = null;
        if (m.find()) {
            clantag = m.group(1);
        }
        System.out.println(clantag);
        return clantag;
    }

    public String getMapNameFromProvinceID(String frontID, String provinceID) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(buildMapNameUri(applicationID, frontID, provinceID))
                .GET()
                .build();
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        JsonObject obj = new Gson().fromJson(response.body(), JsonObject.class);

        Gson gson = new Gson();
        Response<Province> respWithProvinces = gson.fromJson(obj.getAsJsonObject(), ProvinceResponse.class);
        return respWithProvinces.getData().get(0).getMapName();
    }


    private URI buildClanScheduledMatchesUri(String applicationID, String clanID) throws URISyntaxException {
        URI clanRequestUri = new URIBuilder(WotApiUris.clanbattlesBaseUrl)
                .addParameter("application_id", applicationID)
                .addParameter("clan_id", clanID)
                .build();
        return clanRequestUri;
    }

    private URI buildClanNameUri(String applicationID, String clanID) throws URISyntaxException {
        URI clanRequestUri = new URIBuilder(WotApiUris.clanRequestBaseUrl)
                .addParameter("application_id", applicationID)
                .addParameter("clan_id", clanID)
                .addParameter("field", "tag")
                .build();
        return clanRequestUri;
    }

    private URI buildMapNameUri(String applicationID, String frontID, String provinceID) throws URISyntaxException {
        URI provinceBaseUrl = new URIBuilder(WotApiUris.provinceBaseUrl)
                .addParameter("application_id", applicationID)
                .addParameter("front_id", frontID)
                .addParameter("province_id", provinceID)
                .build();
        return provinceBaseUrl;
    }


}
