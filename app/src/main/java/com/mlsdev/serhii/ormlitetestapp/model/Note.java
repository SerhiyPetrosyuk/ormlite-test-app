package com.mlsdev.serhii.ormlitetestapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by serhii on 04.04.16.
 */
public class Note implements Serializable, Parcelable{
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeLong(this.createDate);
        dest.writeLong(this.lastEditDate);
    }

    public Note() {
    }

    protected Note(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.createDate = in.readLong();
        this.lastEditDate = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
