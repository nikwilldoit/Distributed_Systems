package domain;

public final class RiskConfig {

    private static final double[] LOW_MULTIPLIERS =
            {0.0, 0.0, 0.0, 0.1, 0.5, 1.0, 1.1, 1.3, 2.0, 2.5};

    private static final double[] MEDIUM_MULTIPLIERS =
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 1.0, 1.5, 2.5, 3.5};

    private static final double[] HIGH_MULTIPLIERS =
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 2.0, 6.5};

    private static final int LOW_JACKPOT = 10;
    private static final int MEDIUM_JACKPOT = 20;
    private static final int HIGH_JACKPOT = 40;

    public static double[] getMultipliers(RiskLevel level) {
        return switch (level) {
            case LOW -> LOW_MULTIPLIERS;
            case MEDIUM -> MEDIUM_MULTIPLIERS;
            case HIGH -> HIGH_MULTIPLIERS;
        };
    }

    public static int getJackpot(RiskLevel level) {
        return switch (level) {
            case LOW -> LOW_JACKPOT;
            case MEDIUM -> MEDIUM_JACKPOT;
            case HIGH -> HIGH_JACKPOT;
        };
    }
}
