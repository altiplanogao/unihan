package being.andygao.unihan;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherMappings {
    public enum Field {
        kBigFive,
        kCCCII,
        kCNS1986,
        kCNS1992,
        kEACC,
        kGB0,
        kGB1,
        kGB3,
        kGB5,
        kGB7,
        kGB8,
        kHKSCS,
        kIBMJapan,
        kJa,
        kJinmeiyoKanji,
        kJis0,
        kJis1,
        kJIS0213,
        kJoyoKanji,
        kKoreanEducationHanja,
        kKoreanName,
        kKPS0,
        kKPS1,
        kKSC0,
        kKSC1,
        kMainlandTelegraph,
        kPseudoGB1,
        kTaiwanTelegraph,
        kTGH,
        kXerox,
    }

    public static class Processor extends AbstractResourceProcessor {
        public Processor(Map<Character, Hanzi> unihan) {
            super(unihan);
        }

        @Override
        protected String getResourceName() {
            return "Unihan_OtherMappings.txt";
        }

        @Override
        protected void processEntry(char chr, String field, String value) {
            processEntry(chr, Field.valueOf(field), value);
        }

        protected void processEntry(char chr, Field field, String value) {
            Hanzi hanzi = get(chr);
            hanzi.otherMappings.put(field, value);
        }
    }

    private final Map<Field, List<String>> values = new HashMap<>();

    public OtherMappings() {
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
