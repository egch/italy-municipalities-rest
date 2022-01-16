package org.enricogiurin.poc.italymunicipalities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class BeansFactory {
    private static final String PATH = "data/Elenco-comuni-italiani.csv";

    @Bean()
    @Qualifier("municipalities")
    public List<String> read() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(PATH);
        Reader reader = new InputStreamReader(is);
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(reader);) {
            String line;
            while ((line = br.readLine()) != null) {
                String municipalityName = line.split(";")[5];
                records.add(municipalityName);
            }
        }
        records.remove(0);
        records.sort(null);
        log.info("List of municipalities loaded from the file: {}", PATH);
        return records;
    }

}
