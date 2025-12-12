import React from "react";
import axios from "axios";

declare global {
  interface Window {
    TossPayments: any;
  }
}

interface PaymentButtonProps {
  orderId: number;
}

const PaymentButton: React.FC<PaymentButtonProps> = ({ orderId }) => {
  const handlePayment = async () => {
    try {
      // 1️⃣ 서버에 결제 요청
      const res = await axios.post("/api/payments/request", {
        orderId,
      });

      const { amount, orderName, paymentOrderId } = res.data;

      // 2️⃣ 토스 결제 객체 생성
        const tossPayments = new window.TossPayments(
        process.env.REACT_APP_TOSS_CLIENT_KEY
        );


      // 3️⃣ 결제창 호출
      await tossPayments.requestPayment("CARD", {
        amount,
        orderId: paymentOrderId, // 서버에서 만든 orderId
        orderName,
        successUrl: `${window.location.origin}/payment/success`,
        failUrl: `${window.location.origin}/payment/fail`,
      });

    } catch (error) {
      console.error("결제 요청 실패", error);
      alert("결제 요청 중 오류가 발생했습니다.");
    }
  };

  return <button onClick={handlePayment}>결제하기</button>;
};

export default PaymentButton;