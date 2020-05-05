package being.andygao.unihan;

import java.util.HashMap;
import java.util.Map;

public class Hanzi {
    private static final Map<Character, Hanzi> hanziMap = new HashMap<>();

    static {
        new DictionaryIndices.Processor(hanziMap).process();
        new DictionaryLikeData.Processor(hanziMap).process();
        new IRGSources.Processor(hanziMap).process();
        new NumericValues.Processor(hanziMap).process();
        new Variants.Processor(hanziMap).process();
        new OtherMappings.Processor(hanziMap).process();
        new RadicalStrokeCounts.Processor(hanziMap).process();
        new Readings.Processor(hanziMap).process();
    }

    private final char value;
    final DictionaryIndices dictionaryIndices = new DictionaryIndices();
    final DictionaryLikeData dictionaryLikeData = new DictionaryLikeData();
    final IRGSources irgSources = new IRGSources();
    final NumericValues numericValues = new NumericValues();
    final Variants variants = new Variants();
    final OtherMappings otherMappings = new OtherMappings();
    final RadicalStrokeCounts radicalStrokeCounts = new RadicalStrokeCounts();
    final Readings readings = new Readings();

    Hanzi(char value) {
        this.value = value;
    }

    public static Hanzi get(char value) {
        return hanziMap.get(value);
    }
}
