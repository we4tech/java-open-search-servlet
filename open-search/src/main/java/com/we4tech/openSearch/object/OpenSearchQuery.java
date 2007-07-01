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

/**
 * Query object for Open search
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchQuery {

  public static final String ROLE_REQUEST = "request";
  public static final String ROLE_EXAMPLE = "example";
  public static final String ROLE_RELATED = "related";
  public static final String ROLE_CORRECTION = "correction";
  public static final String ROLE_SUBSET = "subset";
  public static final String ROLE_SUPERSET = "superset";

  private String role;
  private String title;
  private String searchTerms;

  public String getRole() {
    return role;
  }

  public void setRole(final String pRole) {
    role = pRole;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String pTitle) {
    title = pTitle;
  }

  public String getSearchTerms() {
    return searchTerms;
  }

  public void setSearchTerms(final String pSearchTerms) {
    searchTerms = pSearchTerms;
  }
}
