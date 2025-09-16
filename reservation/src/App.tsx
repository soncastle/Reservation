import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import HomePage from './pages/HomePage';
import MainPage from './pages/MainPage';
import Header from './components/Header';
import Footer from './components/Footer';
import Menu from './pages/Menu';

function App() {
  return (
    <div className="App">
      <Header/>
      <hr />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage/>} />
          <Route path="/homepage" element={<HomePage />} />
          <Route path="/menu" element={<Menu/>}/>
        </Routes>
      </BrowserRouter>
      <hr />
      <Footer/>
    </div>
  );
}

export default App;
