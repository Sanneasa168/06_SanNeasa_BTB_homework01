package com.example.homework001.TicketRepository;

import com.example.homework001.TicketDomain.Ticket;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class TicketRepository {
        List<Ticket> tickets;
        public Integer getLast(){
            if(tickets.isEmpty()){
                return null;
            }
            return tickets.getLast().getId();
        }

        public TicketRepository(){
            tickets = new ArrayList<>();
            tickets.add(Ticket
                    .builder()
                            .id(12)
                            .passengerName("Theara")
                            .sourceStation("In Parist")
                            .ticketDate(LocalDate.of(2025,12,16))
                            .ticketPrice(BigDecimal.valueOf(2000))
                            .seatNumber(33)
                            .paymentStatus(true)
                            .ticketStatus(true)

                    .build());

            tickets.add(Ticket
                    .builder()
                    .id(13)
                    .passengerName("Neasa")
                    .sourceStation("In Parist")
                    .ticketDate(LocalDate.of(2025,8,22))
                    .ticketPrice(BigDecimal.valueOf(3000))
                    .seatNumber(16)
                    .paymentStatus(true)
                    .ticketStatus(true)
                    .build());

            tickets.add(Ticket
                    .builder()
                    .id(14)
                    .passengerName("Naro")
                    .sourceStation("In Hong Kong")
                    .ticketDate(LocalDate.of(2025,9,22))
                    .ticketPrice(BigDecimal.valueOf(3000))
                    .seatNumber(46)
                    .paymentStatus(true)
                    .ticketStatus(true)
                    .build());
        }

}
