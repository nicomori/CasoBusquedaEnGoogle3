package framework.src.Util.utils

class OpsUIField {
    private final String inputValue
    private final String showValue

    OpsUIField(String singleValue) {
        this.inputValue = singleValue
        this.showValue = singleValue
    }

    OpsUIField(String inputValue, String showValue) {
        this.inputValue = inputValue
        this.showValue = showValue
    }

    String getShowValue() {
        showValue
    }

    String toString() {
        this.inputValue
    }
}
