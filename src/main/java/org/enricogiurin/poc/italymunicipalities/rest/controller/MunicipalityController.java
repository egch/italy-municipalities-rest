package org.enricogiurin.poc.italymunicipalities.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.enricogiurin.poc.italymunicipalities.dto.Municipality;
import org.enricogiurin.poc.italymunicipalities.service.MunicipalityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/municipality")
@RequiredArgsConstructor
@Slf4j
public class MunicipalityController {


    private final MunicipalityService municipalityService;


    @RequestMapping(
            method = {RequestMethod.GET},
            produces = "application/json"
    )
    public ResponseEntity<Page<Municipality>> list(Pageable pageable, @RequestParam(name = "name", required = false) String name) {
        Page<Municipality> page = municipalityService.list(pageable, name);
        return ResponseEntity.ok(page);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            produces = "application/json",
            value = "/{code}"
    )
    public ResponseEntity<Municipality> get(@PathVariable String code) {
        Municipality municipality;
        try {
            municipality = municipalityService.findByCode(code);
        } catch (IllegalArgumentException e) {
            log.warn(e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(municipality, HttpStatus.OK);
    }

}

