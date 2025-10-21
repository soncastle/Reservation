import React from 'react'
import "../styles/Tailwind.css";
import { useGoBack,  useGoMainPage } from '../hooks/useGo';

const IntroducePage = () => {
  return (
    <div>
        <div className='font-sans mt-2 font-semibold text-lg'>
            마이리솔(My Little Sol)은 사시사철 푸른 소나무의 솔을 받아 24시, 언제든 당신을 기다립니다.
            <br/>
            따뜻하고 맛있는 음식과 오순도순 이야기를 나누면서 영화를 볼 수 있는 마이리솔입니다.
        </div>
        <br/>
       <div className="overflow-hidden whitespace-nowrap">
  <div className="inline-block animate-slide bt-2 mb-7">
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
    </div>
  )
}

export default IntroducePage