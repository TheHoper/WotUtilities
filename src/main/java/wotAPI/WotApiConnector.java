package wotAPI;

import wotAPI.responseJsonConverter.Response;
import wotAPI.responseJsonConverter.ScheduledMatch;
import wotAPI.responseJsonConverter.ScheduledMatchesResponse;
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
import java.util.List;

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

    private URI buildClanScheduledMatchesUri(String applicationID, String clanID) throws URISyntaxException {
        URI clanRequestUri = new URIBuilder(WotApiUris.clanbattlesBaseUrl)
                .addParameter("application_id", applicationID)
                .addParameter("clan_id", clanID)
                .build();
        return clanRequestUri;
    }

    public String getClanNameFromID(Integer clanID){
        return "";
    }

    public String getProvinceNameFromID(Integer provinceID){
        return"";
    }





}
