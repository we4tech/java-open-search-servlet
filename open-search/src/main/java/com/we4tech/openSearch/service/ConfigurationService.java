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

import com.we4tech.openSearch.object.Configuration;

/**
 * Manager user defined configuration.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public interface ConfigurationService {

  /**
   * Retrieve configuration from user defined properties.
   * @return {@code Configuration} object instance is returned.
   * @param pOpenSearchConfigurationPath location to configuration file.
   */
  public Configuration getConfiguration(final String pOpenSearchConfigurationPath);

  /**
   * Store {@code Configuration} into the storage.
   * @param pConfiguration configuration.
   */
  public void storeConfiguration(final Configuration pConfiguration);
}
