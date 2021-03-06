package net.sf.eclipsefp.haskell.ui.views;

import java.util.Collection;
import net.sf.eclipsefp.haskell.core.cabal.CabalPackageRef;
import net.sf.eclipsefp.haskell.core.cabal.CabalPackageVersion;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * content provided for packages and their versions
 * @author JPMoresmau
 *
 */
public class CabalPackageContentProvider implements ITreeContentProvider {

  @Override
  public Object[] getChildren( final Object arg0 ) {
    if (arg0 instanceof CabalPackageRef){
      return ((CabalPackageRef)arg0).getCabalPackageVersions().toArray();
    }
    return new Object[0];
  }

  @Override
  public Object getParent( final Object arg0 ) {
   if (arg0 instanceof CabalPackageVersion){
     return ((CabalPackageVersion)arg0).getRef();
   }
    return null;
  }

  @Override
  public boolean hasChildren( final Object arg0 ) {
    if (arg0 instanceof CabalPackageRef){
      return true;
    }
    return false;
  }

  @Override
  public Object[] getElements( final Object arg0 ) {
    if (arg0 instanceof Collection<?>){
      return ((Collection<?>)arg0).toArray();
    }
    return null;
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public void inputChanged( final Viewer arg0, final Object arg1, final Object arg2 ) {
    // TODO Auto-generated method stub

  }

}
