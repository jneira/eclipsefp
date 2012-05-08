/**
 * Copyright (c) 2012 by JP Moresmau
 * This code is made available under the terms of the Eclipse Public License,
 * version 1.0 (EPL). See http://www.eclipse.org/legal/epl-v10.html
 */
package net.sf.eclipsefp.haskell.ui.internal.search;

import net.sf.eclipsefp.haskell.buildwrapper.BuildWrapperPlugin;
import net.sf.eclipsefp.haskell.buildwrapper.types.UsageResults;
import net.sf.eclipsefp.haskell.buildwrapper.usage.UsageQueryFlags;
import net.sf.eclipsefp.haskell.ui.internal.util.UITexts;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;


/**
 * Haskell Usage Query
 * @author JP Moresmau
 *
 */
public class UsageQuery implements ISearchQuery {
  private final String term;
  private final IProject project;
  private final UsageSearchResult sr;

  private int typeFlags=UsageQueryFlags.TYPE_ALL;
  private int scopeFlags=UsageQueryFlags.SCOPE_ALL;

  public UsageQuery( final String term,final IProject p ) {
    super();
    this.term = term;
    this.project=p;
    sr=new UsageSearchResult( this,term, project );
  }

  /* (non-Javadoc)
   * @see org.eclipse.search.ui.ISearchQuery#run(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public IStatus run( final IProgressMonitor paramIProgressMonitor )
      throws OperationCanceledException {
    /*UsageResults resultsRefs=BuildWrapperPlugin.getDefault().getUsageAPI().getModuleReferences( null, module,project );
    UsageResults resultsDefs=BuildWrapperPlugin.getDefault().getUsageAPI().getModuleDefinitions( null, module,project );
    resultsRefs.add(resultsDefs);*/
    UsageResults results=BuildWrapperPlugin.getDefault().getUsageAPI().exactSearch( null, term, project, typeFlags, scopeFlags );
    sr.setResults( results);

    return Status.OK_STATUS;
  }

  /* (non-Javadoc)
   * @see org.eclipse.search.ui.ISearchQuery#getLabel()
   */
  @Override
  public String getLabel() {
    return UITexts.References_query_label;
  }

  /* (non-Javadoc)
   * @see org.eclipse.search.ui.ISearchQuery#canRerun()
   */
  @Override
  public boolean canRerun() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.eclipse.search.ui.ISearchQuery#canRunInBackground()
   */
  @Override
  public boolean canRunInBackground() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.eclipse.search.ui.ISearchQuery#getSearchResult()
   */
  @Override
  public ISearchResult getSearchResult() {
    return sr;
  }


  public int getTypeFlags() {
    return typeFlags;
  }


  public void setTypeFlags( final int typeFlags ) {
    this.typeFlags = typeFlags;
  }


  public int getScopeFlags() {
    return scopeFlags;
  }


  public void setScopeFlags( final int scopeFlags ) {
    this.scopeFlags = scopeFlags;
  }

}
