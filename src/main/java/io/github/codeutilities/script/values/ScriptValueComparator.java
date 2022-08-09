package io.github.codeutilities.script.values;

import java.util.Comparator;
public class ScriptValueComparator implements Comparator<ScriptValue> {
    @Override
    public int compare(ScriptValue o1, ScriptValue o2) {
        return o1.getCompareValue().compare(o2.getCompareValue());
    }
}
