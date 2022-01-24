package org.enricogiurin.poc.italymunicipalities.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.service.MunicipalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/municipality")
@RequiredArgsConstructor
@Slf4j
public class MunicipalityController {


    private final MunicipalityService municipalityService;


    @RequestMapping(
            method = {RequestMethod.GET}
    )
    public ResponseEntity<List<Municipality>> list() {
        List<Municipality> all = municipalityService.findAll();
        return ResponseEntity.ok(all);
    }
}

