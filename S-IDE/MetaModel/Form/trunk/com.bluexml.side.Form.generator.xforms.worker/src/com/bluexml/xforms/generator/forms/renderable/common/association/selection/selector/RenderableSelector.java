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


package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.ModelElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.AbstractModelElementUpdater;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementEnumeration;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementInstanceList;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementUpdaterEnum;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementUpdaterList;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean.AssociationType;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

/**
 * The left column of a selection widget, consisting of selection list, count outputs, search input
 * and Create button.
 */
public class RenderableSelector extends AbstractRenderable {

	/** The bind id. */
	private ModelElementBindSimple bindId;

	/** The bind label. */
	private ModelElementBindSimple bindLabel;

	/** The bind for max results. */
	private ModelElementBindSimple bindMaxResults;

	/** The bind for the data type. */
	private ModelElementBindSimple bindType;

	/** The instance name. */
	private String instanceName;

	/** The instance path. */
	private String instancePath;

	/** The instance node path. */
	private String instanceNodePath;

	/** The model element list updater. */
	private AbstractModelElementUpdater modelElementUpdater;

	/** The parent renderable */
	private AbstractRenderable parent;

	/**
	 * Instantiates a new renderable selector.
	 * 
	 * @param assoBean
	 *            the association bean
	 */
	public RenderableSelector(AssociationBean assoBean) {
		super(assoBean);

		parent = null;

		String idStr;
		if (bean.isForField()) {
			String overridingType = bean.getOverridingType();
			idStr = overridingType.replaceAll(":", "_") + bean.getName() + "ListExt";
			instanceName = XFormsGenerator.getId(idStr); // #1523
			modelElementUpdater = new ModelElementUpdaterList(overridingType, instanceName, bean);
		} else if (bean.getAssociationType() == AssociationType.clazz) {
			idStr = ModelTools.getCompleteNameJAXB(bean.getDestinationClass()) + "List";
			instanceName = XFormsGenerator.getId(idStr);
			modelElementUpdater = new ModelElementUpdaterList(bean.getDestinationClass(),
					instanceName, bean);
		} else {
			idStr = ModelTools.getCompleteNameJAXB(bean.getDestinationSelect().getEnumeration())
					+ "EnumInstance";
			instanceName = XFormsGenerator.getId(idStr);
			modelElementUpdater = new ModelElementUpdaterEnum(bean, instanceName);
		}
		instancePath = "instance('" + instanceName + "')/";
		instanceNodePath = instancePath + MsgId.INT_INSTANCE_SELECT_ITEM;

		// bindId = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDID);
		// bindLabel = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDLABEL);
		// bindMaxResults = new ModelElementBindSimple(instancePath +
		// MsgId.INT_INSTANCE_SELECTEDMAX);
		// bindType = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDTYPE);
		//
		// // bindId.setType(new QName("string"));
		// // bindLabel.setType(new QName("string"));
		// // bindMaxResults.setType(new QName("string"));
		// bindMaxResults.setHidden(true);
		// bindType.setHidden(true);

		// for workflows, we don't want anything but the list
		RenderableSelectorSearcher searcher = new RenderableSelectorSearcher(bean, this);
		RenderableSelectorList itemList = new RenderableSelectorList(bean, this);

		if (bean.isInFeatureSearchMode() && bean.isItemSelector()) {
			add(searcher);
		}

		add(itemList);
		if (bean.isItemSelector()) {
			if (bean.isNoStatsOutput() == false) {
				RenderableSelectorCount statsOutput = new RenderableSelectorCount(bean, this);
				add(statsOutput);
			}

			if (bean.isInFeatureFilterMode()) {
				add(searcher);
			}

			if (bean.isShowingActions()
					&& bean.getAssociationType() == AssociationBean.AssociationType.clazz) {
				add(new RenderableSelectorCreate(bean, this));
			}
		}
	}

	/**
	 * Gets the instance name.
	 * 
	 * @return the instance name
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * Gets the instance path.
	 * 
	 * @return the instance path
	 */
	public String getInstancePath() {
		return instancePath;
	}

