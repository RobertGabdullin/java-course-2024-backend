package edu.java.client;

import lombok.Getter;

@Getter public class TypeLink {
    int type, id;
    String owner, rep;

    public TypeLink(String url) {
        if (url.startsWith("https://github.com/")) {
            type = 1;
            String[] parts = url.substring("https://github.com/".length()).split("/");
            if (parts.length >= 2) {
                owner = parts[0];
                rep = parts[1];
            } else {
                type = -1;
            }
        } else if (url.startsWith("https://stackoverflow.com/questions/")) {
            type = 2;
            String[] parts = url.split("/");
            if (parts.length >= 5) {
                id = Integer.parseInt(parts[4]);
            } else {
                type = -1;
            }
        } else {
            type = -1;
        }
    }
}

