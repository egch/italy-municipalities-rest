package org.enricogiurin.poc.italymunicipalities.controller;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.service.MunicipalityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MunicipalityController.class})
class MunicipalityControllerTest {
    public static final String URL = "/municipality";

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private MunicipalityService municipalityService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void list() throws Exception {
        //given
        when(municipalityService.findAll())
                .thenReturn(buildList());

        //when-then
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name").value("San Bonifacio"))
                .andExpect(jsonPath("$[1].province").value("Verona"))
                .andExpect(jsonPath("$[1].region").value("Veneto"))
                .andExpect(jsonPath("$[0].name").value("Spinea"))
                .andExpect(jsonPath("$[0].province").value("Venezia"))
                .andExpect(jsonPath("$[0].region").value("Veneto"));

        verify(municipalityService).findAll();
    }


    private List<Municipality> buildList() {
        return List.of(Municipality.builder()
                        .name("Spinea")
                        .province("Venezia")
                        .region("Veneto")
                        .build(),
                Municipality.builder()
                        .name("San Bonifacio")
                        .province("Verona")
                        .region("Veneto")
                        .build()
        );

    }
}