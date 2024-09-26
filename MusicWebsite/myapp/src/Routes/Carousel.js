import React, { useEffect, useState } from "react";
import "../App.css"; 
import apiClient from "../components/LoginEndPoint";
import PlaylistCard from "../components/PlaylistCard";
import DisplaySongs from "../components/DisplaySongs";
import { RiDownload2Line } from "react-icons/ri";
import { YTLoginEndPoint, setYTClientToken } from "../components/YTLoginEndPoint";
import { TiCancel } from "react-icons/ti";
import axios from "axios";

const Carousel = () => {

  const [playlists, setPlaylists] = useState(
    []
  );

  const [selectedPlaylistNumber, setSelectedPlaylistNumber] = useState(
    -1
  );

  const [selectedPlaylist, setSelectedPlaylist] = useState(
    ""
  );

  const [ytTokenActive, setYtTokenActive] = useState(
    false
  );

  const [playlistname, setPlaylistName] = useState (
    ""
  );

  const [downloading, setDownloading] = useState(
    false
  )

  const selectVideo = (playlistURLArg, index, name) => {
    if (selectedPlaylist === playlistURLArg) {
      setSelectedPlaylist("");
      setSelectedPlaylistNumber(-1);
      setPlaylistName("")
    } else {
      setSelectedPlaylist(playlistURLArg);
      setSelectedPlaylistNumber(index);
      setPlaylistName(name)
    }
  }

  const handleLoginClick = () => {
    window.location.href = YTLoginEndPoint;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    const form = e.target;
    const formData = new FormData(form);

    const formJson = Object.fromEntries(formData.entries());

    if(formJson["myInput"] !== ""){


      var myParams = {
        videoId: formJson["myInput"].split('?v=')[1],
        videoName: formJson["myInput"],
      };

      console.log(formJson["myInput"])
      setDownloading(true);
      axios.post('/downloadSong', myParams).then(function(response){
        console.log(response);
        alert("Downloaded captions for: " + formJson["myInput"]);
        setDownloading(false);
      })
      .catch(function(error){
        console.log(error.response.data);
        alert("Couldn't download song caption");
        // alert("Song has no captions")
        setDownloading(false);
      });
    }else{
      console.log("LOSETER")
    }
  };

  useEffect(() => {
    
    const token = window.localStorage.getItem("yt_token");
    const hash= window.location.hash;
    
    if(!token && hash){
      window.location.hash=""; 
      const _token = hash.split('&')[0].split('=')[1];
      const token_expiration = hash.split('&')[2].split('=')[1];
      window.localStorage.setItem("yt_token", _token);
      window.localStorage.setItem("yt_token_expiration", token_expiration);
      setYTClientToken(_token);
      setYtTokenActive(true);
    } else{
      setYTClientToken(token);
      if(token){
        setYtTokenActive(true);
      }
    }

  apiClient.get("me/playlists").then(response => {
    setPlaylists(response.data.items);
  })

}, [])

  return (
    <>
      <div className='background'>

        <div className="inputBox">
          <form method="post" onSubmit={handleSubmit}>
            <label>
              Youtube Link: <input name="myInput" /> {!downloading ? (<button type="submit"><RiDownload2Line/></button>) : (<TiCancel/>) }
            </label>
          </form>
        </div>

        <div className="flexContainer">
          <div className='carousel left'>
            {ytTokenActive ? (
              playlists.map((playlist, index) => {
                return(
                  <div className='leftCarouselContent' key={index}>
                    <PlaylistCard 
                      playlistImg = {playlist.images[0]}
                      cardClass = "playlistCard"
                    />
                    <div className="toolTipContainer">
                      <h3 onClick={
                        () => selectVideo(
                          playlist.images[0], 
                          index,
                          playlist.name
                        )
                      } className="toolTip left">Select</h3>
                      <a href={playlist.external_urls.spotify}><h3 className="toolTip right">View</h3></a>
                    </div>
                  </div>
                );
              })
            ) : (
              <div className="ytLoginStyle">
                <h1 onClick={handleLoginClick} >Log Into Youtube</h1>
              </div>
            )}
          </div>
          <div className='carousel right'>
            <div className="topBar">
                <div>
                  <PlaylistCard 
                    playlistImg = {selectedPlaylist}
                    cardClass = "playlistCard right"
                  />
                </div>
                <div id="playlistName">
                  <h1>{playlistname}</h1>
                </div>          
            </div>
            {selectedPlaylistNumber >= 0 && 
              <DisplaySongs playlistIndex={selectedPlaylistNumber}/>
            }
          </div>
        </div>
      </div>
    </>
  );
  
}


export default Carousel
