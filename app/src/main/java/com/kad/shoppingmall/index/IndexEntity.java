package com.kad.shoppingmall.index;

import java.io.Serializable;

public class IndexEntity implements Serializable {

    private String picUrl;

    public IndexEntity(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
