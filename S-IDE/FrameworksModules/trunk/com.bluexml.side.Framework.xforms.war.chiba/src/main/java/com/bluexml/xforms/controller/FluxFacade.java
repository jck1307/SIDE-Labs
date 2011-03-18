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


// Copyright 2001-2007 ChibaXForms GmbH
/*
 *
 *    Artistic License
 *
 *    Preamble
 *
 *    The intent of this document is to state the conditions under which a Package may be copied, such that
 *    the Copyright Holder maintains some semblance of artistic control over the development of the
 *    package, while giving the users of the package the right to use and distribute the Package in a
 *    more-or-less customary fashion, plus the right to make reasonable modifications.
 *
 *    Definitions:
 *
 *    "Package" refers to the collection of files distributed by the Copyright Holder, and derivatives
 *    of that collection of files created through textual modification.
 *
 *    "Standard Version" refers to such a Package if it has not been modified, or has been modified
 *    in accordance with the wishes of the Copyright Holder.
 *
 *    "Copyright Holder" is whoever is named in the copyright or copyrights for the package.
 *
 *    "You" is you, if you're thinking about copying or distributing this Package.
 *
 *    "Reasonable copying fee" is whatever you can justify on the basis of media cost, duplication
 *    charges, time of people involved, and so on. (You will not be required to justify it to the
 *    Copyright Holder, but only to the computing community at large as a market that must bear the
 *    fee.)
 *
 *    "Freely Available" means that no fee is charged for the item itself, though there may be fees
 *    involved in handling the item. It also means that recipients of the item may redistribute it under
 *    the same conditions they received it.
 *
 *    1. You may make and give away verbatim copies of the source form of the Standard Version of this
 *    Package without restriction, provided that you duplicate all of the original copyright notices and
 *    associated disclaimers.
 *
 *    2. You may apply bug fixes, portability fixes and other modifications derived from the Public Domain
 *    or from the Copyright Holder. A Package modified in such a way shall still be considered the
 *    Standard Version.
 *
 *    3. You may otherwise modify your copy of this Package in any way, provided that you insert a
 *    prominent notice in each changed file stating how and when you changed that file, and provided that
 *    you do at least ONE of the following:
 *
 *        a) place your modifications in the Public Domain or otherwise make them Freely
 *        Available, such as by posting said modifications to Usenet or an equivalent medium, or
 *        placing the modifications on a major archive site such as ftp.uu.net, or by allowing the
 *        Copyright Holder to include your modifications in the Standard Version of the Package.
 *
 *        b) use the modified Package only within your corporation or organization.
 *
 *        c) rename any non-standard executables so the names do not conflict with standard
 *        executables, which must also be provided, and provide a separate manual page for each
 *        non-standard executable that clearly documents how it differs from the Standard
 *        Version.
 *
 *        d) make other distribution arrangements with the Copyright Holder.
 *
 *    4. You may distribute the programs of this Package in object code or executable form, provided that
 *    you do at least ONE of the following:
 *
 *        a) distribute a Standard Version of the executables and library files, together with
 *        instructions (in the manual page or equivalent) on where to get the Standard Version.
 *
 *        b) accompany the distribution with the machine-readable source of the Package with
 *        your modifications.
 *
 *        c) accompany any non-standard executables with their corresponding Standard Version
 *        executables, giving the non-standard executables non-standard names, and clearly
 *        documenting the differences in manual pages (or equivalent), together with instructions
 *        on where to get the Standard Version.
 *
 *        d) make other distribution arrangements with the Copyright Holder.
 *
 *    5. You may charge a reasonable copying fee for any distribution of this Package. You may charge
 *    any fee you choose for support of this Package. You may not charge a fee for this Package itself.
 *    However, you may distribute this Package in aggregate with other (possibly commercial) programs as
 *    part of a larger (possibly commercial) software distribution provided that you do not advertise this
 *    Package as a product of your own.
 *
 *    6. The scripts and library files supplied as input to or produced as output from the programs of this
 *    Package do not automatically fall under the copyright of this Package, but belong to whomever
 *    generated them, and may be sold commercially, and may be aggregated with this Package.
 *
 *    7. C or perl subroutines supplied by you and linked into this Package shall not be considered part of
 *    this Package.
 *
 *    8. The name of the Copyright Holder may not be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 *    9. THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED
 *    WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 *    MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
package com.bluexml.xforms.controller;

import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.chiba.agent.web.event.DefaultUIEventImpl;
import org.chiba.agent.web.event.UIEvent;
import org.chiba.agent.web.flux.EventLog;
import org.chiba.agent.web.flux.FluxException;
import org.chiba.agent.web.flux.FluxProcessor;
import org.chiba.agent.web.session.XFormsSession;
import org.chiba.agent.web.session.XFormsSessionManager;
import org.chiba.agent.web.upload.UploadInfo;
import org.chiba.processor.XFormsProcessor;
import org.chiba.xml.dom.DOMUtil;
import org.chiba.xml.xforms.ChibaBean;
import org.chiba.xml.xforms.exception.XFormsException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.ltd.getahead.dwr.ExecutionContext;

/**
 * AJAX Facade class to hide the full functionality from the web-client.
 * 
 * @author Joern Turner
 * @version $Id: FluxFacade.java 2875 2007-09-28 09:43:30Z lars $
 */
