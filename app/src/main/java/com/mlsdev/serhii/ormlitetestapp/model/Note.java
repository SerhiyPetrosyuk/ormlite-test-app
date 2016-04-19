package com.mlsdev.serhii.ormlitetestapp.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by serhii on 04.04.16.
 */
public class Note {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_DATE = "create_date";
    public static final String LAST_EDITED_DATE = "last_edit_date";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = TITLE)
    private String title;
    @DatabaseField(columnName = DESCRIPTION)
    private String description;
    @DatabaseField(columnName = CREATED_DATE)
    private long createDate;
    @DatabaseField(columnName = LAST_EDITED_DATE)
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
