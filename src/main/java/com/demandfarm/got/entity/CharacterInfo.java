package com.demandfarm.got.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "character_info")
public class CharacterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Integer characterId;

    @Column(name = "character_link")
    private String characterLink;

    @Column(name = "actor_name")
    private String actorName;

    @Column(name = "actor_link")
    private String actorLink;

    @Column(name = "charname", unique = true)
    private String charname;

    @Column(name = "royal")
    private String royal;

    @Column(name = "character_image_thumb")
    private String characterImageThumb;

    @Column(name = "character_image_full")
    private String characterImageFull;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "housename")
    private String houseName;

    @Column(name = "kingsguard")
    private String kingsguard;

    @Column(name = "favourite")
    private String favourite;

	public Integer getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public String getCharacterLink() {
		return characterLink;
	}

	public void setCharacterLink(String characterLink) {
		this.characterLink = characterLink;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorLink() {
		return actorLink;
	}

	public void setActorLink(String actorLink) {
		this.actorLink = actorLink;
	}

	public String getCharname() {
		return charname;
	}

	public void setCharname(String charname) {
		this.charname = charname;
	}

	public String getRoyal() {
		return royal;
	}

	public void setRoyal(String royal) {
		this.royal = royal;
	}

	public String getCharacterImageThumb() {
		return characterImageThumb;
	}

	public void setCharacterImageThumb(String characterImageThumb) {
		this.characterImageThumb = characterImageThumb;
	}

	public String getCharacterImageFull() {
		return characterImageFull;
	}

	public void setCharacterImageFull(String characterImageFull) {
		this.characterImageFull = characterImageFull;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getKingsguard() {
		return kingsguard;
	}

	public void setKingsguard(String kingsguard) {
		this.kingsguard = kingsguard;
	}

	public String getFavourite() {
		return favourite;
	}

	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

    
}
