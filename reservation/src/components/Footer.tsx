import React from 'react'
import { useGoMap } from '../hooks/useGo';
import '../styles/Tailwind.css';
import '../styles/Footer.css';

const Footer = () => {
  const {goMap} = useGoMap();
  return (
    <div>
    <button className='footer-button' onClick={goMap}>위치 : 경기 파주시 야당동 178-29</button>
    <div className='footer-text'>
    <div>솔 콜 : 031-123-4345</div>
    <div>시간 : 마시고 싶을 때까지</div>
    <div>요일 : 사장님 아프기 전까지</div>
    </div>
    <a href='https://www.instagram.com/gimbap_toast_paju/?igsh=MXh1YnI1ZDZzOG5taA%3D%3D#' className='no-underline hover:underline'>
    인스타그램
    </a>
    </div>
  )
}

export default Footer