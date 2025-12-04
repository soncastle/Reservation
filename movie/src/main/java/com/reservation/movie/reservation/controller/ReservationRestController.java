package com.reservation.movie.reservation.controller;

import com.reservation.movie.common.ApiResponse;
import com.reservation.movie.reservation.service.ReservationService;
import com.reservation.movie.reservation.reservationDto.ReservationDto;
import com.reservation.movie.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Reservation API", description = "영화 좌석 예약 기능")
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationRestController {
  private final ReservationService reservationService;

  @Operation(summary = "좌석 예약", description = "영화화면 기반 좌석 예약")
  @PostMapping("/movie")
  public ResponseEntity<ApiResponse<String>> reservationMovie(@RequestBody ReservationDto reservationDto){
    String response = reservationService.reservationMovie(reservationDto);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @Operation(summary = "영화별 좌석 예약 현황")
  @GetMapping("/seats/{movieId}")
  public ResponseEntity<ApiResponse<List<Integer>>> findAllSeatNumbers(@PathVariable int movieId){
    List<Integer> response = reservationService.findAllSeatNumbers(movieId);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @Operation(summary = "예약취소", description = "마이페이지에서 예약한 영화 취소")
  @PatchMapping("/movie/cancel")
  public ResponseEntity<ApiResponse<String>> reservationCancel(@RequestBody Map<String, String> reservationTime){

    String resTime = reservationTime.get("reservationTime");
    String response = reservationService.reservationCancel(resTime);
    return ResponseEntity.ok(ApiResponse.success(response));
  }
}
