import { useNavigate } from 'react-router-dom'

export const useGoHomeAndMenu = () => {
    const navagate = useNavigate();
    const goHome = () => {
        navagate('/SeatsReservation');
    }
    const goMenu = () => {
        navagate('/Menu');
    }
  return {goHome, goMenu}
}

export const useGoShowMoviceList = () => {
    const navagate = useNavigate();
    const goMoviceList = () => {
        navagate('/MovieList');
    } 
    return {goMoviceList};
}

export const useGoMap = () => {
    const navagate = useNavigate();
    const goMap = () => {
        navagate('/Map');
    }
    return {goMap};
}