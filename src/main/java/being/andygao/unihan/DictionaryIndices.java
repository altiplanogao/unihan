package being.andygao.unihan;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryIndices {
    public enum Field {
        kCheungBauerIndex,
        kCowles,
        kDaeJaweon,
        kFennIndex,
        kGSR,
        kHanYu,
        kIRGDaeJaweon,
        kIRGDaiKanwaZiten,
        kIRGHanyuDaZidian,
        kIRGKangXi,
        kKangXi,
        kKarlgren,
        kLau,
        kMatthews,
        kMeyerWempe,
        kMorohashi,
        kNelson,
        kSBGY,
    }

    public static class Processor extends AbstractResourceProcessor {
        public Processor(Map<Character, Hanzi> unihan) {
            super(unihan);
        }

        @Override
        protected String getResourceName() {
            return "Unihan_DictionaryIndices.txt";
        }

        @Override
        protected void processEntry(char chr, String field, String value) {
            processEntry(chr, Field.valueOf(field), value);
        }

        protected void processEntry(char chr, Field field, String value) {
            Hanzi hanzi = get(chr);
            hanzi.dictionaryIndices.put(field, value);
        }
    }

    private final Map<Field, List<String>> indices = new HashMap<>();

    public DictionaryIndices() {
    }

    public void put(Field field, String value) {
        indices.computeIfAbsent(field, f -> new ArrayList<>()).add(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Field, List<String>> entry : indices.entrySet()) {
            Field f = entry.getKey();
            sb.append(" ").append(f)
                    .append("[")
                    .append(StringUtils.join(entry.getValue(), ", "))
                    .append("]");
        }
        return sb.toString();
    }
}
