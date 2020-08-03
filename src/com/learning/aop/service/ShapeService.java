package com.learning.aop.service;

import com.learning.aop.model.Circle;
import com.learning.aop.model.Triangle;

public class ShapeService {
	
	private Circle circle;
	private Triangle triangle;
	/**
	 * @return the circle
	 */
	public Circle getCircle() {
		return circle;
	}
	/**
	 * @param circle the circle to set
	 */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	/**
	 * @return the triangle
	 */
	public Triangle getTriangle() {
		return triangle;
	}
	/**
	 * @param triangle the triangle to set
	 */
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	
	

}
