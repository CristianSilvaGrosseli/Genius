package cristian.genius;

public enum GeniusEnums {

    BUTTON_BLUE(1), BUTTON_RED(2), BUTTON_YELLOW(3), BUTTON_GREEN(4);

    public int buttonId;
    GeniusEnums(int buttonId) {
        this.buttonId = buttonId;
    }
}
