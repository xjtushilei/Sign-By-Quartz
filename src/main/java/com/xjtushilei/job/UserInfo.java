package com.xjtushilei.job;

import javax.persistence.Entity;
import java.time.LocalTime;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
@Entity
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

    private int error1;
    private int error2;
    private int error3;
    private int error4;
    private int error5;
    private int error6;


    public UserInfo() {
    }

    public UserInfo(String name, String idCard, String email, LocalTime time1, LocalTime time2, LocalTime time3, LocalTime time4, LocalTime time5, LocalTime time6, int error1, int error2, int error3, int error4, int error5, int error6) {
        this.name = name;
        this.idCard = idCard;
        this.email = email;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.time5 = time5;
        this.time6 = time6;
        this.error1 = error1;
        this.error2 = error2;
        this.error3 = error3;
        this.error4 = error4;
        this.error5 = error5;
        this.error6 = error6;
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

    public int getError1() {
        return error1;
    }

    public void setError1(int error1) {
        this.error1 = error1;
    }

    public int getError2() {
        return error2;
    }

    public void setError2(int error2) {
        this.error2 = error2;
    }

    public int getError3() {
        return error3;
    }

    public void setError3(int error3) {
        this.error3 = error3;
    }

    public int getError4() {
        return error4;
    }

    public void setError4(int error4) {
        this.error4 = error4;
    }

    public int getError5() {
        return error5;
    }

    public void setError5(int error5) {
        this.error5 = error5;
    }

    public int getError6() {
        return error6;
    }

    public void setError6(int error6) {
        this.error6 = error6;
    }
}

