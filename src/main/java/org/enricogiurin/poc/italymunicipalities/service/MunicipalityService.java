package org.enricogiurin.poc.italymunicipalities.service;

import lombok.RequiredArgsConstructor;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
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
     *
     * @param pageable
     * @return
     */
    public Page<Municipality> list(Pageable pageable, String... name) {
        final int skipCount = (pageable.getPageNumber()) * pageable.getPageSize();
        final Supplier<Stream<Municipality>> streamSupplier = buildSupplier(name);
        final List<Municipality> list = streamSupplier.get()
                .skip(skipCount)
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final int totPages = (int) streamSupplier.get().count();
        return new PageImpl<>(list, pageable, totPages);
    }

    private Supplier<Stream<Municipality>> buildSupplier(String... name) {
        return () -> {
            if (name.length > 0 && name[0] != null) {
                return municipalities
                        .stream().filter(municipality -> municipality
                                .getName().toLowerCase().contains(name[0].toLowerCase()));
            } else {
                return municipalities
                        .stream();
            }
        };
    }
}
