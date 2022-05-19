package Main;

public class Main {

    public static void main(String[] args) {

        AvgAndPercentile avgAndPercentile = new AvgAndPercentile();
        WriteFiles writeFiles = new WriteFiles();
        writeFiles.write(avgAndPercentile.getAvg(), avgAndPercentile.getPercentile());

    }
}

