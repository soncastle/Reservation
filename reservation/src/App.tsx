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
import { useAuth } from "./hooks/useAuth";
import MyPage from "./pages/MyPage";

function App() {
  const {isLoggedIn} = useAuth();
  const userRole = localStorage.getItem("role")
  return (
    <div className="App">
      <div className='backGroundColor'>
      <BrowserRouter>
            <Header/>
            
      <hr />
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
        </Routes>
              <hr />
      <Footer/>
      </BrowserRouter>
      </div>
      </div>
  );
}

export default App;
