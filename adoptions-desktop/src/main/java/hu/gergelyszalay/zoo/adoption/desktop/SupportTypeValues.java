package hu.gergelyszalay.zoo.adoption.desktop;

import java.util.ArrayList;
import java.util.List;

public enum SupportTypeValues {

    FOOD("Táp (Kg)"),
    FORAGE("Takarmány (Kg)"),
    MEDICINE("Gyógyszer hozzájárulás (ezer Ft)");

    private final String value;

    SupportTypeValues(String value) {
        this.value = value;

    }

    public static List<String> getValueList() {
        List<String> values = new ArrayList<>();
        for (SupportTypeValues value :
                SupportTypeValues.values()) {
            values.add(value.getValue());
        }
        return values;
    }

    public String getValue() {
        return value;
    }
}
