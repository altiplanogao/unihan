package being.andygao.unihan;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class AbstractResourceProcessor {
    protected final Map<Character, Hanzi> unihan;

    public AbstractResourceProcessor(Map<Character, Hanzi> unihan) {
        this.unihan = unihan;
    }

    protected abstract String getResourceName();

    protected Hanzi get(char chr) {
        return unihan.computeIfAbsent(chr, c -> new Hanzi(c));
    }

    public final void process() {
        URL resource = getClass().getClassLoader().getResource("Unihan/" + getResourceName());
        try (InputStream stream = resource.openStream();
             InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            do {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                line = line.trim();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                if (line.startsWith("#")) {
                    continue;
                } else if (line.startsWith("U+")) {
                    processLine(line);
                } else {
                    throw new IllegalStateException("Unexpected");
                }
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String[] seg = line.split("\t");
        if (seg.length != 3) {
            throw new IllegalStateException("Unexpected");
        }
        try {
            int code = Integer.parseInt(seg[0].substring(2), 0x10);
            processEntry((char) code, seg[1], seg[2]);
        } finally {
        }
    }

    protected abstract void processEntry(char chr, String field, String value);
}
