import React from 'react'
import '../styles/Header.css'
import '../styles/Tailwind.css'
import { useLocation } from 'react-router-dom';
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMainPage, useGoMap, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo';

const Header = () => {
    const imagePath: string = '/images/logo.png';
    const {goHome, goMenu} = useGoHomeAndMenu();
    const {goMoviceList} = useGoShowMoviceList();
    const {goLoginPage} = useGoLoginPage();
    const {goSignUpPage} = useGoSignUpPage();
    const {goIntroducePage} = useGoIntroducePage();
    const location = useLocation(); 
  return (
    <div>
        <img className='w-80 mt-1' src={imagePath} alt="Header Image"></img>
        <div className="flex justify-center flex-wrap gap-2 border-8 mb-3">
        {location.pathname !== "/IntroducePage" &&(
        <button className='header-button' onClick={goIntroducePage}>솔이란?</button>)}
        {location.pathname !== "/" &&(
        <button className='header-button' onClick={goHome}>메인으로</button>)}
        {location.pathname !== "/Menu" &&(
        <button className='header-button' onClick={goMenu}>솔 음식</button>)}
        {location.pathname !== "/MovieList" &&(
        <button className='header-button' onClick={goMoviceList}>솔이 영화</button>)}
        {location.pathname !== "/LoginPage" &&(
          <button className='header-button' onClick={goLoginPage}>로그인</button>)}
        {location.pathname !== "/SignPage" &&(
          <button className='header-button' onClick={goSignUpPage}>회원가입</button>)}
          </div>
    </div>
  )
}

export default Header