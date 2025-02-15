package com.kodnest.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.service.PlaylistService;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{
	public Playlist findByName(String name);


}