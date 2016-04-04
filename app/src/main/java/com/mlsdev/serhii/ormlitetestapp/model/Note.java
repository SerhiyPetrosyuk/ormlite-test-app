package com.mlsdev.serhii.ormlitetestapp.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by serhii on 04.04.16.
 */
public class Note {
    @DatabaseField(generatedId = true)
    private int id;
    private String title;
    private String description;
    private long createDate;
    private long lastEditDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setLastEditDate(long lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getLastEditDate() {
        return lastEditDate;
    }
}
