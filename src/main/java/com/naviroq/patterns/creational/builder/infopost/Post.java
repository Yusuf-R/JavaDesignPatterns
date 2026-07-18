package com.naviroq.patterns.creational.builder.infopost;

public class Post {
    // 1. Private fields
    private final String text;
    private final String layout;
    private final String media;

    // 2. PRIVATE CONSTRUCTOR: Only our internal Builder can call this!
    private Post(Builder builder) {
        this.text = builder.text;
        this.layout = builder.layout;
        this.media = builder.media;
    }

    // Getters
    public String getText() { return text; }
    public String getLayout() { return layout; }
    public String getMedia() { return media; }

    // 3. THE STATIC NESTED BUILDER CLASS
    public static class Builder {
        private String text;
        private String layout;
        private String media;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setLayout(String layout) {
            this.layout = layout;
            return this;
        }

        public Builder setMedia(String media) {
            this.media = media;
            return this;
        }

        // The only way to finally get a Post object
        public Post build() {
            return new Post(this);
        }
    }
}
