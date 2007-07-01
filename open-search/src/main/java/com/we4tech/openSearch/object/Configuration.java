/* $Id$ */
/*
 ******************************************************************************
 *   Copyright (C) 2006 nhm tanveer hossain khan (hasan) 
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ******************************************************************************
 * $LastChangedBy$
 * $LastChangedDate$
 * $LastChangedRevision$
 ******************************************************************************
*/
package com.we4tech.openSearch.object;

import java.util.List;
import java.util.Collections;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * All Open search realted configuration properties.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class Configuration {

  /**
   * Default syndication right is set to "open".
   */
  public static final String SYNDICATION_RIGHT_OPEN = "open";
  public static final String SYNDICATION_RIGHT_LIMITED = "limited";
  public static final String SYNDICATION_RIGHT_PRIVATE = "private";
  public static final String SYNDICATION_RIGHT_CLOSED = "closed";

  /**
   * Default langauge selection is "*".
   */
  public static final String LANGUAGE_ALL = "*";

  /**
   * Default input encoding is "UTF-8".
   */
  public static final String ENCODING = "UTF-8";
  
  private String shortName;
  private String description;
  private String contact;
  private String longName;
  private String attribution;
  private String syndicationRight = SYNDICATION_RIGHT_OPEN;
  private boolean adultContent;
  private String language = LANGUAGE_ALL;
  private String outputEncoding = ENCODING;
  private String inputEncoding = ENCODING;
  private String tags;
  private String developer;

  private OpenSearchQuery query;
  private List<OpenSearchUrl> urls;
  private List<OpenSearchIconImage> images;
  private static final String TAG_START = "<";
  private static final String TAG_END = ">";

  public String getShortName() {
    return shortName;
  }

  public void setShortName(final String pShortName) {
    shortName = pShortName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String pDescription) {
    description = pDescription;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(final String pContact) {
    contact = pContact;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(final String pLongName) {
    longName = pLongName;
  }

  public String getAttribution() {
    return attribution;
  }

  public void setAttribution(final String pAttribution) {
    attribution = pAttribution;
  }

  public String getSyndicationRight() {
    return syndicationRight;
  }

  public void setSyndicationRight(final String pSyndicationRight) {
    syndicationRight = pSyndicationRight;
  }

  public boolean isAdultContent() {
    return adultContent;
  }

  public void setAdultContent(final boolean pAdultContent) {
    adultContent = pAdultContent;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(final String pLanguage) {
    language = pLanguage;
  }

  public String getOutputEncoding() {
    return outputEncoding;
  }

  public void setOutputEncoding(final String pOutputEncoding) {
    outputEncoding = pOutputEncoding;
  }

  public String getInputEncoding() {
    return inputEncoding;
  }

  public void setInputEncoding(final String pInputEncoding) {
    inputEncoding = pInputEncoding;
  }

  public List<OpenSearchIconImage> getImages() {
    return images;
  }

  public void setImages(final List<OpenSearchIconImage> pImages) {
    images = pImages;
  }

  public OpenSearchQuery getQuery() {
    return query;
  }

  public void setQuery(final OpenSearchQuery pQuery) {
    query = pQuery;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(final String pTags) {
    tags = pTags;
  }

  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(final String pDeveloper) {
    developer = pDeveloper;
  }

  public List<OpenSearchUrl> getUrls() {
    return urls;
  }

  public void setUrls(final List<OpenSearchUrl> pUrls) {
    urls = pUrls;
  }

  /**
   * Confirm the valid configuration properties.
   * @throws IllegalArgumentException if any property is not valid.
   * @return {@code true} is content is valid.
   */
  public boolean validate() throws IllegalArgumentException {

    LogManager.getLogger(getClass()).
               debug("Validating configuration properties.");

    // short name can hold only 16 chars. no html content.
    throwExceptionIf(shortName, Condition.MORE_THAN, 16);
    throwExceptionIf(shortName, Condition.HAS_XML_TAG);

    // description can hold only 1024 chars. no html content.
    throwExceptionIf(description, Condition.MORE_THAN, 1024);
    throwExceptionIf(description, Condition.HAS_XML_TAG);

    // contact has to be email|uri. no html
    throwExceptionIf(contact, Condition.NOT_URI);
    throwExceptionIf(contact, Condition.HAS_XML_TAG);

    // tags can hold only 256 chars. no html
    throwExceptionIf(tags, Condition.MORE_THAN, 256);
    throwExceptionIf(tags, Condition.HAS_XML_TAG);

    // long name can hold only 48 chars, no html
    throwExceptionIf(longName, Condition.MORE_THAN, 48);
    throwExceptionIf(longName, Condition.HAS_XML_TAG);

    // developer property can hold only 64 chars. no html content.
    throwExceptionIf(developer, Condition.MORE_THAN, 64);
    throwExceptionIf(developer, Condition.HAS_XML_TAG);

    // attribution can hold only 256 chars. no html
    throwExceptionIf(attribution, Condition.MORE_THAN, 256);
    throwExceptionIf(attribution, Condition.HAS_XML_TAG);

    return true;
  }

  /**
   * Supported condition.
   */
  private enum Condition {
    MORE_THAN,
    NOT_URI, HAS_XML_TAG
  }

  private void throwExceptionIf(final Object... pConditionParameters) {
    final Logger log = LogManager.getLogger(getClass());
    log.debug("Throw exception if - ");
    // verify self parameter.
    if (pConditionParameters == null || pConditionParameters.length < 2) {
      throw new IllegalArgumentException("Less parameter than it requires. " +
                                          "please at least give 2 parameters");
    }

    // Based on different condition the 3rd parameter will be looked up.
    final Object field = pConditionParameters[0];
    final Condition condition = (Condition) pConditionParameters[1];

    if (log.isDebugEnabled()) {
      log.debug(" condition - " + condition + " failed on value - " + field);
    }

    // select rule based on condition.
    switch (condition) {
      case MORE_THAN:
        if (field instanceof String) {
          final Integer maxCharLength = (Integer) pConditionParameters[2];
          if (((String) field).length() > maxCharLength) {
            throwException("Invalid content length. it exceeds the " +
                           "maximum number of chars (" + maxCharLength + ").");
          }
        } else if (field instanceof List) {
          final Integer maxSize = (Integer) pConditionParameters[2];
          if (((List) field).size() > maxSize) {
            throwException("Invalid content length. it exceeds the " +
                           "maximum number of items (" + maxSize + ").");
          }
        }
        break;

      case HAS_XML_TAG:
        final String stringField = (String) field;
        log.debug((stringField != null ? stringField.indexOf(TAG_START) : ""));
        if (stringField != null &&
            (stringField.indexOf(TAG_START) != -1
                || stringField.indexOf(TAG_END)!= -1)) {
          throwException("Invalid Xml tag found on content.");
        }
        break;

      case NOT_URI:
        final String stringField2 = (String) field;
        // TODO: NOT_URI condition has not yet implemented.
        break;
    }
    
  }

  private void throwException(final String pMessage) {
    throw new IllegalArgumentException(pMessage);
  }
}
