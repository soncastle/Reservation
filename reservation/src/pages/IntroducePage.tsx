import React from 'react'
import "../styles/Tailwind.css";
import { useGoBack,  useGoMainPage } from '../hooks/useGo';

const IntroducePage = () => {
    const {goBack} = useGoBack();
  return (
    <div>
        <div>
            마리솔은 사시사철 푸른 소나무의 솔을 받아 24시, 언제든 당신을 기다립니다. 
        </div>
        <button onClick={goBack}>이전 페이지로</button>

    </div>
  )
}

export default IntroducePage