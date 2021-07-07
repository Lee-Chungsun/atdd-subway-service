package nextstep.subway.path.policy;

import java.util.Arrays;

public enum ChargePolicy {
    BASIC_DISTANCE(0, 10, 0, 0),
    MIDDLE_DISTANCE(10, 50, 5, 100),
    LONG_DISTANCE(50, Integer.MAX_VALUE, 8, 100);

    private int minDistance;
    private int maxDistance;
    private int surchargeDistance;
    private int surcharge;

    ChargePolicy(int minDistance, int maxDistance, int surchargeDistance, int surcharge) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.surchargeDistance = surchargeDistance;
        this.surcharge = surcharge;
    }

    public static ChargePolicy getDistancePolicy(int distance) {
        return Arrays.stream(ChargePolicy.values())
                .filter(chargePolicy -> chargePolicy.isMatchingDistance(distance))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘 못 된 거리입니다."));
    }

    public boolean isMatchingDistance(int distance) {
        return minDistance < distance && distance <= maxDistance;
    }

    public int chargeCalculate(int distance) {
        return (int) ((Math.ceil((distance - this.minDistance - 1) / this.surchargeDistance)) + 1) * this.surcharge;
    }
}
