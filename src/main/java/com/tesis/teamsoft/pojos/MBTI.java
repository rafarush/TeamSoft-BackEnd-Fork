package com.tesis.teamsoft.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MBTI {
    @JsonProperty("CI")
    private String ci;
    @JsonProperty("value_result")
    private String valueResult;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("type")
    private String type;
}
