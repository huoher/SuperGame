package top.qiuchi.zhang.supergame.entity;

public class Score {

    private String username;

    private String achievement;

    public Score() {
    }

    public Score(String username, String achievement) {
        this.username = username;
        this.achievement = achievement;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "Score{" +
                "username='" + username + '\'' +
                ", achievement=" + achievement +
                '}';
    }
}