@SuppressWarnings("all")
public class FluxFacade {
	// this is a custom event to activate a trigger in XForms.
	public static final String FLUX_ACTIVATE_EVENT = "flux-action-event";
	private static final Log LOGGER = LogFactory.getLog(FluxFacade.class);
	private HttpSession session;

	/**
	 * grabs the actual web from the session.
	 */
	public FluxFacade() {
		session = ExecutionContext.get().getSession();
	}

	/**
	 * todo: implement XForms init via Ajax call
	 * 
	 * @return update info to initially setup the browser page
	 * @throws FluxException
	 *             when everything goes wrong
	 */
	public org.w3c.dom.Element init() throws FluxException {
		return null;
	}

	/**
	 * executes a trigger
	 * 
	 * @param id
	 *            the id of the trigger to execute
	 * @param sessionKey
	 *            the sessionKey identifying the user session
	 * @return the list of events that may result through this action
	 * @throws FluxException
	 *             raises exception if problem during processing occurs
	 */
	public org.w3c.dom.Element fireAction(String id, String sessionKey)
			throws FluxException {
		UIEvent uiActivateEvent = new DefaultUIEventImpl();
		uiActivateEvent.initEvent(FLUX_ACTIVATE_EVENT, id, null);
		return handleUIEvent(uiActivateEvent, sessionKey);
	}

