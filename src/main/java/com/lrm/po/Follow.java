package com.lrm.po;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_follow")
public class Follow {
    //搞不清和user表的关系，只能直接写long 型了
    @Id
    @GeneratedValue
    private Long id;
    private Long auser;
    private Long buser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date followTime;

    public Follow() {
    }

    public Follow(Long auser, Long buser, Date followTime) {
        this.auser = auser;
        this.buser = buser;
        this.followTime = followTime;
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

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", auser=" + auser +
                ", buser=" + buser +
                ", followTime=" + followTime +
                '}';
    }
}
