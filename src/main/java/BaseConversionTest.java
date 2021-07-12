import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class BaseConversionTest {
    private static final int CIRCLES = 100;
    private static final int WARM_UP = 20;
    private static final long START_ID = 1475633600;
    private static final long STOP_ID =  1475643600;
    private static final int ID_INTERVAL = (int) (STOP_ID - START_ID);

    public static void main(String[] args) {
        Base62FPELinkShortener shortener = new Base62FPELinkShortener();

        long summary = 0L;

        for (int k = 0; k < CIRCLES; k++) {
            List<String> shortens = new ArrayList<>(ID_INTERVAL);

            for (long i = START_ID; i < STOP_ID; i++) {
                String shorten = shortener.shortenLink(i);
                shortens.add(shorten);
            }

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            for (int i = 0; i < ID_INTERVAL; i++) {
                String shorten = shortens.get(i);
                shortener.recoverLink(shorten);
            }
            stopWatch.stop();
            if (k > WARM_UP) {
                summary += stopWatch.getTotalTimeNanos();
            }
            System.out.println(stopWatch.getTotalTimeNanos());
        }

        summary = summary / (CIRCLES - WARM_UP);

        System.out.println("Average time: " + summary);
    }
}