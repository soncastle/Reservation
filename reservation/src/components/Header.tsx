import React from 'react'
import '../styles/Tailwind.css'
import { useLocation } from 'react-router-dom';
import { useGoHomeAndMenu, useGoMainPage } from '../hooks/useGo';

const Header = () => {
    const imagePath: string = '/images/logo.jpg ';
    // const {goMainPage} = useGoMainPage();
    const {goHome} = useGoHomeAndMenu();
    const location = useLocation(); 
  return (
    <div>
        <img className='w-80' src={imagePath} alt="Header Image"></img>
        <br/>
        {location.pathname !== "/" &&(
        <button onClick={goHome}>메인으로</button>
        )}
    </div>
  )
}

export default Header