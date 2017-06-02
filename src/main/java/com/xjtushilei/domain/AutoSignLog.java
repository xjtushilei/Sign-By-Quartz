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

    private String type;

    private int sum;

    public AutoSignLog(Date localDateTime, String type, int sum) {
        this.localDateTime = localDateTime;
        this.type = type;
        this.sum = sum;
    }

    public AutoSignLog() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

