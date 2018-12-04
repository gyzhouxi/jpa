package cn.com.zgy.jpawork;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id; //课程id
@Column(nullable = false)
private String name; //课程名
@ManyToMany(mappedBy="courseList")
private List<Stu> stuList;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Stu> getStuList() {
	return stuList;
}
public void setStuList(List<Stu> stuList) {
	this.stuList = stuList;
}


}
