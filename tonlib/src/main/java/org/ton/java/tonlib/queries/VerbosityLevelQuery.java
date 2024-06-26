package org.ton.java.tonlib.queries;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class VerbosityLevelQuery extends ExtraQuery {
    @SerializedName("@type")
    final String type = "setLogVerbosityLevel";
    int new_verbosity_level;
}
