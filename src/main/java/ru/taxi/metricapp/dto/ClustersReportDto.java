package ru.taxi.metricapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClustersReportDto {

    private String distanceMeasure;
    private Integer numClusters;
    private List<Point> clusterCenters;
    private String modelInfo;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Point {
        private Double lat;
        private Double lng;
    }
}
