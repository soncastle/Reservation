import React from 'react'
import '../styles/Tailwind.css'
import Header from '../components/Header'
import Footer from '../components/Footer'
import { Link, useNavigate } from 'react-router-dom'
import { useGoHomeAndMenu, useGoIntroducePage, useGoLoginPage, useGoMap, useGoShowMoviceList, useGoSignUpPage } from '../hooks/useGo'


const MainPage = () => {
  const imagePath1:string ='/images/1.jpg';
  const imagePath2:string ='/images/2.jpg';
  const imagePath3:string ='/images/3.jpg';

  return (
    <div>
        <h1 className='mt-5'>마이리솔 영화관</h1>
    <div className='grid grid-cols-3 gap-4 mt-6 mb-7'>
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath1} alt="사진1" />
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath2} alt="사진2" />
    <img className="w-full h-64 object-cover rounded-lg" src={imagePath3} alt="사진3" />
</div>
    </div>
    
  )
}

export default MainPage