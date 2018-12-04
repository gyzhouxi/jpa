package cn.com.zgy.jpawork;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class TestMTM {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpawork");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		// 4. 持久化操作

		//addCourse(transaction,entityManager);     //添加课程信息
		addUser(transaction,entityManager);   	//添加学生以及对应的课程信息
		//updateUser(transaction,entityManager);    //修改
		//  delUser(transaction,entityManager);       //删除

		//addClass(transaction,entityManager); 
		// 5. 提交事务
		transaction.commit();

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	public static void addUser(EntityTransaction transaction, EntityManager entityManager) {
		transaction.begin();//开启事务
		List<Course> courseList = entityManager.createQuery("SELECT a FROM Course a where id in (3,5)").getResultList();//查找符合条件的课程信息，where后面设置所查询的条件
		Stu stu = new Stu();
		stu.setStuname("lihua");
		stu.setStupwd("lihua123");
		stu.setAddress(new Address("河北"));
		stu.setCourseList(courseList);
		Class class1 =new Class();
		class1.setName("班级一");
		entityManager.persist(class1);
		stu.setClass1(class1);
		
		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(stu);
	}
	
	public static void addClass(EntityTransaction transaction, EntityManager entityManager) {
		transaction.begin();//开启事务
		List<Stu> stuList = entityManager.createQuery("SELECT a FROM Stu a").getResultList();//查找符合条件的课程信息，where后面设置所查询的条件
		Class class1 = new Class();
		class1.setName("班级一");
		class1.setArticleList(stuList);
	}
	
	public static void addCourse(EntityTransaction transaction, EntityManager entityManager) {  //像课程表添加课程
		transaction.begin();
		Course course = new Course();
		course.setName("C");
		Course course1 = new Course();
		course1.setName("JAVA");
		Course course2 = new Course();
		course2.setName("C#");
		Course course3 = new Course();
		course3.setName("C++");
		Course course4 = new Course();
		course4.setName("C--");
		entityManager.persist(course);
		entityManager.persist(course1);
		entityManager.persist(course2);
		entityManager.persist(course3);
		entityManager.persist(course4);
	}
	public static void updateUser(EntityTransaction transaction, EntityManager entityManager) {
		transaction.begin();
		// 修改操作
		 Query query = entityManager.createQuery("update Stu s set s.stuname=:name where s.id=:id"); //修改指定id的姓名
		 query.setParameter("name", "xiaobai");
		 query.setParameter("id", 2);
		 query.executeUpdate();
	}
	public static void delUser(EntityTransaction transaction, EntityManager entityManager) {
		transaction.begin();
		entityManager.remove(entityManager.find(Stu.class, 3));
	}

}
