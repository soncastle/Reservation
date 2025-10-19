import React from 'react'
import '../styles/Tailwind.css'
import Header from '../components/Header'
import Footer from '../components/Footer'
import { Link, useNavigate } from 'react-router-dom'
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMap, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo'


const MainPage = () => {
  const imagePath1:string ='/images/1.jpg';
  const imagePath2:string ='/images/2.jpg';
  const imagePath3:string ='/images/3.jpg';
    const {goHome, goMenu} = useGoHomeAndMenu();
    const {goMoviceList} = useGoShowMoviceList();
    const {goMap} = useGoMap();
    const {goLoginPage} = useGoLoginPage();
    const {goSignUpPage} = useGoSignUpPage();
    const {goIntroducePage} = useGoIntroducePage();

  return (
    <div>
        <h1>순찬이네 영화관</h1>
    <button onClick={goIntroducePage}> 찬이네란? </button>
    <button onClick={goMenu}>메뉴 확인</button>
    <button onClick={goMoviceList}>영화 리스트 확인</button>
    <button onClick={goMap}>장소 확인</button>
    <button onClick={goLoginPage}>로그인</button>
    <button onClick={goSignUpPage}>회원가입</button>
    <div className='grid grid-cols-3 gap-4 mt-3 mb-3'>
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath1} alt="사진1" />
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath2} alt="사진2" />
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath3} alt="사진3" />
</div>
    </div>
    
  )
}

export default MainPage