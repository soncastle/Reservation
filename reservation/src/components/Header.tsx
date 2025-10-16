import React from 'react'
import '../styles/Tailwind.css'

const Header = () => {
    const imagePath: string = '/images/logo.jpg ';
  return (
    <div>
        <img className='w-80' src={imagePath} alt="Header Image"></img>
    </div>
  )
}

export default Header