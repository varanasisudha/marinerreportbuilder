package util;

import java.util.ArrayList;
import java.util.List;

public enum FileType {

CSV("csv"), JSON ("json"), XML ("xml");

    private String type;
    FileType(String type) {
        this.type = type;
    }

    public static List<String> getSupportedTypes() {

        List<String>  supportedFileTypes = new ArrayList<>();
        supportedFileTypes.add(CSV.name());
        supportedFileTypes.add(JSON.name());
        supportedFileTypes.add(XML.name());

        return supportedFileTypes;
    }


}
