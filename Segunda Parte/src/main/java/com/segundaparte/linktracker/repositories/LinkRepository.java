package com.segundaparte.linktracker.repositories;

import com.segundaparte.linktracker.dtos.LinkDTO;
import com.segundaparte.linktracker.exceptionHandler.LinkException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepository {
    private Map<Integer, LinkDTO> repository = new HashMap<Integer, LinkDTO>();
    private int lastId = 0;

    public LinkDTO getByLinkId(Integer linkId, boolean visiting) throws LinkException {
        if (linkId == null)
            throw new LinkException(LinkException.LINK_NOT_FOUND, LinkException.LINK_NOT_FOUND_MSG);
        LinkDTO linkDTO = repository.get(linkId);
        if (linkDTO == null)
            throw new LinkException(LinkException.LINK_NOT_FOUND, LinkException.LINK_NOT_FOUND_MSG);
        if (visiting) {
            linkDTO.setVisitas(linkDTO.getVisitas() + 1);
            repository.put(linkId, linkDTO);
        }
        return linkDTO;
    }

    public LinkDTO saveAndFlush(LinkDTO link) throws Exception {
        if (repository.get(link.getId()) != null)
            throw new LinkException(LinkException.ID_ALREADY_EXISTS, LinkException.ID_ALREADY_EXISTS_MSG);
        if (isValid(link.getUrl())){
            lastId++;
            repository.put(lastId, new LinkDTO(link.getUrl(),lastId,String.valueOf(link.getPassword())));
            return repository.get(lastId);
        }
        else{
            throw new LinkException(LinkException.URL_NOT_VALID,LinkException.URL_NOT_VALID_MSG);
        }
    }

    public boolean isValid(String url){
        String regex = "^(https?|http|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return url.matches(regex);
    }

    public void invalidateLink(int id) {
        repository.remove(id);
    }
}
