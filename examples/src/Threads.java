import java.util.concurrent.atomic.AtomicLong;

public class Threads {

    public static class Worker extends Thread {

        public static final AtomicLong instances = new AtomicLong();
        public static final ThreadGroup group = new ThreadGroup("Workers");

        private final Long delay;
        private final Runnable runnable;

        private final String description;

        public Worker(boolean blockShutdown, Long delay, Runnable runnable, String description) {
            super(group, "Worker-" + instances.incrementAndGet());
            setDaemon(!blockShutdown);

            this.delay = delay;
            this.runnable = runnable;

            this.description = description;
        }

        public Long getDelay() {
            return delay;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public void run() {
            if(delay != null) {
                Threads.sleep(delay);
            }

            runnable.run();
        }
    }

    public static void runLater(Runnable runnable, Long delay, String description) {
        new Worker(false, delay, runnable, description).start();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
