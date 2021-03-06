package net.sf.eclipsefp.haskell.core.partitioned.runner;


/**
 * Runner for Alex lexer tool.
 *
 * @author Alejandro Serrano
 */
public class AlexRunner extends PartitionedRunner {
  private static String fullPath;

   public static void setFullPath( final String path ) {
    fullPath = path;
  }

	@Override
	public String getExecutableName() {
	  if (fullPath!=null && fullPath.length()>0){
	    return fullPath;
	  }
		return "alex"; //$NON-NLS-1$
	}

}
