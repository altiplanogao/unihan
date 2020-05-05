package being.andygao.unihan;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IRGSources {
    public enum Field {
        kCompatibilityVariant,
        kIICore,
        kIRG_GSource,
        kIRG_HSource,
        kIRG_JSource,
        kIRG_KPSource,
        kIRG_KSource,
        kIRG_MSource,
        kIRG_SSource,
        kIRG_TSource,
        kIRG_UKSource,
        kIRG_USource,
        kIRG_VSource,
        kRSUnicode,
        kTotalStrokes,
    }

    public static class Processor extends AbstractResourceProcessor {
        public Processor(Map<Character, Hanzi> unihan) {
            super(unihan);
        }

        @Override
        protected String getResourceName() {
            return "Unihan_IRGSources.txt";
        }

        @Override
        protected void processEntry(char chr, String field, String value) {
            processEntry(chr, Field.valueOf(field), value);
        }

        protected void processEntry(char chr, Field field, String value) {
            Hanzi hanzi = get(chr);
            hanzi.irgSources.put(field, value);
        }
    }


    private final Map<Field, List<String>> values = new HashMap<>();

    public IRGSources() {
    }

    public void put(Field field, String value) {
        values.computeIfAbsent(field, f -> new ArrayList<>()).add(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Field, List<String>> entry : values.entrySet()) {
            Field f = entry.getKey();
            sb.append(" ").append(f)
                    .append("[")
                    .append(StringUtils.join(entry.getValue(), ", "))
                    .append("]");
        }
        return sb.toString();
    }
}
