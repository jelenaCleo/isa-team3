package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.model.Client;

import javax.mail.MessagingException;

public interface EmailService {

    String createConfirmRegistrationEmail(String email, String token);

    String createAdminResponseEmail(String message, boolean isApproved);

    void sendEmail(String to, String subject, String emailContent) throws MessagingException;

    String createDeleteAccountResponseEmail(String message, boolean isApproved);

    void sendReservationEmail(ReservationDto map, Client client);
}
