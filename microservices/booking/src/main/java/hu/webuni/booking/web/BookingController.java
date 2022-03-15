package hu.webuni.booking.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.booking.dto.PurchaseData;
import hu.webuni.booking.dto.TicketData;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Value("${booking.bonus}")
    double bonusRate;

    @PostMapping("/ticket")
    public PurchaseData buyTicket(@RequestBody TicketData ticketData) {
        return null;
    }
}
