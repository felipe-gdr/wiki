package data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoutedARIs {
    public List<String> aris;


    @JsonValue
    public List<String> getAris() {
        return aris != null && !aris.isEmpty() ? aris : null;
    }

}
