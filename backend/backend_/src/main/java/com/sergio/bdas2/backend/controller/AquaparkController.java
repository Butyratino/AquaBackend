package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.service.AquaparkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aquapark")
@RequiredArgsConstructor
public class AquaparkController {

    private final AquaparkService aquaparkService;


}
