package com.reservation.movie.reservation.controller;

import com.reservation.movie.common.ApiResponse;
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
  public ResponseEntity<ApiResponse<String>> reservationMovie(@RequestBody ReservationDto reservationDto){
    String response = reservationService.reservationMovie(reservationDto);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
  @GetMapping("/seats/{movieId}")
  public ResponseEntity<ApiResponse<List<Integer>>> findAllSeatNumbers(@PathVariable int movieId){
    List<Integer> response = reservationService.findAllSeatNumbers(movieId);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @PatchMapping("/movie/cancel")
  public ResponseEntity<ApiResponse<String>> reservationCancel(@RequestBody Map<String, String> reservationTime, HttpSession session){
    User user = (User)session.getAttribute("user");
    String email = user.getEmail();

    String resTime = reservationTime.get("reservationTime");
    String response = reservationService.reservationCancel(email, resTime);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
