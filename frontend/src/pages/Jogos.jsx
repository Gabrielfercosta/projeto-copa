import { useState, useEffect } from 'react'

function Jogos({ usuario }) {
    const [jogos, setJogos] = useState([])
    const [palpites, setPalpites] = useState([])
    const [meusPalpites, setMeusPalpites] = useState({})

    useEffect(() => {
        fetch("http://localhost:8080/palpite")
            .then(res => res.json())
            .then(data => {
                const meus = {}
                data.filter(p => p.usuario === usuario.id).forEach(p => {
                    meus[p.jogoId] = { time1: p.placarTime1, time2: p.placarTime2 }
                })
                setMeusPalpites(meus)
            })
    }, [])

    useEffect(() => {
        fetch("http://localhost:8080/jogo")
            .then(response => response.json())
            .then(data => setJogos(data))
    }, [])

    function enviarPalpite(jogoId) {
        if(!palpites[jogoId] || !palpites[jogoId].time1 || !palpites[jogoId].time2){
            alert("O valor do placar não pode ser nulo");
            return;
        }
        fetch("http://localhost:8080/palpite", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                usuario: usuario.id,
                jogoId: jogoId,
                placarTime1: Number(palpites[jogoId].time1),
                placarTime2: Number(palpites[jogoId].time2)
            })
        })
            .then(res => res.json())
            .then(data => {
                alert("Palpite enviado")
                setMeusPalpites({
                    ...meusPalpites,
                    [jogoId]: { time1: palpites[jogoId].time1, time2: palpites[jogoId].time2 }
                })
            })
            .catch(err => alert("Erro ao enviar palpite"))
    }


    return (
        <div className="min-h-screen bg-gray-900 p-8">
            <h1 className="text-3xl font-bold text-white text-center mb-8">Copa do Mundo</h1>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-4 max-w-6xl mx-auto">
                {jogos.map(jogo => (
                    <div key={jogo.id} className="bg-gray-800 rounded-lg p-4 border border-gray-700 hover:border-green-500 transition">
                        <div className="text-white">
                            {new Date(jogo.dataHora).toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' })}
                        </div>
                        <div className="relative flex items-center justify-between min-h-[40px] gap-2">
                            <span className="text-white font-medium text-sm w-24 text-left">{jogo.time1}</span>
                            <img src={jogo.crestTime1} alt={jogo.time1} className="w-8 h-8" />
                            <input
                                type="number"
                                className="w-10 h-8 text-center bg-gray-700 text-white rounded border border-gray-600 focus:border-green-500 outline-none z-10"
                                placeholder="0"
                                min="0"
                                onInput={(e) => { if (e.target.value < 0) e.target.value = 0 }}
                                onChange={(e) => setPalpites({
                                    ...palpites,
                                    [jogo.id]: { ...palpites[jogo.id], time1: e.target.value }
                                })}
                                value={palpites[jogo.id]?.time1 ?? meusPalpites[jogo.id]?.time1 ?? ''}
                                disabled={jogo.status !== "TIMED" || meusPalpites[jogo.id] !== undefined}
                            />
                            <div className="bg-gray-900 px-3 py-1 rounded">
                                <span className="text-green-400 font-bold">
                                    {jogo.placarTime1 ?? '-'} x {jogo.placarTime2 ?? '-'}
                                </span>
                            </div>
                            <input
                                type="number"
                                className="w-10 h-8 text-center bg-gray-700 text-white rounded border border-gray-600 focus:border-green-500 outline-none z-10"
                                placeholder="0"
                                min="0"
                                onInput={(e) => { if (e.target.value < 0) e.target.value = 0 }}
                                onChange={(e) => setPalpites({
                                    ...palpites,
                                    [jogo.id]: { ...palpites[jogo.id], time2: e.target.value }
                                })}
                                value={palpites[jogo.id]?.time2 ?? meusPalpites[jogo.id]?.time2 ?? ''}
                                disabled={jogo.status !== "TIMED" || meusPalpites[jogo.id] !== undefined}
                            />
                            <img src={jogo.crestTime1} alt={jogo.time1} className="w-8 h-8" />
                            <span className="text-white font-medium text-sm w-24 text-right">{jogo.time2}</span>
                        </div>
                        <div className="mt-2 text-center">
                            <span className={`text-xs px-2 py-1 rounded ${
                                jogo.status === 'FINISHED' ? 'bg-red-900 text-red-300' :
                                    jogo.status === 'TIMED' ? 'bg-blue-900 text-blue-300' :
                                        jogo.status === 'IN_PLAY' ? 'bg-green-900 text-green-300' :
                                            'bg-gray-700 text-gray-300'
                            }`}>
                                {jogo.status === 'FINISHED' ? 'Encerrado' :
                                    jogo.status === 'TIMED' ? 'Agendado' :
                                        jogo.status === 'IN_PLAY' ? 'Ao Vivo' :
                                            jogo.status === 'LIVE' ? 'Ao Vivo' :
                                                jogo.status}
                            </span>
                        </div>
                        <button onClick={() => enviarPalpite(jogo.id)}
                                className="mt-3 w-full bg-green-600 hover:bg-green-500 text-white font-bold py-2 px-4 rounded transition disabled:bg-gray-600 disabled:cursor-not-allowed"
                                disabled={jogo.status !== "TIMED" || meusPalpites[jogo.id] !== undefined}>
                            Enviar
                        </button>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Jogos
