import React, { useState } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";

function SeatsReservation() {
  const { movieId } = useParams(); // URL에서 영화 ID 가져오기
  const location = useLocation();
  const { title } = location.state || {}; // navigate에서 전달받은 영화 제목   || 이건 없을 경우 undefind로 나타냄!

  const rows = 5;
  const cols = 10;
  const totalSeats = rows * cols;

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

    alert(
      `예약 완료! 🎬 영화: ${title}, 좌석 번호: ${selectedSeats.join(", ")}`
    );

    setSelectedSeats([]); // 선택 초기화
  };

  return (
    <div className="home">
      <h1>🎬 영화 좌석 예약</h1>
      <h2>{title ? title : `영화 ID: ${movieId}`}</h2>

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

export default SeatsReservation;
