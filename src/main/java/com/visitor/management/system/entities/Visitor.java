package com.visitor.management.system.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "visitors")
@NoArgsConstructor
@Getter
@Setter
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name ="full_name")
	private String fullName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name ="address")
	private String address;
	
	@Column(name = "mobile_Number")
	private String mobileNo;
	
	@Column(name ="email_id")
	private String emailId;
	
	@Column(name ="purpose")
	private String purposeToVisit;
	
	@Column(name ="visiting_date")
	private String visitingDate;
	
	@Column(name="image_name")
	private String imageName;

}
