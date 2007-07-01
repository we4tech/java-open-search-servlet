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
package com.we4tech.openSearch.service;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 * Skeleton implementation of {@code OpenSearchService}.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public abstract class AbstractOpenSearchService implements OpenSearchService {

  protected final Logger LOG = LogManager.getLogger(getClass());
  protected final ConfigurationService mConfigurationService;


  /**
   * Default constructor, {@code ConfigurationService} has been initated over
   * the constructor injection.
   * @param pConfigurationService dependency injected.
   */
  public AbstractOpenSearchService(
         final ConfigurationService pConfigurationService) {
    LOG.debug("Constructing abstract open search service.");
    mConfigurationService = pConfigurationService;
  }

  /**
   * Render open search formatted xml content.
   *
   * @return {@code String} open search formatted xml.
   * @param pOpenSearchConfigurationPath {@inheritDoc}
   */
  public String getOpenSearchFormattedXml(
         final String pOpenSearchConfigurationPath) {
    throw new IllegalStateException("This method is not implemented. " +
                                    "you have invoked this method from abstract " +
                                    "implementation.");
  }
}
