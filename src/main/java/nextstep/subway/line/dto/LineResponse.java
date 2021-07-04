package nextstep.subway.line.dto;

import nextstep.subway.line.domain.Line;
import nextstep.subway.station.dto.StationResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private List<StationResponse> stations;
    private int surcharge;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LineResponse() {
    }

    public LineResponse(Long id, String name, String color, List<StationResponse> stations, int surcharge, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
        this.surcharge = surcharge;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static LineResponse of(Line persistLine) {
        List<StationResponse> stationResponses = persistLine.getStations()
                .stream().map(station -> StationResponse.of(station))
                .collect(Collectors.toList());
        return new LineResponse(persistLine.getId(), persistLine.getName(), persistLine.getColor(), stationResponses, persistLine.getSurcharge(), persistLine.getCreatedDate(), persistLine.getModifiedDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<StationResponse> getStations() {
        return stations;
    }

    public int getSurcharge() {
        return surcharge;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
