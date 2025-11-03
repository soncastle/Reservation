import React, { useEffect, useState } from 'react'
import '../styles/Header.css'
import '../styles/Tailwind.css'
import { useLocation } from 'react-router-dom';
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMainPage, useGoMap, useGoMyPage, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo';
import axios from 'axios';

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

    useEffect(() => {
      const checkLogin = async () => {
        try {
          const response = await axios.get("http://localhost:8080/api/user/checkSession", {
            withCredentials: true,
          });
          if (response.data.email)  {
            setIsLogin(true);
            setIsEmail(response.data.email);
          }
        } catch {
          setIsLogin(false);
        }
      };
      checkLogin();
    }, []);

    const handleLogout = async() => {
      try{
      await axios.post("http://localhost:8080/api/user/logout", {}, {withCredentials : true});
        alert("로그아웃되었습니다.")
        setIsLogin(false);
        window.location.reload();
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
                    <button className='header-button' onClick={goMyPage}>마이페이지</button>
                     <button className='header-button' onClick={handleLogout}>로그아웃</button>
                     </>
                )}
          </div>
    </div>
  )
}

export default Header