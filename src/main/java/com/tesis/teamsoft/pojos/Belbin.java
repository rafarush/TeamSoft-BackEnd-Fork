package com.tesis.teamsoft.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Belbin {
    @JsonProperty("CI")
	private String ci;
	@JsonProperty("value_result")
	private String valueResult;
	@JsonProperty("item_name")
	private String itemName;
    @JsonProperty("PEI")
	private char PEI;
}
