package za.co.jaspa.diskilive.models;

import java.io.Serializable;

public class Match  implements Serializable {

    private String matchId;
    private String homeTeam;
    private String awayTeam;
    private String homeTeamGoals;
    private String awayTeamGoals;
    private String homeTeamBadge;
    private String awayTeamBadge;
    private String kickOffTime;
    private String league;

    public Match() {
        super();
    }


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(String homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public String getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(String awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public String getHomeTeamBadge() {
        return homeTeamBadge;
    }

    public void setHomeTeamBadge(String homeTeamBadge) {
        this.homeTeamBadge = homeTeamBadge;
    }

    public String getAwayTeamBadge() {
        return awayTeamBadge;
    }

    public void setAwayTeamBadge(String awayTeamBadge) {
        this.awayTeamBadge = awayTeamBadge;
    }

    public String getKickOffTime() {
        return kickOffTime;
    }

    public void setKickOffTime(String kickOffTime) {
        this.kickOffTime = kickOffTime;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }
}
