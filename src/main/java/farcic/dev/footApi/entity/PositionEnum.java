package farcic.dev.footApi.entity;

public enum PositionEnum {
    GOALKEEPER("Goalkeeper"),
    DEFENDER("Defender"),
    FULLBACK("Fullback"),
    MIDFIELDER("Midfielder"),
    FORWARD("Forward");

    private final String label;

    PositionEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
