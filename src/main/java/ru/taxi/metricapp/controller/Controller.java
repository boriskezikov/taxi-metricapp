package ru.taxi.metricapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.taxi.metricapp.dto.ClustersReportDto;
import ru.taxi.metricapp.dto.PreviewResponseDto;
import ru.taxi.metricapp.dto.TrainingReportDto;
import ru.taxi.metricapp.spark.SparkAdapter;

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

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainingReportDto trainingReport() throws JsonProcessingException {
        return sparkAdapter.loadTrainingReport();
    }

    @GetMapping("/clusters")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClustersReportDto clustersReport() {
        return sparkAdapter.loadClusters();
    }
}
