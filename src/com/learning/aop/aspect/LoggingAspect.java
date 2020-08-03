package com.learning.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.learning.aop.model.Circle;
import com.learning.aop.model.Triangle;

@Aspect
public class LoggingAspect {

	// This advice will be invoked when any getName() is invoked within the
	// application.
	@Before("execution(public String getName())")
	public void loggingAdvice() {
		System.out.println(
				"In entire application all the getter methods which retrun String as Argument, this method will be invoked");
	}
	// The above method will be invoked irrespective of the class. Whenever the
	// getMethod is
	// invoked the aspect will trigger loggginAdvice. If you wish to restrict the
	// invocation only
	// on the Class method. Then we should provide the class name just before method
	// as below.

	@Before("execution(public String com.learning.aop.model.Circle.getName())")
	public void loggingCircleAdvice() {
		System.out.println("Only Circle advice ");
	}

	@Before("execution(public String com.learning.aop.model.Triangle.getName())")
	public void loggingTriangleAdvice() {
		System.out.println("Only Triangle advice ");
	}

	/*
	 * We use wild card on all the getters then we will write following advice This
	 * method will be executed for all those methods which starts with the name get
	 */

	@Before("execution(public String get())")
	public void loggingAspectUsingWildCard() {
		System.out.println("Wild Card Advice. ");
	}

	/*
	 * if you wish to apply advice to all the methods which starts the method name
	 * as get and returns anything then we use the following execution pattern
	 */
	@Before("execution(public * get*())")
	public void loggingAspectUsingWildCardOnReturnStatement() {
		System.out.println("A get method which returns anything should invoke this advice");
	}

	@Before("forAllGetters() ")
	public void aspectUsingPointCutExpression() {
		System.out.println(
				"Pointcut execution for invoking a get method irrespective of what ever the return type is returns ");
	}

	/*
	 * Introducing pointCuts. Creating a pointcut which is applicable to all the
	 * getters.
	 */

	@Pointcut("execution( * get*())")
	public void forAllGetters() {
		System.out.println(
				"This method will never gets executed and you will not see this statement in console, No matter what");
	}
/*
 *  This method will be invoked on all the methods inside circle class and also all methods inside ShapeServcie
 */
	@Before("forAllMethodsInCircle() && forAllMethodsInsideShapeServicePC()")
	public void logginAspectForAllMethodsInCircle() {
		System.out.println("All methods in Circle");
	}

	/*
	 * Creating a pointcut expression which will be invoked for all the methods in a
	 * class
	 */

	@Pointcut("within(com.learning.aop.model.Circle)")
	public void forAllMethodsInCircle() {
		System.out.println(
				"This method will never gets executed and you will not see this statement in console, No matter what");
	}

	@Pointcut("within( com.learning.aop.service.ShapeService")
	public void forAllMethodsInsideShapeServicePC() {

	}

	@Before("forAllMethodsInsideShapeServicePC()")
	public void forAllMethodsInsideShapeServiceAdvice() {
		System.out.println("for All Methods Inside Shape Service");
	}
	
	/*
	 * In order to run the advice dynamically based on the target method it is getting invoked. 
	 * Then we would need the method signature from where the method got executed. To achieve the same 
	 * we would be passing jointPoint to the advice method.
	 * For instance, forAllMethodsInCircle() has two types of methods defined 
	 * getters and setters. We might be writing different advice for setter and different advice for getter. 
	 * In order to achieve it , we  would be using jointpoint argument
	 * */
	@Before("with(com.learning.aop.model.Circle)")
	public void allCircleMethods(JoinPoint point) {
		// toString method will tell which Class which method is being invoked 
		System.out.println(point.toString());
		// getTarget method is another method which comes handy when you want to get the instance of the target class
		Object object  = point.getTarget();
		if(object instanceof Circle) {
			Circle circle = (Circle) object;
			circle.setName("Advice changed state");
		}else {
			Triangle triangle = (Triangle) object;
			triangle.setName("Advice changed state");
		}
	}
		
	@Before("arg(String)")
	public void anyMethodWhichTakesStringArg() {
			System.out.println("Method which is Taking String argument");
	}
	
	@Before("arg(name)")
	public void anyMethodWhichTakesStringAttribute(String name) {
			System.out.println("Method which is Taking String attribute as "+name);
	}
	
	// The following setter will be invoked after the setter method invocation.
	
	@After("arg(name)")
	public void setterAfterAdvice(String name ) {
		System.out.println("The setter has invoked  with setter value as "+name);
	}
	//This method is invoked after returning something from a method . 
	//The input value and at the same time the output value we can capture
	@AfterReturning(pointcut="args(name)", returning="returnString")
	public void inputAndOutputValues(String name, String returnString) {
		System.out.println("Input "+name+"\n Output "+returnString);
	}
		
}
