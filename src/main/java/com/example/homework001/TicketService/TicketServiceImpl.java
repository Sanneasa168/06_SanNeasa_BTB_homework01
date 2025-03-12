package com.example.homework001.TicketService;

import com.example.homework001.TicketDomain.Ticket;
import com.example.homework001.TicketRepository.TicketRepository;
import com.example.homework001.dto.TicketCreateRequest;
import com.example.homework001.dto.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private  final TicketRepository ticketRepository;
    @Override
    public TicketResponse createTicket(TicketCreateRequest createRequest) {
        Ticket newTicket = Ticket.builder()
                .id(ticketRepository.getLast()+1)
                .passengerName(createRequest.passengerName())
                .ticketPrice(createRequest.ticketPrice())
                .seatNumber(createRequest.seatNumber())
                .ticketDate(createRequest.ticketDate())
                .ticketStatus(createRequest.ticketStatus())
                .sourceStation(createRequest.sourceStation())
                .build();

        ticketRepository.getTickets().add(newTicket);
        return TicketResponse.builder()
                .passengerName(newTicket.getPassengerName())
                .ticketPrice(newTicket.getTicketPrice())
                .seatNumber(newTicket.getSeatNumber())
                .ticketDate(newTicket.getTicketDate())
                .ticketStatus(newTicket.isTicketStatus())
                .sourceStation(newTicket.getSourceStation())
                .build();
    }

    @Override
    public List<TicketResponse> getAllTicket() {

        return ticketRepository.getTickets()
                .stream()
                .map( ticket-> TicketResponse.builder()
                        .id(ticket.getId())
                        .passengerName(ticket.getPassengerName())
                        .ticketPrice(ticket.getTicketPrice())
                        .ticketDate(ticket.getTicketDate())
                        .seatNumber(ticket.getSeatNumber())
                        .sourceStation(ticket.getSourceStation())
                        .build()
                        ).toList();
    }

    @Override
    public TicketResponse getTicketById(int id) {
        return ticketRepository.getTickets()
                .stream()
                .filter(ticket-> ticket.getId().equals(id))
                .findFirst()
                .map(ticket -> TicketResponse.builder()
                                .id(ticket.getId())
                                .passengerName(ticket.getPassengerName())
                                .ticketPrice(ticket.getTicketPrice())
                                .ticketDate(ticket.getTicketDate())
                                .seatNumber(ticket.getSeatNumber())
                                .sourceStation(ticket.getSourceStation())
                                .ticketStatus(ticket.isTicketStatus())
                                .paymentStatus(ticket.isPaymentStatus())
                                .build())
                .orElseThrow(
                        ()-> new IllegalArgumentException("ID Ticket not found")
                );
    }

    @Override
    public List<TicketResponse> createBulkTicket(List<TicketCreateRequest> createRequests) {
        List<TicketResponse> responses = new ArrayList<>();
        for (TicketCreateRequest createRequest : createRequests) {
            Ticket newTicket = Ticket.builder()
                    .id(ticketRepository.getLast()+1)
                    .passengerName(createRequest.passengerName())
                    .ticketPrice(createRequest.ticketPrice())
                    .seatNumber(createRequest.seatNumber())
                    .ticketDate(createRequest.ticketDate())
                    .ticketStatus(createRequest.ticketStatus())
                    .sourceStation(createRequest.sourceStation())
                    .build();
            ticketRepository.getTickets().add(newTicket);

            TicketResponse response = TicketResponse.builder()
                    .id(newTicket.getId())
                    .passengerName(newTicket.getPassengerName())
                    .ticketPrice(newTicket.getTicketPrice())
                    .seatNumber(newTicket.getSeatNumber())
                    .ticketDate(newTicket.getTicketDate())
                    .ticketStatus(newTicket.isTicketStatus())
                    .sourceStation(newTicket.getSourceStation())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<TicketResponse> getTicketByNamePassenger(String passengerName) {
        List<TicketResponse> ticketResponses=   ticketRepository.getTickets()
                .stream()
                .filter(ticket -> ticket.getPassengerName().equals(passengerName))
                .map( ticket -> TicketResponse.builder()
                                        .id(ticket.getId())
                                        .passengerName(ticket.getPassengerName())
                                        .ticketPrice(ticket.getTicketPrice())
                                        .ticketDate(ticket.getTicketDate())
                                        .seatNumber(ticket.getSeatNumber())
                                        .sourceStation(ticket.getSourceStation())
                                        .ticketStatus(ticket.isTicketStatus())
                                        .paymentStatus(ticket.isPaymentStatus())
                                        .build())
                  .collect(Collectors.toList());
         if(ticketResponses.isEmpty()){
             throw  new IllegalArgumentException("No ticket for passengerName"+passengerName);
         }
          return ticketResponses;
    }

    @Override
    public TicketResponse updateById(int id, TicketCreateRequest ticketCreateRequest) {
       ticketRepository.getTickets()
                .stream()
                .filter(ticket-> ticket.getId().equals(id))
                .findFirst()
                .map(ticket ->{
                            ticket.setPassengerName(ticketCreateRequest.passengerName());
                            ticket.setTicketPrice(ticketCreateRequest.ticketPrice());
                            ticket.setSeatNumber(ticketCreateRequest.seatNumber());
                            ticket.setTicketDate(ticketCreateRequest.ticketDate());
                            ticket.setTicketStatus(ticketCreateRequest.ticketStatus());
                            ticket.setSourceStation(ticketCreateRequest.sourceStation());
                            return ticket;
                        })
                .orElseThrow(() -> new IllegalArgumentException("Ticket with ID " + id + " not found"));

        return TicketResponse.builder()
                .id(ticketRepository.getLast()+1)
                .passengerName(ticketCreateRequest.passengerName())
                .ticketPrice(ticketCreateRequest.ticketPrice())
                .ticketDate(ticketCreateRequest.ticketDate())
                .seatNumber(ticketCreateRequest.seatNumber())
                .sourceStation(ticketCreateRequest.sourceStation())
                .ticketStatus(ticketCreateRequest.ticketStatus())
                .build();
    }

    @Override
    public List<TicketResponse> updatePaymentStatus(List<Integer> ticketId, boolean newPaymentStatus) {
        List<TicketResponse> updatedTickets = new ArrayList<>();
        for(Integer id : ticketId){
            Ticket ticket = ticketRepository.getTickets()
                    .stream()
                    .filter(t->t.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Ticket with ID " + id + " not found"));

            ticket.setPaymentStatus(newPaymentStatus);

            TicketResponse response = TicketResponse.builder()
                    .id(ticket.getId())
                    .passengerName(ticket.getPassengerName())
                    .ticketPrice(ticket.getTicketPrice())
                    .seatNumber(ticket.getSeatNumber())
                    .ticketDate(ticket.getTicketDate())
                    .ticketStatus(ticket.isTicketStatus())
                    .sourceStation(ticket.getSourceStation())
                    .build();
            updatedTickets.add(response);
        }

        return  updatedTickets;
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.getTickets()
                .stream()
                .filter(ticket -> ticket.getId().equals(id))
                .peek(ticket ->ticketRepository.getTickets().remove(ticket))
                .findFirst()
                .orElseThrow(
                        ()-> new IllegalArgumentException("ID ticket not found ??")
                );
    }

}