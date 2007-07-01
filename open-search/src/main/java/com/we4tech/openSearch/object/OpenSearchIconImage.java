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
 * Object for open search icon image.
 * @author <a href="mailto:hasan@somewherein.net">nhm tanveer hossain khan (hasan)</a>
 */
public class OpenSearchIconImage {
  
  private Integer height;
  private Integer width;
  private String mimeType;
  private String url;

  public Integer getHeight() {
    return height;
  }

  public void setHeight(final Integer pHeight) {
    height = pHeight;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(final Integer pWidth) {
    width = pWidth;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(final String pMimeType) {
    mimeType = pMimeType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String pUrl) {
    url = pUrl;
  }
}
