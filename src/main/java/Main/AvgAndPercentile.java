package Main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.DoubleStream;

public class AvgAndPercentile {

    private static final String PATH = "src/main/resources/tickets.json";
    private static final double PERCENTILE = 90.0;

    @SneakyThrows
    private TicketsList ticketsList() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(Paths.get(PATH).toFile(), TicketsList.class);
    }

    public double getPercentile() {
        double[] array = getDuration(ticketsList()).toArray();
        Percentile percentile = new Percentile();
        percentile.setData(array);
        return percentile.evaluate(PERCENTILE);
    }

    public double getAvg() {
        return getDuration(ticketsList()).average().getAsDouble();
    }

    private static DoubleStream getDuration(TicketsList tickets) {
        return tickets.getTickets().stream().mapToDouble(e -> {
            ZonedDateTime departure = ZonedDateTime.of(e.getDepartureDate(), e.getDepartureTime(), ZoneId.of("GMT+10"));
            ZonedDateTime arrival = ZonedDateTime.of(e.getArrivalDate(), e.getArrivalTime(), ZoneId.of("GMT+3"));
            return Duration.between(departure, arrival).toMinutes();
        });
    }
}
