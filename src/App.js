// src/App.js

import React from 'react';
import Navbar from './components/Navbar'; // 1. Importe o componente que você criou
import PlayerList from './components/PlayerList';
import './App.css';

function App() {
  return (
    <div className="App">
      {/* 2. Adicione a Navbar aqui para que ela apareça no topo de tudo */}
      <Navbar />

      <main className="main-content">
        <PlayerList />
      </main>
    </div>
  );
}

export default App;