package com.naviroq.patterns.creational.builder.infopost.demo;

import com.naviroq.patterns.creational.builder.infopost.Post;
import com.naviroq.patterns.creational.builder.infopost.director.PostDirector;

public class Main {
    public static void main(String[] args) {
        // 1. Create the Director (The Recipe Manager)
        PostDirector director = new PostDirector();

        // 2. Create a fresh empty instance of your Nested Builder
        Post.Builder builder = new Post.Builder();

        // 3. Pass the builder to the director to inject the standard "Viral" recipe
        director.makeViralPreset(builder);

        // 4. Finally, call build() to get the secure Post object!
        Post myViralPost = builder.build();

        System.out.println("Text: " + myViralPost.getText());     // Big news!
        System.out.println("Layout: " + myViralPost.getLayout()); // ShortForm
    }
}