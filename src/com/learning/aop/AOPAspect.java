package com.learning.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.aop.model.Circle;
import com.learning.aop.model.Triangle;
import com.learning.aop.service.ShapeService;

public class AOPAspect {

	public static void main(String[] args) {

		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ShapeService service = ctx.getBean("shapeService",ShapeService.class);
		Triangle triangle = service.getTriangle();
		String triangleName = triangle.getName();// loggingAdvice
		
		Circle circle = service.getCircle(); 
		String name = circle.getName();
		
		
		
		
	}

}
