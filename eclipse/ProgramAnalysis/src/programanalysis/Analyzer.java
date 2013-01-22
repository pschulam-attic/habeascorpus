package programanalysis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.eclipse.jdt.core.dom.CompilationUnit;

import ca.mcgill.cs.swevo.ppa.PPAOptions;
import ca.mcgill.cs.swevo.ppa.ui.PPAUtil;

public class Analyzer {
    
    private CompilationUnit cu;
    private String analysis;
    
    public Analyzer(String filename) {
	File source = new File(filename);
	cu = PPAUtil.getCU(source, new PPAOptions());
	
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	PPAVisitor visitor = new PPAVisitor(ps);
	cu.accept(visitor);
	analysis = baos.toString();
    }
    
    public String getAnalysis() {
	return analysis;
    }
}
