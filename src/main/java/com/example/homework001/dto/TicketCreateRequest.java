package com.example.homework001.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record TicketCreateRequest(
        Integer seatNumber,
        String passengerName,
        LocalDate ticketDate,
        String sourceStation,
        BigDecimal ticketPrice,
        boolean paymentStatus,
        boolean ticketStatus
) {

}
