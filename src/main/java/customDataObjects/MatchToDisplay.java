package customDataObjects;

import java.util.Date;

public class MatchToDisplay {

    private String competitorClan;
    private Date date;
    private String provinceName;
    private String mapName;

    public MatchToDisplay(String competitorClan, Date date, String provinceName, String mapName) {
        this.competitorClan = competitorClan;
        this.date = date;
        this.provinceName = provinceName;
        this.mapName = mapName;
    }

    public String getCompetitorClan() {
        return competitorClan;
    }

    public void setCompetitorClan(String competitorClan) {
        this.competitorClan = competitorClan;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public String toString() {
        return "customDataObjects.MatchToDisplay{" +
                "competitorClan=" + competitorClan +
                ", date=" + date +
                ", provinceName='" + provinceName + '\'' +
                ", mapName='" + mapName + '\'' +
                '}';
    }
}