	/**
	 * Gets the instance node path.
	 * 
	 * @return the instance node path
	 */
	public String getInstanceNodePath() {
		return instanceNodePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/**
	 * Gets the bind id.
	 * 
	 * @return the bind id
	 */
	public ModelElementBindSimple getBindId() {
		initBinds();
		return bindId;
	}

	/**
	 * Gets the bind label.
	 * 
	 * @return the bind label
	 */
	public ModelElementBindSimple getBindLabel() {
		initBinds();
		return bindLabel;
	}

	/**
	 * @return the bindMaxResults
	 */
	public ModelElementBindSimple getBindMaxResults() {
		initBinds();
		return bindMaxResults;
	}

	/**
	 * Gets the bind for the data type.
	 * 
	 * @return
	 */
	public ModelElementBindSimple getBindType() {
		initBinds();
		return bindType;
	}

	public AbstractModelElementUpdater getModelElementUpdater() {
		return modelElementUpdater;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedDiv rendered = new RenderedDiv(XFormsGenerator.getId("Selector"));
		initBinds();

		ModelElement modelElementInit;
		if (bean.isForField()) {
			modelElementInit = new ModelElementInstanceList(bean.getOverridingType(), instanceName,
					bean);
		} else if (bean.getAssociationType() == AssociationType.clazz) {
			modelElementInit = new ModelElementInstanceList(bean, instanceName);
		} else {
			modelElementInit = new ModelElementEnumeration(bean.getDestinationSelect(),
					instanceName);
		}

		rendered.addModelElement(modelElementInit);

		if (bean.isItemSelector()) {
			rendered.addModelElement(modelElementUpdater);
		}

		if (bean.isItemSelector()) {
			rendered.addModelElementRoot(bindId);
			rendered.addModelElementRoot(bindLabel);
			rendered.addModelElementRoot(bindMaxResults);
			rendered.addModelElementRoot(bindType);
		}
		return rendered;
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#getDivStyle()
	 */
	@Override
	public String getDivStyle() {
		return MsgId.INT_CSS_SELECT_LIST.getText();
	}

	/**
	 * We need to add this function so that nested forms (following the additional feature of #1225)
	 * continue to perform as needed in #978, meaning "so that mandatory selection widgets have
	 * their alert message display when nothing is selected and that message disappears when an
	 * element is selected". <br>
	 * If bindId, bindLabel, etc. are created in the constructor, the same binds stand for all
	 * versions of the form. The consequence is that any previously set constraint is accumulated
	 * with new constraints, leading to the first version performing as expected w.r.t. #978 and the
	 * other versions always failing to satisfy the constraint.<br>
	 * By creating the binds here, the binds are recreated each time the form is rendered, thus
	 * resetting the constraint.
	 */
	public void initBinds() {
		if (bindId != null) {
			return;
		}
		bindId = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDID);
		bindLabel = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDLABEL);
		bindMaxResults = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDMAX);
		bindType = new ModelElementBindSimple(instancePath + MsgId.INT_INSTANCE_SELECTEDTYPE);

		// NONE OF THESE IS REALLY NEEDED
		// bindId.setType(new QName("string"));
		// bindLabel.setType(new QName("string"));
		// bindMaxResults.setType(new QName("string"));
		// bindMaxResults.setHidden(true);
		// bindType.setHidden(true);
		if (bean.isMandatory()) {
			// #978
			// setting bindId's required to true will give the visual cue (a red star under
			// Chiba)
			bindId.setRequired(true);
			bindId.setAnAssociation(true);
			// !!! the constraint will be set by the actions (RenderableSSingleActions or
			// RenderableSMultipleActionsAddRemove) !!!
		}
		if (bean.isDisabled()) {
			bindId.setReadOnly(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.Renderable#renderEnd(com.bluexml.xforms.generator.forms
	 * .Rendered)
	 */
	@Override
	public void renderEnd(Rendered rendered) {
		super.renderEnd(rendered);
		bindId = null;
		bindLabel = null;
		bindMaxResults = null;
		bindType = null;
	}

	/**
	 * 
	 */
	public boolean isForField() {
		return bean.isForField();
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(AbstractRenderable parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public AbstractRenderable getParent() {
		return parent;
	}

}
