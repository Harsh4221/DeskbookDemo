package com.onerivet.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[Designation]", schema = "[Ref]")
public class Designation {
	@Id
	@Column(name = "DesignationId")
	private int designationId;

	@Column(name = "DesignationName")
	private String designationName;
}
