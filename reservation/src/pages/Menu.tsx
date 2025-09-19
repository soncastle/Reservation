import React from 'react';
import '../styles/Menu.css';
import { useNavigate } from 'react-router-dom';
import MainPage from './MainPage';
import {useGoHomeAndMenu} from '../hooks/useGo';

const Menu = () => {
  const {goHome, goMenu} = useGoHomeAndMenu();  

  return (
    <div className="main-container">
      <h1>24시 김밥과 토스트 마리솔 & 야간포차 메뉴</h1>
    <div className="sub-container">
      <table className="menu-table">
        <caption></caption>
        <thead>
          <tr>
            <th>야채 토스트</th>
            <th>돌솥비빔밥</th>
            <th>야채비빔밥</th>
            <th>된장찌개</th>
            <th>육개장</th>
            <th>떡만두국</th>
            <th>만두국</th>
            <th>떡국</th>
            <th>김치비빔국수</th>
            <th>생선까스</th>
            <th>고구마치즈돈까스</th>
            <th>치즈돈까스</th>
            <th>등심돈까스</th>
            <th>꽁치김치찌개</th>
            <th>쫄면</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>3,000원</td>
            <td>8,000원</td>
            <td>7,000원</td>
            <td>7,000원</td>
            <td>8,000원</td>
            <td>7,000원</td>
            <td>7,000원</td>
            <td>7,000원</td>
            <td>6,500원</td>
            <td>8,000원</td>
            <td>9,000원</td>
            <td>8,500원</td>
            <td>8,000원</td>
            <td>8,000원</td>
            <td>7,000원</td>
          </tr>
        </tbody>
      </table>
    </div>
      <button onClick={goHome}>예약 확인</button>
     </div>
  );
};

export default Menu;