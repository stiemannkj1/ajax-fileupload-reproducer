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

import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ApplicationWrapper;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;


/**
 * @author  Kyle Stiemann
 */
public class ApplicationImpl extends ApplicationWrapper {

	private Application wrappedApplication;

	public ApplicationImpl(Application wrappedApplication) {
		this.wrappedApplication = wrappedApplication;
	}

	@Override
	public UIComponent createComponent(String componentType) throws FacesException {
		return createComponent(componentType, super.createComponent(componentType));
	}

//  @Override
//  public UIComponent createComponent(FacesContext context, Resource componentResource) {
//      return createComponent(componentType, super.createComponent(context, componentResource));
//  }

	@Override
	public UIComponent createComponent(FacesContext context, String componentType, String rendererType) {
		return createComponent(componentType, super.createComponent(context, componentType, rendererType));
	}

	@Override
	public UIComponent createComponent(javax.el.ValueExpression componentExpression, FacesContext context,
		String componentType) throws FacesException {
		return createComponent(componentType, super.createComponent(componentExpression, context, componentType));
	}

	@Override
	public UIComponent createComponent(ValueBinding componentBinding, FacesContext context, String componentType)
		throws FacesException {
		return createComponent(componentType, super.createComponent(componentBinding, context, componentType));
	}

	@Override
	public UIComponent createComponent(javax.el.ValueExpression componentExpression, FacesContext context,
		String componentType, String rendererType) {
		return createComponent(componentType,
				super.createComponent(componentExpression, context, componentType, rendererType));
	}

	private UIComponent createComponent(String componentType, UIComponent uiComponent) {

		if (UIViewRoot.COMPONENT_TYPE.equals(componentType)) {
			return new UIViewRootImpl();
		}
		else {
			return uiComponent;
		}
	}

	@Override
	public Application getWrapped() {
		return wrappedApplication;
	}
}
