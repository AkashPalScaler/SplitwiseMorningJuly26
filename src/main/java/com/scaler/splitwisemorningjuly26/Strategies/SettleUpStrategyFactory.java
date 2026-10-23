package com.scaler.splitwisemorningjuly26.Strategies;

import com.scaler.splitwisemorningjuly26.Models.SettleUpType;

public class SettleUpStrategyFactory {
    public static SettleUpStrategy getSettleUpStrategy(SettleUpType settleUpType){
        if(settleUpType.equals(SettleUpType.HEAP)){
            return new HeapSettleUpStrategy();
        }else{
            throw new  IllegalArgumentException("Invalid settle up strategy");
        }
    }
}
