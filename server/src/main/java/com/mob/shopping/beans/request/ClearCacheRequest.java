package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClearCacheRequest {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("key", key)
                .toString();
    }
}
