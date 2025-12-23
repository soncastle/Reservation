import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";
import axios from "axios";
import { useGoBack } from "../hooks/useGo";
import api from "../common/api/axiosInstance";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../common/redux/store";
import { checkSession } from "../common/redux/userSlice";
import "../styles/Tailwind.css";

axios.defaults.withCredentials = true;

function SeatsReservation() {
  const dispatch = useDispatch<AppDispatch>();
  const { movieId } = useParams();
  const location = useLocation();
  const { title } = location.state || {};
  const { goBack } = useGoBack();

  const totalSeats = 8;

  const [selectedSeats, setSelectedSeats] = useState<number[]>([]);
  const [reservedSeats, setReservedSeats] = useState<number[]>([]);

  /* 이미 예약된 좌석 조회 */
  useEffect(() => {
    const fetchReservedSeats = async () => {
      try {
        const response = await api.get(`/reservation/seats/${movieId}`);
        setReservedSeats(response.data.data.map(Number));
      } catch (error: any) {
        console.log(error.message);
      }
    };
    fetchReservedSeats();
  }, [movieId]);

  /* 좌석 선택 토글 */
  const toggleSeat = (seatId: number) => {
    if (reservedSeats.includes(seatId)) return;

    setSelectedSeats((prev) =>
      prev.includes(seatId)
        ? prev.filter((id) => id !== seatId)
        : [...prev, seatId]
    );
  };

  /* 결제 시작 */
  const handlePayment = async () => {
    if (selectedSeats.length === 0) {
      alert("좌석을 선택하세요.");
      return;
    }

    // 로그인(세션) 확인
    await dispatch(checkSession());
    const orderId = `ORDER_${Date.now()}`;
    const amount = selectedSeats.length * 10000;

    sessionStorage.setItem(
      "reservationInfo",
      JSON.stringify({
        movieId,
        movieTitle: title,
        seatNumbers: selectedSeats,
      })
    );

    const tossPayments = new (window as any).TossPayments(
      process.env.REACT_APP_TOSS_CLIENT_KEY
    );

    try{
    await tossPayments.requestPayment("CARD", {
      amount,
      orderId,
      orderName: `${title} 좌석 예약`,
      successUrl: `${window.location.origin}/pay/paymentsuccess`,
      failUrl: `${window.location.origin}/pay/paymentfail`,
    })}
    catch(err : any){
        if (err?.code === "USER_CANCEL") {
    console.log("결제 취소");
    return;
  }
    console.error("결제 에러", err);
    alert("결제 중 오류가 발생했습니다.");
    }
  }

  return (
    <div className="restaurant">
      <h1>좌석 예약</h1>
      <h2>{title ? title : `영화 ID: ${movieId}`}</h2>
      <h4 className="mb-5">예약 보증금으로 1테이블당 10,000원의 보증금이 부과됩니다. 당일 취소시 환불되지 않습니다.</h4>
      <hr/>
      <div className="screen-label">🎬 SCREEN</div>

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
              <div className="chair top" />
              <div className="chairSide left" />
              <div className="table">{seatId}</div>
              <div className="chairSide right" />
              <div className="chair bottom" />
            </div>
          );
        })}
      </div>

      <div>
        <button className="reserve-btn" onClick={handlePayment}>
          결제하기
        </button>
        <button className="reserve-btn" onClick={goBack}>
          이전으로
        </button>
      </div>
    </div>
  );
}

export default SeatsReservation;
