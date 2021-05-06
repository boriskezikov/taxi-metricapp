package ru.taxi.metricapp.spark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.taxi.metricapp.dto.PreviewResponseDto;
import ru.taxi.metricapp.dto.TripRecordDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.taxi.metricapp.config.Constants.RAW_DATA_DRIVE;
import static ru.taxi.metricapp.config.Constants.RAW_PREVIEW_FILENAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class SparkAdapter {

    private final SparkSession ss;
    private final ObjectMapper om;

    @Value("${hdfs.host}")
    private String hdfsHost;

    public PreviewResponseDto loadRawData(int recordsCount) {
        Dataset<Row> csv = ss.read().format("csv")
                .option("header", "true")
                .load(hdfsHost + RAW_DATA_DRIVE + RAW_PREVIEW_FILENAME);
        csv.printSchema();
        List<Row> rows = csv.takeAsList(recordsCount);
        List<TripRecordDto> trips = rows.parallelStream().map(row->{
            String json  = row.json();
            try {
                return om.readValue(json, TripRecordDto.class);
            } catch (JsonProcessingException e) {
               throw new RuntimeException(e);
            }

        }).collect(Collectors.toList());
        return PreviewResponseDto.builder().previewData(trips).recordsCount(recordsCount).build();
    }
}
