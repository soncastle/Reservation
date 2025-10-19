import React from 'react'
import { useGoMap } from '../hooks/useGo'

const Footer = () => {
  const {goMap} = useGoMap();
  return (
    <div>
    <button onClick={goMap}>찬이 출몰 위치 : 경기 파주시 야당동 178-29</button>
    <div>찬이 콜 : 031-123-4345</div>
    <div>시간 : 마시고 싶을 때까지</div>
    <div>요일 : 사장님 아프기 전까지</div>
    <a href='https://www.instagram.com/gimbap_toast_paju/?igsh=MXh1YnI1ZDZzOG5taA%3D%3D#'>
    인스타그램
    </a>
    </div>
  )
}

export default Footer