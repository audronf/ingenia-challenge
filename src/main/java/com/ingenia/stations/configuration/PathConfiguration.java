package com.ingenia.stations.configuration;

import com.ingenia.stations.models.Path;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class PathConfiguration {

    private final ArrayList<Path> paths = new ArrayList<>();

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public boolean addPath(Path newPath) {
        return paths.add(newPath);
    }
}
