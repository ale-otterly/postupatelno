package main.model.entities.enums;

public enum PaymentMethod {
    CASH("Наличные"),
    CARD("Перевод");

    private final String displayValue;

    PaymentMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
