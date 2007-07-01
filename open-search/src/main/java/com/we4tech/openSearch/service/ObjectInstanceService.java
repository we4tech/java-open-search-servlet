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

import impl.com.we4tech.openSearch.service.ConfigurationServiceImpl;
import impl.com.we4tech.openSearch.service.OpenSearchServiceImpl;

/**
 * Manage singeton instance and inject dependency for class.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class ObjectInstanceService {

  private final Logger LOG = LogManager.getLogger(ObjectInstanceService.class);
  private static final ObjectInstanceService INSTNACE =
                                             new ObjectInstanceService();
  private ConfigurationService mConfigurationService;
  private OpenSearchService mOpenSearchService;

  /**
   * Return singleton instance of {@code ObjectInstanceService}.
   * @return singleton instance of {@code ObjectInstanceService}.
   */
  public static ObjectInstanceService getInstance() {
    return INSTNACE;
  }

  /**
   * Return the instance of {@code ConfigurationService}. if it wasn't create
   * before the new implementation instance will be generated.
   * @return {@code ConfigurationService} implemented class instance
   *         is returned.
   */
  public ConfigurationService getConfigurationService() {
    LOG.debug("Returning the instance of ConfigurationService object.");
    if (mConfigurationService == null) {
      synchronized (this) {
        LOG.debug("ConfigurationService object was't initiated before. " +
                  "now creating a new instance.");
        mConfigurationService = new ConfigurationServiceImpl();
      }
    }
    return mConfigurationService;
  }

  /**
   * Return the instance of {@code OpenSearchService}. if this class
   * wasn't initated before, create a new instance. otherwise return the
   * existing one.
   * @return the instance of {@code OpenSearchService}.
   */
  public OpenSearchService getOpenSearchService() {
    LOG.debug("Returning the instance of OpenSearchService object.");
    if (mOpenSearchService == null) {
      synchronized (this) {
        LOG.debug("OpenSearchService object wasn't initiated before." +
                  "now creating a new instance.");
        mOpenSearchService
            = new OpenSearchServiceImpl(getConfigurationService());
      }
    }
    return mOpenSearchService;
  }
}
