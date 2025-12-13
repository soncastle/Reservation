import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import { useEffect } from "react";
import api from "../../common/api/axiosInstance";

const PaymentSuccess = () => {
  const [params] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const confirmAndReserve = async () => {
      const paymentKey = params.get("paymentKey");
      const orderId = params.get("orderId");
      const amount = Number(params.get("amount"));

      if (!paymentKey || !orderId) {
        alert("결제 정보가 올바르지 않습니다.");
        return;
      }

      // 1️⃣ 결제 승인
      await api.post("/payments/confirm", {
        paymentKey,
        orderId,
        amount,
      });

      // 2️⃣ 좌석 예약 확정
      const reservationInfo = sessionStorage.getItem("reservationInfo");
      if (!reservationInfo) {
        alert("예약 정보가 없습니다.");
        return;
      }

      const { movieId, movieTitle, seatNumbers } =
        JSON.parse(reservationInfo);

      await api.post("/reservation/movie", {
        movieId,
        movieTitle,
        seatNumbers,
      });

      sessionStorage.removeItem("reservationInfo");

      alert("결제 및 예약이 완료되었습니다!");
      navigate("/mypage"); // 또는 완료 페이지
    };

    confirmAndReserve();
  }, []);

  return <div>결제 처리 중입니다...</div>;
};

export default PaymentSuccess;