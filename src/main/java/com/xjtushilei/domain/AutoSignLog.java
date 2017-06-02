package com.xjtushilei.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
@Entity
public class AutoSignLog {

    @Id
    @GeneratedValue
    private Long id;

    private Date localDateTime;

    private String name;

    private String email;

    private String type;

    private String info;

    public AutoSignLog() {
    }

    public AutoSignLog(Date localDateTime, String name, String email, String type, String info) {
        this.localDateTime = localDateTime;
        this.name = name;
        this.email = email;
        this.type = type;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(Date localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "AutoSignLog{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String mailString() {
        return "<p>" +
                "'" + localDateTime + "'" +
                ",'" + name + "'" +
                ", '" + email + "'" +
                ", '" + type + "'" +
                ", '" + info + "'" +
                "</p>";
    }
}

