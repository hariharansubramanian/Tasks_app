package com.example.hari.taskit;

import java.util.Date;

/**
 * Created by Nicky on 7/31/2015.
 */
public class Task {
    private String mName;
    private Date mDueDate;
    private boolean mDone;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean mDone) {
        this.mDone = mDone;
    }
    public String toString(){
        return mName;
    }
}
