package com.kvp.streams.step3;

import com.kvp.domain.step3.Receipt;
import com.kvp.domain.step3.ReceiptWithGrade;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Optional;

public class ReceiptWithGradeTransformer implements ValueTransformer<Receipt, ReceiptWithGrade> {
    private KeyValueStore<String, Long> totalPurchasePriceStore;
    private String storeName;
    private ProcessorContext context;

    public ReceiptWithGradeTransformer(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        totalPurchasePriceStore = this.context.getStateStore(storeName);
    }

    @Override
    public ReceiptWithGrade transform(Receipt receipt) {
        Long beforeTotalPurchasePrice = Optional.ofNullable(totalPurchasePriceStore.get(receipt.getName()))
                .orElse(0L);
        Long totalPurchasePrice = beforeTotalPurchasePrice + receipt.getPurchasePrice();

        totalPurchasePriceStore.put(receipt.getName(), totalPurchasePrice);
        return ReceiptWithGrade.of(receipt, totalPurchasePrice);
    }

    @Override
    public void close() {

    }
}
