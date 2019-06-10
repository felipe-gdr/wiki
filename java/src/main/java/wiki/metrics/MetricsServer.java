package wiki.metrics;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetricsServer {
    private static final Map<String, Gauge> GAUGE_MAP = new ConcurrentHashMap<>();

    public void start() {
        try {
            new HTTPServer(1234);
        } catch (IOException e) {
            throw new IllegalStateException("Error initializing Prometheus client", e);
        }
    }

    public void increaseGauge(String metric) {
        Gauge gauge = GAUGE_MAP.computeIfAbsent(metric, key -> Gauge.build().name(metric).help(metric).register());

        gauge.inc();
    }

    public void decreaseGauge(String metric) {
        Gauge gauge = GAUGE_MAP.computeIfAbsent(metric, key -> Gauge.build().name(metric).help(metric).register());

        gauge.dec();
    }
}
