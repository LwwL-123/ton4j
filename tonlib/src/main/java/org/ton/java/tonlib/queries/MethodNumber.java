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
public class MethodNumber extends ExtraQuery {
    @SerializedName(value = "@type")
    final String type = "smc.methodIdNumber";
    long number;
}
