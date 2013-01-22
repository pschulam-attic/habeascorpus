package programanalysis;

import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

    /* (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
     */
    public Object start(IApplicationContext context) throws Exception {
	System.out.println("Hello RCP World!");
	final Map args = context.getArguments();
	final String[] appArgs = (String[]) args.get("application.args");
	for (final String arg : appArgs)
	    System.out.println(arg);
	
	Analyzer analyzer = new Analyzer("/home/pschulam/tmp/ppa/A.java");
	System.out.println(analyzer.getAnalysis());

	return IApplication.EXIT_OK;
    }

    /* (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#stop()
     */
    public void stop() {
	// nothing to do
    }
}
