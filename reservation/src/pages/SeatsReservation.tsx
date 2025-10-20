import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";
import axios from "axios";
import { useGoBack } from "../hooks/useGo";

function SeatsReservation() {
  const { movieId } = useParams();
  const location = useLocation();
  const { title } = location.state || {};

  const totalSeats = 8;

  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
  const [reservedSeats, setReservedSeats] = useState<number[]>([]);
  const { goBack } = useGoBack();

  useEffect(() => {
    const fetchReservedSeats = async () => {
      try {
        const response = await axios.get<number[]>(
          `http://localhost:8080/api/reservation/seats/${movieId}`
        );
        setReservedSeats(response.data.map(Number));
      } catch (error) {
        console.error("예약 좌석 가져오기 실패", error);
      }
    };
    fetchReservedSeats();
  }, [movieId]);

  const toggleSeat = (seatId: number) => {
    if (reservedSeats.includes(seatId)) return;
    setSelectedSeats((prev) =>
      prev.includes(seatId)
        ? prev.filter((id) => id !== seatId)
        : [...prev, seatId]
    );
  };

  const handleReservation = async (e: React.FormEvent) => {
    e.preventDefault();

    if (selectedSeats.length === 0) {
      alert("좌석을 선택해주세요!");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/reservation/movie", {
        movieId,
        movieTitle: title,
        seatNumbers: selectedSeats,
      });

      alert(
        `예약 완료! 🍽️ 식당: ${title}, 좌석 번호: ${selectedSeats.join(", ")}`
      );

      setReservedSeats((prev) => [...prev, ...selectedSeats]);
      setSelectedSeats([]);
    } catch (error) {
      alert("예약 실패!");
      console.error(error);
    }
  };

  return (
    <div className="restaurant">
      <h1>좌석 예약</h1>
      <h2>{title ? title : `가게 ID: ${movieId}`}</h2>
        <div className="screen-label">
        🎬 SCREEN
        </div>

      <div className="table-grid">
        {Array.from({ length: totalSeats }, (_, i) => {
          const seatId = i + 1;
          const isSelected = selectedSeats.includes(seatId);
          const isReserved = reservedSeats.includes(seatId);

          return (
            <div
              key={seatId}
              className={`seat-table ${
                isReserved ? "reserved" : isSelected ? "selected" : ""
              }`}
              onClick={() => toggleSeat(seatId)}
            >
              <div className="chair top"></div>
              <div className="chairSide left"/>
              <div className="table">{seatId}</div>
              <div className="chairSide right"/>
              <div className="chair bottom"></div>
            </div>
          );
        })}
      </div>

      <div>
        <button className="reserve-btn" onClick={handleReservation}>
          예약하기
        </button>
        <button className="reserve-btn" onClick={goBack}>
          이전으로
        </button>
      </div>
    </div>
  );
}

export default SeatsReservation;
