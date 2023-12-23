package app.rssreader.infrastructure.bookmark.path;

import java.io.File;

import javax.inject.Inject;

import app.rssreader.application.config.AppConfig;

public class BookmarksPath {
    private final AppConfig config;

    @Inject
    public BookmarksPath(AppConfig config) {
        this.config = config;
    }

    public File getPath() {
        return config.getBookmarksFilePath();
    }
}
