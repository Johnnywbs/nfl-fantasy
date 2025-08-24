// src/components/PlayerList.js

import React, { useState, useEffect } from 'react';
import './css/PlayerList.css'; // Vamos adicionar um pouco de estilo

function PlayerList() {
  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(true); // Adiciona um estado de carregamento

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/player')
      .then(response => response.json())
      .then(data => {
        setPlayers(data);
        setLoading(false); // Desativa o carregamento após receber os dados
      })
      .catch(error => {
        console.error('Erro ao buscar dados da API:', error);
        setLoading(false); // Desativa o carregamento mesmo se der erro
      });
  }, []);

  if (loading) {
    return <p>A carregar jogadores...</p>;
  }

  return (
    <div className="player-container">
        <h1>NFL Fantasy Players - 2024</h1>
      <table className="player-table">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Posição</th>
            <th>Time</th>
            <th>Idade</th>
            <th>Jogos</th>
            <th>Jogos (titular)</th>
            <th>Pontos (Fantasy)</th>
            <th>PPR</th>
          </tr>
        </thead>
        <tbody>
          {players.map(player => (
            // A key é importante para o React otimizar a lista
            <tr key={player.id}>
              <td>{player.player_name}</td>
              <td>{player.position}</td>
              <td>{player.team}</td>
              <td>{player.age}</td>
              <td>{player.games}</td>
              <td>{player.games_started}</td>
              <td>{player.fantasy_points}</td>
              <td>{player.ppr}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default PlayerList;