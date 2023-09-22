package main.model.entities.enums;

public enum Subject {
    MATHEMATICS("Математика"),
    ADVANCED_MATHEMATICS("Олимпиадная математика"),
    PHYSICS("Физика"),
    RUSSIAN("Русский язык и анализ текста");

    private final String displayValue;

    Subject(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}