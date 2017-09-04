package com.xjtushilei.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
@Entity
public class AutoSignUserInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String idCard;

    private String email;

    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private boolean sendEmail = false;

    private boolean autoSign = true;

    public AutoSignUserInfo(String name, String idCard, String email, String grade, boolean sendEmail, boolean autoSign) {
        this.name = name;
        this.idCard = idCard;
        this.email = email;
        this.grade = grade;
        this.sendEmail = sendEmail;
        this.autoSign = autoSign;
    }

    public AutoSignUserInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isAutoSign() {
        return autoSign;
    }

    public void setAutoSign(boolean autoSign) {
        this.autoSign = autoSign;
    }

    @Override
    public String toString() {
        return "AutoSignUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                ", sendEmail=" + sendEmail +
                ", autoSign=" + autoSign +
                '}';
    }
}

