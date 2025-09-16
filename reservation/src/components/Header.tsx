import React from 'react'

const Header = () => {
    const imagePath: string = '/images/logo.jpg ';
  return (
    <div>
        <img className='image' src={imagePath} alt="Header Image"></img>
    </div>
  )
}

export default Header