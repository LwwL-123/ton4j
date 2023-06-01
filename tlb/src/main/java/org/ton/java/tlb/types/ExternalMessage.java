package org.ton.java.tlb.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ton.java.address.Address;
import org.ton.java.cell.Cell;
import org.ton.java.cell.CellBuilder;
import org.ton.java.cell.CellSlice;

import java.math.BigInteger;

import static java.util.Objects.nonNull;

@Builder
@Getter
@Setter
@ToString
public class ExternalMessage {
    long magic;             // `tlb:"$10"` ext_in_msg_info$10
    Address srcAddr;        // `tlb:"addr"`
    Address dstAddr;        // `tlb:"addr"`
    BigInteger importFee;   // `tlb:"."`
    StateInit stateInit;    // `tlb:"maybe either . ^"`
    Cell body;              // `tlb:"either . ^"`

    public Cell toCell() {
        CellBuilder result = CellBuilder.beginCell()
                .storeUint(0b10, 2)
                .storeAddress(srcAddr)
                .storeAddress(dstAddr)
                .storeCoins(importFee)
                .storeBit(nonNull(stateInit)); //maybe


        if (nonNull(stateInit)) { //either
            Cell stateInitCell = stateInit.toCell();
            if ((result.bits.getFreeBits() - 2 < stateInitCell.bits.getUsedBytes()) || (result.getFreeRefs() - 1 < result.getMaxRefs())) {
                result.storeBit(true);
                result.storeRef(stateInitCell);
            } else {
                result.storeBit(false);
                result.storeSlice(CellSlice.beginParse(stateInitCell));
            }
        }

        if (nonNull(body)) { //either
            if ((result.bits.getFreeBits() - 1 < body.bits.getUsedBytes()) || (result.getFreeRefs() < body.getMaxRefs())) {
                result.storeBit(true);
                result.storeRef(body);
            } else {
                result.storeBit(false);
                result.storeSlice(CellSlice.beginParse(body));
            }
        } else {
            result.storeBit(false);
        }

        return result.endCell();
    }
}
