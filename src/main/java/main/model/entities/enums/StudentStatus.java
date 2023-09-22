package main.model.entities.enums;

public enum StudentStatus {
    ACTIVE("Активен"),
    TEMPORARILY_INACTIVE("Временно неактивен"),
    INACTIVE("Неактивен"),
    IN_SEARCH ("Поиск группы");

    private final String displayValue;

    StudentStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
