import { useSearchParams, useNavigate } from "react-router-dom";

const PaymentFail = () => {
  const [params] = useSearchParams();
  const navigate = useNavigate();

  const errorCode = params.get("code");
  const errorMessage = params.get("message");

  return (
    <div>
      <h2>결제 실패</h2>
      <p>사유: {errorMessage}</p>
      <button onClick={() => navigate("/")}>홈으로 돌아가기</button>
    </div>
  );
};

export default PaymentFail;
