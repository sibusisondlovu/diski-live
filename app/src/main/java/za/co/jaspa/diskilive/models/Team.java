package za.co.jaspa.diskilive.models;

import java.io.Serializable;

public class Team  implements Serializable {
    private int team_id;
    private String team;
    private String logo;

    public Team() {
    }

    public Team(int team_id, String team, String logo) {
        this.team_id = team_id;
        this.team = team;
        this.logo = logo;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
