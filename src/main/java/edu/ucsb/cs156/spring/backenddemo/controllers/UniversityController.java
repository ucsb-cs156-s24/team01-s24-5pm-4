package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="University info from universities.hipolabs.com")
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    @Autowired
    UniversityQueryService universityQueryService;

    @Operation(summary = "Get list of universities that match a given name", description = "Uses API documented here: http://universities.hipolabs.com/search")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversities(@Parameter(name="name", description = "name to search, e.g. 'Harvard' or 'Stanford'") @RequestParam String name) throws JsonProcessingException {
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}
