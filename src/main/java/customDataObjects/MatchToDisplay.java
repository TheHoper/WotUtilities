package customDataObjects;

import java.util.Date;
import java.util.Objects;

public class MatchToDisplay {

    private String competitorClan;
    private Date date;
    private String provinceName;
    private String mapName;
    private int matchNumber;

    public MatchToDisplay(String competitorClan, Date date, String provinceName, String mapName) {
        this.competitorClan = competitorClan;
        this.date = date;
        this.provinceName = provinceName;
        this.mapName = mapName;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
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
                ", matchNumber = '" + matchNumber + "'" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchToDisplay that = (MatchToDisplay) o;
        return Objects.equals(competitorClan, that.competitorClan) &&
                Objects.equals(date, that.date) &&
                Objects.equals(provinceName, that.provinceName) &&
                Objects.equals(mapName, that.mapName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitorClan, date, provinceName, mapName);
    }
}
