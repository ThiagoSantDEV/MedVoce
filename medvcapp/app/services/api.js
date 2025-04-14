import axios from 'axios';

// Configuração base do axios
const api = axios.create({
  baseURL: 'http://192.168.56.1:8080', // IP do seu backend
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  },
  timeout: 10000, // 10 segundos
});

// Interceptador de requisições
api.interceptors.request.use(config => {
  console.log('Enviando requisição para:', config.url);
  return config;
}, error => {
  return Promise.reject(error);
});

// Interceptador de respostas
api.interceptors.response.use(response => {
  return response;
}, error => {
  if (error.code === 'ECONNABORTED') {
    return Promise.reject({message: 'Tempo de requisição esgotado'});
  }
  
  if (!error.response) {
    return Promise.reject({message: 'Erro de conexão com o servidor'});
  }

  return Promise.reject(error);
});

export default api;