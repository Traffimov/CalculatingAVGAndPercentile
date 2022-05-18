package Main;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Ticket {

    private String origin;

    @JsonProperty("origin_name")

    private String originName;
    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonProperty("departure_date")
    @JsonFormat(timezone = "GMT+10", pattern = "dd.MM.yy")
    private LocalDate departureDate;

    @JsonProperty("departure_time")
    @JsonFormat(timezone = "GMT+10", pattern = "H:mm")
    private LocalTime departureTime;


    @JsonProperty("arrival_date")
    @JsonFormat(timezone = "GMT+3", pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonProperty("arrival_time")
    @JsonFormat(timezone = "GMT+3", pattern = "H:mm")
    private LocalTime arrivalTime;

    private String carrier;
    private int stops;
    private int price;

}
