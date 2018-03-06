package etsa02_basicbots.test;

public class MethodCall {
	private String methodName;
	private Object[] arguments;
	
	public MethodCall(String methodName, Object... arguments) {
		// TODO Auto-generated constructor stub
		this.methodName = methodName;
		this.arguments = arguments;
	}
	
	public boolean isMethod(String methodName) {
		return this.methodName.equals(methodName);
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public Object[] getArguments() {
		return arguments;
	}
}
