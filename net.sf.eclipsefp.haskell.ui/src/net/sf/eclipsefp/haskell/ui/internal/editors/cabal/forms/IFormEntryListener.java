/**
 * (c) 2011, Alejandro Serrano
 * Released under the condidtions of the EPL.
 */
package net.sf.eclipsefp.haskell.ui.internal.editors.cabal.forms;


public interface IFormEntryListener {
  void focusGained( FormEntry entry );
  void textDirty( FormEntry entry );
  void textValueChanged( FormEntry entry );
  void selectionChanged( FormEntry entry );
}
