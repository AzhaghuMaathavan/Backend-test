package com.netflixclone.backend.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class YouTubeService {

    private static final Map<String, String> VIDEO_URLS = new LinkedHashMap<>();

    static {
        // Movie data with video IDs from markdown
        VIDEO_URLS.put("Jana Nayagan", "https://www.youtube.com/embed/fJaAYcERf3Y");
        VIDEO_URLS.put("KINGDOM", "https://www.youtube.com/embed/BYvSLN3rk9g");
        VIDEO_URLS.put("Mark", "https://www.youtube.com/embed/Qf05XesDec0");
        VIDEO_URLS.put("Parasakthi", "https://www.youtube.com/embed/dXCliyBQnWU");
        VIDEO_URLS.put("Diesel", "https://www.youtube.com/embed/pxzHn_T1Vvs");
        VIDEO_URLS.put("Bigil", "https://www.youtube.com/embed/T4rBL-pXJ_0");
        VIDEO_URLS.put("Kabali", "https://www.youtube.com/embed/7w90c3RM8Io");
        VIDEO_URLS.put("Sarkar", "https://www.youtube.com/embed/FQ7gPe1rF8w");
        VIDEO_URLS.put("Kaavalan", "https://www.youtube.com/embed/OXSvXqLwZzQ");
        VIDEO_URLS.put("Leo", "https://www.youtube.com/embed/gLjMdlCRpXo");
    }

    public String getVideoUrlByTitle(String title) {
        return VIDEO_URLS.getOrDefault(title, null);
    }

    public String getThumbnailUrl(String videoEmbedUrl) {
        // Extract video ID from embed URL
        Pattern pattern = Pattern.compile("embed/([^?]+)");
        Matcher matcher = pattern.matcher(videoEmbedUrl);
        
        if (matcher.find()) {
            String videoId = matcher.group(1);
            return "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
        }
        return "https://via.placeholder.com/400x600?text=No+Image";
    }

    public Map<String, String> getAllVideos() {
        return new LinkedHashMap<>(VIDEO_URLS);
    }
}
