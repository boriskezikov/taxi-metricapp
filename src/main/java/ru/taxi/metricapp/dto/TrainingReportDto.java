package ru.taxi.metricapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingReportDto {

    private Double mae;
    private Double mse;
    private Double rmse;
    private Double r2;
    private Double var;
    private Double processing_time;
}
