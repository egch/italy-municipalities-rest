package org.enricogiurin.poc.italymunicipalities.controller;

import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.service.MunicipalityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.enricogiurin.poc.italymunicipalities.TestUtilities.buildList;
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
                .andExpect(jsonPath("$[0].code").value("1"))
                .andExpect(jsonPath("$[0].name").value("Spinea"))
                .andExpect(jsonPath("$[0].province").value("Venezia"))
                .andExpect(jsonPath("$[0].region").value("Veneto"))
                .andExpect(jsonPath("$[1].code").value("2"))
                .andExpect(jsonPath("$[1].name").value("San Bonifacio"))
                .andExpect(jsonPath("$[1].province").value("Verona"))
                .andExpect(jsonPath("$[1].region").value("Veneto"));
        verify(municipalityService).findAll();
    }


    @Test
    void findByCode() throws Exception {
        //given
        when(municipalityService.findByCode("1"))
                .thenReturn(Municipality.builder()
                        .code("1")
                        .name("Spinea")
                        .build());


        //when-then
        mockMvc.perform(get(URL+"/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
        verify(municipalityService).findByCode("1");
    }

    @Test
    void findByCode_404() throws Exception {
        //given
        when(municipalityService.findByCode("xxx"))
                .thenThrow(new IllegalArgumentException("Invalid code"));


        //when-then
        mockMvc.perform(get(URL+"/xxx"))
                .andExpect(status().is4xxClientError());
        verify(municipalityService).findByCode("xxx");
    }



}