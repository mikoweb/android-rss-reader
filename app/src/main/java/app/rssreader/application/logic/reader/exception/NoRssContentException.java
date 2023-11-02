package app.rssreader.application.logic.reader.exception;

public class NoRssContentException extends RuntimeException {
    public NoRssContentException() {
        super("Not found RSS content!");
    }
}
