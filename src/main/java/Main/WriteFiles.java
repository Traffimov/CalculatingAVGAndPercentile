package Main;

import lombok.SneakyThrows;

import java.io.FileWriter;

public class WriteFiles {

    @SneakyThrows
    public void write(double percentile, double avg) {
        try (FileWriter fileWriter = new FileWriter("file.txt")) {
            fileWriter.write("Average flight time from Vladivostok to Tel-Aviv - " + avg + "\n");
            fileWriter.write("90th percentile of flight time between Vladivostok and Tel-Aviv - " + percentile);
            fileWriter.flush();
        }
    }
}
