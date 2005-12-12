package net.sf.eclipsefp.haskell.core.jparser.ast;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import de.leiffrenzel.fp.haskell.core.halamo.ICompilationUnit;
import de.leiffrenzel.fp.haskell.core.halamo.IDeclaration;
import de.leiffrenzel.fp.haskell.core.halamo.IExportSpecification;
import de.leiffrenzel.fp.haskell.core.halamo.IImport;
import de.leiffrenzel.fp.haskell.core.halamo.IModule;

public class Module extends HaskellLanguageElement implements IModule {

	private List<IExportSpecification> fExports;
	private List<IImport> fImports;
	private List<IDeclaration> fDecls;

	public Module() {
		fExports = new Vector<IExportSpecification>();
		fImports = new Vector<IImport>();
		fDecls = new Vector<IDeclaration>();
	}
	
	public IExportSpecification[] getExportSpecifications() {
		return fExports.toArray(new IExportSpecification[fExports.size()]);
	}

	public IImport[] getImports() {
		return fImports.toArray(new IImport[fImports.size()]);
	}

	public IDeclaration[] getDeclarations() {
		return fDecls.toArray(new IDeclaration[fDecls.size()]);
	}

	public ICompilationUnit getCompilationUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addExports(List<IExportSpecification> someExports) {
		fExports.addAll(someExports);
	}

	public void addImports(List<IImport> someImports) {
		fImports.addAll(someImports);
	}

	/**
	 * Adds a list of imports to the module. This is a convenience method.
	 * 
	 * @param imports the imports to be added
	 */
	public void addImports(IImport[] imports) {
		addImports(Arrays.asList(imports));
	}

	public void addDeclarations(List<IDeclaration> someDecls) {
		fDecls.addAll(someDecls);
	}

	public void addDeclarations(IDeclaration[] someDecls) {
		addDeclarations(Arrays.asList(someDecls));
	}

	public void addDeclaration(IDeclaration decl) {
		fDecls.add(decl);
	}

}
