import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import MainPage from './pages/MainPage';
import Header from './components/Header';
import Footer from './components/Footer';
import Menu from './pages/Menu';
import MovieList from './pages/MovieList';
import Map from './pages/Map';
import SeatsReservation from './pages/SeatsReservation';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import IntroducePage from './pages/IntroducePage';
import MyPage from "./pages/MyPage";
import { useDispatch } from "react-redux";
import { AppDispatch } from "./common/redux/store";
import { useEffect } from "react";
import { checkSession } from "./common/redux/userSlice";
import PaymentSuccess from "./pages/pay/PaymentSuccess";
import PaymentFail from "./pages/pay/PaymentFail";

function App() {
  const dispatch = useDispatch<AppDispatch>();
  useEffect(() => {
    dispatch(checkSession());
  }, [dispatch]);

  return (
    <div className="App">
      <div className='backGroundColor'>
      <BrowserRouter>
            <Header/>
            
      <hr />

      <div className="main-content">
        <Routes>
          <Route path="/" element={<MainPage/>} />
          <Route path="/SeatsReservation/:movieId" element={<SeatsReservation />} />
          <Route path="/menu" element={<Menu/>}/>
          <Route path="/movielist" element={<MovieList/>}/>
          <Route path="/map" element={<Map/>}/>
          <Route path="/loginPage" element={<LoginPage/>}/>
          <Route path='/signuppage' element={<SignUpPage/>}/>
          <Route path='/introducepage' element={<IntroducePage/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
          <Route path="/pay/paymentsuccess" element={<PaymentSuccess/>}/>
          <Route path="/pay/paymentfail" element={<PaymentFail/>}/>
        </Routes>
        </div>
              <hr />
      <Footer/>
      </BrowserRouter>
      </div>
      </div>
  );
}

export default App;
