package main.model.entities.enums;

public enum AttendanceType {
    OFFLINE("Очно"),
    ONLINE("Онлайн"),
    ANY("Любой");

    private final String displayValue;

    AttendanceType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
