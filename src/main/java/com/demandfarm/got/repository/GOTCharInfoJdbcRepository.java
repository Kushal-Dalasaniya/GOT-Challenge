package com.demandfarm.got.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GOTCharInfoJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<String> getGotHouseList(){
		String query = "SELECT DISTINCT housename FROM character_info";
		return jdbcTemplate.queryForList(query, String.class);
	}
	
	public void saveCharRelation (Integer mainCharId, String secondCharName,String relation) {
		String query = "INSERT INTO char_relation (main_char, second_char, relation) VALUES (?, (select character_id from character_info where charname = ? ), ?)";
		Object [] arg= new Object[] {mainCharId,secondCharName,relation};
		jdbcTemplate.update(query, arg);
	}
	
	public List<String> getAllRelationCharName(Integer mainCharId, String relation){
		String query = "select ci.charname from char_relation cr "
				+ "inner join character_info ci on ci.character_id = cr.second_char "
				+ "where cr.main_char=? and cr.relation = ?";
		Object [] arg= new Object[] {mainCharId,relation};
		return jdbcTemplate.queryForList(query,arg,String.class);
	}
}
