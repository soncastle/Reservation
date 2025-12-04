import React, { useEffect, useState } from 'react'
import '../styles/Header.css'
import '../styles/Tailwind.css'
import { useLocation } from 'react-router-dom';
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMainPage, useGoMap, useGoMyPage, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo';
import api from '../common/api/axiosInstance';
import { checkSession } from '../common/redux/userSlice';
import { useDispatch } from 'react-redux';
import { AppDispatch } from '../common/redux/store';

const Header = () => {
    const imagePath: string = '/images/logo.png';
    const {goHome, goMenu} = useGoHomeAndMenu();
    const {goMoviceList} = useGoShowMoviceList();
    const {goLoginPage} = useGoLoginPage();
    const {goSignUpPage} = useGoSignUpPage();
    const {goIntroducePage} = useGoIntroducePage();
    const location = useLocation(); 
    const {goMyPage} = useGoMyPage();

const [isLogin, setIsLogin] = useState(false);
const [isEmail, setIsEmail] = useState(null);
const dispatch = useDispatch<AppDispatch>();
 
    useEffect(() => {
     const conformSesseion = async() => {
     try{ 
      await dispatch(checkSession()).unwrap();
     setIsLogin(true);
     } catch (err:any) {
      setIsLogin(false);
     }}
     conformSesseion();
    }, []);

    const handleLogout = async() => {
      try{
      await api.post("/user/logout", {});
        alert("로그아웃되었습니다.")
        setIsLogin(false);
        goHome();
    }catch (error){
      console.log(error);
      alert("로그아웃 실패");
    }
    }
    
  return (
    <div>
        <img className='w-80 mt-1' src={imagePath} alt="Header Image"></img>
        <div className="flex justify-center flex-wrap gap-2 border-8 mb-3">
        {location.pathname !== "/" &&(
        <button className='header-button' onClick={goHome}>메인으로</button>)}
        {location.pathname !== "/IntroducePage" &&(
        <button className='header-button' onClick={goIntroducePage}>솔이란?</button>)}
        {location.pathname !== "/Menu" &&(
        <button className='header-button' onClick={goMenu}>솔 음식</button>)}
        {location.pathname !== "/MovieList" &&(
        <button className='header-button' onClick={goMoviceList}>솔 영화</button>)}


          {!isLogin ? (
            <>    {location.pathname !== "/LoginPage" &&(
                     <button className='header-button' onClick={goLoginPage}>로그인</button>)}
                  {location.pathname !== "/SignUpPage" &&(
                     <button className='header-button' onClick={goSignUpPage}>회원가입</button>)}
            </>
                ) : (
                  <>
                    {location.pathname !== "/MyPage" &&(
                      <button className='header-button' onClick={goMyPage}>마이페이지</button>)}
                     <button className='header-button' onClick={handleLogout}>로그아웃</button>
                     </>
                )}
          </div>
    </div>
  )
}

export default Header