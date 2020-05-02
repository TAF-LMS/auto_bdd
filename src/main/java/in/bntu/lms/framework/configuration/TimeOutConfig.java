package in.bntu.lms.framework.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class TimeOutConfig {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private final TimeUnit timeUnit;
    private final Long timeOut;

    private TimeOutConfig() {
        this(TimeUnit.SECONDS, 60L);
    }

    public Duration getTimeOut() {
        return Duration.ofMillis(timeUnit.toMillis(timeOut));
    }
}
