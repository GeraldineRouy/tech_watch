package org.jaalon.tech_watch;

import java.util.UUID;

public class Link {

    private String id;
    private String title;
    private String url;
    private String description;

    public Link(String title, String url, String description) {

        this.title = title;
        this.url = url;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }
}
