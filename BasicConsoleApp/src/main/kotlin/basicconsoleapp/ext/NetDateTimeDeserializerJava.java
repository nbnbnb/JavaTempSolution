package basicconsoleapp.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetDateTimeDeserializerJava extends JsonDeserializer<LocalDateTime> {

    private static Pattern pattern = Pattern.compile("\\/Date\\((\\d+)([-+])(\\d{4})\\)\\/");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Matcher matcher = pattern.matcher(p.getText());
        if (matcher.matches()) {
            return LocalDateTime.ofEpochSecond(Long.parseLong(matcher.group(1)) / 1000, 0, ZoneOffset.of("+8"));
        }
        return null;

    }
}
