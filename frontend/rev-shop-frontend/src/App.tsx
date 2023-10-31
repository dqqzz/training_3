import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './components/navbar';
import Home from './components/Home';
import Browse from './components/Browse';
import Profile from './components/Profile';
import { ReactDOM } from 'react';


function App() {
  return (
    <div className="App">
      <header className="Header">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path = "/" element={<Home></Home>}/>
          <Route path = "/browse" element={<Browse></Browse>} />
          <Route path="/profile" element={<Profile></Profile>}/>
        </Routes>
      </BrowserRouter>      
      </header>
    </div>   
  );
}

export default App;
