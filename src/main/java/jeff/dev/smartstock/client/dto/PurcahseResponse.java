package jeff.dev.smartstock.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurcahseResponse(@JsonProperty("message") String message) {
}
