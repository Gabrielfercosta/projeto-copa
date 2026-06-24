import { useState, useEffect } from 'react'

function Jogos({ usuario }) {
    const [jogos, setJogos] = useState([])
    const [palpites, setPalpites] = useState([])
    const [meusPalpites, setMeusPalpites] = useState({})
    const [valorSelecionado, setValorSelecionado] = useState('todos');
    const [editando, setEditando] = useState({})

    const handleChange = (event) => {
        setValorSelecionado(event.target.value);
    };

    useEffect(() => {
        fetch("https://bolao-copa-soft.up.railway.app/palpite")
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
        fetch("https://bolao-copa-soft.up.railway.app/jogo")
            .then(response => response.json())
            .then(data => setJogos(data))
    }, [])

    function enviarPalpite(jogoId) {
        if(!palpites[jogoId] || !palpites[jogoId].time1 || !palpites[jogoId].time2){
            alert("O valor do placar não pode ser nulo");
            return;
        }
        fetch("https://bolao-copa-soft.up.railway.app/palpite", {
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

    function atualizarPalpite(jogoId) {
        if (!palpites[jogoId] || !palpites[jogoId].time1 || !palpites[jogoId].time2) {
            alert("O valor do placar não pode ser nulo");
            return;
        }
        fetch(`https://bolao-copa-soft.up.railway.app/palpite/${jogoId}`, {
            method: "PUT",
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
                alert("Palpite atualizado")
                setMeusPalpites({
                    ...meusPalpites,
                    [jogoId]: { time1: palpites[jogoId].time1, time2: palpites[jogoId].time2 }
                })
                setEditando({ ...editando, [jogo.id]: false })
            })
            .catch(err => alert("Erro ao atualizar palpite"))
    }

    return (
        <div className="min-h-screen bg-[#0a1128] p-8">
            <div className="max-w-6xl mx-auto">
                <h1 className="text-3xl font-bold text-white mb-2">| Palpites do Dia</h1>
                <p className="text-gray-400 mb-6 ml-4">Acompanhe os jogos e garanta seus pontos.</p>

                <div className="mb-6">
                    <select
                        value={valorSelecionado}
                        onChange={handleChange}
                        className="bg-[#131d3b] text-white px-4 py-2 rounded border border-gray-700 focus:border-yellow-500 outline-none cursor-pointer"
                    >
                        <option value="todos">Todos</option>
                        <option value="encerrados">Encerrados</option>
                        <option value="agendados">Agendados</option>
                        <option value="ao vivo">Ao Vivo</option>
                    </select>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    {jogos.filter(jogo => {
                        if (valorSelecionado === 'todos') return true
                        if (valorSelecionado === 'encerrados') return jogo.status === 'FINISHED'
                        if (valorSelecionado === 'agendados') return jogo.status === 'TIMED'
                        if (valorSelecionado === 'ao vivo') return jogo.status === 'IN_PLAY'
                    }).map(jogo => (
                        <div key={jogo.id} className="bg-[#131d3b] rounded-xl p-5 border border-gray-800">
                            <div className="flex items-center justify-between mb-4">
                                <div className="flex items-center gap-2">
                                    <span className="text-gray-400 text-sm">
                                        {new Date(jogo.dataHora).toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' })}
                                    </span>
                                </div>
                                <span className={`text-xs px-2 py-1 rounded ${
                                    jogo.status === 'FINISHED' ? 'bg-red-900/50 text-red-300' :
                                        jogo.status === 'TIMED' ? 'bg-blue-900/50 text-blue-300' :
                                            jogo.status === 'IN_PLAY' ? 'bg-green-900/50 text-green-300' :
                                                'bg-gray-700 text-gray-300'
                                }`}>
                                    {jogo.status === 'FINISHED' ? 'Encerrado' :
                                        jogo.status === 'TIMED' ? 'Agendado' :
                                            jogo.status === 'IN_PLAY' ? 'Ao Vivo' :
                                                jogo.status === 'PAUSED' ? 'Intervalo' :
                                                jogo.status}
                                </span>
                            </div>

                            <div className="flex items-center justify-between">
                                <div className="flex flex-col items-center gap-2 w-24">
                                    <img src={jogo.crestTime1} alt={jogo.time1} className="w-14 h-14" />
                                    <span className="text-white font-bold text-xs text-center uppercase">{jogo.time1}</span>
                                </div>

                                <div className="flex items-center gap-3">
                                    <input
                                        type="number"
                                        className="w-12 h-12 text-center bg-[#0a1128] text-white text-xl font-bold rounded-lg border border-gray-700 focus:border-yellow-500 outline-none"
                                        placeholder="-"
                                        min="0"
                                        onInput={(e) => { if (e.target.value < 0) e.target.value = 0 }}
                                        onChange={(e) => setPalpites({
                                            ...palpites,
                                            [jogo.id]: { ...palpites[jogo.id], time1: e.target.value }
                                        })}
                                        value={palpites[jogo.id]?.time1 ?? meusPalpites[jogo.id]?.time1 ?? ''}
                                        disabled={jogo.status !== "TIMED" || (meusPalpites[jogo.id] !== undefined && !editando[jogo.id])}
                                    />
                                    <span className="text-yellow-400 font-bold text-lg">x</span>
                                    <input
                                        type="number"
                                        className="w-12 h-12 text-center bg-[#0a1128] text-white text-xl font-bold rounded-lg border border-gray-700 focus:border-yellow-500 outline-none"
                                        placeholder="-"
                                        min="0"
                                        onInput={(e) => { if (e.target.value < 0) e.target.value = 0 }}
                                        onChange={(e) => setPalpites({
                                            ...palpites,
                                            [jogo.id]: { ...palpites[jogo.id], time2: e.target.value }
                                        })}
                                        value={palpites[jogo.id]?.time2 ?? meusPalpites[jogo.id]?.time2 ?? ''}
                                        disabled={jogo.status !== "TIMED" || (meusPalpites[jogo.id] !== undefined && !editando[jogo.id])}
                                    />
                                </div>
                                <div className="flex flex-col items-center gap-2 w-24">
                                    <img src={jogo.crestTime2} alt={jogo.time2} className="w-14 h-14" />
                                    <span className="text-white font-bold text-xs text-center uppercase">{jogo.time2}</span>
                                </div>
                            </div>
                            <div className="mt-3 text-center">
                                <span className="text-gray-400 text-sm">Placar: </span>
                                <span className="text-yellow-400 font-bold">{jogo.placarTime1} x {jogo.placarTime2}</span>
                            </div>
                            {meusPalpites[jogo.id] && jogo.status === 'TIMED' && !editando[jogo.id] ? (
                                <button
                                    onClick={() => setEditando({ ...editando, [jogo.id]: true })}
                                    className="mt-4 w-full bg-transparent border border-yellow-500 text-yellow-500 hover:bg-yellow-500 hover:text-black font-bold py-2 px-4 rounded-lg transition cursor-pointer uppercase tracking-wide text-sm"
                                >
                                    Editar Palpite
                                </button>
                            ) : (
                                <button
                                    onClick={() => editando[jogo.id] ? atualizarPalpite(jogo.id) : enviarPalpite(jogo.id)}
                                    className="mt-4 w-full bg-yellow-500 hover:bg-yellow-400 text-black font-bold py-3 px-4 rounded-lg transition disabled:bg-gray-700 disabled:text-gray-500 disabled:cursor-not-allowed cursor-pointer uppercase tracking-wide"
                                    disabled={jogo.status !== "TIMED" || (meusPalpites[jogo.id] !== undefined && !editando[jogo.id])}
                                >
                                    {jogo.status === 'FINISHED' ? 'Palpites Finalizados' :
                                        editando[jogo.id] ? 'Atualizar Palpite' :
                                            meusPalpites[jogo.id] ? 'Palpite Salvo' : 'Salvar Palpite'}
                                </button>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default Jogos
