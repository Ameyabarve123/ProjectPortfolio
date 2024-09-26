import React from 'react'
import "../App.css"; 

const PlaylistCard = (props) => {
  return (
    <div className={props.cardClass}>
        <img src={props.playlistImg.url}/>
    </div>
    
  )
}

export default PlaylistCard
