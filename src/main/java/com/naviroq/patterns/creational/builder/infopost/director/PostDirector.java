package com.naviroq.patterns.creational.builder.infopost.director;

import com.naviroq.patterns.creational.builder.infopost.Post;

public class PostDirector {
    // The Director takes the builder and applies the standard "Viral" recipe
    public void makeViralPreset(Post.Builder builder) {
        builder.setText("Big news! 🔥🚀")
                .setLayout("ShortForm")
                .setMedia("Video");
        // Notice we DO NOT call .build() here. We just configure the steps.
    }

    // The Director applies the standard "Corporate" recipe
    public void makeCorporatePreset(Post.Builder builder) {
        builder.setText("Official Announcement.")
                .setLayout("LongForm")
                .setMedia("PDFDocument");
    }
}
