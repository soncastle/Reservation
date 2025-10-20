import React from 'react'
import '../styles/Tailwind.css'
import { useLocation } from 'react-router-dom';
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMainPage, useGoMap, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo';

const Header = () => {
    const imagePath: string = '/images/marisol.png';
    // const {goMainPage} = useGoMainPage();
    const {goHome, goMenu} = useGoHomeAndMenu();
    const {goMoviceList} = useGoShowMoviceList();
    const {goMap} = useGoMap();
    const {goLoginPage} = useGoLoginPage();
    const {goSignUpPage} = useGoSignUpPage();
    const {goIntroducePage} = useGoIntroducePage();
    const location = useLocation(); 
  return (
    <div>
        <img className='w-80' src={imagePath} alt="Header Image"></img>
        <div className="flex justify-center flex-wrap gap-2 mt-2">
        {location.pathname !== "/IntroducePage" &&(
        <button onClick={goIntroducePage}>찬이란?</button>)}
        {location.pathname !== "/" &&(
        <button onClick={goHome}>메인으로</button>)}
        {location.pathname !== "/Menu" &&(
        <button onClick={goMenu}>찬이 음식</button>)}
        {location.pathname !== "/MovieList" &&(
        <button onClick={goMoviceList}>찬이 영화</button>)}
        {location.pathname !== "/LoginPage" &&(
          <button onClick={goLoginPage}>로그인</button>)}
        {location.pathname !== "/SignPage" &&(
          <button onClick={goSignUpPage}>회원가입</button>)}
          </div>
    <br/>
    </div>
  )
}

export default Header