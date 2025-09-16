import React from 'react'
import Header from '../components/Header'
import Footer from '../components/Footer'
import { Link, useNavigate } from 'react-router-dom'
import GoHomeAndMenu from '../hooks/GoHomeAndMenu'


const MainPage = () => {
  const imagePath1:string ='/images/1.jpg';
  const imagePath2:string ='/images/2.jpg';
  const imagePath3:string ='/images/3.jpg';
    const {handleGoHome, handleGoMenu} = GoHomeAndMenu();
    // const navigate = useNavigate();
    // const handleGoHome = () => {
    //     navigate('/HomePage');
    // }
    // const handleGoMenu = () => {
    //     navigate('/Menu');
    // }
  return (
    <div>
        <h1>24시 김밥과 토스트 마리솔 & 야간포차</h1>
    <button onClick={handleGoHome}>자리 현황 확인</button>
    <button onClick={handleGoMenu}>메뉴 확인</button>
    <div>   
    <img className="image" src={imagePath1} alt="사진1" />
    <img className="image" src={imagePath2} alt="사진2" />
    <img className="image" src={imagePath3} alt="사진3" />
</div>
    </div>
    
  )
}

export default MainPage