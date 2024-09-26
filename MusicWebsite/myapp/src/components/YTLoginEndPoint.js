import axios from 'axios'

const clientId = ''; 
const authEndpoint = 'https://accounts.google.com/o/oauth2/v2/auth';
const redirectUri = encodeURIComponent("http://localhost:3000/download");
const scopes = ["https://www.googleapis.com/auth/youtube.readonly", "https://www.googleapis.com/auth/youtube.force-ssl"];

export const YTLoginEndPoint = `${authEndpoint}?client_id=${clientId}&redirect_uri=${redirectUri}&scope=${encodeURIComponent(scopes.join(" "))}&response_type=token&show_dialog=true`;

const ytApiClient = axios.create({
  baseURL: "https://www.googleapis.com/youtube/v3/",
});

export const setYTClientToken= (token) =>{
  ytApiClient.interceptors.request.use(async function(config) {
    config.headers.Authorization = "Bearer " + token;
    return config;
  });
};

export default ytApiClient;