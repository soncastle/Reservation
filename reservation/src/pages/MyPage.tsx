import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/Tailwind.css";
import api from "../common/api/axiosInstance";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../common/redux/store";
import { checkSession } from "../common/redux/userSlice";
import { useGoHomeAndMenu } from "../hooks/useGo";
axios.defaults.withCredentials = true;

type ReservationInfo = {
  movieTitle : string | null,
  reservationTime : string | null,
  seatNumbers : string | null,
  reservationState : String | null,
  cancelTime : String | null,
  refunded : string | null

}

function MyPage() {
  const dispatch = useDispatch<AppDispatch>();
  const [isEmail, setIsEmail] = useState(null);
  const [isReservationData, setReservationData] = useState<ReservationInfo[]>([]);
  const {goHome} = useGoHomeAndMenu(); 


const fatchUserReservationData = async () => {
const userReservationData = await api.get("/user/reservations")
      if(Array.isArray(userReservationData.data.data)){
        setReservationData(userReservationData.data.data);
      }
}


useEffect(() => {
    const handelCheckSession = async () => {
      try {
        const res = await dispatch(checkSession()).unwrap();
        setIsEmail(res.data.email);
        fatchUserReservationData();
      } catch (err: any) {
        alert(err.message);
        goHome();
      }
    }
  handelCheckSession();
  }, []);

const handleCancel = async (reservationTime : string) => {
  try{
    await api.patch("/reservation/movie/cancel", {
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
                    ?(<>{respone.cancelTime}
                      <br/>{respone.refunded}
                    </> ): respone.reservationTime}</td>
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