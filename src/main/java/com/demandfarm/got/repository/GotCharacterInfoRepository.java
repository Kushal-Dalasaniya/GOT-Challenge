package com.demandfarm.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demandfarm.got.entity.CharacterInfo;

public interface GotCharacterInfoRepository extends JpaRepository<CharacterInfo, Integer> {
	public CharacterInfo getByCharname(String name);
	public List<CharacterInfo> getByHouseName(String housename);
}
