/** 
 * Copyright (c) 2012 by JP Moresmau
 * This code is made available under the terms of the Eclipse Public License,
 * version 1.0 (EPL). See http://www.eclipse.org/legal/epl-v10.html
 */
package net.sf.eclipsefp.haskell.buildwrapper.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

/**
 * @author JP Moresmau
 *
 */
public class UsageResults {
	private Map<IProject,Map<IFile,Map<String,Collection<SearchResultLocation>>>> allResults=new HashMap<IProject, Map<IFile,Map<String,Collection<SearchResultLocation>>>>();
	
	private int size=0;
	
	public void put(IFile file,Map<String,Collection<SearchResultLocation>> locs){
		IProject p=file.getProject();
		Map<IFile,Map<String,Collection<SearchResultLocation>>> m=allResults.get(p);
		if (m==null){
			m=new HashMap<IFile, Map<String,Collection<SearchResultLocation>>>();
			allResults.put(p, m);
		}
		Map<String,Collection<SearchResultLocation>> sections=m.get(file);
		if (sections==null){
			sections=new HashMap<String, Collection<SearchResultLocation>>();
			m.put(file, sections);
		}
		for (String s:locs.keySet()){
			Collection<SearchResultLocation> allLocs=sections.get(s);
			if (allLocs==null){
				allLocs=new ArrayList<SearchResultLocation>();
				sections.put(s, allLocs);
			}
			Collection<SearchResultLocation> sLocs=locs.get(s);
			for (SearchResultLocation l:sLocs){
				l.setIFile(file);
			}
			size+=sLocs.size();
			allLocs.addAll(sLocs);
		}
	}
	
	public void add(UsageResults r){
		if (r!=null){
			for (IProject p:r.listProjects()){
				Map<IFile,Map<String,Collection<SearchResultLocation>>> m=r.getUsageInProject(p);
				for (IFile f:m.keySet()){
					put(f, m.get(f));
				}
			}
		}
	}
	
	public void filter(Set<IResource> workingSet){
		for (Iterator<IProject> itP=allResults.keySet().iterator();itP.hasNext();){
			IProject p=itP.next();
			if (!workingSet.contains(p)){
				Map<IFile,Map<String,Collection<SearchResultLocation>>> m=allResults.get(p);
				for (Iterator<IFile> itF=m.keySet().iterator();itF.hasNext();){
					IFile f=itF.next();
					if (!workingSet.contains(f)){
						IContainer pa=f.getParent();
						boolean remove=true;
						while (pa!=null && !(pa instanceof IProject) && remove){
							if (workingSet.contains(pa)){
								remove=false;
							}
							pa=pa.getParent();
						}
						if (remove){
							itF.remove();
						}
					}
				}
				if (m.isEmpty()){
					itP.remove();
				}
			}
		}
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	public Set<IProject> listProjects(){
		return allResults.keySet();
	}
	
	public Map<IFile,Map<String,Collection<SearchResultLocation>>> getUsageInProject(IProject p){
		return allResults.get(p);
	}
	
}
