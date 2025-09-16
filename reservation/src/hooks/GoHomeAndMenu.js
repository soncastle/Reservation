import React from 'react'
import { useNavigate } from 'react-router-dom'

const GoHomeAndMenu = () => {
    const navagate = useNavigate();
    const handleGoHome = () => {
        navagate('/HomePage');
    }
    const handleGoMenu = () => {
        navagate('/Menu');
    }
  return {handleGoHome, handleGoMenu}
}

export default GoHomeAndMenu