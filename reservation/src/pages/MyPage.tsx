import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import axios from "axios";
import { useGoBack, useGoHomeAndMenu } from "../hooks/useGo";
import "../styles/Tailwind.css";
import api from "../common/api/axiosInstance";
import { ApiError } from "../common/api/errorHandler";
axios.defaults.withCredentials = true;

type ReservationInfo = {
  movieTitle : string | null,
  reservationTime : string | null,
  seatNumbers : string | null,
  reservationState : String | null,
  cancelTime : String | null
}

function MyPage() {
  const [isEmail, setIsEmail] = useState(null);
  const [isReservationData, setReservationData] = useState<ReservationInfo[]>([]); 


const fatchUserReservationData = async () => {
const userReservionData = await api.get("/user/userReservation")
      if(Array.isArray(userReservionData.data)){
        setReservationData(userReservionData.data);
      }
}

useEffect(() => {
  const checkSession = async () => {
    try {
      const response = await api.get("/user/checkSession");
      await fatchUserReservationData();
      if(response.data){
        setIsEmail(response.data.email);
        }      
    } catch(error : any) {
      alert(error.message);
    }
  };
  checkSession();
}, []); //안넣어도 되지만 내부에 외부함수를 사용하였으므로, 랜더링즉시 실행되는 useEffect가 변화를 감지하여 다시 실행될 수 있도록 안전상 넣어둔 것.
const handleCancel = async (reservationTime : string) => {
 
  try{
    await api.post("/reservation/movie/cancel", {
      reservationTime: reservationTime
  })
  alert("취소가 완료되었습니다")
  await fatchUserReservationData();
  }catch(error: any){
    console.log(error.message);
  }
}

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
              <th className="border px-4 py-2">예약 / 취소 시간</th>
              <th className="border px-4 py-2">예약 상태</th>
            </tr>
          </thead>
          <tbody>
            {isReservationData.length > 0 ? (
              isReservationData.map((respone) => (
                <tr key={respone.reservationTime} className="hover:bg-gray-50">
                  <td className="border px-4 py-2">{respone.movieTitle}</td>
                  <td className="border px-4 py-2">{respone.seatNumbers}</td>
                  <td className="border px-4 py-2">{respone.reservationState === "취소"
                    ? respone.cancelTime : respone.reservationTime}</td>
                  <td>{respone.reservationState === "취소" ? "취소" : <button onClick={() => handleCancel(respone.reservationTime!)} className="px-5 py-1 bg-orange-200 text-black rounded hover:bg-orange-800 transition"> 예약 취소</button>}</td>
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