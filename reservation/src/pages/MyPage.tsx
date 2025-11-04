import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import axios from "axios";
import { useGoBack, useGoHomeAndMenu } from "../hooks/useGo";
import "../styles/Tailwind.css";
axios.defaults.withCredentials = true;

type ReservationInfo = {
  movieTitle : string | null,
  reservationTime : string | null,
  seatNumbers : string | null
}

function MyPage() {
  const {goHome} = useGoHomeAndMenu();
  const [isEmail, setIsEmail] = useState(null);
  const [isReservationData, setReservationData] = useState<ReservationInfo[]>([]); 
  // const [ReservationInfo, setReservationInfo] = useState<ReservationInfo>({
  //   movieTitle : null,
  //   reservationTime : null,
  //   reservationSeats : null
  // });


useEffect(() => {
  const checkSession = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/user/checkSession", {
        withCredentials: true
      });
      if(response.data){
        setIsEmail(response.data.email);
        }
      const userReservionData = await axios.get("http://localhost:8080/api/user/userReservation", {
        withCredentials: true
      })
      if(Array.isArray(userReservionData.data)){
        setReservationData(userReservionData.data);
      }
      
    } catch {
      alert("로그인 후 이용바랍니다.");
    }
  };
  checkSession();
}, []); //안넣어도 되지만 내부에 외부함수를 사용하였으므로, 랜더링즉시 실행되는 useEffect가 변화를 감지하여 다시 실행될 수 있도록 안전상 넣어둔 것.


  return (
    <div>
      <h3 className="text-2xl mt-5 font-bold">
       예약자 이메일 : {isEmail}
      </h3>
      <div className="mypag p-8">
        <h2 className="text-4xl font-bold mb-4">예약 내역</h2>
        <table className="min-w-full border border-gray-300 text-sm text-center">
          <thead className="bg-gray-100">
            <tr>
              <th className="border px-4 py-2">영화 제목</th>
              <th className="border px-4 py-2">좌석 번호</th>
              <th className="border px-4 py-2">예약 시간</th>
            </tr>
          </thead>
          <tbody>
            {isReservationData.length > 0 ? (
              isReservationData.map((respone) => (
                <tr key={respone.reservationTime} className="hover:bg-gray-50">
                  <td className="border px-4 py-2">{respone.movieTitle}</td>
                  <td className="border px-4 py-2">{respone.seatNumbers}</td>
                  <td className="border px-4 py-2">{respone.reservationTime}</td>
                </tr>
              ))
            ) :(
              <tr>
                <td colSpan={3} className="py-4 text-gray-500">
                  예약내역이 없습니다.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default MyPage;
