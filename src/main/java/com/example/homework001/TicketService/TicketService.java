package com.example.homework001.TicketService;


import com.example.homework001.dto.TicketCreateRequest;
import com.example.homework001.dto.TicketResponse;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(TicketCreateRequest createRequest);

    List<TicketResponse> getAllTicket();

    TicketResponse getTicketById(int id);

    List<TicketResponse> createBulkTicket(List<TicketCreateRequest> createRequest);

    List<TicketResponse> getTicketByNamePassenger(String passengerName);

    TicketResponse updateById(int id,TicketCreateRequest ticketCreateRequest);

    List<TicketResponse>   updatePaymentStatus (List<Integer> ticketId,boolean newPaymentStatus);

    void deleteById(int id);
}
