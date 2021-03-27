package za.co.jaspa.diskilive.models;

import java.io.Serializable;

public class Match  implements Serializable {
    private int match_id;
    private String status;
    private String match_start;
    private String minute;
    private String home_team;
    private String away_team;
    private String home_team_logo;
    private String away_team_logo;
    private Stat stats;

    public Match() {
    }

    public Match(int match_id, String status, String match_start, String minute, String home_team, String away_team, String home_team_logo, String away_team_logo, Stat stats) {
        this.match_id = match_id;
        this.status = status;
        this.match_start = match_start;
        this.minute = minute;
        this.home_team = home_team;
        this.away_team = away_team;
        this.home_team_logo = home_team_logo;
        this.away_team_logo = away_team_logo;
        this.stats = stats;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatch_start() {
        return match_start;
    }

    public void setMatch_start(String match_start) {
        this.match_start = match_start;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getHome_team_logo() {
        return home_team_logo;
    }

    public void setHome_team_logo(String home_team_logo) {
        this.home_team_logo = home_team_logo;
    }

    public String getAway_team_logo() {
        return away_team_logo;
    }

    public void setAway_team_logo(String away_team_logo) {
        this.away_team_logo = away_team_logo;
    }

    public Stat getStats() {
        return stats;
    }

    public void setStats(Stat stats) {
        this.stats = stats;
    }
}
