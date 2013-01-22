package programanalysis;

import java.io.File;

public class DirectoryAnalyzer {
    
    private final String inDirname;
    private final String outDirname;
    private final File[] files;
    
    public DirectoryAnalyzer(String inDirname, String outDirname) throws RuntimeException {
	this.inDirname = inDirname;
	this.outDirname = outDirname;
	File dir = new File(inDirname);
	if (dir.isDirectory()) {
	    files = dir.listFiles();
	    for (final File file : files) {
		String filename = file.getName();
		if (filename.endsWith(".java")) {
		    String analysis = analyzeFile(filename);
		    writeAnalysis(analysis, filename);
		}
	    }
	}
	else {
	    throw new RuntimeException(inDirname + " is not a directory");
	}
    }
    
    private String analyzeFile(String filename) {
	Analyzer a = new Analyzer(filename);
	return a.getAnalysis();
    }
    
    private void writeAnalysis(String analysis, String inputFilename) throws RuntimeException {
	
    }

}
