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

import junit.framework.TestCase;

/**
 * Test {@code OpenSearchService} class.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchServiceTest extends TestCase {

  private static final Logger LOG =
      LogManager.getLogger(OpenSearchServiceTest.class);
  private OpenSearchService mOpenSearchService;

  @Override
  protected void setUp() throws Exception {
    mOpenSearchService = ObjectInstanceService.getInstance().
                         getOpenSearchService();
  }

  public void testOpenSearchFormattedXml() {
    String xml = mOpenSearchService.getOpenSearchFormattedXml("open-search.xml");
    LOG.debug("XML - " + xml);
    assertNotNull(xml);
  }
}
