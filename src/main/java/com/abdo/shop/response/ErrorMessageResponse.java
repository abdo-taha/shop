package com.abdo.shop.response;

import java.time.Instant;
import java.util.Map;



import lombok.Builder;

@Builder
public record ErrorMessageResponse(
    Instant createAt,
    String message,
    Map<String,Object> details    
) {
        
}
