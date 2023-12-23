package app.rssreader.application.config;

import java.io.File;

import javax.inject.Inject;

import app.rssreader.domain.config.Config;

public class AppConfig implements Config {
    private static File bookmarksFilePath;

    @Inject
    public AppConfig() {}

    @Override
    public File getBookmarksFilePath() {
        return AppConfig.bookmarksFilePath;
    }

    public static void setBookmarksFilePath(File bookmarksFilePath) {
        AppConfig.bookmarksFilePath = bookmarksFilePath;
    }
}
