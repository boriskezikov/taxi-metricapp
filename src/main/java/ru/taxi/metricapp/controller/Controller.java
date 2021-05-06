package ru.taxi.metricapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.taxi.metricapp.spark.SparkAdapter;
import ru.taxi.metricapp.dto.PreviewResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/metrics")
public class Controller {

    private final SparkAdapter sparkAdapter;

    @GetMapping("/preview")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PreviewResponseDto previewData(@RequestParam(value = "records", defaultValue = "2500") Integer recordsCount) {
        return sparkAdapter.loadRawData(recordsCount);
    }
}
