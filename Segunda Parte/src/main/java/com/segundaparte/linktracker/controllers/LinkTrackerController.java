package com.segundaparte.linktracker.controllers;

import com.segundaparte.linktracker.dtos.LinkDTO;
import com.segundaparte.linktracker.exceptionHandler.LinkException;
import com.segundaparte.linktracker.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LinkTrackerController {
    @Autowired
    private LinkService linkService;

    @PostMapping("generate")
    public ResponseEntity generateLink(@RequestBody LinkDTO linkDTO) {
        try{
            return ResponseEntity.ok(linkService.generateLink(linkDTO));
        } catch (Exception e){
            return new ResponseEntity(LinkException.URL_NOT_VALID_MSG, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "link/{linkId}", params = {"password"})
    public void redirectLink(@PathVariable String linkId, @RequestParam("password") String password, HttpServletResponse response) throws LinkException, IOException {
        LinkDTO link = linkService.redirectLink(linkId, password);
        if (link != null){
            response.sendRedirect(link.getUrl());
        } else {
            response.sendError(404);
        }
    }

    @GetMapping("metrics/{linkId}")
    public ResponseEntity metricsLink(@PathVariable String linkId) throws LinkException {
        return ResponseEntity.ok(linkService.getMetrics(linkId));
    }

    @PostMapping("invalidate/{linkID}")
    public ResponseEntity invalidateLink(@RequestBody LinkDTO linkDTO) throws LinkException {
        return ResponseEntity.ok(linkService.invalidateLink(linkDTO));
    }
}
