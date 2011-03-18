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


package com.bluexml.side.util.feedback.startup;

import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.util.feedback.FeedbackActivator;
import com.bluexml.side.util.feedback.management.FeedbackSender;
import com.bluexml.side.util.feedback.ui.PopUpDialogBox;

public class Startup implements IStartup {

	public void earlyStartup() {
		
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				// Get preferences :
				int prefp = FeedbackActivator.getFeedbackUploadPeriodPreference();
				if (prefp==0){
					FeedbackActivator.setFeedbackUploadPeriod(7);
				}
				
				int pref = FeedbackActivator.getFeedBackPreference();
				if (pref == 0 ){
					FeedbackActivator.setFeedBackPreference(FeedbackActivator.FEEDBACK_PREF_ALWAYS);
				}
					
				if (
					pref == FeedbackActivator.FEEDBACK_PREF_NOTNOW ||
							pref == FeedbackActivator.FEEDBACK_PREF_NOW) {
					// Get last update date
					Long longDate = FeedbackActivator.getDefault().getPreferenceStore().getLong(FeedbackActivator.LAST_UPDATE_DATE);
					if (longDate.equals(0L)) {
						// Does we need to send data?
						Date lastUpdate = new Date(longDate);
						Date now = new Date();
						long delta = now.getTime() - lastUpdate.getTime();
						if (delta / (FeedbackActivator.MILLISECONDS_PER_DAY) >= FeedbackActivator.getFeedbackUploadPeriodPreference()) {
							// Show pop up and send data
							PopUpDialogBox popup = new PopUpDialogBox(Display.getDefault().getActiveShell());
							popup.open();
						}
					}
				} else if (pref == FeedbackActivator.FEEDBACK_PREF_ALWAYS) {
					FeedbackSender.doSend();
				} else if (pref != FeedbackActivator.FEEDBACK_PREF_NEVERSETTED) {
					Date nowDate = new Date();
					FeedbackActivator.setFeedBackPreference(FeedbackActivator.FEEDBACK_PREF_NEVERSETTED);
					FeedbackActivator.getDefault().getPreferenceStore().setValue(FeedbackActivator.LAST_UPDATE_DATE, nowDate.getTime());
				}
			}
		});
	}
}
