package com.baseball;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Game")
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "game_id")
	private Integer id;
	
	@Column(name="location")
	private String location;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="homeTeam")
	private String homeTeam;
	
	@Column(name="awayTeam")
	private String awayTeam;
	
	//경기 상태 확인 0=경기전, 1=경기중, 2=경기끝(수정만 가능)
	@Column(name="status", nullable=false)
	@ColumnDefault("0")
	private Integer status;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Play> plays = new ArrayList<Play>();
}
