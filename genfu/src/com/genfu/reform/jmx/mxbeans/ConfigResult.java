/**
 * QueueSample.java - Java type representing a snapshot of a given queue.
 * It bundles together the instant time the snapshot was taken, the queue
 * size and the queue head.
 */

package com.genfu.reform.jmx.mxbeans;

import java.beans.ConstructorProperties;
import java.util.Date;

public class ConfigResult {
    
    private final Date date;
    private final int code;
    private final String info;
    
    @ConstructorProperties({"date", "code", "info"})
    public ConfigResult(Date date, int code, String info) {
        this.date = date;
        this.code = code;
        this.info = info;
    }
    
    public Date getDate() {
        return date;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
}
