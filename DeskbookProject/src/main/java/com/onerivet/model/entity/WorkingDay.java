package com.onerivet.model.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "[WorkingDay]", schema = "[Ref]")
public class WorkingDay {
	@Id
	@Column(name = "WorkingDayId")
	private int workingDayId;

	@Column(name = "Day")
	private String workingDayName;
	
//	@OneToMany(mappedBy = "day")
//	private Set<EmployeeWorkingDays> days;

	public WorkingDay(String workingDayName) {
		super();
		this.workingDayName = workingDayName;
	}
	
	

}
