import { useSearchParams } from "react-router-dom";
import axios from "axios";
import { useEffect } from "react";

const PaymentSuccess = () => {
  const [params] = useSearchParams();

  useEffect(() => {
    const confirmPayment = async () => {
      await axios.post("http://localhost:8080/api/payments/confirm", {
        paymentKey: params.get("paymentKey"),
        orderId: params.get("orderId"),
        amount: Number(params.get("amount")),
      });
    };
    confirmPayment();
  }, []);

  return <div>결제 처리 중입니다...</div>;
};

export default PaymentSuccess;