package org.enricogiurin.poc.italymunicipalities.service;

import lombok.RequiredArgsConstructor;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    private final List<Municipality> municipalities;

    public Municipality findByCode(String code){
        return municipalities.stream()
                .filter(municipality -> municipality.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No municipality found having code: "+code));
    }

    public Page<Municipality> list(Pageable pageable) {
        return new PageImpl<>(municipalities, pageable, municipalities.size());
    }
}
