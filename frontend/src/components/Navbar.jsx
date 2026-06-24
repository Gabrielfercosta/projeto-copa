function Navbar({ usuario, pagina, setPagina, onLogout }) {
    return (
        <nav className="bg-[#0d1a2d] border-b border-gray-800 px-6 py-4">
            <div className="max-w-6xl mx-auto flex items-center justify-between">
                <span className="text-xl font-bold text-yellow-400 tracking-wide">⚽ BOLÃO COPA</span>

                <div className="flex gap-1">
                    <button
                        onClick={() => setPagina('jogos')}
                        className={`px-4 py-2 rounded-lg text-sm font-medium transition cursor-pointer ${
                            pagina === 'jogos'
                                ? 'text-yellow-400 border-b-2 border-yellow-400'
                                : 'text-gray-400 hover:text-white'
                        }`}
                    >
                        Jogos
                    </button>
                    <button
                        onClick={() => setPagina('ranking')}
                        className={`px-4 py-2 rounded-lg text-sm font-medium transition cursor-pointer ${
                            pagina === 'ranking'
                                ? 'text-yellow-400 border-b-2 border-yellow-400'
                                : 'text-gray-400 hover:text-white'
                        }`}
                    >
                        Ranking
                    </button>
                    <button
                        onClick={() => setPagina('regras')}
                        className={`px-4 py-2 rounded-lg text-sm font-medium transition cursor-pointer ${
                            pagina === 'regras'
                                ? 'text-yellow-400 border-b-2 border-yellow-400'
                                : 'text-gray-400 hover:text-white'
                        }`}
                    >
                        Regras
                    </button>
                </div>

                <div className="flex items-center gap-4">
                    <span className="text-gray-400 text-sm">Olá, <span className="text-white font-medium">{usuario.nome}</span></span>
                    <button
                        onClick={onLogout}
                        className="px-3 py-1.5 rounded-lg bg-red-900/30 border border-red-800 text-red-400 hover:bg-red-900/50 hover:text-red-300 text-xs font-medium transition cursor-pointer"
                    >
                        Sair
                    </button>
                </div>
            </div>
        </nav>
    )
}

export default Navbar
