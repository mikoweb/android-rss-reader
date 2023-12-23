package app.rssreader.application.command;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.query.GetBookmarksQuery;

public class LoadBookmarksCommand {
    private final GetBookmarksQuery getBookmarksQuery;

    @Inject
    public LoadBookmarksCommand(GetBookmarksQuery getBookmarksQuery) {
        this.getBookmarksQuery = getBookmarksQuery;
    }

    public void run() throws JsonProcessingException {
        // TODO
        ArrayList<BookmarkItemDto> list = getBookmarksQuery.getBookmarksSafe();
        ObjectMapper objectMapper = new ObjectMapper();
        Log.i("APP_LOG", objectMapper.writeValueAsString(list));
    }
}
