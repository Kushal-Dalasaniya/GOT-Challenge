package com.demandfarm.got.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demandfarm.got.exception.GotException;
import com.demandfarm.got.model.CharacterDetails;
import com.demandfarm.got.model.GotCharacterList;
import com.demandfarm.got.model.ListOfHouses;
import com.demandfarm.got.service.GotDataService;
import com.demandfarm.got.util.GotConstants;
import com.demandfarm.got.util.GotExceptionConstants;
import com.demandfarm.got.util.HeaderMapper;

import io.swagger.annotations.ApiParam;
@CrossOrigin
@RestController
public class GotDataApiController implements GotDataApi{

    @Autowired
	private Environment env;
    
    @Autowired
    GotDataService gotDataService;

	@Override
	public ResponseEntity<ListOfHouses> getHousesList(String accept, String correlationId) {
		
		if(accept == null || !accept.contains(GotConstants.APPLICATION_JSON)) 
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
		HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
		
		try {
			ListOfHouses responce = gotDataService.getGotHouseList(correlationId);
			return new ResponseEntity<>(responce,responseHeaders,HttpStatus.OK);
		}
		catch(GotException ex) {
			throw new GotException(ex.getStatusCode(), ex.getCode(), ex.getMessage(), correlationId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new GotException(HttpStatus.INTERNAL_SERVER_ERROR, GotExceptionConstants.GOT_ERROR_0001, 
					env.getRequiredProperty(GotExceptionConstants.GOT_ERROR_0001), correlationId);
		}
	}
	

	@Override
	public ResponseEntity<Void> saveGotCharactersList(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			,@ApiParam(value = "A unique identifier value that is attached to requests that allow reference to a particular transaction." ,required=true) @RequestHeader(value="CorrelationId", required=true) String correlationId
			,@ApiParam(value = "save all characters details" ,required=true )  @Valid @RequestBody GotCharacterList body
			) {
		if(accept == null || !accept.contains(GotConstants.APPLICATION_JSON)) 
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
		HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
		
		try {
			gotDataService.saveAllGotCharacterData(body,correlationId);
			return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
		}
		catch(GotException ex) {
			throw new GotException(ex.getStatusCode(), ex.getCode(), ex.getMessage(), correlationId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new GotException(HttpStatus.INTERNAL_SERVER_ERROR, GotExceptionConstants.GOT_ERROR_0001, 
					env.getRequiredProperty(GotExceptionConstants.GOT_ERROR_0001), correlationId);
		}
	}
	
	@Override
	public ResponseEntity<GotCharacterList> getHouseCharacters(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			,@ApiParam(value = "A unique identifier value that is attached to requests that allow reference to a particular transaction." ,required=true) @RequestHeader(value="CorrelationId", required=true) String correlationId
			,@ApiParam(value = "got house name",required=true) @PathVariable("houseName") String houseName){
		
		if(accept == null || !accept.contains(GotConstants.APPLICATION_JSON)) 
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
		HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
		
		try {
			GotCharacterList response = gotDataService.getHouseChar(houseName,correlationId);
			return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);
		}
		catch(GotException ex) {
			throw new GotException(ex.getStatusCode(), ex.getCode(), ex.getMessage(), correlationId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new GotException(HttpStatus.INTERNAL_SERVER_ERROR, GotExceptionConstants.GOT_ERROR_0001, 
					env.getRequiredProperty(GotExceptionConstants.GOT_ERROR_0001), correlationId);
		}
	}
	
	@Override
	 public ResponseEntity<Void> setFavourteCharacter(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			 ,@ApiParam(value = "A unique identifier value that is attached to requests that allow reference to a particular transaction." ,required=true) @RequestHeader(value="CorrelationId", required=true) String correlationId
			 ,@ApiParam(value = "character ID",required=true) @PathVariable("id") Integer id,@ApiParam(value = "To set favourite value") @Valid @RequestParam(value = "favourite", required = false) Boolean favourite){
		 
		 if(accept == null || !accept.contains(GotConstants.APPLICATION_JSON)) 
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			
			HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
			
			try {
			    gotDataService.setFavouriteChar(id,favourite);
				return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
			}
			catch(GotException ex) {
				throw new GotException(ex.getStatusCode(), ex.getCode(), ex.getMessage(), correlationId);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				throw new GotException(HttpStatus.INTERNAL_SERVER_ERROR, GotExceptionConstants.GOT_ERROR_0001, 
						env.getRequiredProperty(GotExceptionConstants.GOT_ERROR_0001), correlationId);
			}
	 }
	
	@Override
	public ResponseEntity<CharacterDetails> getCharacterDetails(@ApiParam(value = "Supported Content Type- application/json" ,required=true) @RequestHeader(value="Accept", required=true) String accept
			,@ApiParam(value = "A unique identifier value that is attached to requests that allow reference to a particular transaction." ,required=true) @RequestHeader(value="CorrelationId", required=true) String correlationId
			,@ApiParam(value = "characterid",required=true) @PathVariable("characterId") Integer characterId){
		
		 	if(accept == null || !accept.contains(GotConstants.APPLICATION_JSON)) 
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			
			HttpHeaders responseHeaders = HeaderMapper.getHttpHeaders(correlationId);
			
			try {
				CharacterDetails response = gotDataService.getCharDetails(characterId);
				return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);
			}
			catch(GotException ex) {
				throw new GotException(ex.getStatusCode(), ex.getCode(), ex.getMessage(), correlationId);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				throw new GotException(HttpStatus.INTERNAL_SERVER_ERROR, GotExceptionConstants.GOT_ERROR_0001, 
						env.getRequiredProperty(GotExceptionConstants.GOT_ERROR_0001), correlationId);
			}
	}

}
