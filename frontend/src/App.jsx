import { useState } from 'react'
import Jogos from './pages/Jogos'
import Login from './pages/Login'
import Navbar from './components/Navbar'
import Ranking from './pages/Ranking'
import './App.css'
import Regras from "./pages/Regras.jsx";

function App() {
    const [usuario, setUsuario] = useState(() => {
        const salvo = localStorage.getItem('usuario')
        return salvo ? JSON.parse(salvo) : null
    })
    const [pagina, setPagina] = useState('jogos')

    function handleLogin(data) {
        localStorage.setItem('usuario', JSON.stringify(data))
        setUsuario(data)
    }

    function handleLogout() {
        localStorage.removeItem('usuario')
        setUsuario(null)
    }

    if (!usuario) {
        return <Login onLogin={handleLogin} />
    }

    return (
        <div>
            <Navbar usuario={usuario} pagina={pagina} setPagina={setPagina} onLogout={handleLogout} />
            {pagina === 'jogos' && <Jogos usuario={usuario} />}
            {pagina === 'ranking' && <Ranking />}
            {pagina === 'regras' && <Regras />}
        </div>
    )
}

export default App
