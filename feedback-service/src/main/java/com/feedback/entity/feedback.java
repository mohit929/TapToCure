package com.feedback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "feedbacks")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class feedback {
	
	@Id
	private int commentId;
	public feedback(int commentId, String comment, String rating, String clinicId, String clinicName, String doctorId,
			String doctorName, String userEmail) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.rating = rating;
		this.clinicId = clinicId;
		this.clinicName = clinicName;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.userEmail = userEmail;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getClinicId() {
		return clinicId;
	}
	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	private String comment;
    private String rating;
	private String clinicId;
	private String clinicName;
	private String doctorId;
	private String doctorName;
	private String userEmail;
	public feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [commentId=" + commentId + ", comment=" + comment + ", rating=" + rating + ", clinicId=" + clinicId
				+ ", clinicName=" + clinicName + ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", userEmail=" + userEmail + "]";
	}
}

