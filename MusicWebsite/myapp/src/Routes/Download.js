import React, { useState, useEffect } from 'react';
import { LoginEndPoint, setClientToken } from '../components/LoginEndPoint';
import Carousel from './Carousel';

const Download = () => {
  const [token, setToken] = useState("");

  useEffect(() => {
    const token = window.localStorage.getItem("token");
    const hash= window.location.hash;
    if(!token && hash){
      window.location.hash=""; 
      const _token = hash.split('&')[0].split('=')[1];
      const token_expiration = hash.split('&')[2].split('=')[1];
      window.localStorage.setItem("token", _token);
      window.localStorage.setItem("token_expiration", token_expiration);
      setToken(_token);
      setClientToken(_token);
    } else{
      setToken(token);
      setClientToken(token);
    }

}, [])

  return (token ? (
      <Carousel />
    ) : (
      <div className='background'>
        <div className='centerContent'>
          <a href={LoginEndPoint}className='spotifyButton'>
            Log In
          </a>
      </div>
    </div>
    )
  );
};

export default Download;
