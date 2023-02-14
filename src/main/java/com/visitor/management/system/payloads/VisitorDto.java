package com.visitor.management.system.payloads;





import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VisitorDto {
	
	private Long id;
	
	@NotEmpty
	@Size(min=2,message = "Name should be atleast 2 character")
	private String fullName;
	
	@NotEmpty
	@Size(min = 4,max = 6,message = "Atleast minimum 4 character ")
	private String gender;

	@NotEmpty
	@Size(min = 5,message = "At least 5 character address")
	private String address;
     
	@NotEmpty
	private String mobileNo;
    @NotEmpty
	@Email(message = "your email id hai not valid formation")
	private String emailId;

	@NotNull
	private String purposeToVisit;
	
	@NotNull
	private String visitingDate;
	
	private String ImageName;

}
