package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.dto.ReservationDto;
import com.ftn.fishingbooker.dto.UtilityDto;
import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.exception.ResourceConflictException;
import com.ftn.fishingbooker.mapper.ReservationMapper;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.Utility;
import com.ftn.fishingbooker.projection.ReservationInfo;
import com.ftn.fishingbooker.repository.ReservationRepository;
import com.ftn.fishingbooker.service.DateService;
import com.ftn.fishingbooker.service.ReservationService;
import com.ftn.fishingbooker.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final DateService dateService;
    private final UtilityService utilityService;

    @Override
    public Collection<Reservation> findAllForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }

    @Override
    public Collection<Reservation> getReservationForVacationHome(Long homeId) {
        return reservationRepository.getReservationForVacationHome(homeId);
    }

    @Override
    public Collection<Reservation> getReservationForBoat(Long boatId) {
        return reservationRepository.getReservationForBoat(boatId);
    }

    @Override
    public Reservation makeReservation(Client client, ReservationDto reservationDto, double durationInHours) {
        Date newEndDate = dateService.addHoursToJavaUtilDate(reservationDto.getStartDate(), durationInHours);
        reservationDto.setEndDate(newEndDate);
        Reservation newReservation = ReservationMapper.map(reservationDto);

        newReservation.setClient(client);
        newReservation.setPrice(calculatePrice(reservationDto));
        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);

        return reservationRepository.save(newReservation);
    }

    @Override
    public Collection<Reservation> getReservationsForAdventures(Collection<Long> ids) {
        return reservationRepository.getReservationsForAdventures(ids);
    }

    @Override
    public Collection<ReservationInfo> getUpcomingReservationsForInstructor(Long id) {
        return reservationRepository.getUpcomingReservationsForInstructor(id);
    }

    @Override
    public Collection<Reservation> getPastReservationsForInstructor(Long id) {
        return reservationRepository.getPastReservationsForInstructor(id);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow( () -> new ResourceConflictException("Reservation not found"));
    }

    @Override
    public Collection<Reservation> getReservationsForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }

    @Override
    public Reservation makeReservation(Client client, ReservationDto reservationDto) {

        Reservation newReservation = ReservationMapper.map(reservationDto);
        newReservation.setClient(client);
        newReservation.setPrice(calculatePrice(reservationDto));
        Set<Utility> utilitySet = new HashSet<>();
        for (UtilityDto utilityDto : reservationDto.getUtilities()
        ) {
            Utility utility = utilityService.getByName(utilityDto.getName());
            utilitySet.add(utility);
        }
        newReservation.setUtilities(utilitySet);
        return reservationRepository.save(newReservation);
    }

    @Override
    public boolean dateOverlapsWithReservation(Collection<Reservation> reservations, Date startDate, Date endDate) {
        if (reservations == null) {
            return false;
        }
        for (Reservation reservation : reservations) {
            if (dateService.doPeriodsOverlap(reservation.getStartDate(), reservation.getEndDate(), startDate, endDate)) {
                return true;
            }
        }
        return false;
    }


    private double calculatePrice(ReservationDto reservationDto) {

        try {
            var diff = 1.0;
            if (reservationDto.getType() != ReservationType.ADVENTURE) {
                diff = dateService.DifferenceBetweenDates(reservationDto.getStartDate(), reservationDto.getEndDate());
            }
            double utilities = 0.0;
            for (UtilityDto utility : reservationDto.getUtilities()
            ) {
                utilities += utility.getPrice();
            }
            return reservationDto.getPrice() * diff + utilities;

        } catch (ParseException exception) {
            System.out.println(exception);
        }

        return 0;
    }
}
