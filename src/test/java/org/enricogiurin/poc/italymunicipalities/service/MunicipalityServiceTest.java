package org.enricogiurin.poc.italymunicipalities.service;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.enricogiurin.poc.italymunicipalities.TestUtilities.buildList;


class MunicipalityServiceTest {
    private MunicipalityService municipalityService;

    @BeforeEach
    void setUp() {
        this.municipalityService = new MunicipalityService(buildList());
    }

    @Test
    void findAll() {
        //when
        Page<Municipality> list = municipalityService.list(Pageable.ofSize(2));
        //then
        assertThat(list).isNotNull();
        assertThat(list).hasSize(2);
    }

    @Test
    void findAll_name() {
        //when
        Page<Municipality> list = municipalityService.list(Pageable.ofSize(2), "Sp");
        //then
        assertThat(list).isNotNull();
        assertThat(list).hasSize(1);
    }

    @Test
    void findByCode() {
        //when
        Municipality spinea = municipalityService.findByCode("1");
        //then
        assertThat(spinea).isNotNull();
        assertThat(spinea.name()).isEqualTo("Spinea");
    }

    @Test
    void findByCode_NotFound() {
        assertThatThrownBy(() -> municipalityService.findByCode("xxx")).isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("xxx");
    }

}