import React from 'react'
import "../styles/Tailwind.css";
import { useGoBack,  useGoMainPage } from '../hooks/useGo';

const IntroducePage = () => {
    const {goBack} = useGoBack();
  return (
    <div>
        <div>
            마리솔은 사시사철 푸른 소나무의 솔을 받아 24시, 언제든 당신을 기다립니다.
            <br/>
            오순도순 따뜻하고 맛있는 이야기를 나누면서 영화를 볼 수 있는 마리솔입니다.
        </div>
        <br/>
       <div className="overflow-hidden whitespace-nowrap">
  <div className="inline-block animate-slide">
    {Array(2).fill(0).map(() => (
      <>
        <img src="/images/4.jpg" className="inline-block w-80 h-80 mx-4 rounded-full object-cover" />
        <img src="/images/5.jpg" className="inline-block w-80 h-80 mx-4 rounded-full object-cover" />
        <img src="/images/6.jpg" className="inline-block w-80 h-80 mx-4 rounded-full object-cover" />
        <img src="/images/7.jpg" className="inline-block w-80 h-80 mx-4 rounded-full object-cover" />
        <img src="/images/8.jpg" className="inline-block w-80 h-80 mx-4 rounded-full object-cover" />
      </>
    ))}</div>
</div>
        <br/>
        <button onClick={goBack}>이전 페이지로</button>

    </div>
  )
}

export default IntroducePage