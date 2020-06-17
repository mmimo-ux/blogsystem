package com.lrm.po;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_followmsg")
public class FollowMessage {
    //搞不清和user表的关系，只能直接写long 型了
    @Id
    @GeneratedValue
    private Long id;
    private Long auser;
    private Long buser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date followTime;
    private int status;

    public FollowMessage() {
    }

    public FollowMessage(Long auser, Long buser, Date followTime,int status) {
        this.auser = auser;
        this.buser = buser;
        this.followTime = followTime;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuser() {
        return auser;
    }

    public void setAuser(Long auser) {
        this.auser = auser;
    }

    public Long getBuser() {
        return buser;
    }

    public void setBuser(Long buser) {
        this.buser = buser;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FollowMessage{" +
                "id=" + id +
                ", auser=" + auser +
                ", buser=" + buser +
                ", followTime=" + followTime +
                ", status=" + status +
                '}';
    }
}
