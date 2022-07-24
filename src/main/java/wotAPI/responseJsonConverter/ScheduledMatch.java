package wotAPI.responseJsonConverter;

import com.google.gson.annotations.SerializedName;

public class ScheduledMatch {
    @SerializedName(value = "attack_type")
    private String attackType;

    @SerializedName(value = "front_id")
    private String frondID;

    @SerializedName(value = "front_name")
    private String frontName;

    @SerializedName(value = "competitor_id")
    private Long competitorID;

    @SerializedName(value = "time")
    private Long scheduledTime;

    @SerializedName(value = "vehicle_level")
    private Long vehicleLevel;

    @SerializedName(value = "province_id")
    private String provinceID;

    @SerializedName(value = "type")
    private String type;


    @SerializedName(value = "province_name")
    private String provinceName;

    public ScheduledMatch(String attackType, String frondID, String frontName, Long competitorID, Long scheduledTime, Long vehicleLevel, String provinceID, String type, String provinceName) {
        this.attackType = attackType;
        this.frondID = frondID;
        this.frontName = frontName;
        this.competitorID = competitorID;
        this.scheduledTime = scheduledTime;
        this.vehicleLevel = vehicleLevel;
        this.provinceID = provinceID;
        this.type = type;
        this.provinceName = provinceName;
    }


    public String getAttackType() {
        return attackType;
    }

    public String getFrondID() {
        return frondID;
    }

    public String getFrontName() {
        return frontName;
    }

    public Long getCompetitorID() {
        return competitorID;
    }

    public Long getScheduledTime() {
        return scheduledTime;
    }

    public Long getVehicleLevel() {
        return vehicleLevel;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public String getType() {
        return type;
    }

    public String getProvinceName() {
        return provinceName;
    }

    @Override
    public String toString() {
        return "ResponseJsonConverter.ScheduledMatch{" +
                "appackType='" + attackType + '\'' +
                ", frondID='" + frondID + '\'' +
                ", frontName='" + frontName + '\'' +
                ", competitorID=" + competitorID +
                ", scheduledTime=" + scheduledTime +
                ", vehicleLevel=" + vehicleLevel +
                ", provinceID='" + provinceID + '\'' +
                ", type='" + type + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
