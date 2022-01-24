package org.enricogiurin.poc.italymunicipalities.util;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private static final String FIELD_SEPARATOR = ";";

    private static final int NAME_POS = 5;
    private static final int REGION_POS = 10;
    private static final int PROVINCE_POS = 11;

    public Municipality stringToMunicipality(String line) {
        String[] fields = line.split(FIELD_SEPARATOR);
        return Municipality.builder()
                .name(fields[NAME_POS])
                .province(fields[PROVINCE_POS])
                .region(fields[REGION_POS])
                .build();
    }
}
