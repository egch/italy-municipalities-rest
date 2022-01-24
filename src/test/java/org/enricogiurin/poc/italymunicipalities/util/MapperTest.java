package org.enricogiurin.poc.italymunicipalities.util;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {
    static final String SPINEA = "05;227;027; 038 ;027038;Spinea;Spinea;;2;Nord-est;Veneto;Venezia;3;0;VE;27038;27038;27038;27038;I908;ITH;ITH3;ITH35;ITH;ITH3;ITH35";
    private Mapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new Mapper();
    }

    @Test
    void stringToMunicipality() {
        //given-when
        Municipality spinea = mapper.stringToMunicipality(SPINEA);

        //then
        assertThat(spinea).isNotNull();
        assertThat(spinea.getName()).isEqualTo("Spinea");
        assertThat(spinea.getProvince()).isEqualTo("Venezia");
        assertThat(spinea.getRegion()).isEqualTo("Veneto");
    }
}