import { useState } from 'react'

function Login({ onLogin }) {
    const [nome, setNome] = useState('')
    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')
    const [modo, setModo] = useState('login')

    function cadastrar() {
        fetch("https://bolao-copa-soft.up.railway.app/usuario", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nome, email, senha })
        })
            .then(res => {
                if (!res.ok) return res.json().then(err => { throw new Error(err.message) })
                return res.json()
            })
            .then(data => {
                alert("Usuario cadastrado!")
                onLogin(data)
            })
            .catch(err => alert(err.message))
    }


    function login() {
        fetch("https://bolao-copa-soft.up.railway.app/usuario/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, senha })
        })
            .then(res => {
                if (!res.ok) throw new Error("Email ou senha incorretos")
                return res.json()
            })
            .then(data => onLogin(data))
            .catch(err => alert(err.message))
    }

    return (
        <div className="min-h-screen bg-gray-900 flex items-center justify-center">
            <div className="bg-gray-800 p-8 rounded-lg border border-gray-700 w-full max-w-md">
                <h1 className="text-2xl font-bold text-white text-center mb-6">
                    {modo === 'login' ? 'Login' : 'Cadastro'}
                </h1>

                <div className="flex flex-col gap-4">
                    {modo === 'cadastro' ? (
                        <input
                            placeholder="Nome"
                            value={nome}
                            onChange={e => setNome(e.target.value)}
                            className="bg-gray-700 text-white px-4 py-3 rounded border border-gray-600 focus:border-green-500 outline-none"
                        />
                    ) : null}
                    <input
                        placeholder="Email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        className="bg-gray-700 text-white px-4 py-3 rounded border border-gray-600 focus:border-green-500 outline-none"
                    />
                    <input
                        placeholder="Senha"
                        type="password"
                        value={senha}
                        onChange={e => setSenha(e.target.value)}
                        className="bg-gray-700 text-white px-4 py-3 rounded border border-gray-600 focus:border-green-500 outline-none"
                    />

                    <button
                        onClick={modo === 'login' ? login : cadastrar}
                        className="bg-green-600 hover:bg-green-500 text-white font-bold py-3 rounded transition "
                    >
                        {modo === 'login' ? 'Entrar' : 'Cadastrar'}
                    </button>
                </div>

                <p
                    onClick={() => setModo(modo === 'login' ? 'cadastro' : 'login')}
                    className="text-gray-400 text-center mt-4 cursor-pointer hover:text-green-400 transition"
                >
                    {modo === 'login' ? 'Não tem conta? Cadastre-se' : 'Já tem conta? Faça login'}
                </p>
            </div>
        </div>
    )
}

export default Login
