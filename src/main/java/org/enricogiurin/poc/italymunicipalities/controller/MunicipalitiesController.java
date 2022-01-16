package org.enricogiurin.poc.italymunicipalities.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/municipalities")
@Slf4j
public class MunicipalitiesController {


    private final List<String> municipalities;

    public MunicipalitiesController(@Qualifier("municipalities") List<String> municipalities) {
        this.municipalities = municipalities;
    }

    @RequestMapping(
            method = {RequestMethod.GET}
    )
    public ResponseEntity<List<String>> list()  {
        return ResponseEntity.ok(municipalities);
    }
}

