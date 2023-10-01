package org.enricogiurin.poc.italymunicipalities.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.util.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
class BeansFactory {

    private static final String PATH = "data/Elenco-comuni-italiani.csv";
    private final Mapper mapper;

    @Bean()
    @Qualifier("municipalities")
    List<Municipality> list() throws IOException {
        List<Municipality> records = new LinkedList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(PATH);
             Reader reader = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                Municipality municipality = mapper.stringToMunicipality(line);
                records.add(municipality);
            }
        }
        records.remove(0);
        records.sort((m1, m2) -> m1.name().compareToIgnoreCase(m2.name()));
        log.info("List of municipalities loaded from the file: {}", PATH);
        return records;
    }

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

}
