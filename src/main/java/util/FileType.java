package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public enum FileType {

CSV("csv"), JSON ("json"), XML ("xml");

    private String type;
    FileType(String type) {
        this.type = type;
    }

    public static Set<String> getSupportedTypes() {

        Set<String>  supportedFileTypes = new HashSet<>();
        supportedFileTypes.add(CSV.name().toLowerCase());
        supportedFileTypes.add(JSON.name().toLowerCase());
        supportedFileTypes.add(XML.name().toLowerCase());

        return supportedFileTypes;
    }


}
