package com.segundaparte.linktracker.services;

import com.segundaparte.linktracker.dtos.LinkDTO;
import com.segundaparte.linktracker.exceptionHandler.LinkException;

import java.util.InvalidPropertiesFormatException;

public interface LinkService {
    LinkDTO generateLink(LinkDTO url) throws Exception;

    LinkDTO redirectLink(String linkId, String password) throws InvalidPropertiesFormatException, LinkException;

    Object getMetrics(String linkId) throws LinkException;

    Object invalidateLink(LinkDTO linkDTO) throws LinkException;
}
