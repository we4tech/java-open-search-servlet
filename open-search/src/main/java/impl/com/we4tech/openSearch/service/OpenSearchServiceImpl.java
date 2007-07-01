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
package impl.com.we4tech.openSearch.service;

import com.we4tech.openSearch.service.AbstractOpenSearchService;
import com.we4tech.openSearch.service.ConfigurationService;
import com.we4tech.openSearch.object.Configuration;
import com.we4tech.openSearch.object.OpenSearchUrl;
import com.we4tech.openSearch.object.OpenSearchIconImage;
import com.we4tech.openSearch.object.OpenSearchQuery;

/**
 * Implementation of {@code AbstractOpenSearchService} class.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchServiceImpl extends AbstractOpenSearchService {
  private static final String OPEN_SEARCH_NAMESPACE =
                              "http://a9.com/-/spec/opensearch/1.1/";
  private static final String OPEN_SEARCH_ENCODING = "UTF-8";


  /**
   * Default constructor, {@code ConfigurationService} has been initated over
   * the constructor injection.
   *
   * @param pConfigurationService dependency injected.
   */
  public OpenSearchServiceImpl(final ConfigurationService pConfigurationService) {
    super(pConfigurationService);
  }


  /**
   * Render open search formatted xml content.
   *
   * @return {@code String} open search formatted xml.
   * @param pOpenSearchConfigurationPath {@inheritDoc}
   */
  @Override
  public String getOpenSearchFormattedXml(
         final String pOpenSearchConfigurationPath) {
    LOG.debug("Building open search formatted xml.");
    return buildFromConfiguration(pOpenSearchConfigurationPath);
  }

  private String buildFromConfiguration(
          final String pOpenSearchConfigurationPath) {
    final Configuration configuration =
        mConfigurationService.getConfiguration(pOpenSearchConfigurationPath);
    if (configuration != null && configuration.validate()) {
      StringBuilder builder = new StringBuilder();
      builder.append("<?xml version=\"1.0\" encoding=\"").
              append(OPEN_SEARCH_ENCODING).append("\"?>").
              append("<OpenSearchDescription xmlns=\"").
              append(OPEN_SEARCH_NAMESPACE).append("\">");
      // Short name
      if (configuration.getShortName() != null) {
        builder.append("<ShortName>").
                append(configuration.getShortName()).
                append("</ShortName>");
      }
      // Description
      if (configuration.getDescription() != null) {
        builder.append("<Description>").append(configuration.getDescription()).
                append("</Description>");
      }
      // Tags
      if (configuration.getTags() != null) {
        builder.append("<Tags>").append(configuration.getTags()).
                append("</Tags>");
      }
      // Contact
      if (configuration.getContact() != null) {
        builder.append("<Contact>").append(configuration.getContact()).
                append("</Contact>");
      }
      // Url
      if (configuration.getUrls() != null && !configuration.getUrls().isEmpty())
      {
        for (OpenSearchUrl url : configuration.getUrls()) {
          builder.append("<Url type=\"").append(url.getType()).append("\" ").
                  append(" template=\"").append(url.getTemplate()).append("\"");
          // Method
          if (url.getMethod() != null) {
            builder.append(" method=\"").append(url.getMethod()).append("\" ");
          }
          // Page offset
          if (url.getPageOffset() != null) {
            builder.append(" pageOffset=\"").append(url.getPageOffset()).
                    append("\"");
          }
          // Index offset
          if (url.getIndexOffset() != null) {
            builder.append(" pageOffset=\"").append(url.getIndexOffset()).
                    append("\"");
          }
          builder.append("/>");
        }
      }
      // LongName
      if (configuration.getLongName() != null) {
        builder.append("<LongName>").append(configuration.getLongName()).
                append("</LongName>");
      }
      // Image
      if (configuration.getImages() != null
          && !configuration.getImages().isEmpty()) {
        for (OpenSearchIconImage image : configuration.getImages()) {
          builder.append("<Image height=\"").append(image.getHeight()).
                  append("\" width=\"").append(image.getWidth()).
                  append("\" type=\"").append(image.getMimeType()).
                  append("\">").append(image.getUrl()).append("</Image>");
        }
      }
      // Query
      if (configuration.getQuery() != null) {
        final OpenSearchQuery query = configuration.getQuery();
        builder.append("<Query role=\"").append(query.getRole()).append("\"").
                append(" searchTerms=\"").append(query.getSearchTerms()).append("\" ");
        builder.append(" title=\"").append(query.getTitle()).append("\" ");
        builder.append("/>");
      }
      // Developer
      if (configuration.getDeveloper() != null) {
        builder.append("<Developer>").append(configuration.getDeveloper()).
                append("</Developer>");
      }
      // Attribution
      if (configuration.getAttribution() != null) {
        builder.append("<Attribution>").append(configuration.getAttribution()).
                append("</Attribution>");
      }
      // SyndicationRight
      if (configuration.getSyndicationRight() != null) {
        builder.append("<SyndicationRight>").
                append(configuration.getSyndicationRight()).
                append("</SyndicationRight>");
      }
      // AdultContent
      builder.append("<AdultContent>").append(configuration.isAdultContent()).
              append("</AdultContent>");
      // Language
      builder.append("<Language>").append(configuration.getLanguage()).
              append("</Language>");
      // OutputEncoding
      builder.append("<OutputEncoding>").
              append(configuration.getOutputEncoding()).
              append("</OutputEncoding>");
      // InputEncoding
      builder.append("<InputEncoding>").
              append(configuration.getInputEncoding()).
              append("</InputEncoding>");

      // End open search tag
      builder.append("</OpenSearchDescription>");

      return builder.toString();
    } else {
      LOG.debug("Invalid Configuration found.");
    }
    return null;
  }
}
