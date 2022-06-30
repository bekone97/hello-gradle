package com.example.controller;

import com.example.model.entity.EntityUpdateStatistics;
import com.example.service.EntityUpdateStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatisticsController {

    private final EntityUpdateStatisticsService entityUpdateStatisticsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntityUpdateStatistics> getStatistics(){
        return entityUpdateStatisticsService.findAll();
    }
}
