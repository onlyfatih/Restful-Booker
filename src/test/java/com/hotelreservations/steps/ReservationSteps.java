package com.hotelreservations.steps;

import com.hotelreservations.models.Auth;
import com.hotelreservations.models.BookingResponse;
import com.hotelreservations.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("Kullanici yeni bir rezervasyon olusturuyor")
    public void cagriBaslangici() {
        reservationService = new ReservationService();
    }

    @Given("Kullanici rezervasyon icin gerekli bilgileri veriyor")
    public void createAuth() {
        authKey = reservationService.generateToken();
    }

    @When("Kullanici otel rezervasyonu olusturuyor")
    public void createReservation() {
        bookingResponse = reservationService.createBooking();
    }

    @Then("Rezervasyon basarili sekilde olusturuldu")
    public void reservationAssertions() {
        Assertions.assertEquals("Fatih",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Arslan",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000,bookingResponse.getBooking().getTotalprice());
    }

    @Then("Kullanici olusturulan rezervasyonu iptal ediyor")
    public void cancelReservation () {
        reservationService.deleteBooking(authKey,bookingResponse.getBookingId());
    }

}
