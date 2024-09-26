import axios from 'axios';

const clientId = '3a7f1f02404246428f58d6f046048783'; 
const authEndpoint = 'https://accounts.spotify.com/authorize';
const redirectUri = encodeURIComponent("http://localhost:3000/download");
const scopes = ["user-library-read", "playlist-read-private"];

export const LoginEndPoint = `${authEndpoint}?client_id=${clientId}&redirect_uri=${redirectUri}&scope=${scopes.join("%20")}&response_type=token&show_dialog=true`;

const apiClient = axios.create({
  baseURL: "https://api.spotify.com/v1/",
});

export const setClientToken= (token) =>{
  apiClient.interceptors.request.use(async function(config) {
    config.headers.Authorization = "Bearer " + token;
    return config;
  });
};

export default apiClient;