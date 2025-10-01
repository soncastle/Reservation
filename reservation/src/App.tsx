import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import MainPage from './pages/MainPage';
import Header from './components/Header';
import Footer from './components/Footer';
import Menu from './pages/Menu';
import MovieList from './pages/MovieList';
import Map from './pages/Map';
import SeatsReservation from './pages/SeatsReservation';

function App() {
  return (
    <div className="App">
      <Header/>
      <hr />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage/>} />
          <Route path="/SeatsReservation/:movieId" element={<SeatsReservation />} />
          <Route path="/menu" element={<Menu/>}/>
          <Route path="/movielist" element={<MovieList/>}/>
          <Route path="/map" element={<Map/>}/>
        </Routes>
              <hr />
      <Footer/>
      </BrowserRouter>

    </div>
  );
}

export default App;
