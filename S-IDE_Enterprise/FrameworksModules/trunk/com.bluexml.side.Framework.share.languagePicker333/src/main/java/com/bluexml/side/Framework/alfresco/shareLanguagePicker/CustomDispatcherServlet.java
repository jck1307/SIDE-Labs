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


package com.bluexml.side.Framework.alfresco.shareLanguagePicker;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alfresco.i18n.I18NUtil;
import org.alfresco.web.framework.render.RenderContext;
import org.alfresco.web.framework.render.RenderHelper;
import org.alfresco.web.site.FrameworkHelper;
import org.alfresco.web.site.RequestContext;
import org.alfresco.web.site.ThemeUtil;
import org.alfresco.web.site.Timer;
import org.alfresco.web.site.servlet.DispatcherServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
@SuppressWarnings("serial")
/**
 * 
 */
public class CustomDispatcherServlet extends DispatcherServlet {
	
	private static Log logger = LogFactory.getLog(DispatcherServlet.class);
    private static final String MIMETYPE_HTML = "text/html;charset=utf-8";
    
    public static void setLanguageFromLayoutParam(HttpServletRequest req)
    {
    	String urlLang = req.getParameter("shareLang");
	   	HttpSession currentSession = req.getSession();
	   	String sessionLang = (String) currentSession.getAttribute("shareLang");
	   	
    	//1st option: Select the url param shareLang
    	if (urlLang != null) {
    		I18NUtil.setLocale(I18NUtil.parseLocale(urlLang));    		
    		currentSession.setAttribute("shareLang", urlLang);
    	}
    	else if (sessionLang != null) {
    		I18NUtil.setLocale(I18NUtil.parseLocale(sessionLang));
    	}
    	else {    	
	       // set language locale from browser header
	       String acceptLang = req.getHeader("Accept-Language");
	       if (acceptLang != null && acceptLang.length() != 0)
	       {
	          StringTokenizer t = new StringTokenizer(acceptLang, ",; ");
	          // get language and convert to java locale format
	          String language = t.nextToken().replace('-', '_');
	          I18NUtil.setLocale(I18NUtil.parseLocale(language));
	       }
    	}
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
	{
	    boolean errorOccured = false;
	    
	    // bind a timer for reporting of dispatches
	    if (Timer.isTimerEnabled())
	    {
	        Timer.bindTimer(request);
	        Timer.start(request, "service");
	    }
	    
	    if (logger.isDebugEnabled())
	    {
	        String qs = request.getQueryString();
	        logger.debug("Processing URL: ("  + request.getMethod() + ") " + request.getRequestURI() + 
	              ((qs != null && qs.length() != 0) ? ("?" + qs) : ""));
	    }
	    
	    // apply language from browser locale setting
	    setLanguageFromLayoutParam(request);
	    
	    // no caching for generated page data
	    setNoCacheHeaders(response);
	    
	    // set response content type and charset
	    response.setContentType(MIMETYPE_HTML);
	    
	    // initialize the request context
	    RequestContext context = null;
	    try
	    {
	        context = FrameworkHelper.initRequestContext(request);
	    }
	    catch (Exception ex)
	    {
	        throw new ServletException(ex);
	    }
	    
	    if (logger.isDebugEnabled())
	        debug(context, "Context created for request: " + request.getRequestURI());
	    
	    // stamp any theme information onto the request
	    ThemeUtil.applyTheme(context, request);
	    
	    // create the top render context
	    RenderContext renderContext = RenderHelper.provideRenderContext(context, request, response);        
	    
	    // dispatch to render the page
	    try
	    {
	        if (Timer.isTimerEnabled())
	            Timer.start(request, "dispatch");
	
	        // dispatch to page processing code
	        dispatch(renderContext);
	        
	        if (Timer.isTimerEnabled())
	            Timer.stop(request, "dispatch");
	    }
	    catch (Throwable t)
	    {
	        /**
	         * The framework should naturally catch exceptions and handle
	         * them gracefully using the system pages defined in the
	         * configuration file.  For instance, if a component fails
	         * to render, it should resort to displaying itself with
	         * a friendly message.
	         * 
	         * On the other hand, it could be the case that something
	         * really nasty came this way - for instance an Error.
	         * 
	         * Or it may be the case that a system page was unavailable
	         * to handle the error.  In that case, the exception will fall
	         * back here as a RequestDispatchException.
	         * 
	         * It may also be the case that the system administrator
	         * wishes the errors to trickle back.  This would be the
	         * typical setup if the administrator wishes the servlet
	         * container to handle the error.
	         * 
	         * Finally, they may have opted to throw a Runtime Exception
	         * back.  We must handle that case as well.
	         */
	        
	        errorOccured = true;
	        
	        logger.error(t);
	        
	        // for now, we will throw back to servlet container
	        if (t instanceof RuntimeException)
	        {
	            throw (RuntimeException)t;
	        }
	        else
	        {
	            throw new ServletException("Error during dispatch: " + t.getMessage(), t);
	        }
	    }
	    finally
	    {
	        // clean up - unless an error occured as then we don't want to commit the response yet
	        if (!errorOccured)
	        {
	            response.getWriter().flush();
	            response.getWriter().close();
	        }
	        
	        // release any resources associated with the request context
	        context.release();
	        
	        // stop the service timer and print out any timing information (if enabled)
	        if (Timer.isTimerEnabled())
	        {
	            Timer.stop(request, "service");
	            Timer.reportAll(request);
	            Timer.unbindTimer(request);
	        }
	    }
	}

}
