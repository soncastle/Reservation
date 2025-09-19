import React, { useState } from "react";
import "../styles/ReservationPage.css";

function ReservationPage() {
  const totalSeats = 50; // 좌석 수 (예시)
  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);

  const toggleSeat = (seatId: number) => {
    setSelectedSeats((prev) =>
      prev.includes(seatId)
        ? prev.filter((id) => id !== seatId)
        : [...prev, seatId]
    );
  };

  const handleReservation = () => {
    if (selectedSeats.length === 0) {
      alert("좌석을 선택해주세요!");
      return;
    }
    alert(`예약 완료! 좌석 번호: ${selectedSeats.join(", ")}`);
    setSelectedSeats([]); // 선택 초기화
  };

  return (
    <div className="home">
      <h1>식장 좌석 예약</h1>
      <div className="seat-grid">
        {Array.from({ length: totalSeats }, (_, i) => {
          const seatId = i + 1;
          const isSelected = selectedSeats.includes(seatId);
          return (
            <div
              key={seatId}
              className={`seat ${isSelected ? "selected" : ""}`}
              onClick={() => toggleSeat(seatId)}
            >
              {seatId}
            </div>
          ); 
        })}
      </div>
      <button className="reserve-btn" onClick={handleReservation}>
        예약하기
      </button>
    </div>
  );
}

export default ReservationPage;
