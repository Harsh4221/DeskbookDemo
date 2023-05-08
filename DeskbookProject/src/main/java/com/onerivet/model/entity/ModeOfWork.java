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
@Table(name = "[ModeOfWork]", schema = "[Ref]")
public class ModeOfWork {
	@Id
	@Column(name = "ModeOfWorkId")
	private int modeOfWorkId;
	
	@Column(name = "ModeOfWorkName")
	private String modeOfWorkName;
}
