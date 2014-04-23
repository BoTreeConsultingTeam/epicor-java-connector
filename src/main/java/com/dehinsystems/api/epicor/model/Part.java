package com.dehinsystems.api.epicor.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Part {
    private String uniqueId;
    private String name;
    private String description;

    public Part(String uniqueId, String name, String description) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.description = description;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("uniqueId", uniqueId)
                    .append("name", name)
                    .append("description", description)
                    .toString();
    }

}
