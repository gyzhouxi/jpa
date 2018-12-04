package cn.com.zgy.jpawork;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Class {
		@Id // 主键
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	    private Integer id; //id
		 @Column(nullable = false, length = 20)
		    private String name;//姓名
		    @OneToMany(mappedBy = "class1",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
		    private List<Stu> stuList;//学生列表
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
			public List<Stu> getArticleList() {
				return stuList;
			}
			public void setArticleList(List<Stu> stuList) {
				this.stuList = stuList;
			}
		    
}
