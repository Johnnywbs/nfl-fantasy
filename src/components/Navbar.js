// src/components/Navbar.js

import React from 'react';
import './css/Navbar.css'; // Vamos importar o CSS para aplicar os estilos

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        {/* O logo/nome do site, que leva à página inicial */}
        <a href="/">NFL Fantasy Hub</a>
      </div>
      <ul className="navbar-links">
        {/* Lista de links de navegação */}
        <li><a href="/">Jogadores</a></li>
        <li><a href="/teams">Times</a></li>
        <li><a href="/about">Sobre</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;