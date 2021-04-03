package com.segundaparte.linktracker.services;

import com.segundaparte.linktracker.dtos.LinkDTO;
import com.segundaparte.linktracker.exceptionHandler.LinkException;
import com.segundaparte.linktracker.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService{
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public LinkDTO generateLink(LinkDTO linkDTO) throws Exception {
        return linkRepository.saveAndFlush(linkDTO);
    }

    @Override
    public LinkDTO redirectLink(String linkId, String password) throws LinkException {
        LinkDTO link = linkRepository.getByLinkId(Integer.parseInt(linkId), true);
        return checkLinkAndPassword(password, link);
    }

    private LinkDTO checkLinkAndPassword(String password, LinkDTO linkDTO) {
        LinkDTO result = null;
        if (linkDTO.getPassword() != null && linkDTO.getPassword().equals(password)){
            result = linkDTO;
        }
        return result;
    }

    @Override
    public LinkDTO getMetrics(String linkId) throws LinkException {
        return linkRepository.getByLinkId(Integer.parseInt(linkId), false);
    }

    @Override
    public LinkDTO invalidateLink(LinkDTO linkDTO) throws LinkException {
        LinkDTO invalidate = linkRepository.getByLinkId(linkDTO.getId(), false);
        linkRepository.invalidateLink(linkDTO.getId());
        return invalidate;
    }
}
