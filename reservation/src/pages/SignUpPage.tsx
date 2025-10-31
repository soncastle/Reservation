import React, { useState } from "react";
import "../styles/SignUpPage.css";
import axios from "axios";
import { useGoLoginPage } from "../hooks/useGo";

const SignUpPage: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [userName, setUserName] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");

  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const { goLoginPage } = useGoLoginPage();

  const validateEmail = (email: string) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  };

  /** ✅ 비밀번호 유효성 검사 (8자 이상, 숫자+문자 조합) */
  const validatePassword = (password: string) => {
    const regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;
    return regex.test(password);
  };

  const validateForm = () => {
    const newErrors: { [key: string]: string } = {};

    if (!email.trim()) newErrors.email = "이메일을 입력해주세요.";
    else if (!validateEmail(email))
      newErrors.email = "올바른 이메일 형식이 아닙니다.";

    if (!userName.trim()) newErrors.userName = "이름을 입력해주세요.";
    else if (userName.length < 2)
      newErrors.userName = "이름은 2글자 이상이어야 합니다.";

    if (!password) newErrors.password = "비밀번호를 입력해주세요.";
    else if (!validatePassword(password))
      newErrors.password = "비밀번호는 8자 이상, 숫자+문자 조합이어야 합니다.";

    if (password !== confirmPassword)
      newErrors.confirmPassword = "비밀번호가 일치하지 않습니다.";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // 에러 없으면 true
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) return;

    try {
      const response = await axios.post("http://localhost:8080/api/user/signup", {
        email,
        userName,
        password,
      });

      alert("회원가입 성공!");
      console.log(response.data);
      goLoginPage();
    } catch (error) {
      console.error(error);
      alert("회원가입 실패! 이미 존재하는 이메일일 수 있습니다.");
    }
  };

  return (
    <div className="signup-container">
      <h2 className="signup-title">회원가입</h2>
      <form className="signup-form" onSubmit={handleSubmit}>
        {/* 이메일 */}
        <div className="form-group">
          <label htmlFor="email">이메일</label>
          <input
            type="email"
            id="email"
            placeholder="이메일을 입력하세요"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className={errors.email ? "input-error" : ""}
            required
          />
          {errors.email && <p className="error-message">{errors.email}</p>}
        </div>

        {/* 이름 */}
        <div className="form-group">
          <label htmlFor="userName">이름</label>
          <input
            type="text"
            id="userName"
            placeholder="이름을 입력하세요"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            className={errors.userName ? "input-error" : ""}
            required
          />
          {errors.userName && <p className="error-message">{errors.userName}</p>}
        </div>

        {/* 비밀번호 */}
        <div className="form-group">
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호를 입력하세요"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className={errors.password ? "input-error" : ""}
            required
          />
          {errors.password && <p className="error-message">{errors.password}</p>}
        </div>

        {/* 비밀번호 확인 */}
        <div className="form-group">
          <label htmlFor="confirmPassword">비밀번호 확인</label>
          <input
            type="password"
            id="confirmPassword"
            placeholder="비밀번호를 다시 입력하세요"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            className={errors.confirmPassword ? "input-error" : ""}
            required
          />
          {errors.confirmPassword && (
            <p className="error-message">{errors.confirmPassword}</p>
          )}
        </div>

        <button type="submit" className="signup-button">
          회원가입
        </button>
      </form>
    </div>
  );
};

export default SignUpPage;
