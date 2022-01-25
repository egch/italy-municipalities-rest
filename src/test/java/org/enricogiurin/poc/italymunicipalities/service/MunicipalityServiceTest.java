package org.enricogiurin.poc.italymunicipalities.service;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<Municipality> all = municipalityService.findAll();
        //then
        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);
    }

    @Test
    void findByCode() {
        //when
        Municipality spinea = municipalityService.findByCode("1");
        //then
        assertThat(spinea).isNotNull();
        assertThat(spinea.getName()).isEqualTo("Spinea");
    }

    @Test
    void findByCode_NotFound() {
        assertThatThrownBy(() -> municipalityService.findByCode("xxx")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("xxx");
    }

}