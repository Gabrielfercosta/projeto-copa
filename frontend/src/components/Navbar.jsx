function Navbar({ usuario, pagina, setPagina, onLogout }) {
    return (
        <nav className="bg-gray-800 border-b border-gray-700 p-4">
            <div className="max-w-6xl mx-auto flex items-center justify-between">
                <span className="text-white font-bold text-lg">Copa 2026</span>
                <div className="flex gap-4">
                    <button
                        onClick={() => setPagina('jogos')}
                        className={`px-3 py-1 rounded transition ${pagina === 'jogos' ? 'bg-green-600 text-white' : 'text-gray-400 hover:text-white'}`}
                    >
                        Jogos
                    </button>
                    <button
                        onClick={() => setPagina('ranking')}
                        className={`px-3 py-1 rounded transition ${pagina === 'ranking' ? 'bg-green-600 text-white' : 'text-gray-400 hover:text-white'}`}
                    >
                        Ranking
                    </button>
                </div>
                <button onClick={onLogout} className="text-red-400 hover:text-red-300 text-sm">
                    Sair
                </button>

            </div>
        </nav>
    )
}

export default Navbar
