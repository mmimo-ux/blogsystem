package com.lrm.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_link")
public class Link {

    @Id
    @GeneratedValue
    private Long id;
    private String linkname;
    private String url;
    private String imgaddress;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgaddress() {
        return imgaddress;
    }

    public void setImgaddress(String imgaddress) {
        this.imgaddress = imgaddress;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkname='" + linkname + '\'' +
                ", url='" + url + '\'' +
                ", imgaddress='" + imgaddress + '\'' +
                ", user=" + user +
                '}';
    }
}
