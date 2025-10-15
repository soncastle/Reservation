import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";
import axios from "axios";

function SeatsReservation() {
  const { movieId } = useParams(); // URL에서 영화 ID
  const location = useLocation();
  const { title } = location.state || {}; // 영화 제목

  const rows = 5;
  const cols = 10;
  const totalSeats = rows * cols;

  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
  const [reservedSeats, setReservedSeats] = useState<number[]>([]);

  // ✅ 서버에서 예약된 좌석 가져오기
  useEffect(() => {
    const fetchReservedSeats = async () => {
      try {
        const response = await axios.get<number[]>(
          `http://localhost:8080/api/reservation/seats/${movieId}`
        );
        // 타입 강제 변환(Number) → string 문제 방지
        setReservedSeats(response.data.map(Number));
        console.log("예약된 좌석", response.data);
      } catch (error) {
        console.error("예약 좌석 가져오기 실패", error);
      }
    };
    fetchReservedSeats();
  }, [movieId]);

  // ✅ 좌석 선택 토글
  const toggleSeat = (seatId: number) => {
    // 예약된 좌석은 클릭 불가
    if (reservedSeats.includes(seatId)) return;

    setSelectedSeats((prev) =>
      prev.includes(seatId)
        ? prev.filter((id) => id !== seatId)
        : [...prev, seatId]
    );
  };

  // ✅ 예약하기
  const handleReservation = async (e: React.FormEvent) => {
    e.preventDefault();

    if (selectedSeats.length === 0) {
      alert("좌석을 선택해주세요!");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/reservation/movie", {
        movieId: movieId,
        movieTitle: title,
        seatNumbers: selectedSeats,
      });

      alert(
        `예약 완료! 🎬 영화: ${title}, 좌석 번호: ${selectedSeats.join(", ")}`
      );

      // 예약 완료한 좌석을 reservedSeats에 추가
      setReservedSeats((prev) => [...prev, ...selectedSeats]);
      setSelectedSeats([]); // 선택 초기화
    } catch (error) {
      alert("예약 실패!");
      console.error(error);
    }
  };

  return (
    <div className="home">
      <h1>🎬 영화 좌석 예약</h1>
      <h2>{title ? title : `영화 ID: ${movieId}`}</h2>

      <div className="seat-grid">
        {Array.from({ length: totalSeats }, (_, i) => {
          const seatId = i + 1;
          const isSelected = selectedSeats.includes(seatId);
          const isReserved = reservedSeats.includes(seatId);

          return (
            <div
              key={seatId}
              className={`seat ${
                isReserved ? "reserved" : isSelected ? "selected" : ""
              }`}
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