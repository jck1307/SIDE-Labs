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


package com.bluexml.side.util.deployer.war;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.util.componentmonitor.MonitorWriter;
import com.bluexml.side.util.libs.FileExtensionFilter;
import com.bluexml.side.util.libs.FileHelper;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.libs.zip.TrueZipHelper;

public abstract class DirectWebAppsDeployer extends WarDeployer {
	public DirectWebAppsDeployer(String cleanKey, String webappName, String webappKeyName, String packageExt) {
		super(cleanKey, null, webappName, webappKeyName);
		this.packageExt = packageExt;
		tzh = new TrueZipHelper(packageExt);
	}

	protected TrueZipHelper tzh = null;
	protected File wkdir = null;
	protected String packageExt = null;

	public File getWorkingDir() throws Exception {
		if (wkdir == null) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.1")); //$NON-NLS-1$
		}
		return wkdir;
	}

	@Override
	protected void clean(File fileToDeploy) throws Exception {
		monitor.beginTask(Activator.Messages.getString("DirectWebAppsDeployer.0")); //$NON-NLS-1$
		boolean result = true;
		// delete exploded webapps
		if (getDeployedWebbAppFolder().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("WarDeployer.2", getDeployedWebbAppFolder().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getDeployedWebbAppFolder(), false);
		}

		// unzip .war or .org if exist
		if (getBackupWarFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.1"), Activator.Messages.getString("DirectWebAppsDeployer.4", getWarToPatchFile().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			TrueZipHelper tzh2 = new TrueZipHelper(backupWarExt);
			result = tzh2.copyFiles(getBackupWarFile(), getDeployedWebbAppFolder(), true);
		} else if (getWarToPatchFile().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.4", getWarToPatchFile().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			TrueZipHelper tzh2 = new TrueZipHelper(warToPatchExt);
			result = tzh2.copyFiles(getWarToPatchFile(), getDeployedWebbAppFolder(), true);
		} else {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.7") + getWarToPatchFile().getAbsolutePath() + Activator.Messages.getString("DirectWebAppsDeployer.9")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!result) {
			throw new Exception(Activator.Messages.getString("DirectWebAppsDeployer.10")); //$NON-NLS-1$
		}
		monitor.taskDone(Activator.Messages.getString("DirectWebAppsDeployer.2")); //$NON-NLS-1$
	}

	public FileFilter getFileFilter() {
		return new FileExtensionFilter(packageExt);
	}

	@Override
	protected void deployProcess(File fileToDeploy) throws Exception {
		if (!getDeployedWebbAppFolder().exists()) {
			this.clean(fileToDeploy);
		}
		if (fileToDeploy.exists() && fileToDeploy.isDirectory()) {
			for (File f : fileToDeploy.listFiles(getFileFilter())) {
				deployFile(f);
			}
		} else if (fileToDeploy.exists() && fileToDeploy.isFile()) {
			deployFile(fileToDeploy);
		} else {
			monitor.addWarningTextAndLog(Activator.Messages.getString("WarDeployer.5"), "");
		}
	}

	private void deployFile(File f) throws Exception {
		monitor.getLog().addInfoLog(Activator.Messages.getString("WarDeployer.6"), Activator.Messages.getString("WarDeployer.7", f.getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
		File explodedPackage = new File(getWorkingDir(), f.getName().replaceAll("\\." + packageExt, "")); //$NON-NLS-1$ //$NON-NLS-2$

		explodedPackage.mkdirs();
		// unzip files in tmp
		tzh.copyFiles(f, explodedPackage, true);

		Map<String, File> map = createMapper(explodedPackage);
		List<File> fileList = FileHelper.listAll(explodedPackage);

		dispatchFiles(fileList, map);
	}

	@Override
	protected void preProcess(File fileToDeploy) throws Exception {
		File out = IFileHelper.getFile(IFileHelper.getIFolder(getTargetPath()));
		this.wkdir = new File(out, this.toString());
		if (getWorkingDir().exists()) {
			monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("DirectWebAppsDeployer.17", getWorkingDir().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
			FileHelper.deleteFile(getWorkingDir(), false);
		}
		monitor.getLog().addInfoLog(Activator.Messages.getString("DirectWebAppsDeployer.5"), Activator.Messages.getString("WarDeployer.19", getWorkingDir().getName()), ""); //$NON-NLS-1$ //$NON-NLS-2$
		getWorkingDir().mkdirs();
	}

	public Map<String, File> createMapper(File packageDirectory) {
		File packageFolder = getDeployedWebbAppFolder();

		Map<String, File> mapper = new HashMap<String, File>();
		// all files just copied in same directory organization, so filter is on
		// packageDirectory
		addToMap(mapper, packageDirectory.getAbsolutePath(), packageFolder, "/");
		return mapper;
	}

	protected void addToMap(Map<String, File> map, String key, File ampRoot, String target) {
		map.put(key.replace("/", File.separator), createTargetFolders(ampRoot, target.replace("/", File.separator))); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected File createTargetFolders(File ampRoot, String p) {
		File dir = new File(ampRoot, p);
		dir.mkdirs();
		return dir;
	}

	protected void dispatchFiles(List<File> files, Map<String, File> mapper) throws Exception {
		MonitorWriter mw = new MonitorWriter(monitor, Activator.Messages.getString("DirectWebAppsDeployer.3"), ""); //$NON-NLS-1$ //$NON-NLS-2$
		for (File f : files) {
			for (Map.Entry<String, File> ent : mapper.entrySet()) {
				if (f.getAbsolutePath().indexOf(ent.getKey()) != -1) {
					String path = f.getAbsolutePath();
					String pathIn = ent.getValue().getAbsolutePath() + File.separator + path.substring(path.indexOf(ent.getKey()) + ent.getKey().length());
					File dest = new File(pathIn);
					dest.getParentFile().mkdirs();
					// put to this dir
					FileHelper.copyFiles(f, dest, true, mw);
				}
			}
		}
	}


}
