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

export const useGoLoginPage = () =>{
    const navagate = useNavigate();
    const goLoginPage = () => {
        navagate('/LoginPage');
    }
    return {goLoginPage};
}

export const useGoSignUpPage = () => {
    const navagate = useNavigate();
    const goSignUpPage = () => {
        navagate('/SignUpPage');
    }
    return {goSignUpPage};
}

export const useGoMainPage = () => {
    const navagate = useNavigate();
    const goMainPage = () => {
        navagate('/');
    }
    return{goMainPage};
}

export const useGoBack = () => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return goBack;
}