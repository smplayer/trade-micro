package com.as.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-4-25.
 */

@MappedSuperclass
public abstract class BaseEntity {
    protected String id;
    protected Long createdTime;
    protected Long lastUpdatedTime;
    protected String status;
//    protected Boolean persistent;

    public BaseEntity(){
//        setId(UUID.randomUUID().toString().replaceAll("-", ""));
    }
    public BaseEntity(Map<String, Object> map){
        this.id = (String) map.get("id");
        this.createdTime = (Long) map.get("createdTime");
        this.lastUpdatedTime = (Long) map.get("lastUpdatedTime");
        this.status = (String) map.get("status");
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    protected Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("createdTime", this.createdTime);
        map.put("lastUpdatedTime", this.lastUpdatedTime);
        map.put("status", this.status);
        return map;
    }
}
