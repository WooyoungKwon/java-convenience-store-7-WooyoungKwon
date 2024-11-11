package store.receipt;

import java.util.List;

public class ReceiptService {
    public Receipt createReceipt(List<ReceiptDto> receiptDto) {
        Receipt receipt = new Receipt();
        for (ReceiptDto dto : receiptDto) {
            receipt.addReceipt(dto);
        }
        return receipt;
    }
}
