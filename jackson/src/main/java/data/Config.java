package data;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Config {
    @JsonProperty
    private String name;

    @JsonProperty(value = "routed-aris")
    private RoutedARIs routedARIs;

    @JsonSetter(value = "routed-aris")
    public void setAris(List<String> aris) {
        if(aris != null && !aris.isEmpty()) {
            this.routedARIs = new RoutedARIs();

            routedARIs.aris = aris;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public RoutedARIs getRoutedARIs() {
        return routedARIs;
    }
}
