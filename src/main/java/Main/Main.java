package Main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    private static final String PATH = "src/main/resources/tickets.json";
    private static final double PERCENTILE = 90.0;

    @SneakyThrows
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        TicketsList tickets = mapper.readValue(Paths.get(PATH).toFile(),
                TicketsList.class);

        double avg = tickets.getTickets().stream().mapToDouble(e -> {
            ZonedDateTime departure = ZonedDateTime.of(e.getDepartureDate(), e.getDepartureTime(), ZoneId.of("GMT+10"));
            ZonedDateTime arrival = ZonedDateTime.of(e.getArrivalDate(), e.getArrivalTime(), ZoneId.of("GMT+3"));
            return Duration.between(departure, arrival).toMinutes();
        }).average().getAsDouble();

        double[] array = tickets.getTickets().stream().mapToDouble(e -> {
            ZonedDateTime departure = ZonedDateTime.of(e.getDepartureDate(), e.getDepartureTime(), ZoneId.of("GMT+10"));
            ZonedDateTime arrival = ZonedDateTime.of(e.getArrivalDate(), e.getArrivalTime(), ZoneId.of("GMT+3"));
            return Duration.between(departure, arrival).toMinutes();
        }).toArray();
        Percentile percentile = new Percentile();
        percentile.setData(array);
        percentile.evaluate(PERCENTILE);

        try (FileWriter fileWriter = new FileWriter("file.txt")) {
            fileWriter.write("Average flight time from Vladivostok to Tel-Aviv - " + avg + "\n");
            fileWriter.write("90th percentile of flight time between Vladivostok and Tel-Aviv - " + percentile.evaluate());
            fileWriter.flush();
        }

    }

}

