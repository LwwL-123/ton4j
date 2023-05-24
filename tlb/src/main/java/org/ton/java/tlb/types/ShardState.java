package org.ton.java.tlb.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ShardState {
    ShardStateUnsplit left;
    ShardStateUnsplit right;

}