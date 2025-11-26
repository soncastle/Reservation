import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";
import axios from "axios";
import { useGoBack } from "../hooks/useGo";
import api from "../common/api/axiosInstance";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../common/redux/store";
import { checkSession } from "../common/redux/userSlice";

axios.defaults.withCredentials = true;

function SeatsReservation() {
  const dispatch = useDispatch<AppDispatch>();
  const { movieId } = useParams();
  const location = useLocation();
  const { title } = location.state || {};

  const totalSeats = 8;

  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
  const [reservedSeats, setReservedSeats] = useState<number[]>([]);
  const { goBack } = useGoBack();

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const fetchReservedSeats = async () => {
      try {
        const response = await api.get<number[]>(
          `/reservation/seats/${movieId}`
        );
        console.log("resdata" + response.data)
        setReservedSeats(response.data.map(Number));
        
      } catch (error: any) {
        console.log("ㅎㅎㅎ" + error.message)
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

    try {
      dispatch(checkSession());
      await api.post("/reservation/movie", {
        movieId,
        movieTitle: title,
        seatNumbers: selectedSeats,
      })
      alert(
        `예약 완료! 영화 제목: ${title}, 좌석 번호: ${selectedSeats.join(", ")}`
      );

      setReservedSeats((prev) => [...prev, ...selectedSeats]);
      setSelectedSeats([]);
      
    } catch (error: any) {
      alert(error.message);
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
