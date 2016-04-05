/**
 * Copyright (c) 2000-2016 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;


/**
 * @author  Kyle Stiemann
 */
@ManagedBean
@RequestScoped
public class FileUploadBean {
	private Part uploadedFile;

	public FileUploadBean() {
	}

	public String getFileText() {
		String text = "";

		if (null != uploadedFile) {

			try {
				InputStream is = uploadedFile.getInputStream();
				text = new Scanner(is).useDelimiter("\\A").next();
			}
			catch (IOException ex) {

			}
		}

		return text;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
