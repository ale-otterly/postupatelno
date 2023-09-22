package main.model.entities.enums;

public enum CommunicationMethod {
    VK("ВК"),
    TG("ТГ"),
    WA("ВА"),
    VB ("ВБ");

    private final String displayValue;

    CommunicationMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
