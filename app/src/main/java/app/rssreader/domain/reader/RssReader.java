package app.rssreader.domain.reader;

import java.util.concurrent.ExecutionException;

import app.rssreader.domain.dto.rss.Rss;

public interface RssReader {
    Rss read(String url) throws ExecutionException, InterruptedException;
}
