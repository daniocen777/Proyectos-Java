package com.danicode.marvel.persistence.integration.marvel.dto;

public class ComicDto {
    private Long id;
    private String title;
    private String description;
    private String modified;
    private String resourceURI;
    private ThumbnailDto thumbnail;

    public ComicDto(Long id, String title, String description, String modified, String resourceURI, ThumbnailDto thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.modified = modified;
        this.resourceURI = resourceURI;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public ThumbnailDto getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailDto thumbnail) {
        this.thumbnail = thumbnail;
    }
}
