package com.reservation.movie.reservation.controller;

import com.reservation.movie.reservation.service.ReservationService;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationRestController {
  private final ReservationService reservationService;

  @PostMapping("/movie")
  String reservationMovie(@RequestBody ReservationDto reservationDto){
    return reservationService.reservationMovie(reservationDto);
  }
  @GetMapping("/seats/{movieId}")
  public List<Integer> findAllSeatNumbers(@PathVariable int movieId){
    return reservationService.findAllSeatNumbers(movieId);
  }

  @PatchMapping("/movie/cancel")
  public ResponseEntity<?> reservationCancel(@RequestBody Map<String, String> reservationTime, HttpSession session){
    User user = (User)session.getAttribute("user");
    String email = user.getEmail();

    String resTime = reservationTime.get("reservationTime");
    String ok = reservationService.reservationCancel(email, resTime);
    return ResponseEntity.status(HttpStatus.OK).body(ok);
  }
}
