package net.spring;

import org.junit.Test;

import net.spring.context.AppContext;
import net.spring.dao.AnnotationTransactionDao;
import net.spring.dao.AopTxDao;
import net.spring.dao.BaseDao;

public class App {
	@Test
	public void springJdbTemplateTransactionTest() {
		BaseDao baseDao = AppContext.getBean("baseDao", BaseDao.class);
		
		System.out.println(baseDao.transaction());;
	}
	
	@Test
	public void springAopTxTransactionTest() {
		try {
			AopTxDao aopTxDao = AppContext.getBean("aopTxDao", AopTxDao.class);
			
			System.out.println(aopTxDao.aopTxTransaction());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	@Test
	public void annotationTransactionTest() {
		try {
			AnnotationTransactionDao annotationTransactionDao = AppContext.getBean("annotationTransactionDao", AnnotationTransactionDao.class);
			
			System.out.println(annotationTransactionDao.annotationTransaction());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
