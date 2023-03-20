package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateSectionRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateSectionRequest;
import com.arturfrimu.interview.helper.dto.response.Response.SectionInfoResponse;
import com.arturfrimu.interview.helper.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionInfoResponse>> list() {
        var sections = sectionService.list();
        return ok(sections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionInfoResponse> find(@PathVariable("id") Long id) {
        var section = sectionService.find(id);
        return ok(section);
    }

    @PostMapping
    public ResponseEntity<SectionInfoResponse> create(@RequestBody CreateSectionRequest body) {
        var command = of(body).map(Commands.CreateSectionCommand::valueOf).get();
        var createdSection = sectionService.create(command);
        return ok(createdSection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionInfoResponse> updateSection(@PathVariable("id") Long id, @RequestBody UpdateSectionRequest body) {
        var command = of(body).map(Commands.UpdateSectionCommand::valueOf).get();
        var updatedSection = sectionService.update(id, command);
        return ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        sectionService.delete(id);
        return ok().build();
    }
}
