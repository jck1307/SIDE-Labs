/*
    Copyright (C) 2007-2011  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


package com.bluexml.xforms.generator.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;

/**
 * A rendered item. All elements of the generated XHTML templates are derived from this class.
 * <p/>
 * Content elements in the &lt;head&gt; section are "ModelElement's" (either <em>instance</em>,
 * <em>submission</em> or <em>bind</em>).
 * <p/>
 * Content elements in the &lt;body&gt; section (i.e. DIVs and XForms controls) are necessarily from
 * subclasses of this in which the xforms DOM element is non null. If that DOM element is null, the
 * object being rendered will not contribute anything in the generated template.
 */
public abstract class Rendered {

	/** The current form that's being rendered. */
	private static Rendered rootContainer = null;

	/** The rendered children. */
	private List<Rendered> children = new ArrayList<Rendered>();

	/** The model elements to be inserted. */
	private List<ModelElement> modelElements = new ArrayList<ModelElement>();

	/** The xforms DOM element. */
	protected Element xformsElement;

	/** The optional data. */
	private String optionnalData;

	/** Does layout have to return to line after this element?. */
	private boolean returnToLine = false;

	/** The bind holder which binds of this rendered will be nested into. */
	ModelElementBindHolder bindHolder = null;

	/** The stack of bind holders that have been encountered since the rendering started. */
	private static Stack<ModelElementBindHolder> bindHolderStack = null;

	/**
	 * Gets the optional data.
	 * 
	 * @return the optional data
	 */
	public String getOptionalData() {
		return optionnalData;
	}

	/**
	 * Sets the optional data.
	 * 
	 * @param optionnalData
	 *            the new optional data
	 */
	public void setOptionalData(String optionnalData) {
		this.optionnalData = optionnalData;
	}

	/**
	 * Adds the rendered item to owned collections.
	 * 
	 * @param rendered
	 *            the rendered
	 * @param renderable
	 *            the renderable that has been rendered
	 */
	public void addRendered(Rendered rendered, @SuppressWarnings("unused") Renderable renderable) {
		modelElements.addAll(rendered.modelElements);
		getChildren().add(rendered);
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<Rendered> getChildren() {
		return children;
	}

	/**
	 * Gets the model elements.
	 * 
	 * @return the model elements
	 */
	public List<ModelElement> getModelElements() {
		return modelElements;
	}

	/**
	 * Adds the all model element.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public void addAllModelElement(List<ModelElement> modelElement) {
		for (ModelElement modelElementToAdd : modelElement) {
			addModelElementLocal(modelElementToAdd);
		}
	}

	/**
	 * Add a model element to this object.
	 * 
	 * @param modelElement
	 */
	public void addModelElementLocal(ModelElement modelElement) {
		modelElements.add(modelElement);
	}

	/**
	 * Adds the model element, not adding it in the holder even if one exists
	 * 
	 * @param modelElement
	 * @param asStandalone
	 *            if true, the model element is added to the root container
	 */
	public void addModelElementRoot(ModelElement modelElement) {
		getRootContainer().addModelElementLocal(modelElement);
	}

	/**
	 * Adds the model element into the bind holder if any.
	 * 
	 * @param modelElement
	 *            the model element
	 */
	public void addModelElement(ModelElement modelElement) {
		ModelElementBindHolder holder = getTopBindHolder();
		if (holder == null || !(modelElement instanceof ModelElementBindSimple)) {
			addModelElementLocal(modelElement);
		} else {
			holder.addSubBind((ModelElementBindSimple) modelElement);
		}
	}

	/**
	 * Gets the bind holder at the top of the stack.
	 * 
	 * @return
	 */
	private static ModelElementBindHolder getTopBindHolder() {
		if (getBindHolderStack().size() > 0) {
			return getBindHolderStack().peek();
		}
		return null;
	}

	private static Rendered getRootContainer() {
		return rootContainer;
	}

	/**
	 * Gets the xforms DOM element.
	 * 
	 * @return the xforms element
	 */
	public Element getXformsElement() {
		return xformsElement;
	}

	/**
	 * Checks if is holder (to not have to create a group for each rendered item).
	 * 
	 * @return true, if is holder
	 */
	public abstract boolean isHolder();

	/**
	 * Render end.
	 * 
	 * @param renderable
	 *            the renderable
	 */
	public void renderEnd() {
		if (getBindHolder() != null) {
			getBindHolderStack().pop();
		}
	}

	/**
	 * Sets the xforms element.
	 * 
	 * @param xformsElement
	 *            the new xforms element
	 */
	public void setXformsElement(Element xformsElement) {
		this.xformsElement = xformsElement;
	}

	/**
	 * Checks if is return to line.
	 * 
	 * @return true, if is return to line
	 */
	public boolean isReturnToLine() {
		return returnToLine;
	}

	/**
	 * Sets the return to line.
	 * 
	 * @param returnToLine
	 *            the new return to line
	 */
	public void setReturnToLine(boolean returnToLine) {
		this.returnToLine = returnToLine;
	}

	public void setRepeater(ModelElementBindHolder bindRepeater) {
		setBindHolder(bindRepeater);
		getBindHolderStack().push(bindRepeater);
	}

	/**
	 * @param bindHolder
	 *            the bindHolder to set
	 */
	private void setBindHolder(ModelElementBindHolder bindHolder) {
		this.bindHolder = bindHolder;
	}

	/**
	 * @return the bindHolder
	 */
	public ModelElementBindHolder getBindHolder() {
		return bindHolder;
	}

	/**
	 * @return the bindHolderStack
	 */
	private static Stack<ModelElementBindHolder> getBindHolderStack() {
		if (bindHolderStack == null) {
			bindHolderStack = new Stack<ModelElementBindHolder>();
		}
		return bindHolderStack;
	}

	/**
	 * @param rootContainer
	 *            the rootContainer to set
	 */
	protected static void setRootContainer(Rendered rootContainer) {
		Rendered.rootContainer = rootContainer;
	}

}
