/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.jmxclient.vo;

import java.util.Date;
import java.util.Map;

/**
 * @author xuebai
 * @data 2017年11月13日15:58
 * @modify 2017年11月13日
 * @since v
 */
public class MemStatus {
    private Date date;
    private Map<String, Object> status;

    public MemStatus() {
    }

    public MemStatus(Date date, Map<String, Object> status) {
        this.date = date;
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public Map<String, Object> getStatus() {
        return status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }
}
