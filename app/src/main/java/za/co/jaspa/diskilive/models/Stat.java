package za.co.jaspa.diskilive.models;

import java.io.Serializable;

public class Stat  implements Serializable  {
    private int home_score;
    private int away_score;
    private int match_id;

    public Stat() {
    }

    public Stat(int home_score, int away_score, int match_id) {
        this.home_score = home_score;
        this.away_score = away_score;
        this.match_id = match_id;
    }

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int away_score) {
        this.away_score = away_score;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
