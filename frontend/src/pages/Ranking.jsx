import { useState, useEffect } from 'react'

function Ranking() {
    const [ranking, setRanking] = useState([])

    useEffect(() => {
        fetch("https://bolao-copa-soft.up.railway.app/palpite/ranking")
            .then(res => res.json())
            .then(data => setRanking(data))
    }, [])

    return (
        <div className="min-h-screen bg-gray-900 p-8">
            <h1 className="text-3xl font-bold text-white text-center mb-8">Ranking</h1>
            <div className="max-w-md mx-auto">
                {ranking.map((r, index) => (
                    <div key={r.usuarioId} className="flex items-center justify-between bg-gray-800 p-4 rounded mb-2 border border-gray-700">
                        <div className="flex items-center gap-3">
                            <span className="text-green-400 font-bold text-lg">{index + 1}°</span>
                            <span className="text-white">{r.nome}</span>
                        </div>
                        <span className="text-green-400 font-bold">{r.totalPontos} pts</span>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Ranking
