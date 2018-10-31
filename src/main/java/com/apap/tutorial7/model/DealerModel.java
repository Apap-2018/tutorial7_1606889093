package com.apap.tutorial7.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;	

@Entity
@Table(name = "dealer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DealerModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "alamat", nullable = false)
	private String alamat;
	
	@NotNull
	@Size(max = 13)
	@Column(name = "no_telp", nullable = false)
	private String noTelp;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CarModel> listCar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}

	public List<CarModel> getListCar() {
		return listCar;
	}

	public void setListCar(List<CarModel> listCar) {
		this.listCar = listCar;
	}
	
	
}
