package programanalysis;

import java.io.PrintStream;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PPABindingsUtil;

public class PPAVisitor extends ASTVisitor {

    private PrintStream printer;
    
    public PPAVisitor(PrintStream printer) {
	super();
	this.printer = printer;
    }
    
    @Override
    public void postVisit(ASTNode node) {
	super.postVisit(node);
	
	if (node instanceof Expression) {
	    Expression exp = (Expression) node;
	    
	    IBinding binding = null;
	    if (exp instanceof Name) {
		Name name = (Name) exp;
		binding = name.resolveBinding();
	    }
	    else {
		return;
	    }
	    if (binding != null) {
		printer.println(node.toString() + "|" + PPABindingsUtil.getBindingText(binding));
	    }
	    else {
		printer.println(node.toString() + "|" + "ERROR: binding is null");
	    }
	    printer.flush();
	}
    }
    
}
