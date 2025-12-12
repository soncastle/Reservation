import axios from 'axios'
import React from 'react'
axios.defaults.withCredentials = true;

type SignupInfo = {
    email : string | null,
    name : string | null,
    signupDate : string | null,
    reservationCount : string | null,
}

const SignupList = () => {

    


    return (
    <div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이메일</th>
                    <th>이름</th>
                    <th>가입일자</th>
                    <th>예약횟수</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>
  )
}

export default SignupList