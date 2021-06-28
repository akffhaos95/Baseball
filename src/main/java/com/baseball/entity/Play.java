package com.baseball;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="Play")
public class Play {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="play_id") //플레이 고유번호 
	private Integer id;
	
	@Column(name="inning") //이닝
	private Integer inning;
	@Column(name="team") //공격팀
	private Integer team;
	
	//공격 포지션
	@Column(name="bat_num") //타자 타순 
	private Integer bat_num;
	@Column(name="bat_pos") //타자 포지션 
	private Integer bat_pos;
	@Column(name="bat_id") //타자 ID
	private Integer bat_id;
	
	//결과
	@Column(name="res") //타격 결과 
	private String res;
	@Column(name="res_detail") //타격 상세 
	private String res_detail;
	@Column(name="runner") //주자 
	private Integer runner;
	@Column(name="run") //득점 
	private Integer run;
	@Column(name="er") //자책점 
	private Integer er;
	@Column(name="pitch") //투구수 
	private Integer pitch;
	
	//수비 포지션
	@Column(name="p") //투수 
	private Integer p;
	@Column(name="c") //포수 
	private Integer c;
	@Column(name="b1") //1루수 
	private Integer b1;
	@Column(name="b2") //2루수 
	private Integer b2;
	@Column(name="b3") //3루수
	private Integer b3;
	@Column(name="ss") //유격수
	private Integer ss;
	@Column(name="lf") //좌익수 
	private Integer lf;
	@Column(name="cf") //중견수 
	private Integer cf;
	@Column(name="rf") //우익수 
	private Integer rf;
}