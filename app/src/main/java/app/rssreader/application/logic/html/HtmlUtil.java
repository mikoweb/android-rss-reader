package app.rssreader.application.logic.html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    public static String removeTags(String text) {
        return text.replaceAll("<[^>]*>", "").trim();
    }

    public static List<String> findImagesSrc(String text) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);

        while (m.find()) {
            list.add(m.group(1));
        }

        return list;
    }
}
