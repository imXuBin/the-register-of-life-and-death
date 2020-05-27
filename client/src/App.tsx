import React, { useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import { get } from './https';

function App() {
  useEffect(() => {
    const welcome = async (name: string) => {
      const { data } = await get('/api/welcome', { name })
      alert(data)
    }
    welcome('peter')
  }, [])
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
