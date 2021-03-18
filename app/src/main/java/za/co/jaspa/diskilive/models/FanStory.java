package za.co.jaspa.diskilive.models;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;

public class FanStory implements Serializable {

    private String uid;
    private String title;
    private String body;
    private String media;
    private String mediaType;
    private String fanUid;
    private String fanUsername;
    private String fanAvatar;
    private String createdAt;
    private int commentsCount;
    private int upVotesCount;
    private int downVotesCount;
    private int sharesCount;

    public FanStory() {
        super();
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String isMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFanUid() {
        return fanUid;
    }

    public void setFanUid(String fanUid) {
        this.fanUid = fanUid;
    }

    public String getFanUsername() {
        return fanUsername;
    }

    public void setFanUsername(String fanUsername) {
        this.fanUsername = fanUsername;
    }

    public String getFanAvatar() {
        return fanAvatar;
    }

    public void setFanAvatar(String fanAvatar) {
        this.fanAvatar = fanAvatar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getUpVotesCount() {
        return upVotesCount;
    }

    public void setUpVotesCount(int upVotesCount) {
        this.upVotesCount = upVotesCount;
    }

    public int getDownVotesCount() {
        return downVotesCount;
    }

    public void setDownVotesCount(int downVotesCount) {
        this.downVotesCount = downVotesCount;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }
}
