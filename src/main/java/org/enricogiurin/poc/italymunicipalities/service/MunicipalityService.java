package org.enricogiurin.poc.italymunicipalities.service;

import lombok.RequiredArgsConstructor;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    private final List<Municipality> municipalities;

    public List<Municipality> findAll() {
        return municipalities;
    }
}
