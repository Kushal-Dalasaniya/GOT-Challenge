package com.demandfarm.got.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demandfarm.got.entity.CharacterInfo;
import com.demandfarm.got.model.CharacterDetails;
import com.demandfarm.got.model.GotCharacterList;
import com.demandfarm.got.model.ListOfHouses;
import com.demandfarm.got.repository.GOTCharInfoJdbcRepository;
import com.demandfarm.got.repository.GotCharacterInfoRepository;
import com.demandfarm.got.util.GotConstants;

@Service
public class GotDataService {
	@Autowired
	GotCharacterInfoRepository gotCharacterInfoRepository;
	@Autowired
	GOTCharInfoJdbcRepository gotCharInfoJdbcRepository;
	
	public void saveAllGotCharacterData(GotCharacterList body,String correlationId) {
		
		List<CharacterInfo> entityList = new ArrayList<>();
		HashSet<String> nameSet = new HashSet<>();
		
		for(CharacterDetails character : body.getCharacters()) {
			if(nameSet.contains(character.getCharacterName())) continue;
			nameSet.add(character.getCharacterName());
			
			CharacterInfo charInfo = new CharacterInfo();
			charInfo.setActorLink(character.getActorLink());
			charInfo.setActorName(character.getActorName());
			charInfo.setCharacterImageFull(character.getCharacterImageFull());
			charInfo.setCharacterImageThumb(character.getCharacterImageThumb());
			charInfo.setCharacterLink(character.getCharacterLink());
			charInfo.setCharname(character.getCharacterName());
			charInfo.setHouseName(character.getHouseName());
			charInfo.setNickname(character.getNickname());

			charInfo.setKingsguard(character.isKingsguard()!=null && character.isKingsguard() ? "Y" : "N" );
			charInfo.setRoyal(character.isRoyal()!=null && character.isRoyal() ? "Y" : "N");
			charInfo.setFavourite("N");
			
			entityList.add(charInfo);
		}
		
		gotCharacterInfoRepository.saveAllAndFlush(entityList);
		
		saveCharRelation(body,correlationId);
	}
	
	
	public ListOfHouses getGotHouseList(String correlationId) {
		return new ListOfHouses().houseList(gotCharInfoJdbcRepository.getGotHouseList());
	}
	
	public void saveCharRelation(GotCharacterList body,String correlationId) {
		HashSet<String> nameSet = new HashSet<>();
		
		for(CharacterDetails character : body.getCharacters()) {
			if(nameSet.contains(character.getCharacterName())) continue;
			
			CharacterInfo charinfo = gotCharacterInfoRepository.getByCharname(character.getCharacterName());
			System.out.println(charinfo.getCharacterId());
			
			
			if (character.getAbducted()!=null) {
				for(String st : character.getAbducted()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.ABDUCTED);
				}
			}
			
			if (character.getAbductedBy()!=null) {
				for(String st : character.getAbductedBy()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.ABDUCTED_BY);
				}
			}
			
			if (character.getSiblings()!=null) {
				for(String st : character.getSiblings()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.SIBLINGS);
				}
			}
			
			if (character.getServedBy()!=null) {
				for(String st : character.getServedBy()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.SERVED_BY);
				}
			}
			
			if (character.getServes()!=null) {
				for(String st : character.getServes()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.SERVES);
				}
			}
			
			if (character.getAllies()!=null) {
				for(String st : character.getAllies()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.ALLIES);
				}
			}
			
			if (character.getGuardedBy()!=null) {
				for(String st : character.getGuardedBy()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.GUARDED_BY);
				}
			}
			
			if (character.getGuardianOf()!=null) {
				for(String st : character.getGuardianOf()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.GUARDIAN_OF);
				}
			}
			
			if (character.getMarriedEngaged()!=null) {
				for(String st : character.getMarriedEngaged()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.MARRIED_ENGAGED);
				}
			}
			
			if (character.getParentOf()!=null) {
				for(String st : character.getParentOf()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.PARENT_OF);
				}
			}
			
			if (character.getParents()!=null) {
				for(String st : character.getParents()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.PARENTS);
				}
			}
			
			if (character.getKilled()!=null) {
				for(String st : character.getKilled()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.KILLED);
				}
			}
			
			if (character.getKilledBy()!=null) {
				for(String st : character.getKilledBy()) {
					gotCharInfoJdbcRepository.saveCharRelation(charinfo.getCharacterId(), st, GotConstants.KILLED_BY);
				}
			}
		}
	}
	
	
	public GotCharacterList getHouseChar(String houseName,String correlationId) {
		List<CharacterInfo> ls = gotCharacterInfoRepository.getByHouseName(houseName);
		List<CharacterDetails> charList = new ArrayList<>();
		
		for(CharacterInfo ci : ls) charList.add(setCharEntity(ci,ci.getCharacterId()));
		
		return new GotCharacterList().characters(charList);
	}
	
	public void setFavouriteChar(Integer charID,Boolean favourite) {
		CharacterInfo entity = gotCharacterInfoRepository.getById(charID);
		entity.setFavourite( favourite ? "Y" : "N");
		gotCharacterInfoRepository.save(entity);
	}
	
	public CharacterDetails setCharEntity(CharacterInfo ci,Integer id) {
		
		CharacterDetails charDetails = new CharacterDetails();
		charDetails.setCharacterId(ci.getCharacterId());
		charDetails.setActorName(ci.getCharname());
		charDetails.setActorLink(ci.getActorLink());
		charDetails.setActorName(ci.getActorName());
		charDetails.setCharacterImageFull(ci.getCharacterImageFull());
		charDetails.setCharacterImageThumb(ci.getCharacterImageThumb());
		charDetails.setFavourite(ci.getFavourite()== null ? false : ci.getFavourite().equalsIgnoreCase("Y") ? true : false);
		charDetails.setHouseName(ci.getHouseName());
		charDetails.setCharacterLink(ci.getCharacterLink());
		charDetails.setCharacterName(ci.getCharname());
		charDetails.setRoyal(ci.getRoyal()== null ? false : (ci.getRoyal().equalsIgnoreCase("Y") ? true : false));
		charDetails.setKingsguard(ci.getKingsguard()== null ? false : (ci.getKingsguard().equalsIgnoreCase("Y") ? true : false));
		charDetails.setNickname(ci.getNickname());
		
		charDetails.setAbducted(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.ABDUCTED));
		charDetails.setAbductedBy(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.ABDUCTED_BY));
		charDetails.setAllies(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.ALLIES));
		charDetails.setGuardedBy(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.GUARDED_BY));
		charDetails.setGuardianOf(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.GUARDIAN_OF));
		charDetails.setKilled(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.KILLED));
		charDetails.setKilledBy(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.KILLED_BY));
		charDetails.setMarriedEngaged(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.MARRIED_ENGAGED));
		charDetails.setParentOf(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.PARENT_OF));
		charDetails.setParents(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.PARENTS));
		charDetails.setServedBy(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.SERVED_BY));
		charDetails.setServes(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.SERVES));
		charDetails.setSiblings(gotCharInfoJdbcRepository.getAllRelationCharName(id,GotConstants.SIBLINGS));
		
		return charDetails;
	}
	
	
	public CharacterDetails getCharDetails(Integer id) {
		CharacterInfo ci = gotCharacterInfoRepository.getById(id);
		System.out.println(ci.getFavourite());
		CharacterDetails charDetails=setCharEntity(ci,id);
		System.out.println(charDetails.toString());
		return charDetails;
	}
}
