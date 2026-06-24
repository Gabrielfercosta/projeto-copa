function Regras() {
    return (
        <div className="min-h-screen bg-gray-900 p-8">
            <div className="max-w-2xl mx-auto">
                <h1 className="text-3xl font-bold text-white text-center mb-2">Regras & Pontuação</h1>
                <p className="text-gray-400 text-center mb-8">
                    Entenda o sistema de pontuação e prepare seus palpites para o topo do ranking.
                </p>

                <h2 className="text-white font-bold text-lg mb-4 border-b border-gray-700 pb-2">Sistema de Pontuação</h2>

                <div className="flex flex-col gap-6">
                    <div className="bg-gray-800 p-5 rounded-lg border border-gray-700">
                        <div className="flex items-center gap-3 mb-2">
                            <span className="text-white font-bold text-lg">Placar Exato</span>
                            <span className="bg-green-600 text-white text-xs font-bold px-2 py-1 rounded">+3 PTS</span>
                        </div>
                        <p className="text-gray-400 mb-3">Você acerta exatamente o número de gols de cada equipe.</p>
                        <div className="flex items-center gap-2 text-sm">
                            <span className="bg-gray-700 text-gray-300 px-2 py-1 rounded">Ex: Jogo 2x1</span>
                            <span className="text-gray-500">→</span>
                            <span className="bg-gray-700 text-green-400 px-2 py-1 rounded">Palpite 2x1</span>
                        </div>
                    </div>

                    <div className="bg-gray-800 p-5 rounded-lg border border-gray-700">
                        <div className="flex items-center gap-3 mb-2">
                            <span className="text-white font-bold text-lg">Vencedor ou Empate</span>
                            <span className="bg-blue-600 text-white text-xs font-bold px-2 py-1 rounded">+1 PTS</span>
                        </div>
                        <p className="text-gray-400 mb-3">Você acerta o resultado da partida (quem venceu ou se foi empate), mas erra o placar.</p>
                        <div className="flex items-center gap-2 text-sm">
                            <span className="bg-gray-700 text-gray-300 px-2 py-1 rounded">Ex: Jogo 2x1</span>
                            <span className="text-gray-500">→</span>
                            <span className="bg-gray-700 text-blue-400 px-2 py-1 rounded">Palpite 1x0</span>
                        </div>
                    </div>

                    <div className="bg-gray-800 p-5 rounded-lg border border-gray-700">
                        <div className="flex items-center gap-3 mb-2">
                            <span className="text-white font-bold text-lg">Erro Total</span>
                            <span className="bg-red-600 text-white text-xs font-bold px-2 py-1 rounded">0 PTS</span>
                        </div>
                        <p className="text-gray-400 mb-3">Você erra o vencedor e o resultado da partida.</p>
                        <div className="flex items-center gap-2 text-sm">
                            <span className="bg-gray-700 text-gray-300 px-2 py-1 rounded">Ex: Jogo 2x1</span>
                            <span className="text-gray-500">→</span>
                            <span className="bg-gray-700 text-red-400 px-2 py-1 rounded">Palpite 0x1</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Regras
