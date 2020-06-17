package com.lrm.vo;

import java.util.Date;

public class FollowShow {

    private Long id;
    private String nickname;
    private String avatar;
    private Date followtime;
    private boolean likeeach;
    private int status;
    public FollowShow() {
    }

    public FollowShow(Long id,String nickname, String avatar, Date followtime,int status) {
        this.id=id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.followtime = followtime;
        this.status=status;
    }
    public FollowShow(Long id,String nickname, String avatar, Date followtime,boolean likeeach) {
        this.id=id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.followtime = followtime;
        this.likeeach=likeeach;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getFollowtime() {
        return followtime;
    }

    public void setFollowtime(Date followtime) {
        this.followtime = followtime;
    }

    public boolean isLikeeach() {
        return likeeach;
    }

    public void setLikeeach(boolean likeeach) {
        this.likeeach = likeeach;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FollowShow{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followtime=" + followtime +
                ", likeeach=" + likeeach +
                ", status=" + status +
                '}';
    }
}
