package com.abdo.shop.model.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record PageOfReceipts(Boolean hasNext, List<ReceiptResponse> receipts) {

}
