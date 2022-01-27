package org.enricogiurin.poc.italymunicipalities.service;

import lombok.RequiredArgsConstructor;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MunicipalityService {
    private final List<Municipality> municipalities;

    public Municipality findByCode(String code) {
        return municipalities.stream()
                .filter(municipality -> municipality.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No municipality found having code: " + code));
    }

    /**
     * Pagination service.
     * Thanks to  @see <a href="https://careydevelopment.us/blog/java-streams-how-to-implement-pagination-with-skip-and-limit">careydevelopment</a>
     * @param pageable
     * @return
     */
    public Page<Municipality> list(Pageable pageable, String... name) {
        int skipCount = (pageable.getPageNumber()) * pageable.getPageSize();
        Stream<Municipality> stream = municipalities
                .stream();
        if (name.length > 0 && name[0] != null) {
            stream = stream.filter(municipality -> municipality.getName().contains(name[0]));
        }
        List<Municipality> list = stream
                .skip(skipCount)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, municipalities.size());
    }
}
