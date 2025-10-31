import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import "../styles/SeatsReservation.css";
import axios from "axios";
import { useGoBack } from "../hooks/useGo";
axios.defaults.withCredentials = true;

const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);


useEffect(() => {
  const checkSession = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/user/checkSession", {
        withCredentials: true
      });
      setIsLoggedIn(!!response.data);
    } catch {
      setIsLoggedIn(false);
    }
  };
  checkSession();
}, []);

function MyPage() {

  return (
    <div className="mypage">
      <table>

      </table>
    </div>
  );
}

export default MyPage;
