package de.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.application.hello.Hello;


@ComponentScan("de")
public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		//AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AbstractApplicationContext context =new AnnotationConfigApplicationContext(Main.class);
		context.registerShutdownHook();


	}

}
