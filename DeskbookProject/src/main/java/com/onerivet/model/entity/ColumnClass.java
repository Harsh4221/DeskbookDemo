package com.onerivet.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[Column]", schema = "[Ref]")
public class ColumnClass {
	@Id
	@Column(name = "ColumnId")
	private int columnId;

	@Column(name = "ColumnName")
	private String columnName;
	
	@OneToOne
	@JoinColumn(name = "FloorId")
	private Floor floor;
}
