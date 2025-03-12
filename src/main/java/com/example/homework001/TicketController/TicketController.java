package com.example.homework001.TicketController;

import com.example.homework001.TicketService.TicketService;
import com.example.homework001.dto.TicketCreateRequest;
import com.example.homework001.dto.TicketResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    TicketResponse createNewTicket(TicketCreateRequest ticketCreateRequest) {
         return ticketService.createTicket(ticketCreateRequest);
     }

     @GetMapping
    List<TicketResponse> findAllTickets() {
        return  ticketService.getAllTicket();
     }

     @ResponseStatus(HttpStatus.OK)
     @GetMapping("/{ticket-id}")
     TicketResponse findTicketById(@PathVariable("ticket-id") int id){
        return ticketService.getTicketById(id);
     }

     @GetMapping("/search")
     List<TicketResponse>   findTicketByName(@RequestParam String passengerName ) {
        return ticketService.getTicketByNamePassenger(passengerName);
     }
     @ResponseStatus(HttpStatus.OK)
     @PutMapping("/{ticket-id}")
    TicketResponse updateTicketById(@PathVariable("ticket-id") int id,@RequestBody TicketCreateRequest ticketUpdateRequest){
        return ticketService.updateById(id,ticketUpdateRequest);
     }

     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{ticket-id}")
     void deleteTicketById(@PathVariable("ticket-id") int id){
        ticketService.deleteById(id);
     }



}
