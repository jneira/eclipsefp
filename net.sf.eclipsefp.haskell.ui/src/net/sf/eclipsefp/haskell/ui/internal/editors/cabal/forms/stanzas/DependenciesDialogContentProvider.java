/**
 * (c) 2011, Alejandro Serrano
 * Released under the terms of the EPL.
 */
package net.sf.eclipsefp.haskell.ui.internal.editors.cabal.forms.stanzas;

import java.util.ArrayList;
import java.util.List;
import net.sf.eclipsefp.haskell.browser.BrowserPlugin;
import net.sf.eclipsefp.haskell.browser.Database;
import net.sf.eclipsefp.haskell.browser.items.HaskellPackage;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the list of available dependencies.
 * @author Alejandro Serrano
 *
 */
public class DependenciesDialogContentProvider implements ITreeContentProvider {

  private HaskellPackage[] elements;

  public DependenciesDialogContentProvider( final List<String> alreadySelected ) {
    super();

    try {
      //BrowserPlugin.getSharedInstance().setCurrentDatabase( DatabaseType.ALL,
       //   null );
      ArrayList<HaskellPackage> pkgs = new ArrayList<HaskellPackage>();
      for( HaskellPackage pkg: BrowserPlugin.getSharedInstance().getPackages(Database.ALL) ) {
        if( alreadySelected.indexOf( pkg.getIdentifier().getName() ) == -1 ) {
          pkgs.add( pkg );
        }
      }
      this.elements = pkgs.toArray( new HaskellPackage[ pkgs.size() ] );
    } catch( Throwable ex ) {
      this.elements = new HaskellPackage[ 0 ];
    }
  }

  @Override
  public void dispose() {
    // Do nothing
  }

  @Override
  public void inputChanged( final Viewer viewer, final Object oldInput, final Object newInput ) {
    // Do nothing
  }

  @Override
  public Object[] getElements( final Object inputElement ) {
    return elements;
  }

  @Override
  public Object[] getChildren( final Object parentElement ) {
    return new Object[ 0 ];
  }

  @Override
  public Object getParent( final Object element ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasChildren( final Object element ) {
    // TODO Auto-generated method stub
    return false;
  }

}