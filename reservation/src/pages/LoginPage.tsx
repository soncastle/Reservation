import React, { useState } from "react";
import "../styles/LoginPage.css";
import { useGoHomeAndMenu } from "../hooks/useGo";
import api from "../common/api/axiosInstance";

const LoginPage: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const {goHome} = useGoHomeAndMenu();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try{
      const response = await api.post("/user/login", {
        email,
        password,
      })
      alert(`안녕하세요! ${response.data.data} 님!`);
      goHome();
      window.location.reload();
    }catch (error: any){
        alert(error.message)
      }
  };

  return (
    <div className="login-container">
      <h2 className="login-title">로그인</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="email">이메일</label>
          <input
            type="email"
            id="email"
            placeholder="이메일을 입력하세요"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호를 입력하세요"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="login-button">
          로그인
        </button>
      </form>
    </div>
  );
};

export default LoginPage;
