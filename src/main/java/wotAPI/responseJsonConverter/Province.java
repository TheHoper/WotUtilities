package wotAPI.responseJsonConverter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Province {

    @SerializedName(value = "arena_name")
    private String mapName;


    @SerializedName(value = "round_number")
    private Integer roundNumber;


    public Province(String mapName, Integer roundNumber) {
        this.mapName = mapName;
        this.roundNumber = roundNumber;
    }


    public String getMapName() {
        return mapName;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }
}
