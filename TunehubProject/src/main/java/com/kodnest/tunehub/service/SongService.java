package com.kodnest.tunehub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;

@Service
public interface SongService {


	void saveSong(Song song);


	boolean songexists(String name);


	List<Song> fetchAllSongs();



	void updateSong(Song song);

	
}
