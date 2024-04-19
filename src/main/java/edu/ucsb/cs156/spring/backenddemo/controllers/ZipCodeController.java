package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="ZipCode info from ZIPTASTIC")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipCodeQueryService;

    @Operation(summary = "Get city description from zipcode", description = "JSON return format")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCode(
        @Parameter(name="zipcode", description="Zipcode of Isla Vista", example="93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipcode: zip={}", zipcode);
        String result = zipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }
}
