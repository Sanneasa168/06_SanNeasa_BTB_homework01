package com.example.homework001.TicketDomain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Ticket {
    private Integer id;
    private Integer seatNumber;
    private String passengerName;
    private LocalDate ticketDate;
    private String sourceStation;
    private BigDecimal ticketPrice;
    private boolean paymentStatus;
    private boolean ticketStatus;
}
