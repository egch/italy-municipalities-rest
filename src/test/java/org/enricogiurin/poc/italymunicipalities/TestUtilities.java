package org.enricogiurin.poc.italymunicipalities;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;

import java.util.List;

public class TestUtilities {
    public static List<Municipality> buildList() {
        return List.of(Municipality.builder()
                        .code("1")
                        .name("Spinea")
                        .province("Venezia")
                        .region("Veneto")
                        .build(),
                Municipality.builder()
                        .code("2")
                        .name("San Bonifacio")
                        .province("Verona")
                        .region("Veneto")
                        .build()
        );
    }
}
