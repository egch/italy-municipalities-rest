package org.enricogiurin.poc.italymunicipalities.service;

import lombok.RequiredArgsConstructor;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Pagination service.
     * Thanks to  @see <a href="https://careydevelopment.us/blog/java-streams-how-to-implement-pagination-with-skip-and-limit">careydevelopment</a>
     * @param pageable
     * @return
     */
    public Page<Municipality> list(Pageable pageable) {
        int skipCount = (pageable.getPageNumber() - 1) * pageable.getPageSize();
        List<Municipality> list = municipalities
                .stream()
                .skip(skipCount)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, municipalities.size());
    }
}
