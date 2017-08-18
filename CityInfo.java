package com.example.tantan.city;

/**
 * Created by TanTan on 18/08/2017.
 */

public class CityInfo {
    private String id, name, img, summary;

    public CityInfo(String id, String name, String img, String summary) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
