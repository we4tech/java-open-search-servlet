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

/**
 * URL object for open search.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchUrl {

  private String type;
  private String method;
  private String template;
  private Integer indexOffset;
  private Integer pageOffset;

  public String getType() {
    return type;
  }

  public void setType(final String pType) {
    type = pType;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(final String pMethod) {
    method = pMethod;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(final String pTemplate) {
    template = pTemplate;
  }

  public Integer getIndexOffset() {
    return indexOffset;
  }

  public void setIndexOffset(final Integer pIndexOffset) {
    indexOffset = pIndexOffset;
  }

  public Integer getPageOffset() {
    return pageOffset;
  }

  public void setPageOffset(final Integer pPageOffset) {
    pageOffset = pPageOffset;
  }
}
