package com.xjtushilei.job;

import java.time.LocalTime;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class UserInfo {

    private String name;

    private String idCard;

    private String email;

    private LocalTime time1;
    private LocalTime time2;
    private LocalTime time3;
    private LocalTime time4;
    private LocalTime time5;
    private LocalTime time6;

    private int error;


    public UserInfo() {
    }

    public UserInfo(String name, String idCard, String email, int error) {
        this.name = name;
        this.idCard = idCard;
        this.email = email;
        this.error = error;
    }

    public UserInfo(String name, String idCard, String email, LocalTime time1, LocalTime time2, LocalTime time3, LocalTime time4, LocalTime time5, LocalTime time6, int error) {
        this.name = name;
        this.idCard = idCard;
        this.email = email;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.time5 = time5;
        this.time6 = time6;
        this.error = error;
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

    public LocalTime getTime1() {
        return time1;
    }

    public void setTime1(LocalTime time1) {
        this.time1 = time1;
    }

    public LocalTime getTime2() {
        return time2;
    }

    public void setTime2(LocalTime time2) {
        this.time2 = time2;
    }

    public LocalTime getTime3() {
        return time3;
    }

    public void setTime3(LocalTime time3) {
        this.time3 = time3;
    }

    public LocalTime getTime4() {
        return time4;
    }

    public void setTime4(LocalTime time4) {
        this.time4 = time4;
    }

    public LocalTime getTime5() {
        return time5;
    }

    public void setTime5(LocalTime time5) {
        this.time5 = time5;
    }

    public LocalTime getTime6() {
        return time6;
    }

    public void setTime6(LocalTime time6) {
        this.time6 = time6;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}

