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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WotApiConnector {


    private final String applicationID = "d9270406f542efcb6d950ff1f9e24bab";

    public List<ScheduledMatch> getScheduledMatches(String clanID) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(buildClanScheduledMatchesUri(applicationID, clanID))
                .GET()
                .build();
        // HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

        //---------------------------Mock--------------------------------------
        StringBuilder sb = new StringBuilder();
        File fileResponseMatch = new File("src/main/resources/responseMatches.json");
        FileReader fr = new FileReader(fileResponseMatch);
        BufferedReader br = new BufferedReader(fr);
        br.lines().forEach(l -> sb.append(l + "\n"));

        String jsonString = sb.toString();

        System.out.println(jsonString);
        //-----------------------EndMock------------------------------------------


        //JsonObject obj = new Gson().fromJson(response.body(), JsonObject.class);
        JsonObject obj = new Gson().fromJson(jsonString, JsonObject.class);

        Gson gson = new Gson();
        Response<ScheduledMatch> respWithSchedMatches = gson.fromJson(obj.getAsJsonObject(), ScheduledMatchesResponse.class);
        System.out.println(respWithSchedMatches.getId());
        System.out.println(respWithSchedMatches.getMeta());
        System.out.println(respWithSchedMatches.getData());
        System.out.println(respWithSchedMatches.getData().get(0).getFrondID());
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
            clantag = m.group(0);
        }
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
                .addParameter("fields", "tag")
                .build();
        return clanRequestUri;
    }

    private URI buildClanNameUri(String applicationID, String clanID) throws URISyntaxException {
        URI clanRequestUri = new URIBuilder(WotApiUris.clanRequestBaseUrl)
                .addParameter("application_id", applicationID)
                .addParameter("clan_id", clanID)
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
