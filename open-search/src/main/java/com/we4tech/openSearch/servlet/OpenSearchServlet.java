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
package com.we4tech.openSearch.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import com.we4tech.openSearch.service.OpenSearchService;
import com.we4tech.openSearch.service.ObjectInstanceService;

/**
 * Servlet which generates the content of Open search xml.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchServlet extends HttpServlet {

  private static final String CONTENT_TYPE = "text/xml";
  private static final Logger LOG =
                       LogManager.getLogger(OpenSearchServlet.class);
  private static final String PARAM_URL_MATCH = "url-match";

  /**
   * Generate OpenSearch xml content through invoking {@code OpenSearchService}.
   * set response type as xml.
   */
  @Override
  protected void service(final HttpServletRequest pHttpServletRequest,
                         final HttpServletResponse pHttpServletResponse)
      throws ServletException, IOException {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Serving xml content - " + pHttpServletRequest.getRequestURI());
    }

    // Lookup open search configuration
    final String urlMatchPattern = getInitParameter(PARAM_URL_MATCH);
    final String servletPath = pHttpServletRequest.getRequestURI();
    final String openSearchConfigurationKey =
        servletPath.replaceAll(urlMatchPattern, "$1");

    if (LOG.isDebugEnabled()) {
      LOG.debug("Looking up configuration - " + openSearchConfigurationKey);
    }
    // retrieve configuration mapping from init-param
    final String openSearchConfigurationPath =
        getInitParameter(openSearchConfigurationKey);

    final OpenSearchService openSearchService =
        ObjectInstanceService.getInstance().getOpenSearchService();

    // Set content type as xml
    pHttpServletResponse.setContentType(CONTENT_TYPE);
    // Generate Open search Xml content from the specified configuration path.
    final String xml =
        openSearchService.getOpenSearchFormattedXml(openSearchConfigurationPath);
    // Write response in stream
    pHttpServletResponse.getWriter().println(xml);
    // flush response
    pHttpServletResponse.getWriter().flush();
    LOG.debug("Open search xml content sent to response.");
  }
}