	/**
	 * sets the value of a control in the processor.
	 * 
	 * @param id
	 *            the id of the control in the host document
	 * @param value
	 *            the new value
	 * @param sessionKey
	 *            the sessionKey identifying the user session
	 * @return the list of events that may result through this action
	 * @throws FluxException
	 *             raises exception if problem during processing occurs
	 */
	public org.w3c.dom.Element setXFormsValue(String id, String value,
			String sessionKey) throws FluxException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("FluxFacade instance: " + this.toString());
		}
		UIEvent event = new DefaultUIEventImpl();
		event.initEvent("SETVALUE", id, value);
		return handleUIEvent(event, sessionKey);
	}

	public org.w3c.dom.Element getXFormsDOM(String sessionKey)
			throws FluxException {
		try {
			return ((Document) getProcessor(sessionKey).getXForms())
					.getDocumentElement();
		} catch (XFormsException e) {
			throw new FluxException(e);
		}
	}

	public org.w3c.dom.Element setRepeatIndex(String id, String position,
			String sessionKey) throws FluxException {
		try {
			UIEvent event = new DefaultUIEventImpl();
			event.initEvent("SETINDEX", id, position);
			return handleUIEvent(event, sessionKey);
		} catch (Exception e) {
			LOGGER.fatal("XFormsSession not found - stopping");
			Document newDocument = DOMUtil.newDocument(false, false);
			Element root = newDocument.createElement("root");
			newDocument.appendChild(root);
			return root;
		}
	}

	/**
	 * fetches the progress of a running upload.
	 * 
	 * @param id
	 *            id of the upload control in use
	 * @param filename
	 *            filename for uploaded data
	 * @param sessionKey
	 *            the sessionKey identifying the user session
	 * @return a array containing two elements for evaluation in browser. First
	 *         param is the upload control id and second will be the current
	 *         progress of the upload.
	 */
	public org.w3c.dom.Element fetchProgress(String id, String filename,
			String sessionKey) {
		String progress;
		UploadInfo uploadInfo;

		if (session != null
				&& session.getAttribute(XFormsSession.ADAPTER_PREFIX
						+ sessionKey + "-uploadInfo") != null) {
			uploadInfo = (UploadInfo) session
					.getAttribute(XFormsSession.ADAPTER_PREFIX + sessionKey
							+ "-uploadInfo");

			if (uploadInfo.isInProgress()) {
				double p = uploadInfo.getBytesRead()
						/ uploadInfo.getTotalSize();

				progress = p + "";
				float total = uploadInfo.getTotalSize();
				float read = uploadInfo.getBytesRead();
				int iProgress = (int) Math.ceil((read / total) * 100);
				if (iProgress < 100) {
					progress = Integer.toString(iProgress);
				} else {
					progress = "99";
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Bytes total: " + uploadInfo.getTotalSize());
					LOGGER.debug("Bytes read: " + uploadInfo.getBytesRead());
					LOGGER
							.debug("elapsed time: "
									+ uploadInfo.getElapsedTime());
					LOGGER.debug("status: " + uploadInfo.getStatus());
					LOGGER.debug("Percent completed: "
							+ Math.ceil((read / total) * 100));
				}
			} else {
				progress = "100";
			}
		} else {
			// if session info is not found for some reason return 100 for
			// safety which allows to exit
			// javascript polling of progress info
			progress = "100";
		}
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		XFormsSession xFormsSession = manager.getXFormsSession(sessionKey);

		if (xFormsSession != null) {
			FluxProcessor processor = (FluxProcessor) xFormsSession
					.getProcessor();
			EventLog eventLog = processor.getEventLog();

			Element eventlogElement = eventLog.getLog();
			eventLog.flush();

			Element progressEvent = eventLog.add("upload-progress-event", id,
					"upload");
			eventLog.addProperty(progressEvent, "progress", progress);
			return eventlogElement;
		} else {
			EventLog eventLog = new EventLog();
			Element eventlogElement = eventLog.getLog();
			Element progressEvent = eventLog.add("upload-progress-event", id,
					"upload");
			eventLog.addProperty(progressEvent, "progress", progress);
			return eventlogElement;

		}
	}

	/**
	 * Note user typing activity (not value change), which extends session
	 * lifetime.
	 * 
	 * @param sessionKey
	 *            the sessionKey identifying the user session
	 */
	public void keepAlive(String sessionKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("FluxFacade keepAlive: " + sessionKey);
		}
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		XFormsSession xFormsSession = manager.getXFormsSession(sessionKey);
		xFormsSession.updateLRU();
	}

	public void setLocale(String locale, String sessionKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("FluxFacade setLocale: " + sessionKey);
		}
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		XFormsSession xFormsSession = manager.getXFormsSession(sessionKey);
		Locale chibalocale = new Locale(locale);
		xFormsSession.getProcessor().setContextParam(ChibaBean.CHIBA_LOCALE,
				chibalocale);
		try {
			xFormsSession.getProcessor().setLocale(locale);
		} catch (XFormsException e) {
			e.printStackTrace(); // ignore
		}
	}

	/**
	 * Note page unload, which rapidly ages session.
	 * 
	 * @param sessionKey
	 *            the sessionKey identifying the user session
	 */
	public void close(String sessionKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("FluxFacade close: " + sessionKey);
		}
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		if (manager == null) {
			return;
		}
		XFormsSession xFormsSession = manager.getXFormsSession(sessionKey);
		try {

			// don't use getXFormsSession to avoid needless error
			if (xFormsSession == null)
				return;
			XFormsProcessor adapter = xFormsSession.getProcessor();
			if (adapter == null)
				return;
			adapter.shutdown();
		} catch (XFormsException e) {
			LOGGER.warn("FluxFacade close: " + sessionKey, e);
		} finally {
			manager.deleteXFormsSession(sessionKey);
		}
	}

	private org.w3c.dom.Element handleUIEvent(UIEvent uiEvent, String sessionKey)
			throws FluxException {
		FluxProcessor processor = getProcessor(sessionKey);
		if (processor != null) {
			try {
				processor.handleUIEvent(uiEvent);
			} catch (XFormsException e) {
				LOGGER.error(e.getMessage());
			}
		} else {
			// session expired or cookie got lost
			throw new FluxException("Session expired. Please start again.");
		}
		EventLog eventLog = processor.getEventLog();
		Element eventlogElement = eventLog.getLog();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(this);
			LOGGER.debug(eventLog.toString());
			LOGGER.debug(processor);
			try {
				prettyPrintDOM(eventlogElement, System.out);

			} catch (TransformerException e) {
				e.printStackTrace();
			}

		}
		return eventlogElement;
	}
	
	public static void prettyPrintDOM(Node node, OutputStream stream) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.transform(new DOMSource(node), new StreamResult(stream));
    }

	private FluxProcessor getProcessor(String sessionKey) throws FluxException {
		if (session == null) {
			throw new FluxException("Session timed out");
		}
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		if (manager == null) {
			throw new FluxException("session timed out");
		}
		XFormsSession xFormsSession = manager.getXFormsSession(sessionKey);
		if (xFormsSession == null) {
			LOGGER.fatal("XFormsSession not found - stopping");
			throw new FluxException(
					"Your session has expired - Please start again.");
		}

		FluxProcessor processor = (FluxProcessor) xFormsSession.getProcessor();
		return processor;
	}

}

// end of class
