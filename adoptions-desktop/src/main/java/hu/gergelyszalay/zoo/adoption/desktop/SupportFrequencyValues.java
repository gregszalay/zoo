package hu.gergelyszalay.zoo.adoption.desktop;

import java.util.ArrayList;
import java.util.List;

public enum SupportFrequencyValues {

    WEEKLY("Hetente"),
    MONTHLY("Havonta"),
    YEARLY("Évente");

    private final String value;

    SupportFrequencyValues(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public static List<String> getValueList() {
        List<String> values = new ArrayList<>();
        for (SupportFrequencyValues value :
                SupportFrequencyValues.values()) {
            values.add(value.getValue());
        }
        return values;
    }
}
