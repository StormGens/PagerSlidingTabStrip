package com.astuetz;

/**
 * Created by zlq on 15/1/8.
 */
public class ComplexSlidingTabParams {
    private int resId;
    private int checkedResId;
    
    private int titleColorNormal;
    private int titleColorChecked;
    
    private String title;
    private boolean showTextTitle;
    
    private String badgeText;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getCheckedResId() {
        return checkedResId;
    }

    public void setCheckedResId(int checkedResId) {
        this.checkedResId = checkedResId;
    }

    public int getTitleColorNormal() {
        return titleColorNormal;
    }

    public void setTitleColorNormal(int titleColorNormal) {
        this.titleColorNormal = titleColorNormal;
    }

    public int getTitleColorChecked() {
        return titleColorChecked;
    }

    public void setTitleColorChecked(int titleColorChecked) {
        this.titleColorChecked = titleColorChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowTextTitle() {
        return showTextTitle;
    }

    public void setShowTextTitle(boolean showTextTitle) {
        this.showTextTitle = showTextTitle;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }
}
