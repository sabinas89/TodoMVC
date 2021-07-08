package drivers;

public class DriverManager {

    public enum DriverType {
        CHROME,
        FIREFOX,
        IE;
    }

    public static DriverHandler getDriverHandler(DriverType driverType) {
        DriverHandler driverHandler;

        switch (driverType) {
            case CHROME -> {
                driverHandler = new ChromeDriverHandler();
                break;
            }
            case FIREFOX -> {
                driverHandler = new FirefoxDriverHandler();
                break;
            }

            case IE -> {
                driverHandler = new IEDriverHandler();
                break;
            }

            default -> throw new IllegalStateException("Unexpected value: " + driverType);
        }
        return driverHandler;
    }
}
