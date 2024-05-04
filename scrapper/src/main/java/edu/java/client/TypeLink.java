package edu.java.client;

import lombok.Getter;

@Getter public class TypeLink {
    int type;
    int id;
    String owner;
    String rep;

    static final String GITHUB_URL = "https://github.com/";
    static final String STACKOVERFLOW_URL = "https://stackoverflow.com/questions/";
    static final int ID = 4;
    static final int VALID_LENGTH = 5;

    public TypeLink(String url) {
        if (url.startsWith(GITHUB_URL)) {
            type = 1;
            String[] parts = url.substring(GITHUB_URL.length()).split("/");
            if (parts.length >= 2) {
                owner = parts[0];
                rep = parts[1];
            } else {
                type = -1;
            }
        } else if (url.startsWith(STACKOVERFLOW_URL)) {
            type = 2;
            String[] parts = url.split("/");
            if (parts.length >= VALID_LENGTH) {
                id = Integer.parseInt(parts[ID]);
            } else {
                type = -1;
            }
        } else {
            type = -1;
        }
    }
}

