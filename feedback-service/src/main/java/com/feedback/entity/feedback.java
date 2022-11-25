package com.feedback.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class feedback {
	
	@Id
	private int commentId;
	private String comment;
	private String rating;
	private String clinicId;
	private String clinicName;
	private String doctorId;
	private String doctorName;
	private String userEmail;

}

