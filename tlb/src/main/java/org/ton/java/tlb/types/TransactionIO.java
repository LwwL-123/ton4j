package org.ton.java.tlb.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class TransactionIO {
    Message in; // `tlb:"maybe ^"`
    MessagesList out;  // `tlb:"maybe ^"`
}
